package com.gs.shop.erp.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录请求
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-25
 */
@Data
@ApiModel(value = "登录请求")
public class LoginRequest {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "用户名")
    private String password;
}