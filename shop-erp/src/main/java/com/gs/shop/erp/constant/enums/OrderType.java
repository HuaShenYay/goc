package com.gs.shop.erp.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单类型
 *
 * @author Pursuer
 * @version 1.0
 * @date 2023/12/10
 */
@Getter
@AllArgsConstructor
public enum OrderType {
    /**
     * 入库
     */
    IN(1, "入库"),
    /**
     * 出库
     */
    OUT(2, "出库");

    private final Integer key;
    private final String value;
}
