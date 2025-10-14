package com.gs.shop.erp;

import com.gs.shop.erp.service.QueryConversionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RepositoryCountQueryTest {

    @Autowired
    private QueryConversionService queryConversionService;

    @Test
    public void testRepositoryCountQuery() {
        // 测试仓库数量查询
        String sql = queryConversionService.convertToSQL("查询仓库数量");
        System.out.println("仓库数量查询SQL: " + sql);
        
        sql = queryConversionService.convertToSQL("仓库总数是多少？");
        System.out.println("仓库总数查询SQL: " + sql);
    }
}