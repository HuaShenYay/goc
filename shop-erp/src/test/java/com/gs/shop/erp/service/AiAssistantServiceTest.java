package com.gs.shop.erp.service;

public class AiAssistantServiceTest {
    public static void main(String[] args) {
        // 模拟AI助手服务的行为
        QueryConversionService queryConversionService = new QueryConversionService();
        
        // 测试"入库记录有多少条"查询
        String userQuery = "入库记录有多少条";
        System.out.println("用户查询: " + userQuery);
        
        // 1. 将自然语言转换为SQL查询
        String sqlQuery = queryConversionService.convertToSQL(userQuery);
        System.out.println("生成的SQL: " + sqlQuery);
        
        if (sqlQuery != null && !sqlQuery.isEmpty()) {
            // 2. 执行查询（在实际应用中，这里会连接数据库执行查询）
            System.out.println("执行数据库查询...");
            // 模拟查询结果
            String mockQueryResult = "| 入库记录总数 |\n| --- |\n| 3 |\n";
            System.out.println("查询结果:\n" + mockQueryResult);
            
            // 3. 基于查询结果生成回答
            System.out.println("AI助手回答: 根据数据库查询结果，系统中共有3条入库记录。");
        } else {
            System.out.println("无法识别查询意图，直接由AI回答...");
        }
    }
}