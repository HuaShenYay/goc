package com.gs.shop.erp.service;

import cn.hutool.core.lang.Assert;
import com.gs.shop.erp.infrustructure.auth.SessionService;
import com.gs.shop.erp.mapper.SysUserMapper;
import com.gs.shop.erp.model.entity.SysUserEntity;
import com.gs.shop.erp.model.form.SysUserForm;
import com.gs.shop.erp.model.request.UpdatePasswordRequest;
import com.gs.shop.erp.model.vo.SysUserVo;
import com.gs.shop.erp.model.query.SysUserQuery;
import com.gs.shop.erp.convert.SysUserConvert;
import com.gs.shop.erp.validator.SysUserValidator;
import lombok.RequiredArgsConstructor;
import com.gs.shop.erp.infrustructure.service.BaseService;
import com.gs.shop.erp.infrustructure.annotation.AutoService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.gs.shop.erp.constant.GlobalConstants.DEFAULT_PASSWORD;

/**
 * 系统用户/业务员业务操作实现类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@AutoService
@RequiredArgsConstructor
public class SysUserService extends BaseService<SysUserMapper, SysUserConvert, SysUserEntity, SysUserQuery, SysUserForm, SysUserVo> {

    private final PasswordEncoder passwordEncoder;
    private final SysUserValidator validator;
    private final SessionService sessionService;

    @Override
    protected boolean beforeSave(SysUserForm form, SysUserEntity entity) {
        //校验唯一
        validator.isUnique(form);
        //密码加密
        entity.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        return super.beforeSave(form, entity);
    }

    @Override
    protected boolean beforeModify(SysUserForm form, SysUserEntity entity) {
        //校验唯一
        validator.isUnique(form);
        //密码置空不修改
        entity.setPassword(null);
        return super.beforeModify(form, entity);
    }

    /**
     * 修改密码
     *
     * @param request 修改密码请求
     * @return java.lang.Boolean
     * @author Pursuer
     * @date 2023/12/9
     * @since 1.0
     **/
    public Boolean updatePassword(UpdatePasswordRequest request) {
        //校验两次新密码是否一致
        Assert.isTrue(request.getNewPassword().equals(request.getReNewPassword()), "两次密码输入不一致！");
        //查询当前登录用户的信息
        SysUserEntity entity = getById(sessionService.getUserId());
        //校验新密码和老密码是否一样
        Assert.isFalse(request.getPassword().equals(request.getNewPassword()), "新密码和老密码不能一致");
        //判断原始密码是否相等
        Assert.isTrue(passwordEncoder.matches(request.getPassword(), entity.getPassword()), "原密码输入错误！");
        //密码加密入库
        entity.setPassword(passwordEncoder.encode(request.getNewPassword()));
        //入库
        return updateById(entity);
    }
}
