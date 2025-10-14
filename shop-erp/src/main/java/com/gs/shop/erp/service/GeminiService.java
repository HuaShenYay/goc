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
public class GeminiService {
    
    private final GeminiConfig geminiConfig;
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * 调用Gemini API进行聊天
     * @param prompt 用户输入的提示
     * @return AI回复的内容
     */
    public String chat(String prompt) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            
            // 设置请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            // 构建请求体
            Map<String, Object> requestBody = new HashMap<>();
            Map<String, Object> contents = new HashMap<>();
            contents.put("parts", new Object[]{new HashMap<String, String>() {{
                put("text", prompt);
            }}});
            requestBody.put("contents", new Object[]{contents});
            
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
            
            // 发送请求
            String url = geminiConfig.getApiUrl() + "?key=" + geminiConfig.getApiKey();
            ResponseEntity<String> response = restTemplate.postForEntity(
                url,
                requestEntity,
                String.class
            );
            
            // 检查响应状态
            if (response.getStatusCode() != HttpStatus.OK) {
                log.error("调用Gemini API失败，状态码: {}", response.getStatusCode());
                return "抱歉，AI助手暂时无法回答您的问题。";
            }
            
            // 记录响应内容用于调试
            log.info("API响应内容: {}", response.getBody());
            
            // 解析响应
            JsonNode jsonResponse = objectMapper.readTree(response.getBody());
            JsonNode candidatesNode = jsonResponse.path("candidates");
            if (candidatesNode.isArray() && candidatesNode.size() > 0) {
                JsonNode contentNode = candidatesNode.get(0).path("content");
                if (contentNode.has("parts")) {
                    JsonNode partsNode = contentNode.path("parts");
                    if (partsNode.isArray() && partsNode.size() > 0) {
                        return partsNode.get(0).path("text").asText();
                    }
                }
            }
            
            log.error("API响应格式不正确: {}", response.getBody());
            return "抱歉，AI助手暂时无法回答您的问题。";
        } catch (Exception e) {
            log.error("调用Gemini API失败", e);
            return "抱歉，AI助手暂时无法回答您的问题。";
        }
    }
}