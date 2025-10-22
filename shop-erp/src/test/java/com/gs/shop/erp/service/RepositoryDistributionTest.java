package com.gs.shop.erp.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RepositoryDistributionTest {
    
    public static void main(String[] args) {
        // 测试QueryConversionService
        QueryConversionService conversionService = new QueryConversionService();
        
        // 测试仓库分布地区查询
        System.out.println("=== 测试仓库分布地区查询 ===");
        String[] testQueries = {
            "仓库分布地区",
            "查询仓库分布",
            "仓库地区"
        };
        
        for (String query : testQueries) {
            String sql = conversionService.convertToSQL(query);
            System.out.println("自然语言查询: " + query);
            System.out.println("生成的SQL: " + sql);
            System.out.println("---");
        }
    }
}