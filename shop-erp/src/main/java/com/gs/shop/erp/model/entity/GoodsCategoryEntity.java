package com.gs.shop.erp.model.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import com.gs.shop.erp.infrustructure.model.BaseEntity;


/**
 * 商品类别实体
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@TableName(value = "goods_category", autoResultMap = true)
@ApiModel(value="GoodsCategoryEntity对象",description="商品类别实体")
public class GoodsCategoryEntity extends BaseEntity {

	/**
	 * 商品类别ID
	 */
	@TableId(type = IdType.AUTO)
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
