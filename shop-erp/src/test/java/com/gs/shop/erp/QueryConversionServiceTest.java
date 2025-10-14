package com.gs.shop.erp;

import com.gs.shop.erp.service.QueryConversionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QueryConversionServiceTest {

    @Autowired
    private QueryConversionService queryConversionService;

    @Test
    public void testConvertToSQL() {
        // 测试库存不足查询
        String sql = queryConversionService.convertToSQL("当前库存不足的商品有哪些？");
        System.out.println("库存不足查询SQL: " + sql);
        
        // 测试上月销售总额查询
        sql = queryConversionService.convertToSQL("上月销售总额是多少？");
        System.out.println("上月销售总额查询SQL: " + sql);
        
        // 测试最畅销商品查询
        sql = queryConversionService.convertToSQL("最畅销的商品是什么？");
        System.out.println("最畅销商品查询SQL: " + sql);
        
        // 测试商品信息查询
        sql = queryConversionService.convertToSQL("查询商品名称");
        System.out.println("商品信息查询SQL: " + sql);
        
        // 测试客户信息查询
        sql = queryConversionService.convertToSQL("客户信息查询");
        System.out.println("客户信息查询SQL: " + sql);
        
        // 测试供应商信息查询
        sql = queryConversionService.convertToSQL("供应商信息查询");
        System.out.println("供应商信息查询SQL: " + sql);
        
        // 测试仓库信息查询
        sql = queryConversionService.convertToSQL("仓库信息查询");
        System.out.println("仓库信息查询SQL: " + sql);
        
        // 测试进货订单查询
        sql = queryConversionService.convertToSQL("最近的进货订单有哪些？");
        System.out.println("进货订单查询SQL: " + sql);
        
        // 测试销售订单查询
        sql = queryConversionService.convertToSQL("最近的销售订单有哪些？");
        System.out.println("销售订单查询SQL: " + sql);
        
        // 测试转仓记录查询
        sql = queryConversionService.convertToSQL("最近的转仓记录有哪些？");
        System.out.println("转仓记录查询SQL: " + sql);
        
        // 测试无法识别的查询
        sql = queryConversionService.convertToSQL("今天天气怎么样？");
        System.out.println("无法识别查询SQL: " + sql);
    }
}