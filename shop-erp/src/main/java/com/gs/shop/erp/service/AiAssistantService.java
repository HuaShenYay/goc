package com.gs.shop.erp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AiAssistantService {
    
    private final GeminiService geminiService;
    
    /**
     * 测试AI服务连接
     * @return 连接是否成功
     */
    public boolean testConnection() {
        try {
            // 发送一个简单的测试请求
            String response = geminiService.chat("你好");
            return response != null && !response.isEmpty() && !response.contains("抱歉");
        } catch (Exception e) {
            log.error("AI服务连接测试失败", e);
            return false;
        }
    }
    
    /**
     * 处理用户的自然语言查询
     * @param query 用户的自然语言查询
     * @return AI助手的回答
     */
    public String processQuery(String query) {
        try {
            // 构建提示词
            String prompt = String.format(
                "你是一个进销存管理系统的AI助手，请根据用户的问题提供帮助。问题是：%s\n\n" +
                "以下是系统的数据结构信息：\n" +
                "1. 商品表(goods)：包含商品ID、名称、分类、售价、规格、描述、库存等字段\n" +
                "2. 仓库表(repository)：包含仓库ID、名称、省份、城市、地址等字段\n" +
                "3. 仓库库存表(repository_stock)：包含仓库商品ID、仓库ID、商品ID、进价、库存等字段\n" +
                "4. 客户表(customer)：包含客户ID、姓名、联系电话、地址等字段\n" +
                "5. 供应商表(supplier)：包含供应商ID、名称、联系电话、地址等字段\n" +
                "6. 入库订单表(purchase_order)：包含订单ID、供应商ID、商品ID、数量、单价、总价、时间等字段\n" +
                "7. 出库订单表(sale_order)：包含订单ID、客户ID、商品ID、数量、单价、总价、时间等字段\n\n" +
                "请根据这些信息回答用户的问题，回答要简洁明了。",
                query
            );
            
            return geminiService.chat(prompt);
        } catch (Exception e) {
            log.error("处理用户查询时发生错误", e);
            return "抱歉，AI助手暂时无法回答您的问题。";
        }
    }
}