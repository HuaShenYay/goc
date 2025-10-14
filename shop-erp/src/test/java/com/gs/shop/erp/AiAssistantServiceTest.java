package com.gs.shop.erp;

import com.gs.shop.erp.service.AiAssistantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AiAssistantServiceTest {

    @Autowired
    private AiAssistantService aiAssistantService;

    @Test
    public void testProcessQuery() {
        // 这只是一个示例测试，实际测试需要mock相关的服务
        try {
            String result = aiAssistantService.processQuery("你好");
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
}