package com.gs.shop.erp.service;

import cn.hutool.core.lang.Assert;
import com.gs.shop.erp.convert.SysUserConvert;
import com.gs.shop.erp.infrustructure.auth.SessionService;
import com.gs.shop.erp.model.entity.SysUserEntity;
import com.gs.shop.erp.model.request.LoginRequest;
import com.gs.shop.erp.model.vo.LoginVo;
import com.gs.shop.erp.model.vo.SysUserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Pursuer
 * @version 1.0
 * @date 2023/11/15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final SysUserService sysUserService;
    private final PasswordEncoder passwordEncoder;
    private final SessionService sessionService;

    /**
     * 登录
     *
     * @param request 登录请求
     * @return com.gs.personal.pojo.vo.LoginVo
     * @author Pursuer
     * @date 2023/12/09
     * @since 1.0
     **/
    public LoginVo login(LoginRequest request) {
        //根据用户名查询
        SysUserEntity entity = sysUserService.lambdaQuery().eq(SysUserEntity::getPhone, request.getUsername()).one();
        //判断是否存在
        Assert.notNull(entity, "用户名不存在");
        //校验密码
        Assert.isTrue(passwordEncoder.matches(request.getPassword(), entity.getPassword()), "密码不正确");
        //转换为Vo
        SysUserVo user = SysUserConvert.INSTANCE.entityToVo(entity);
        //生成Token
        String token = sessionService.createToken(user);
        //清空密码
        user.setPassword(null);
        //创建返回值
        LoginVo vo = new LoginVo();
        vo.setToken(token);
        vo.setUser(user);
        //返回
        return vo;
    }
}