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
 * 转仓记录实体
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@TableName(value = "transfer_history", autoResultMap = true)
@ApiModel(value="TransferHistoryEntity对象",description="转仓记录实体")
public class TransferHistoryEntity extends BaseEntity {

	/**
	 * 转仓ID
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value="转仓ID", notes="字符长度为：10")
	private Integer id;
	/**
	 * 源仓库ID
	 */
	@TableField(condition = SqlCondition.EQUAL)
	@ApiModelProperty(value="源仓库ID", notes="字符长度为：10")
	private Integer source;
	/**
	 * 目标仓库ID
	 */
	@TableField(condition = SqlCondition.EQUAL)
	@ApiModelProperty(value="目标仓库ID", notes="字符长度为：10")
	private Integer target;
	/**
	 * 商品ID
	 */
	@TableField(condition = SqlCondition.EQUAL)
	@ApiModelProperty(value="商品ID", notes="字符长度为：10")
	private Integer goodsId;
	/**
	 * 价格
	 */
	@ApiModelProperty(value="价格", notes="字符长度为：10")
	private BigDecimal price;
	/**
	 * 转仓数量
	 */
	@ApiModelProperty(value="转仓数量", notes="字符长度为：10")
	private Integer count;
}
