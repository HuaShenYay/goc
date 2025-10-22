package com.gs.shop.erp.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public class FullRepositoryDistributionTest {
    
    public static void main(String[] args) {
        // 测试QueryConversionService
        QueryConversionService conversionService = new QueryConversionService();
        
        // 测试仓库分布地区查询
        System.out.println("=== 测试仓库分布地区查询 ===");
        String query = "仓库分布地区";
        String sql = conversionService.convertToSQL(query);
        System.out.println("自然语言查询: " + query);
        System.out.println("生成的SQL: " + sql);
        System.out.println();
        
        // 模拟数据库查询结果
        System.out.println("=== 模拟数据库查询结果 ===");
        System.out.println("基于shop_erp.sql中的数据，预期查询结果应该是:");
        System.out.println("| 省份 | 城市 |");
        System.out.println("| --- | --- |");
        System.out.println("| 420000 | 420100 |");
        System.out.println("| 420000 | 420600 |");
        System.out.println();
        
        // 格式化结果示例
        System.out.println("=== AI助手格式化结果示例 ===");
        String result = "| 省份 | 城市 |\n" +
                       "| --- | --- |\n" +
                       "| 420000 | 420100 |\n" +
                       "| 420000 | 420600 |\n";
        
        System.out.println("查询结果:");
        System.out.println(result);
        
        System.out.println("AI助手回答示例:");
        System.out.println("根据数据库查询结果，仓库分布在以下地区:");
        System.out.println("- 湖北省武汉市(420100)");
        System.out.println("- 湖北省襄阳市(420600)");
        System.out.println("共有2个仓库分布地区。");
    }
}