package com.gs.shop.erp.validator;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.gs.shop.erp.mapper.GoodsCategoryMapper;
import com.gs.shop.erp.mapper.GoodsMapper;
import com.gs.shop.erp.model.entity.GoodsCategoryEntity;
import com.gs.shop.erp.model.entity.GoodsEntity;
import com.gs.shop.erp.model.form.GoodsCategoryForm;
import com.gs.shop.erp.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * 商品类别校验类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Component
@RequiredArgsConstructor
public class GoodsCategoryValidator {

    private final GoodsCategoryMapper mapper;
    private final GoodsMapper goodsMapper;

    /**
     * 校验唯一性
     *
     * @param form 数据
     * @return void
     * @author Pursuer
     * @date 2023/12/9
     * @since 1.0
     **/
    public void isUnique(GoodsCategoryForm form) {
        Long count = new LambdaQueryChainWrapper<>(mapper)
                .eq(GoodsCategoryEntity::getName, form.getName())
                .ne(ObjectUtil.isNotNull(form.getId()), GoodsCategoryEntity::getId, form.getId())
                .count();
        Assert.isTrue(count == 0, "该商品类别已经存在了！");
    }

    /**
     * 校验唯一性
     *
     * @param ids id集合
     * @return void
     * @author Pursuer
     * @date 2023/12/9
     * @since 1.0
     **/
    public void canRemove(List<? extends Serializable> ids) {
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        Long count = new LambdaQueryChainWrapper<>(goodsMapper)
                .in(GoodsEntity::getCategoryId, ids)
                .count();
        Assert.isTrue(count == 0, "该商品类别已经关联商品了！");
    }
}
