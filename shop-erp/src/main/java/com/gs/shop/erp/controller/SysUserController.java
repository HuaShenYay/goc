package com.gs.shop.erp.controller;

import com.gs.shop.erp.infrustructure.api.BaseApi;
import com.gs.shop.erp.infrustructure.auth.SessionService;
import com.gs.shop.erp.infrustructure.result.Result;
import com.gs.shop.erp.mapper.SysUserMapper;
import com.gs.shop.erp.model.entity.SysUserEntity;
import com.gs.shop.erp.model.request.UpdatePasswordRequest;
import com.gs.shop.erp.model.vo.SysUserVo;
import com.gs.shop.erp.model.form.SysUserForm;
import com.gs.shop.erp.model.query.SysUserQuery;
import com.gs.shop.erp.service.SysUserService;
import com.gs.shop.erp.convert.SysUserConvert;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统用户/业务员控制器
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Api(tags = "系统用户/业务员接口")
@Validated
@RestController
@RequestMapping("/sys/user")
@RequiredArgsConstructor
public class SysUserController extends BaseApi<SysUserService, SysUserMapper, SysUserConvert, SysUserEntity, SysUserQuery, SysUserForm, SysUserVo> {

    private final SessionService sessionService;

    @ApiOperation(value = "用户信息")
    @GetMapping("/info")
    public Result<SysUserVo> userInfo() {
        return Result.success(sessionService.getLoginUser());
    }

    @ApiOperation(value = "修改密码")
    @PutMapping("/updatePassword")
    public Result<Boolean> updatePassword(@Validated @RequestBody UpdatePasswordRequest request) {
        return Result.judge(this.baseService.updatePassword(request));
    }
}
