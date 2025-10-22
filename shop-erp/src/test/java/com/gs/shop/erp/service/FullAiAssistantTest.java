package com.gs.shop.erp.service;

public class FullAiAssistantTest {
    public static void main(String[] args) {
        // 模拟完整的AI助手服务处理流程
        QueryConversionService queryConversionService = new QueryConversionService();
        
        // 测试"入库记录有多少条"查询
        String userQuery = "入库记录有多少条";
        System.out.println("=== AI助手处理流程测试 ===");
        System.out.println("1. 用户查询: " + userQuery);
        
        // 2. 意图识别和SQL转换
        System.out.println("2. 开始SQL转换...");
        String sqlQuery = queryConversionService.convertToSQL(userQuery);
        System.out.println("   生成的SQL: " + sqlQuery);
        
        if (sqlQuery != null && !sqlQuery.isEmpty()) {
            // 3. 执行查询
            System.out.println("3. 执行数据库查询...");
            // 模拟查询结果
            String mockQueryResult = "| 入库记录总数 |\n| --- |\n| 3 |\n";
            System.out.println("   查询结果:\n" + mockQueryResult);
            
            // 4. 构造AI提示
            System.out.println("4. 构造AI提示...");
            String prompt = String.format(
                "请根据以下数据库查询结果，用简洁明了的语言回答用户的问题：%s\n\n查询结果：%s", 
                userQuery, 
                mockQueryResult
            );
            System.out.println("   AI提示: " + prompt);
            
            // 5. AI生成回答
            System.out.println("5. AI生成回答...");
            String aiResponse = "根据数据库查询结果，系统中共有3条入库记录。";
            System.out.println("   AI助手回答: " + aiResponse);
        } else {
            System.out.println("无法识别查询意图，直接由AI回答...");
        }
        
        System.out.println("\n=== 测试完成 ===");
    }
}