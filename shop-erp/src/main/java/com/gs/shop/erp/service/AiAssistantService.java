package com.gs.shop.erp.service;

import com.gs.shop.erp.service.AiHealthCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AiAssistantService {
    
    private final GeminiService geminiService;
    private final QueryConversionService queryConversionService;
    private final QueryExecutionService queryExecutionService;
    private final AiHealthCheckService aiHealthCheckService;
    
    /**
     * 测试AI服务连接
     * @return 连接是否成功
     */
    public boolean testConnection() {
        try {
            // 使用详细的健康检查
            AiHealthCheckService.HealthCheckResult healthResult = aiHealthCheckService.detailedHealthCheck();
            if (!healthResult.isSuccess()) {
                log.warn("AI服务连接测试失败: {}", healthResult.getMessage());
            }
            return healthResult.isSuccess();
        } catch (Exception e) {
            log.error("AI服务连接测试失败", e);
            return false;
        }
    }
    
    /**
     * 处理用户的自然语言查询
     * @param query 用户的自然语言查询
     * @return AI助手的回答
     */
    public String processQuery(String query) {
        try {
            log.info("开始处理用户查询: {}", query);
            
            // 首先尝试将自然语言转换为SQL查询
            String sqlQuery = queryConversionService.convertToSQL(query);
            log.info("SQL转换结果: {}", sqlQuery);
            
            // 如果成功生成SQL，则执行查询并返回结果
            if (sqlQuery != null && !sqlQuery.isEmpty()) {
                log.info("执行数据库查询: {}", sqlQuery);
                String queryResult = queryExecutionService.executeQuery(sqlQuery);
                log.info("数据库查询结果: {}", queryResult);
                
                String prompt = String.format(
                    "请根据以下数据库查询结果，用简洁明了的语言回答用户的问题：%s\n\n查询结果：%s", 
                    query, 
                    queryResult
                );
                log.info("发送给AI模型的提示: {}", prompt);
                String aiResponse = geminiService.chat(prompt);
                log.info("AI模型响应: {}", aiResponse);
                return aiResponse;
            }
            
            // 如果无法生成SQL查询，则直接让AI回答
            log.info("无法生成SQL查询，直接由AI回答");
            String prompt = String.format(
                "你是一个进销存管理系统的AI助手，请根据用户的问题提供帮助。问题是：%s\n\n" +
                "以下是系统的数据结构信息：\n" +
                "1. 客户表(customer)：包含客户ID、名称、性别、联系电话、生日、省份、城市、地址等字段\n" +
                "2. 商品表(goods)：包含商品ID、名称、分类ID、售价、规格、描述、库存等字段\n" +
                "3. 商品类别表(goods_category)：包含类别ID、名称、描述等字段\n" +
                "4. 操作日志表(operate_log)：包含日志ID、来源地址、客户端地址、请求方式、接口地址、操作名称、请求参数、用户代理、用户ID、手机号、用户名、操作时间等字段\n" +
                "5. 入库/出库记录表(purchase_sale_history)：包含记录ID、订单ID、类型(1入库/2出库)、仓库ID、商品ID、进价、售价、数量等字段\n" +
                "6. 进出库订单表(purchase_sale_order)：包含订单ID、类型(1进货/2销售)、客户ID、供应商ID、总数量、总金额等字段\n" +
                "7. 仓库表(repository)：包含仓库ID、名称、省份、城市、地址等字段\n" +
                "8. 仓库商品库存表(repository_stock)：包含库存ID、仓库ID、商品ID、进价、库存等字段\n" +
                "9. 供应商表(supplier)：包含供应商ID、名称、联系人、联系电话、省份、城市、地址等字段\n" +
                "10. 系统用户表(sys_user)：包含用户ID、姓名、性别、出生日期、手机号、密码、是否系统用户等字段\n" +
                "11. 转仓记录表(transfer_history)：包含转仓ID、源仓库ID、目标仓库ID、商品ID、价格、数量等字段\n\n" +
                "请根据这些信息回答用户的问题，回答要简洁明了。",
                query
            );
            
            String aiResponse = geminiService.chat(prompt);
            log.info("AI模型响应: {}", aiResponse);
            return aiResponse;
        } catch (Exception e) {
            log.error("处理用户查询时发生错误", e);
            return "抱歉，AI助手暂时无法回答您的问题。";
        }
    }
}