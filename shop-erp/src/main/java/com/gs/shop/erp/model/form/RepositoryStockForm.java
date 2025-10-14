package com.gs.shop.erp.model.form;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 仓库商品库存数据传输对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "RepositoryStockForm对象", description = "仓库商品库存数据传输对象")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryStockForm {

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
}
