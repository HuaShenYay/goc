package com.gs.shop.erp.validator;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.gs.shop.erp.mapper.GoodsMapper;
import com.gs.shop.erp.mapper.PurchaseSaleHistoryMapper;
import com.gs.shop.erp.mapper.RepositoryStockMapper;
import com.gs.shop.erp.mapper.TransferHistoryMapper;
import com.gs.shop.erp.model.entity.GoodsEntity;
import com.gs.shop.erp.model.entity.PurchaseSaleHistoryEntity;
import com.gs.shop.erp.model.entity.RepositoryStockEntity;
import com.gs.shop.erp.model.entity.TransferHistoryEntity;
import com.gs.shop.erp.model.form.GoodsForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * 商品校验类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Component
@RequiredArgsConstructor
public class GoodsValidator {

    private final GoodsMapper mapper;
    private final PurchaseSaleHistoryMapper purchaseSaleHistoryMapper;
    private final RepositoryStockMapper repositoryStockMapper;
    private final TransferHistoryMapper transferHistoryMapper;

    /**
     * 校验唯一性
     *
     * @param form 数据
     * @return void
     * @author Pursuer
     * @date 2023/12/9
     * @since 1.0
     **/
    public void isUnique(GoodsForm form) {
        Long count = new LambdaQueryChainWrapper<>(mapper)
                .eq(GoodsEntity::getName, form.getName())
                .eq(GoodsEntity::getCategoryId, form.getCategoryId())
                .ne(ObjectUtil.isNotNull(form.getId()), GoodsEntity::getId, form.getId())
                .count();
        Assert.isTrue(count == 0, "该类别的商品已经存在了！");
    }

    /**
     * 校验是否可删除
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
        //校验入/出库
        Long count = new LambdaQueryChainWrapper<>(purchaseSaleHistoryMapper)
                .in(PurchaseSaleHistoryEntity::getGoodsId, ids)
                .count();
        Assert.isTrue(count == 0, "该商品已经关联了入/出库信息");
        //校验仓库
        count = new LambdaQueryChainWrapper<>(repositoryStockMapper)
                .in(RepositoryStockEntity::getGoodsId, ids)
                .count();
        Assert.isTrue(count == 0, "该商品已经关联了库存信息");
        //校验转仓
        count = new LambdaQueryChainWrapper<>(transferHistoryMapper)
                .in(TransferHistoryEntity::getGoodsId, ids)
                .count();
        Assert.isTrue(count == 0, "该商品已经关联了转仓信息");
    }
}
