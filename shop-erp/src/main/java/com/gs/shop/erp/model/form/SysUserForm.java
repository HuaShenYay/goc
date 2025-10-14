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
 * 系统用户/业务员数据传输对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "SysUserForm对象", description = "系统用户/业务员数据传输对象")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysUserForm {

    /**
     * 业务员ID
     */
    @ApiModelProperty(value = "业务员ID", notes = "字符长度为：10")
    private Integer id;
    /**
     * 姓名
     */
    @Size(max = 30, message = "姓名长度超出限制")
    @ApiModelProperty(value = "姓名", notes = "字符长度为：30")
    private String name;
    /**
     * 性别 1男/2女
     */
    @ApiModelProperty(value = "性别 1男/2女", notes = "字符长度为：10")
    private Integer sex;
    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "出生日期", notes = "字符长度为：10")
    private LocalDate birthday;
    /**
     * 手机号
     */
    @Size(max = 11, message = "手机号长度超出限制")
    @Pattern(regexp = "((?:0|86|\\+86)?1[3-9]\\d{9})|(^\\d{3,4}-\\d{7,8}$)|(^\\d{6,12}$)", message = "手机号格式不正确")
    @ApiModelProperty(value = "手机号", notes = "字符长度为：11")
    private String phone;
    /**
     * 密码
     */
    @Size(max = 100, message = "密码长度超出限制")
    @ApiModelProperty(value = "密码", notes = "字符长度为：100")
    private String password;
    /**
     * 是否系统用户
     */
    @ApiModelProperty(value = "是否系统用户", notes = "字符长度为：3")
    private Boolean isSystem;
}
