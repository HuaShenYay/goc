package com.gs.shop.erp.validator;

import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.gs.shop.erp.model.entity.RepositoryStockEntity;
import com.gs.shop.erp.model.form.BaseStockForm;
import com.gs.shop.erp.model.form.PurchaseSaleHistoryForm;
import com.gs.shop.erp.model.form.PurchaseSaleOrderForm;
import com.gs.shop.erp.service.RepositoryStockService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 通用库存校验器
 *
 * @author Pursuer
 * @version 1.0
 * @date 2023/12/10
 */
public class BaseStockValidator<T extends BaseStockForm> {

    @Resource
    private RepositoryStockService repositoryStockService;

    /**
     * 校验库存是否充足
     *
     * @param items 表单
     * @return void
     * @author Pursuer
     * @date 2023/12/10
     * @since 1.0
     **/
    public void isStockEnough(List<T> items) {
        //查询库存
        List<Integer> ids = CollStreamUtil.toList(items, T::getRepositoryStockId);
        List<RepositoryStockEntity> stockList = repositoryStockService.listByIds(ids);
        //转换为Map集合
        Map<Integer, RepositoryStockEntity> stockMap = CollStreamUtil.toMap(stockList, RepositoryStockEntity::getId, Function.identity());
        //校验
        for (int i = 0; i < items.size(); i++) {
            T item = items.get(i);
            RepositoryStockEntity stock = stockMap.get(item.getRepositoryStockId());
            Assert.notNull(stock, StrUtil.format("第{}个商品不存在", i + 1));
            Assert.isTrue(stock.getStock() >= item.getCount(), StrUtil.format("第{}个商品库存不足", i + 1));
        }
    }
}