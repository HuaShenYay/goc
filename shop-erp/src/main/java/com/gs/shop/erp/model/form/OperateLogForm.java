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
 * 操作日志数据传输对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value="OperateLogForm对象",description="操作日志数据传输对象")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperateLogForm {

	/**
	 * ID主键
	 */
	@ApiModelProperty(value="ID主键", notes="字符长度为：19")
	private Long id;
	/**
	 * referrer地址
	 */
	@Size(max = 255, message = "referrer地址长度超出限制")
	@ApiModelProperty(value="referrer地址", notes="字符长度为：255")
	private String referrer;
	/**
	 * 客户端地址
	 */
	@Size(max = 255, message = "客户端地址长度超出限制")
	@ApiModelProperty(value="客户端地址", notes="字符长度为：255")
	private String remoteAddress;
	/**
	 * 
	 */
	@Size(max = 10, message = "长度超出限制")
	@ApiModelProperty(value="", notes="字符长度为：10")
	private String method;
	/**
	 * 接口地址
	 */
	@Size(max = 500, message = "接口地址长度超出限制")
	@ApiModelProperty(value="接口地址", notes="字符长度为：500")
	private String requestUrl;
	/**
	 * 操作名称
	 */
	@Size(max = 50, message = "操作名称长度超出限制")
	@ApiModelProperty(value="操作名称", notes="字符长度为：50")
	private String actionName;
	/**
	 * 请求参数
	 */
	@Size(max = 1073741824, message = "请求参数长度超出限制")
	@ApiModelProperty(value="请求参数", notes="字符长度为：1,073,741,824")
	private String requestParam;
	/**
	 * 客户agent
	 */
	@Size(max = 500, message = "客户agent长度超出限制")
	@ApiModelProperty(value="客户agent", notes="字符长度为：500")
	private String userAgent;
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value="用户编号", notes="字符长度为：10")
	private Integer userId;
	/**
	 * 用户手机号
	 */
	@Size(max = 30, message = "用户手机号长度超出限制")
	@ApiModelProperty(value="用户手机号", notes="字符长度为：30")
	private String phone;
	/**
	 * 用户姓名
	 */
	@Size(max = 30, message = "用户姓名长度超出限制")
	@ApiModelProperty(value="用户姓名", notes="字符长度为：30")
	private String username;
	/**
	 * 操作时间
	 */
	@ApiModelProperty(value="操作时间", notes="字符长度为：19")
	private LocalDateTime insertDate;
}
