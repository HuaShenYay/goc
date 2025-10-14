package com.gs.shop.erp.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import com.gs.shop.erp.infrustructure.model.BaseEntity;


/**
 * 进出库订单实体
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@TableName(value = "purchase_sale_order", autoResultMap = true)
@ApiModel(value = "PurchaseSaleOrderEntity对象", description = "进出库订单实体")
public class PurchaseSaleOrderEntity extends BaseEntity {

    /**
     * 订单ID
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "订单ID", notes = "字符长度为：10")
    private Integer id;
    /**
     * 1进/2出
     */
    @TableField(condition = SqlCondition.EQUAL)
    @ApiModelProperty(value = "1进/2出", notes = "字符长度为：10")
    private Integer type;
    /**
     * 客户ID
     */
    @TableField(condition = SqlCondition.EQUAL)
    @ApiModelProperty(value = "客户ID", notes = "字符长度为：10")
    private Integer customerId;
    /**
     * 供货商ID
     */
    @TableField(condition = SqlCondition.EQUAL)
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
}
