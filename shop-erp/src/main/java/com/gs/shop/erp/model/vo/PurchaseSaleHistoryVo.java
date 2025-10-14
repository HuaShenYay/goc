package com.gs.shop.erp.model.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import com.gs.shop.erp.infrustructure.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 入库/出库记录视图对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PurchaseSaleHistoryVo对象", description = "入库/出库记录视图对象")
public class PurchaseSaleHistoryVo extends BaseEntity {

    /**
     * 入库/出库ID
     */
    @ApiModelProperty(value = "入库/出库ID", notes = "字符长度为：10")
    private Integer id;
    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID", notes = "字符长度为：10")
    private Integer orderId;
    /**
     * 1进/2出
     */
    @ApiModelProperty(value = "1进/2出", notes = "字符长度为：10")
    private Integer type;
    /**
     * 仓库ID
     */
    @ApiModelProperty(value = "仓库ID", notes = "字符长度为：10")
    private Integer repositoryId;
    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID", notes = "字符长度为：10")
    private Integer goodsId;
    /**
     * 进价
     */
    @ApiModelProperty(value = "进价", notes = "字符长度为：10")
    private BigDecimal inPrice;
    /**
     * 售价
     */
    @ApiModelProperty(value = "售价", notes = "字符长度为：10")
    private BigDecimal outPrice;
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量", notes = "字符长度为：10")
    private Integer count;
}
