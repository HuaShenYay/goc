package com.gs.shop.erp.service;

public class QueryConversionServiceTest {
    public static void main(String[] args) {
        QueryConversionService service = new QueryConversionService();
        
        // 测试仓库分布地区查询
        String query1 = "仓库分布地区";
        String sql1 = service.convertToSQL(query1);
        System.out.println("查询: " + query1);
        System.out.println("生成的SQL: " + sql1);
        System.out.println();
        
        String query2 = "查询仓库分布";
        String sql2 = service.convertToSQL(query2);
        System.out.println("查询: " + query2);
        System.out.println("生成的SQL: " + sql2);
        System.out.println();
        
        String query3 = "仓库地区";
        String sql3 = service.convertToSQL(query3);
        System.out.println("查询: " + query3);
        System.out.println("生成的SQL: " + sql3);
    }
}