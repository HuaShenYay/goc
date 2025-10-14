package com.gs.shop.erp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author guosh
 */
@SpringBootApplication
@MapperScan("com.gs.shop.erp.mapper")
public class ShopErpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopErpApplication.class, args);
    }

}