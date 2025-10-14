package com.gs.shop.erp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.shop.erp.config.GeminiConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AiHealthCheckService {
    
    private final GeminiConfig geminiConfig;
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * 详细测试AI服务连接
     * @return 健康检查结果
     */
    public HealthCheckResult detailedHealthCheck() {
        HealthCheckResult result = new HealthCheckResult();
        
        try {
            // 检查配置
            if (geminiConfig.getApiKey() == null || geminiConfig.getApiKey().isEmpty()) {
                result.setSuccess(false);
                result.setMessage("Gemini API密钥未配置");
                return result;
            }
            
            if (geminiConfig.getApiUrl() == null || geminiConfig.getApiUrl().isEmpty()) {
                result.setSuccess(false);
                result.setMessage("Gemini API URL未配置");
                return result;
            }
            
            // 测试网络连接
            RestTemplate restTemplate = new RestTemplate();
            
            // 设置请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            // 构建简单的测试请求体
            Map<String, Object> requestBody = new HashMap<>();
            Map<String, Object> contents = new HashMap<>();
            contents.put("parts", new Object[]{new HashMap<String, String>() {{
                put("text", "你好");
            }}});
            requestBody.put("contents", new Object[]{contents});
            
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
            
            // 发送请求
            String url = geminiConfig.getApiUrl() + "?key=" + geminiConfig.getApiKey();
            log.info("健康检查 - 调用Gemini API: {}", url);
            
            ResponseEntity<String> response = restTemplate.postForEntity(
                url,
                requestEntity,
                String.class
            );
            
            // 记录响应信息
            result.setHttpStatusCode(response.getStatusCode().value());
            result.setResponseBody(response.getBody());
            
            // 检查响应状态
            if (response.getStatusCode() != HttpStatus.OK) {
                result.setSuccess(false);
                result.setMessage("Gemini API返回错误状态码: " + response.getStatusCode());
                log.error("健康检查失败，状态码: {}，响应内容: {}", response.getStatusCode(), response.getBody());
                return result;
            }
            
            // 解析响应
            JsonNode jsonResponse = objectMapper.readTree(response.getBody());
            
            // 检查是否有错误信息
            if (jsonResponse.has("error")) {
                JsonNode errorNode = jsonResponse.path("error");
                String errorMessage = errorNode.path("message").asText("未知错误");
                result.setSuccess(false);
                result.setMessage("Gemini API返回错误: " + errorMessage);
                log.error("健康检查失败，API返回错误: {}", errorMessage);
                return result;
            }
            
            // 检查响应格式
            JsonNode candidatesNode = jsonResponse.path("candidates");
            if (!candidatesNode.isArray() || candidatesNode.size() == 0) {
                result.setSuccess(false);
                result.setMessage("API响应格式不正确：缺少candidates字段");
                log.error("健康检查失败，响应格式不正确: {}", response.getBody());
                return result;
            }
            
            // 检查内容
            JsonNode contentNode = candidatesNode.get(0).path("content");
            if (!contentNode.has("parts")) {
                result.setSuccess(false);
                result.setMessage("API响应格式不正确：缺少parts字段");
                log.error("健康检查失败，响应格式不正确: {}", response.getBody());
                return result;
            }
            
            result.setSuccess(true);
            result.setMessage("AI助手服务运行正常");
            log.info("健康检查成功");
            return result;
            
        } catch (Exception e) {
            log.error("健康检查失败", e);
            result.setSuccess(false);
            result.setMessage("健康检查异常: " + e.getMessage());
            return result;
        }
    }
    
    /**
     * 健康检查结果类
     */
    public static class HealthCheckResult {
        private boolean success;
        private String message;
        private int httpStatusCode;
        private String responseBody;
        
        // Getters and setters
        public boolean isSuccess() {
            return success;
        }
        
        public void setSuccess(boolean success) {
            this.success = success;
        }
        
        public String getMessage() {
            return message;
        }
        
        public void setMessage(String message) {
            this.message = message;
        }
        
        public int getHttpStatusCode() {
            return httpStatusCode;
        }
        
        public void setHttpStatusCode(int httpStatusCode) {
            this.httpStatusCode = httpStatusCode;
        }
        
        public String getResponseBody() {
            return responseBody;
        }
        
        public void setResponseBody(String responseBody) {
            this.responseBody = responseBody;
        }
    }
}