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
 * 商品数据传输对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value="GoodsForm对象",description="商品数据传输对象")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoodsForm {

	/**
	 * 商品ID
	 */
	@ApiModelProperty(value="商品ID", notes="字符长度为：10")
	private Integer id;
	/**
	 * 商品名称
	 */
	@Size(max = 50, message = "商品名称长度超出限制")
	@ApiModelProperty(value="商品名称", notes="字符长度为：50")
	private String name;
	/**
	 * 商品分类
	 */
	@ApiModelProperty(value="商品分类", notes="字符长度为：10")
	private Integer categoryId;
	/**
	 * 售价
	 */
	@ApiModelProperty(value="售价", notes="字符长度为：10")
	private BigDecimal outPrice;
	/**
	 * 商品规格
	 */
	@ApiModelProperty(value="商品规格", notes="字符长度为：10")
	private Integer standard;
	/**
	 * 商品描述
	 */
	@Size(max = 255, message = "商品描述长度超出限制")
	@ApiModelProperty(value="商品描述", notes="字符长度为：255")
	private String remark;
	/**
	 * 库存
	 */
	@ApiModelProperty(value="库存", notes="字符长度为：10")
	private Integer stock;
}
