package com.gs.shop.erp.model.vo;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import com.gs.shop.erp.infrustructure.model.BaseEntity;


/**
 * 进出库订单视图对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PurchaseSaleOrderVo对象", description = "进出库订单视图对象")
public class PurchaseSaleOrderVo extends BaseEntity {

    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID", notes = "字符长度为：10")
    private Integer id;
    /**
     * 1进/2出
     */
    @ApiModelProperty(value = "1进/2出", notes = "字符长度为：10")
    private Integer type;
    /**
     * 客户ID
     */
    @ApiModelProperty(value = "客户ID", notes = "字符长度为：10")
    private Integer customerId;
    /**
     * 供货商ID
     */
    @ApiModelProperty(value = "供货商ID", notes = "字符长度为：10")
    private Integer supplierId;
    /**
     * 总数量
     */
    @ApiModelProperty(value = "总数量", notes = "字符长度为：10")
    private Integer totalCount;
    /**
     * 总金额
     */
    @ApiModelProperty(value = "总金额", notes = "字符长度为：10")
    private BigDecimal totalAmount;

    //=======================拓展字段=============================
    /**
     * 客户
     */
    @ApiModelProperty(value = "客户", notes = "字符长度为：10")
    private String customerName;
    /**
     * 供应商
     */
    @ApiModelProperty(value = "供应商", notes = "字符长度为：10")
    private String supplierName;
    /**
     * 业务员
     */
    @ApiModelProperty(value = "业务员", notes = "字符长度为：10")
    private String username;

    /**
     * 详情
     */
    @ApiModelProperty(value = "详情", notes = "字符长度为：10")
    private List<PurchaseSaleHistoryVo> items;
}
