package com.gs.shop.erp.model.form;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 商品类别数据传输对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value="GoodsCategoryForm对象",description="商品类别数据传输对象")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoodsCategoryForm {

	/**
	 * 商品类别ID
	 */
	@ApiModelProperty(value="商品类别ID", notes="字符长度为：10")
	private Integer id;
	/**
	 * 商品类别名称
	 */
	@Size(max = 50, message = "商品类别名称长度超出限制")
	@ApiModelProperty(value="商品类别名称", notes="字符长度为：50")
	private String name;
	/**
	 * 分类描述
	 */
	@Size(max = 255, message = "分类描述长度超出限制")
	@ApiModelProperty(value="分类描述", notes="字符长度为：255")
	private String remark;
}
