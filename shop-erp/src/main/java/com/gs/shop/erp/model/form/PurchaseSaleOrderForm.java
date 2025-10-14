package com.gs.shop.erp.model.form;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 进出库订单数据传输对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PurchaseSaleOrderForm对象", description = "进出库订单数据传输对象")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseSaleOrderForm {

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

    /**
     * 详情
     */
    @NotEmpty(message = "明细不能为空")
    @ApiModelProperty(value = "详情", notes = "字符长度为：10")
    private List<PurchaseSaleHistoryForm> items;
}
