package com.gs.shop.erp.util;

import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;

/**
 * 库存工具
 *
 * @author Pursuer
 * @version 1.0
 * @date 2023/12/10
 */
public class StockUtil {

    /**
     * 拼接库存Key，仓库ID + 商品ID + 价格 = 唯一
     *
     * @param repId   仓库ID
     * @param goodsId 商品ID
     * @param price   价格
     * @return java.lang.String
     * @author Pursuer
     * @date 2023/12/10
     * @since 1.0
     **/
    public static String getStockKey(Integer repId, Integer goodsId, BigDecimal price) {
        return repId + StrUtil.DASHED + goodsId + StrUtil.DASHED + price;
    }
}