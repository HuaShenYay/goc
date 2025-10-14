package com.gs.shop.erp.model.form;

import java.time.LocalDate;
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
 * 客户数据传输对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value="CustomerForm对象",description="客户数据传输对象")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerForm {

	/**
	 * 客户ID
	 */
	@ApiModelProperty(value="客户ID", notes="字符长度为：10")
	private Integer id;
	/**
	 * 客户名称
	 */
	@Size(max = 30, message = "客户名称长度超出限制")
	@ApiModelProperty(value="客户名称", notes="字符长度为：30")
	private String name;
	/**
	 * 客户性别
	 */
	@ApiModelProperty(value="客户性别", notes="字符长度为：30")
	private Integer sex;
	/**
	 * 客户联系电话
	 */
	@Size(max = 15, message = "客户联系电话长度超出限制")
	@Pattern(regexp = "((?:0|86|\\+86)?1[3-9]\\d{9})|(^\\d{3,4}-\\d{7,8}$)|(^\\d{6,12}$)", message = "电话号格式不正确")
	@ApiModelProperty(value="客户联系电话", notes="字符长度为：15")
	private String tel;
	/**
	 * 客户生日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@ApiModelProperty(value="客户生日", notes="字符长度为：10")
	private LocalDate birthday;
	/**
	 * 客户省份
	 */
	@Size(max = 10, message = "客户省份长度超出限制")
	@ApiModelProperty(value="客户省份", notes="字符长度为：10")
	private String province;
	/**
	 * 客户城市
	 */
	@Size(max = 10, message = "客户城市长度超出限制")
	@ApiModelProperty(value="客户城市", notes="字符长度为：10")
	private String city;
	/**
	 * 详细地址
	 */
	@Size(max = 100, message = "详细地址长度超出限制")
	@ApiModelProperty(value="详细地址", notes="字符长度为：100")
	private String address;
}
