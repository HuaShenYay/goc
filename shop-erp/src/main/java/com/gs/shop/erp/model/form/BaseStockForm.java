package com.gs.shop.erp.model.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 通用库存表单
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-25
 */
@Data
public class BaseStockForm {
    /**
     * 转仓数量
     */
    @ApiModelProperty(value = "转仓数量", notes = "字符长度为：10")
    private Integer count;
    /**
     * 库存ID
     */
    @ApiModelProperty(value = "库存ID", notes = "字符长度为：10")
    private Integer repositoryStockId;
}