package com.gs.shop.erp.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 进销存统计实体
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-25
 */
@Data
@ApiModel(value = "进销存统计")
public class PurchaseSaleCountVo {
    /**
     * 商品信息
     */
    @ApiModelProperty(value = "商品信息")
    private String goods;
    /**
     * 进货数量
     */
    @ApiModelProperty(value = "进货数量")
    private Integer purchase;
    /**
     * 销售数量
     */
    @ApiModelProperty(value = "销售数量")
    private Integer sale;
}