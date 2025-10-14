package com.gs.shop.erp.validator;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.gs.shop.erp.mapper.SysUserMapper;
import com.gs.shop.erp.model.entity.SysUserEntity;
import com.gs.shop.erp.model.form.SysUserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 系统用户/业务员校验类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Component
@RequiredArgsConstructor
public class SysUserValidator {

    private final SysUserMapper mapper;

    /**
     * 校验唯一性
     *
     * @param form 表单数据
     * @return void
     * @author Pursuer
     * @date 2023/12/9
     * @since 1.0
     **/
    public void isUnique(SysUserForm form) {
        Long count = new LambdaQueryChainWrapper<>(mapper)
                .eq(SysUserEntity::getPhone, form.getPhone())
                .ne(ObjectUtil.isNotNull(form.getId()), SysUserEntity::getId, form.getId())
                .count();
        Assert.isTrue(count == 0, "该手机号的业务员已经存在了");
    }
}
