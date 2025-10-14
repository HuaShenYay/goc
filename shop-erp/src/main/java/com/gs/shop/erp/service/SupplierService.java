package com.gs.shop.erp.service;

import com.gs.shop.erp.mapper.SupplierMapper;
import com.gs.shop.erp.model.entity.SupplierEntity;
import com.gs.shop.erp.model.form.SupplierForm;
import com.gs.shop.erp.model.vo.SupplierVo;
import com.gs.shop.erp.model.query.SupplierQuery;
import com.gs.shop.erp.convert.SupplierConvert;
import com.gs.shop.erp.validator.SupplierValidator;
import lombok.RequiredArgsConstructor;
import com.gs.shop.erp.infrustructure.service.BaseService;
import com.gs.shop.erp.infrustructure.annotation.AutoService;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

/**
 * 供货商业务操作实现类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@AutoService
@RequiredArgsConstructor
public class SupplierService extends BaseService<SupplierMapper, SupplierConvert, SupplierEntity, SupplierQuery, SupplierForm, SupplierVo> {

    private final SupplierValidator validator;

    @Override
    protected boolean beforeSave(SupplierForm form, SupplierEntity entity) {
        validator.isUnique(form);
        return super.beforeSave(form, entity);
    }

    @Override
    protected boolean beforeModify(SupplierForm form, SupplierEntity entity) {
        validator.isUnique(form);
        return super.beforeModify(form, entity);
    }

    @Override
    protected boolean beforeRemove(List<? extends Serializable> ids) {
        validator.canRemove(ids);
        return super.beforeRemove(ids);
    }

    @Override
    protected Function<String, ? extends Serializable> convertKeyType() {
        return Integer::parseInt;
    }
}
