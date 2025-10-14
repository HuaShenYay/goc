package com.gs.shop.erp.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录视图
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-25
 */
@Data
@ApiModel(value = "登录视图")
public class LoginVo {
    /**
     * Token
     */
    @ApiModelProperty(value = "Token")
    private String token;
    /**
     * 用户
     */
    @ApiModelProperty(value = "用户")
    private SysUserVo user;
}