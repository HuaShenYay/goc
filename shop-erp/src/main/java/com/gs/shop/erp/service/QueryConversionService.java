package com.gs.shop.erp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueryConversionService {
    
    /**
     * 将自然语言查询转换为SQL查询语句
     * @param naturalLanguageQuery 自然语言查询
     * @return SQL查询语句
     */
    public String convertToSQL(String naturalLanguageQuery) {
        try {
            // 转换为小写进行匹配
            String lowerQuery = naturalLanguageQuery.toLowerCase();
            
            // 匹配不同的查询模式
            if (lowerQuery.contains("库存不足") || lowerQuery.contains("库存短缺") || lowerQuery.contains("缺货")) {
                return generateLowStockQuery();
            } else if (lowerQuery.contains("销售总额") && (lowerQuery.contains("上月") || lowerQuery.contains("上个月"))) {
                return generateLastMonthSalesQuery();
            } else if (lowerQuery.contains("销售总额") && (lowerQuery.contains("本月") || lowerQuery.contains("这个月"))) {
                return generateThisMonthSalesQuery();
            } else if (lowerQuery.contains("销售总额") && lowerQuery.contains("去年")) {
                return generateLastYearSalesQuery();
            } else if (lowerQuery.contains("销售总额")) {
                return generateTotalSalesQuery();
            } else if (lowerQuery.contains("采购总额") && (lowerQuery.contains("上月") || lowerQuery.contains("上个月"))) {
                return generateLastMonthPurchaseQuery();
            } else if (lowerQuery.contains("采购总额") && (lowerQuery.contains("本月") || lowerQuery.contains("这个月"))) {
                return generateThisMonthPurchaseQuery();
            } else if (lowerQuery.contains("采购总额") && lowerQuery.contains("去年")) {
                return generateLastYearPurchaseQuery();
            } else if (lowerQuery.contains("采购总额")) {
                return generateTotalPurchaseQuery();
            } else if (lowerQuery.contains("最畅销") || lowerQuery.contains("销量最好")) {
                return generateBestSellingProductsQuery();
            } else if (lowerQuery.contains("滞销") || lowerQuery.contains("销量最差")) {
                return generateWorstSellingProductsQuery();
            } else if (lowerQuery.contains("商品信息") || lowerQuery.contains("商品详情")) {
                return generateProductInfoQuery(naturalLanguageQuery);
            } else if (lowerQuery.contains("仓库库存") || lowerQuery.contains("仓库商品")) {
                return generateRepositoryStockQuery();
            } else if (lowerQuery.contains("客户信息") || lowerQuery.contains("客户详情")) {
                return generateCustomerInfoQuery(naturalLanguageQuery);
            } else if (lowerQuery.contains("供应商信息") || lowerQuery.contains("供货商信息")) {
                return generateSupplierInfoQuery(naturalLanguageQuery);
            } else if (lowerQuery.contains("仓库信息") || lowerQuery.contains("仓库详情")) {
                return generateRepositoryInfoQuery(naturalLanguageQuery);
            } else if (lowerQuery.contains("进货订单") || lowerQuery.contains("采购订单")) {
                return generatePurchaseOrderQuery();
            } else if (lowerQuery.contains("销售订单")) {
                return generateSaleOrderQuery();
            } else if (lowerQuery.contains("转仓记录")) {
                return generateTransferHistoryQuery();
            }
            
            // 如果没有匹配的模式，返回null表示无法转换
            return null;
        } catch (Exception e) {
            log.error("转换自然语言查询到SQL时发生错误", e);
            return null;
        }
    }
    
    /**
     * 生成库存不足商品查询
     * @return SQL查询语句
     */
    private String generateLowStockQuery() {
        return "SELECT g.name AS 商品名称, g.stock AS 库存数量 " +
               "FROM goods g " +
               "WHERE g.stock < 10 " +
               "ORDER BY g.stock ASC";
    }
    
    /**
     * 生成上月销售总额查询
     * @return SQL查询语句
     */
    private String generateLastMonthSalesQuery() {
        return "SELECT SUM(psh.count * psh.out_price) AS 上月销售总额 " +
               "FROM purchase_sale_history psh " +
               "JOIN purchase_sale_order pso ON psh.order_id = pso.id " +
               "WHERE pso.type = 2 " +
               "AND DATE_FORMAT(pso.create_time, '%Y-%m') = DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH), '%Y-%m')";
    }
    
    /**
     * 生成本月销售总额查询
     * @return SQL查询语句
     */
    private String generateThisMonthSalesQuery() {
        return "SELECT SUM(psh.count * psh.out_price) AS 本月销售总额 " +
               "FROM purchase_sale_history psh " +
               "JOIN purchase_sale_order pso ON psh.order_id = pso.id " +
               "WHERE pso.type = 2 " +
               "AND DATE_FORMAT(pso.create_time, '%Y-%m') = DATE_FORMAT(CURDATE(), '%Y-%m')";
    }
    
    /**
     * 生成去年销售总额查询
     * @return SQL查询语句
     */
    private String generateLastYearSalesQuery() {
        return "SELECT SUM(psh.count * psh.out_price) AS 去年销售总额 " +
               "FROM purchase_sale_history psh " +
               "JOIN purchase_sale_order pso ON psh.order_id = pso.id " +
               "WHERE pso.type = 2 " +
               "AND YEAR(pso.create_time) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 YEAR))";
    }
    
    /**
     * 生成总销售总额查询
     * @return SQL查询语句
     */
    private String generateTotalSalesQuery() {
        return "SELECT SUM(psh.count * psh.out_price) AS 销售总额 " +
               "FROM purchase_sale_history psh " +
               "JOIN purchase_sale_order pso ON psh.order_id = pso.id " +
               "WHERE pso.type = 2";
    }
    
    /**
     * 生成上月采购总额查询
     * @return SQL查询语句
     */
    private String generateLastMonthPurchaseQuery() {
        return "SELECT SUM(psh.count * psh.in_price) AS 上月采购总额 " +
               "FROM purchase_sale_history psh " +
               "JOIN purchase_sale_order pso ON psh.order_id = pso.id " +
               "WHERE pso.type = 1 " +
               "AND DATE_FORMAT(pso.create_time, '%Y-%m') = DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH), '%Y-%m')";
    }
    
    /**
     * 生成本月采购总额查询
     * @return SQL查询语句
     */
    private String generateThisMonthPurchaseQuery() {
        return "SELECT SUM(psh.count * psh.in_price) AS 本月采购总额 " +
               "FROM purchase_sale_history psh " +
               "JOIN purchase_sale_order pso ON psh.order_id = pso.id " +
               "WHERE pso.type = 1 " +
               "AND DATE_FORMAT(pso.create_time, '%Y-%m') = DATE_FORMAT(CURDATE(), '%Y-%m')";
    }
    
    /**
     * 生成去年采购总额查询
     * @return SQL查询语句
     */
    private String generateLastYearPurchaseQuery() {
        return "SELECT SUM(psh.count * psh.in_price) AS 去年采购总额 " +
               "FROM purchase_sale_history psh " +
               "JOIN purchase_sale_order pso ON psh.order_id = pso.id " +
               "WHERE pso.type = 1 " +
               "AND YEAR(pso.create_time) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 YEAR))";
    }
    
    /**
     * 生成总采购总额查询
     * @return SQL查询语句
     */
    private String generateTotalPurchaseQuery() {
        return "SELECT SUM(psh.count * psh.in_price) AS 采购总额 " +
               "FROM purchase_sale_history psh " +
               "JOIN purchase_sale_order pso ON psh.order_id = pso.id " +
               "WHERE pso.type = 1";
    }
    
    /**
     * 生成最畅销商品查询
     * @return SQL查询语句
     */
    private String generateBestSellingProductsQuery() {
        return "SELECT g.name AS 商品名称, SUM(psh.count) AS 销量 " +
               "FROM purchase_sale_history psh " +
               "JOIN goods g ON psh.goods_id = g.id " +
               "JOIN purchase_sale_order pso ON psh.order_id = pso.id " +
               "WHERE pso.type = 2 " +
               "GROUP BY g.id, g.name " +
               "ORDER BY 销量 DESC " +
               "LIMIT 10";
    }
    
    /**
     * 生成滞销商品查询
     * @return SQL查询语句
     */
    private String generateWorstSellingProductsQuery() {
        return "SELECT g.name AS 商品名称, SUM(psh.count) AS 销量 " +
               "FROM purchase_sale_history psh " +
               "JOIN goods g ON psh.goods_id = g.id " +
               "JOIN purchase_sale_order pso ON psh.order_id = pso.id " +
               "WHERE pso.type = 2 " +
               "GROUP BY g.id, g.name " +
               "ORDER BY 销量 ASC " +
               "LIMIT 10";
    }
    
    /**
     * 生成商品信息查询
     * @param query 查询语句
     * @return SQL查询语句
     */
    private String generateProductInfoQuery(String query) {
        // 尝试提取商品名称
        String productName = extractProductName(query);
        if (productName != null && !productName.isEmpty()) {
            return "SELECT g.name AS 商品名称, g.out_price AS 售价, g.stock AS 库存, gc.name AS 分类 " +
                   "FROM goods g " +
                   "LEFT JOIN goods_category gc ON g.category_id = gc.id " +
                   "WHERE g.name LIKE '%" + productName + "%'";
        }
        
        // 默认查询所有商品信息
        return "SELECT g.name AS 商品名称, g.out_price AS 售价, g.stock AS 库存, gc.name AS 分类 " +
               "FROM goods g " +
               "LEFT JOIN goods_category gc ON g.category_id = gc.id " +
               "LIMIT 20";
    }
    
    /**
     * 生成仓库库存查询
     * @return SQL查询语句
     */
    private String generateRepositoryStockQuery() {
        return "SELECT r.name AS 仓库名称, g.name AS 商品名称, rs.stock AS 库存数量 " +
               "FROM repository_stock rs " +
               "JOIN repository r ON rs.repository_id = r.id " +
               "JOIN goods g ON rs.goods_id = g.id " +
               "ORDER BY r.name, g.name";
    }
    
    /**
     * 生成客户信息查询
     * @param query 查询语句
     * @return SQL查询语句
     */
    private String generateCustomerInfoQuery(String query) {
        // 尝试提取客户名称
        String customerName = extractCustomerName(query);
        if (customerName != null && !customerName.isEmpty()) {
            return "SELECT c.name AS 客户名称, c.tel AS 联系电话, c.address AS 地址 " +
                   "FROM customer c " +
                   "WHERE c.name LIKE '%" + customerName + "%'";
        }
        
        // 默认查询所有客户信息
        return "SELECT c.name AS 客户名称, c.tel AS 联系电话, c.address AS 地址 " +
               "FROM customer c " +
               "LIMIT 20";
    }
    
    /**
     * 生成供应商信息查询
     * @param query 查询语句
     * @return SQL查询语句
     */
    private String generateSupplierInfoQuery(String query) {
        // 尝试提取供应商名称
        String supplierName = extractSupplierName(query);
        if (supplierName != null && !supplierName.isEmpty()) {
            return "SELECT s.name AS 供应商名称, s.contact AS 联系人, s.tel AS 联系电话, s.address AS 地址 " +
                   "FROM supplier s " +
                   "WHERE s.name LIKE '%" + supplierName + "%'";
        }
        
        // 默认查询所有供应商信息
        return "SELECT s.name AS 供应商名称, s.contact AS 联系人, s.tel AS 联系电话, s.address AS 地址 " +
               "FROM supplier s " +
               "LIMIT 20";
    }
    
    /**
     * 生成仓库信息查询
     * @param query 查询语句
     * @return SQL查询语句
     */
    private String generateRepositoryInfoQuery(String query) {
        // 尝试提取仓库名称
        String repositoryName = extractRepositoryName(query);
        if (repositoryName != null && !repositoryName.isEmpty()) {
            return "SELECT r.name AS 仓库名称, r.address AS 地址 " +
                   "FROM repository r " +
                   "WHERE r.name LIKE '%" + repositoryName + "%'";
        }
        
        // 默认查询所有仓库信息
        return "SELECT r.name AS 仓库名称, r.address AS 地址 " +
               "FROM repository r " +
               "LIMIT 20";
    }
    
    /**
     * 生成进货订单查询
     * @return SQL查询语句
     */
    private String generatePurchaseOrderQuery() {
        return "SELECT pso.id AS 订单ID, s.name AS 供应商, pso.total_count AS 总数量, pso.total_amount AS 总金额, pso.create_time AS 创建时间 " +
               "FROM purchase_sale_order pso " +
               "JOIN supplier s ON pso.supplier_id = s.id " +
               "WHERE pso.type = 1 " +
               "ORDER BY pso.create_time DESC " +
               "LIMIT 20";
    }
    
    /**
     * 生成销售订单查询
     * @return SQL查询语句
     */
    private String generateSaleOrderQuery() {
        return "SELECT pso.id AS 订单ID, c.name AS 客户, pso.total_count AS 总数量, pso.total_amount AS 总金额, pso.create_time AS 创建时间 " +
               "FROM purchase_sale_order pso " +
               "JOIN customer c ON pso.customer_id = c.id " +
               "WHERE pso.type = 2 " +
               "ORDER BY pso.create_time DESC " +
               "LIMIT 20";
    }
    
    /**
     * 生成转仓记录查询
     * @return SQL查询语句
     */
    private String generateTransferHistoryQuery() {
        return "SELECT th.id AS 记录ID, r1.name AS 源仓库, r2.name AS 目标仓库, g.name AS 商品, th.count AS 数量, th.create_time AS 创建时间 " +
               "FROM transfer_history th " +
               "JOIN repository r1 ON th.source = r1.id " +
               "JOIN repository r2 ON th.target = r2.id " +
               "JOIN goods g ON th.goods_id = g.id " +
               "ORDER BY th.create_time DESC " +
               "LIMIT 20";
    }
    
    /**
     * 从查询语句中提取商品名称
     * @param query 查询语句
     * @return 商品名称
     */
    private String extractProductName(String query) {
        // 匹配"商品名称"或"商品"后面的内容
        Pattern pattern = Pattern.compile("商品[名称]?[:：]?\\s*([^\\s]+)");
        Matcher matcher = pattern.matcher(query);
        if (matcher.find()) {
            return matcher.group(1);
        }
        
        // 匹配"查询"后面的内容
        pattern = Pattern.compile("查询[:：]?\\s*([^\\s]+)");
        matcher = pattern.matcher(query);
        if (matcher.find()) {
            return matcher.group(1);
        }
        
        return null;
    }
    
    /**
     * 从查询语句中提取客户名称
     * @param query 查询语句
     * @return 客户名称
     */
    private String extractCustomerName(String query) {
        // 匹配"客户名称"或"客户"后面的内容
        Pattern pattern = Pattern.compile("客户[名称]?[:：]?\\s*([^\\s]+)");
        Matcher matcher = pattern.matcher(query);
        if (matcher.find()) {
            return matcher.group(1);
        }
        
        return null;
    }
    
    /**
     * 从查询语句中提取供应商名称
     * @param query 查询语句
     * @return 供应商名称
     */
    private String extractSupplierName(String query) {
        // 匹配"供应商名称"或"供应商"后面的内容
        Pattern pattern = Pattern.compile("(?:供应商|供货商)[名称]?[:：]?\\s*([^\\s]+)");
        Matcher matcher = pattern.matcher(query);
        if (matcher.find()) {
            return matcher.group(1);
        }
        
        return null;
    }
    
    /**
     * 从查询语句中提取仓库名称
     * @param query 查询语句
     * @return 仓库名称
     */
    private String extractRepositoryName(String query) {
        // 匹配"仓库名称"或"仓库"后面的内容
        Pattern pattern = Pattern.compile("仓库[名称]?[:：]?\\s*([^\\s]+)");
        Matcher matcher = pattern.matcher(query);
        if (matcher.find()) {
            return matcher.group(1);
        }
        
        return null;
    }
}