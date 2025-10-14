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
 * 转仓记录视图对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "TransferHistoryVo对象", description = "转仓记录视图对象")
public class TransferHistoryVo extends BaseEntity {

    /**
     * 转仓ID
     */
    @ApiModelProperty(value = "转仓ID", notes = "字符长度为：10")
    private Integer id;
    /**
     * 源仓库ID
     */
    @ApiModelProperty(value = "源仓库ID", notes = "字符长度为：10")
    private Integer source;
    /**
     * 目标仓库ID
     */
    @ApiModelProperty(value = "目标仓库ID", notes = "字符长度为：10")
    private Integer target;
    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID", notes = "字符长度为：10")
    private Integer goodsId;
    /**
     * 价格
     */
    @ApiModelProperty(value = "价格", notes = "字符长度为：10")
    private BigDecimal price;
    /**
     * 转仓数量
     */
    @ApiModelProperty(value = "转仓数量", notes = "字符长度为：10")
    private Integer count;

    //========================拓展字段=============================>
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称", notes = "字符长度为：10")
    private String goodsName;
    /**
     * 原仓库
     */
    @ApiModelProperty(value = "原仓库", notes = "字符长度为：10")
    private String sourceRepository;
    /**
     * 目标仓库
     */
    @ApiModelProperty(value = "目标仓库", notes = "字符长度为：10")
    private String targetRepository;
}
