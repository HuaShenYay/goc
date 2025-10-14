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
 * 转仓记录数据传输对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "TransferHistoryForm对象", description = "转仓记录数据传输对象")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferHistoryForm extends BaseStockForm {

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
     * 批量数据
     */
    @NotEmpty(message = "数据列表不能为空")
    @ApiModelProperty(value = "批量数据")
    private List<TransferHistoryForm> items;
}
