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
 * 仓库商品库存视图对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "RepositoryStockVo对象", description = "仓库商品库存视图对象")
public class RepositoryStockVo extends BaseEntity {

    /**
     * 仓库商品ID
     */
    @ApiModelProperty(value = "仓库商品ID", notes = "字符长度为：10")
    private Integer id;
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
     * 库存
     */
    @ApiModelProperty(value = "库存", notes = "字符长度为：10")
    private Integer stock;

    //========================拓展字段=============================>
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称", notes = "字符长度为：10")
    private String goodsName;
    /**
     * 仓库名称
     */
    @ApiModelProperty(value = "仓库名称", notes = "字符长度为：10")
    private String repositoryName;
    /**
     * 售价
     */
    @ApiModelProperty(value = "售价", notes = "字符长度为：10")
    private BigDecimal outPrice;
}
