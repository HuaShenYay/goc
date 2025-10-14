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
 * 仓库数据传输对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value="RepositoryForm对象",description="仓库数据传输对象")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryForm {

	/**
	 * 仓库ID
	 */
	@ApiModelProperty(value="仓库ID", notes="字符长度为：10")
	private Integer id;
	/**
	 * 仓库名
	 */
	@Size(max = 50, message = "仓库名长度超出限制")
	@ApiModelProperty(value="仓库名", notes="字符长度为：50")
	private String name;
	/**
	 * 省份
	 */
	@Size(max = 10, message = "省份长度超出限制")
	@ApiModelProperty(value="省份", notes="字符长度为：10")
	private String province;
	/**
	 * 城市
	 */
	@Size(max = 10, message = "城市长度超出限制")
	@ApiModelProperty(value="城市", notes="字符长度为：10")
	private String city;
	/**
	 * 详细地址
	 */
	@Size(max = 100, message = "详细地址长度超出限制")
	@ApiModelProperty(value="详细地址", notes="字符长度为：100")
	private String address;
}
