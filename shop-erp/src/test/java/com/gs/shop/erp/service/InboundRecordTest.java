package com.gs.shop.erp.service;

public class InboundRecordTest {
    public static void main(String[] args) {
        QueryConversionService conversionService = new QueryConversionService();
        
        // 测试入库记录查询
        System.out.println("=== 测试入库记录查询 ===");
        
        // 测试"入库记录有多少条"
        String query1 = "入库记录有多少条";
        String sql1 = conversionService.convertToSQL(query1);
        System.out.println("自然语言查询: " + query1);
        System.out.println("生成的SQL: " + sql1);
        System.out.println();
        
        // 测试"入库记录总数"
        String query2 = "入库记录总数";
        String sql2 = conversionService.convertToSQL(query2);
        System.out.println("自然语言查询: " + query2);
        System.out.println("生成的SQL: " + sql2);
        System.out.println();
        
        // 测试"查询入库记录"
        String query3 = "查询入库记录";
        String sql3 = conversionService.convertToSQL(query3);
        System.out.println("自然语言查询: " + query3);
        System.out.println("生成的SQL: " + sql3);
        System.out.println();
        
        // 测试"入库记录"
        String query4 = "入库记录";
        String sql4 = conversionService.convertToSQL(query4);
        System.out.println("自然语言查询: " + query4);
        System.out.println("生成的SQL: " + sql4);
    }
}