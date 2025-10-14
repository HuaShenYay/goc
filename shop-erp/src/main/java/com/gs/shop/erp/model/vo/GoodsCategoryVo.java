package com.gs.shop.erp.model.vo;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import com.gs.shop.erp.infrustructure.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 商品类别视图对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value="GoodsCategoryVo对象",description="商品类别视图对象")
public class GoodsCategoryVo extends BaseEntity{

	/**
	 * 商品类别ID
	 */
	@ApiModelProperty(value="商品类别ID", notes="字符长度为：10")
	private Integer id;
	/**
	 * 商品类别名称
	 */
	@ApiModelProperty(value="商品类别名称", notes="字符长度为：50")
	private String name;
	/**
	 * 分类描述
	 */
	@ApiModelProperty(value="分类描述", notes="字符长度为：255")
	private String remark;
}
