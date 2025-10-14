package com.gs.shop.erp.validator;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.gs.shop.erp.mapper.PurchaseSaleOrderMapper;
import com.gs.shop.erp.mapper.SupplierMapper;
import com.gs.shop.erp.mapper.SysUserMapper;
import com.gs.shop.erp.model.entity.PurchaseSaleOrderEntity;
import com.gs.shop.erp.model.entity.SupplierEntity;
import com.gs.shop.erp.model.entity.SysUserEntity;
import com.gs.shop.erp.model.form.SupplierForm;
import com.gs.shop.erp.model.form.SysUserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * 供货商校验类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Component
@RequiredArgsConstructor
public class SupplierValidator {
    private final SupplierMapper mapper;
    private final PurchaseSaleOrderMapper purchaseSaleOrderMapper;

    /**
     * 校验唯一性
     *
     * @param form 表单数据
     * @return void
     * @author Pursuer
     * @date 2023/12/9
     * @since 1.0
     **/
    public void isUnique(SupplierForm form) {
        Long count = new LambdaQueryChainWrapper<>(mapper)
                .eq(SupplierEntity::getName, form.getName())
                .eq(SupplierEntity::getProvince, form.getProvince())
                .eq(SupplierEntity::getCity, form.getCity())
                .ne(ObjectUtil.isNotNull(form.getId()), SupplierEntity::getId, form.getId())
                .count();
        Assert.isTrue(count == 0, "该城市已经存在该供货商了");
    }

    /**
     * 是否可删除
     *
     * @param ids ID集合
     * @return void
     * @author Pursuer
     * @date 2023/12/9
     * @since 1.0
     **/
    public void canRemove(List<? extends Serializable> ids) {
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        Long count = new LambdaQueryChainWrapper<>(purchaseSaleOrderMapper)
                .in(PurchaseSaleOrderEntity::getSupplierId, ids)
                .count();
        Assert.isTrue(count == 0, "该供应商已经关联了入库记录！");
    }
}
