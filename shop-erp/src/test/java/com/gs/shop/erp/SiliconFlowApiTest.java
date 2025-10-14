package com.gs.shop.erp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class SiliconFlowApiTest {
    public static void main(String[] args) {
        try {
            // 测试硅基流动API
            String apiKey = "sk-ukiotczontttczlqqdxyugbvziqwrmyqihqncxgiexjwcaiz";
            String apiUrl = "https://api.siliconflow.cn/v1";
            String model = "Qwen/Qwen3-8B";
            
            RestTemplate restTemplate = new RestTemplate();
            
            // 设置请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);
            
            // 构建请求体
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", model);
            requestBody.put("messages", new Object[]{
                new HashMap<String, String>() {{
                    put("role", "user");
                    put("content", "你好");
                }}
            });
            requestBody.put("temperature", 0.7);
            
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
            
            // 发送请求
            ResponseEntity<String> response = restTemplate.postForEntity(
                apiUrl + "/chat/completions",
                requestEntity,
                String.class
            );
            
            System.out.println("响应状态码: " + response.getStatusCode());
            System.out.println("响应内容: " + response.getBody());
            
            // 解析响应
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(response.getBody());
            String content = jsonResponse.path("choices").get(0).path("message").path("content").asText();
            System.out.println("AI回复: " + content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}