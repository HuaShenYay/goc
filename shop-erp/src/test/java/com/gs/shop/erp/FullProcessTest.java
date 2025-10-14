package com.gs.shop.erp;

import com.gs.shop.erp.service.AiAssistantService;
import com.gs.shop.erp.service.QueryConversionService;
import com.gs.shop.erp.service.QueryExecutionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FullProcessTest {

    @Autowired
    private QueryConversionService queryConversionService;
    
    @Autowired
    private QueryExecutionService queryExecutionService;
    
    @Autowired
    private AiAssistantService aiAssistantService;

    @Test
    public void testFullProcess() {
        // 测试完整流程
        String query = "查询仓库数量";
        
        // 第一步：自然语言转SQL
        String sql = queryConversionService.convertToSQL(query);
        System.out.println("第一步 - SQL查询: " + sql);
        
        // 第二步：执行SQL查询
        if (sql != null && !sql.isEmpty()) {
            String queryResult = queryExecutionService.executeQuery(sql);
            System.out.println("第二步 - 查询结果: " + queryResult);
            
            // 第三步：AI处理结果
            String prompt = String.format(
                "请根据以下数据库查询结果，用简洁明了的语言回答用户的问题：%s\n\n查询结果：%s", 
                query, 
                queryResult
            );
            System.out.println("第三步 - 发送给AI的提示: " + prompt);
        }
    }
    
    @Test
    public void testAiAssistantProcess() {
        // 测试AI助手完整处理流程
        String query = "查询仓库数量";
        System.out.println("用户查询: " + query);
        
        String response = aiAssistantService.processQuery(query);
        System.out.println("AI助手回复: " + response);
    }
}