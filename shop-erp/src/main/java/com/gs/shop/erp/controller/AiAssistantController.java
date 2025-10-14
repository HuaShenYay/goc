package com.gs.shop.erp.controller;

import com.gs.shop.erp.service.AiAssistantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiAssistantController {
    
    private final AiAssistantService aiAssistantService;
    
    /**
     * AI助手问答接口
     * @param query 用户的查询
     * @return AI助手的回答
     */
    @PostMapping("/chat")
    public Map<String, Object> chat(@RequestParam(required = false) String query, 
                                   @RequestBody(required = false) Map<String, Object> requestBody) {
        Map<String, Object> result = new HashMap<>();
        
        // 检查查询参数是否为空，优先从请求体获取，其次从查询参数获取
        String actualQuery = null;
        if (requestBody != null && requestBody.containsKey("query")) {
            actualQuery = (String) requestBody.get("query");
        } else if (query != null) {
            actualQuery = query;
        }
        
        if (actualQuery == null || actualQuery.trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "请求参数 'query' 不能为空");
            return result;
        }
        
        try {
            String response = aiAssistantService.processQuery(actualQuery);
            result.put("success", true);
            result.put("data", response);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "AI助手服务暂时不可用: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 健康检查接口
     * @return 服务状态
     */
    @GetMapping("/health")
    public Map<String, Object> healthCheck() {
        Map<String, Object> result = new HashMap<>();
        try {
            // 实际测试AI服务的连通性
            boolean isHealthy = aiAssistantService.testConnection();
            result.put("success", isHealthy);
            result.put("message", isHealthy ? "AI助手服务运行正常" : "AI助手服务连接异常");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "AI助手服务连接异常: " + e.getMessage());
        }
        return result;
    }
}