package com.gs.shop.erp.model.form;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 供货商数据传输对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value="SupplierForm对象",description="供货商数据传输对象")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SupplierForm {

	/**
	 * 供货商ID
	 */
	@ApiModelProperty(value="供货商ID", notes="字符长度为：10")
	private Integer id;
	/**
	 * 供货商名称
	 */
	@Size(max = 50, message = "供货商名称长度超出限制")
	@ApiModelProperty(value="供货商名称", notes="字符长度为：50")
	private String name;
	/**
	 * 联系人
	 */
	@Size(max = 30, message = "联系人长度超出限制")
	@ApiModelProperty(value="联系人", notes="字符长度为：30")
	private String contact;
	/**
	 * 联系电话
	 */
	@Pattern(regexp = "((?:0|86|\\+86)?1[3-9]\\d{9})|(^\\d{3,4}-\\d{7,8}$)|(^\\d{6,12}$)", message = "电话号格式不正确")
	@Size(max = 15, message = "联系电话长度超出限制")
	@ApiModelProperty(value="联系电话", notes="字符长度为：15")
	private String tel;
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
