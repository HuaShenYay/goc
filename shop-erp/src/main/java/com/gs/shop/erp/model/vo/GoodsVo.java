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
 * 商品视图对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "GoodsVo对象", description = "商品视图对象")
public class GoodsVo extends BaseEntity {

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID", notes = "字符长度为：10")
    private Integer id;
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称", notes = "字符长度为：50")
    private String name;
    /**
     * 商品分类
     */
    @ApiModelProperty(value = "商品分类", notes = "字符长度为：10")
    private Integer categoryId;
    /**
     * 售价
     */
    @ApiModelProperty(value = "售价", notes = "字符长度为：10")
    private BigDecimal outPrice;
    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格", notes = "字符长度为：10")
    private Integer standard;
    /**
     * 商品描述
     */
    @ApiModelProperty(value = "商品描述", notes = "字符长度为：255")
    private String remark;
    /**
     * 库存
     */
    @ApiModelProperty(value = "库存", notes = "字符长度为：10")
    private Integer stock;

    //=======================拓展字段=============================
    /**
     * 分类名
     */
    private String categoryName;
}
