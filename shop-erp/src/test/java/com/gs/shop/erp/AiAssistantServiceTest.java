package com.gs.shop.erp;

import com.gs.shop.erp.service.AiAssistantService;
import com.gs.shop.erp.service.QueryConversionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AiAssistantServiceTest {

    @Autowired
    private AiAssistantService aiAssistantService;
    
    @Autowired
    private QueryConversionService queryConversionService;

    @Test
    public void testProcessQuery() {
        // 测试处理用户查询
        try {
            String result = aiAssistantService.processQuery("当前库存不足的商品有哪些？");
            System.out.println("AI助手回答: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testConnection() {
        try {
            boolean isHealthy = aiAssistantService.testConnection();
            System.out.println("AI助手服务状态: " + isHealthy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testQueryConversion() {
        try {
            String sql = queryConversionService.convertToSQL("当前库存不足的商品有哪些？");
            System.out.println("生成的SQL: " + sql);
            
            sql = queryConversionService.convertToSQL("上月销售总额是多少？");
            System.out.println("生成的SQL: " + sql);
            
            sql = queryConversionService.convertToSQL("最畅销的商品是什么？");
            System.out.println("生成的SQL: " + sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}