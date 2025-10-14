package com.gs.shop.erp.controller;

import com.gs.shop.erp.infrustructure.annotation.IgnoreLog;
import com.gs.shop.erp.infrustructure.result.Result;
import com.gs.shop.erp.model.request.LoginRequest;
import com.gs.shop.erp.model.vo.LoginVo;
import com.gs.shop.erp.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录接口
 *
 * @author Pursuer
 * @version 1.0
 * @date 2023/12/09
 */
@Api(tags = "登录接口")
@RestController
@Validated
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @IgnoreLog
    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public Result<LoginVo> login(@Validated @RequestBody LoginRequest request) {
        return Result.success(loginService.login(request));
    }
}