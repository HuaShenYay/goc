package com.gs.shop.erp.model.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import com.gs.shop.erp.infrustructure.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 客户视图对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value="CustomerVo对象",description="客户视图对象")
public class CustomerVo extends BaseEntity{

	/**
	 * 客户ID
	 */
	@ApiModelProperty(value="客户ID", notes="字符长度为：10")
	private Integer id;
	/**
	 * 客户名称
	 */
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
	@ApiModelProperty(value="客户省份", notes="字符长度为：10")
	private String province;
	/**
	 * 客户城市
	 */
	@ApiModelProperty(value="客户城市", notes="字符长度为：10")
	private String city;
	/**
	 * 详细地址
	 */
	@ApiModelProperty(value="详细地址", notes="字符长度为：100")
	private String address;
}
