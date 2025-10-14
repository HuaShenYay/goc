package com.gs.shop.erp.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 修改密码请求
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-25
 */
@Data
@ApiModel(value = "修改密码请求")
public class UpdatePasswordRequest {
    /**
     * 原密码
     */
    @NotBlank(message = "原密码不能为空")
    @ApiModelProperty(value = "原密码")
    private String password;
    /**
     * 新密码
     */
    @NotBlank(message = "新密码不能为空")
    @ApiModelProperty(value = "新密码")
    private String newPassword;
    /**
     * 重复新密码
     */
    @NotBlank(message = "重复新密码不能为空")
    @ApiModelProperty(value = "重复新密码")
    private String reNewPassword;
}