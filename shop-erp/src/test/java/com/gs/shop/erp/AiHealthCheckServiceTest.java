package com.gs.shop.erp;

import com.gs.shop.erp.service.AiHealthCheckService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AiHealthCheckServiceTest {

    @Autowired
    private AiHealthCheckService aiHealthCheckService;

    @Test
    public void testDetailedHealthCheck() {
        try {
            AiHealthCheckService.HealthCheckResult result = aiHealthCheckService.detailedHealthCheck();
            System.out.println("健康检查结果:");
            System.out.println("  成功: " + result.isSuccess());
            System.out.println("  消息: " + result.getMessage());
            System.out.println("  HTTP状态码: " + result.getHttpStatusCode());
            if (result.getResponseBody() != null && result.getResponseBody().length() > 100) {
                System.out.println("  响应体: " + result.getResponseBody().substring(0, 100) + "...");
            } else {
                System.out.println("  响应体: " + result.getResponseBody());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}