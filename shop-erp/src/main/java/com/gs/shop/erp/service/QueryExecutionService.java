package com.gs.shop.erp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueryExecutionService {
    
    private final JdbcTemplate jdbcTemplate;
    
    /**
     * 执行SQL查询并返回结果
     * @param sql SQL查询语句
     * @return 查询结果
     */
    public String executeQuery(String sql) {
        try {
            log.info("开始执行SQL查询: {}", sql);
            
            // 简单的SQL注入防护
            if (!isSafeQuery(sql)) {
                log.warn("查询语句不安全，拒绝执行: {}", sql);
                return "查询语句不安全，拒绝执行";
            }
            
            // 只允许SELECT查询
            if (!sql.trim().toLowerCase().startsWith("select")) {
                log.warn("只允许执行SELECT查询: {}", sql);
                return "只允许执行SELECT查询";
            }
            
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
            log.info("查询返回 {} 条记录", result.size());
            
            if (result.isEmpty()) {
                log.info("查询结果为空");
                return "查询结果为空";
            }
            
            // 格式化结果
            String formattedResult = formatQueryResult(result);
            log.info("格式化查询结果完成");
            return formattedResult;
        } catch (Exception e) {
            log.error("执行SQL查询失败: {}", sql, e);
            return "查询执行失败: " + e.getMessage();
        }
    }
    
    /**
     * 格式化查询结果
     * @param result 查询结果
     * @return 格式化后的结果字符串
     */
    private String formatQueryResult(List<Map<String, Object>> result) {
        try {
            StringBuilder formattedResult = new StringBuilder();
            
            // 添加表头
            Map<String, Object> firstRow = result.get(0);
            formattedResult.append("| ");
            for (String column : firstRow.keySet()) {
                formattedResult.append(column).append(" | ");
            }
            formattedResult.append("\n|");
            for (int i = 0; i < firstRow.size(); i++) {
                formattedResult.append(" --- |");
            }
            formattedResult.append("\n");
            
            // 添加数据行
            for (Map<String, Object> row : result) {
                formattedResult.append("| ");
                for (Object value : row.values()) {
                    formattedResult.append(value != null ? value.toString() : "NULL").append(" | ");
                }
                formattedResult.append("\n");
            }
            
            return formattedResult.toString();
        } catch (Exception e) {
            log.error("格式化查询结果时发生错误", e);
            return "格式化查询结果失败";
        }
    }
    
    /**
     * 简单的SQL注入防护检查
     * @param sql SQL语句
     * @return 是否安全
     */
    private boolean isSafeQuery(String sql) {
        // 转换为小写进行检查
        String lowerSql = sql.toLowerCase();
        
        // 检查是否包含危险关键字
        String[] dangerousKeywords = {"delete", "update", "insert", "drop", "create", "alter", "truncate"};
        
        for (String keyword : dangerousKeywords) {
            if (lowerSql.contains(keyword)) {
                return false;
            }
        }
        
        return true;
    }
}