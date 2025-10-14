package com.gs.shop.erp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gs.shop.erp.comp.GoodsComp;
import com.gs.shop.erp.mapper.GoodsMapper;
import com.gs.shop.erp.model.entity.GoodsEntity;
import com.gs.shop.erp.model.form.GoodsForm;
import com.gs.shop.erp.model.vo.GoodsVo;
import com.gs.shop.erp.model.query.GoodsQuery;
import com.gs.shop.erp.convert.GoodsConvert;
import com.gs.shop.erp.validator.GoodsValidator;
import lombok.RequiredArgsConstructor;
import com.gs.shop.erp.infrustructure.service.BaseService;
import com.gs.shop.erp.infrustructure.annotation.AutoService;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

/**
 * 商品业务操作实现类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@AutoService
@RequiredArgsConstructor
public class GoodsService extends BaseService<GoodsMapper, GoodsConvert, GoodsEntity, GoodsQuery, GoodsForm, GoodsVo> {

    private final GoodsValidator validator;
    private final GoodsComp comp;

    @Override
    protected void postPage(Page<GoodsVo> page, GoodsQuery query) {
        comp.appendCategory(page.getRecords());
    }

    @Override
    protected boolean beforeSave(GoodsForm form, GoodsEntity entity) {
//        //校验唯一性
        validator.isUnique(form);
        return super.beforeSave(form, entity);
    }

    @Override
    protected boolean beforeModify(GoodsForm form, GoodsEntity entity) {
        //校验唯一性
        validator.isUnique(form);
        return super.beforeModify(form, entity);
    }

    @Override
    protected boolean beforeRemove(List<? extends Serializable> ids) {
        //校验
        validator.canRemove(ids);
        return super.beforeRemove(ids);
    }

    @Override
    protected Function<String, ? extends Serializable> convertKeyType() {
        return Integer::parseInt;
    }
}