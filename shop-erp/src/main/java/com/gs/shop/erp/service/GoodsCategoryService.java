package com.gs.shop.erp.service;

import com.gs.shop.erp.mapper.GoodsCategoryMapper;
import com.gs.shop.erp.model.entity.GoodsCategoryEntity;
import com.gs.shop.erp.model.form.GoodsCategoryForm;
import com.gs.shop.erp.model.vo.GoodsCategoryVo;
import com.gs.shop.erp.model.query.GoodsCategoryQuery;
import com.gs.shop.erp.convert.GoodsCategoryConvert;
import com.gs.shop.erp.validator.GoodsCategoryValidator;
import lombok.RequiredArgsConstructor;
import com.gs.shop.erp.infrustructure.service.BaseService;
import com.gs.shop.erp.infrustructure.annotation.AutoService;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

/**
 * 商品类别业务操作实现类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@AutoService
@RequiredArgsConstructor
public class GoodsCategoryService extends BaseService<GoodsCategoryMapper, GoodsCategoryConvert, GoodsCategoryEntity, GoodsCategoryQuery, GoodsCategoryForm, GoodsCategoryVo> {

    private final GoodsCategoryValidator validator;

    @Override
    protected boolean beforeSave(GoodsCategoryForm form, GoodsCategoryEntity entity) {
        //校验唯一性
        validator.isUnique(form);
        return super.beforeSave(form, entity);
    }

    @Override
    protected boolean beforeModify(GoodsCategoryForm form, GoodsCategoryEntity entity) {
        //校验唯一性
        validator.isUnique(form);
        return super.beforeModify(form, entity);
    }

    @Override
    protected boolean beforeRemove(List<? extends Serializable> ids) {
        //校验是否关联
        validator.canRemove(ids);
        return super.beforeRemove(ids);
    }

    @Override
    protected Function<String, ? extends Serializable> convertKeyType() {
        return Integer::parseInt;
    }
}
