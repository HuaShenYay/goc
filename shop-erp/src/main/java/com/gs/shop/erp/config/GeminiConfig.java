package com.gs.shop.erp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "gemini")
public class GeminiConfig {
    private String apiKey;
    private String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent";
}