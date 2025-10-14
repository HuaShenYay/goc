package com.gs.shop.erp.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gs.shop.erp.comp.PurchaseSaleOrderComp;
import com.gs.shop.erp.constant.enums.OrderType;
import com.gs.shop.erp.convert.PurchaseSaleCountConvert;
import com.gs.shop.erp.convert.PurchaseSaleHistoryConvert;
import com.gs.shop.erp.mapper.PurchaseSaleOrderMapper;
import com.gs.shop.erp.model.entity.PurchaseSaleOrderEntity;
import com.gs.shop.erp.model.form.PurchaseSaleHistoryForm;
import com.gs.shop.erp.model.form.PurchaseSaleOrderForm;
import com.gs.shop.erp.model.vo.PurchaseSaleCountVo;
import com.gs.shop.erp.model.vo.PurchaseSaleOrderVo;
import com.gs.shop.erp.model.query.PurchaseSaleOrderQuery;
import com.gs.shop.erp.convert.PurchaseSaleOrderConvert;
import com.gs.shop.erp.validator.PurchaseSaleOrderValidator;
import lombok.RequiredArgsConstructor;
import com.gs.shop.erp.infrustructure.service.BaseService;
import com.gs.shop.erp.infrustructure.annotation.AutoService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 进出库订单业务操作实现类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@AutoService
@RequiredArgsConstructor
public class PurchaseSaleOrderService extends BaseService<PurchaseSaleOrderMapper, PurchaseSaleOrderConvert, PurchaseSaleOrderEntity, PurchaseSaleOrderQuery, PurchaseSaleOrderForm, PurchaseSaleOrderVo> {

    private final PurchaseSaleOrderComp comp;
    private final PurchaseSaleOrderValidator validator;
    private final PurchaseSaleHistoryService purchaseSaleHistoryService;

    @Override
    protected void postPage(Page<PurchaseSaleOrderVo> page, PurchaseSaleOrderQuery query) {
        //填充拓展信息
        comp.appendInfo(page.getRecords());
    }

    @Override
    protected void postFindById(PurchaseSaleOrderVo purchaseSaleOrderVo) {
        comp.appendHistory(CollUtil.toList(purchaseSaleOrderVo));
    }

    @Override
    protected boolean beforeSave(PurchaseSaleOrderForm form, PurchaseSaleOrderEntity entity) {
        //合并相同的项
        List<PurchaseSaleHistoryForm> items = form.getItems();
        items = new ArrayList<>(
                items.stream().collect(Collectors.toMap(
                        t -> {
                            if (OrderType.OUT.getKey().equals(form.getType())) {
                                return t.getRepositoryStockId() + "-" + t.getGoodsId() + "-" + t.getInPrice();
                            } else {
                                return t.getRepositoryId() + "-" + t.getGoodsId() + "-" + t.getInPrice();
                            }
                        },
                        Function.identity()
                        , (s1, s2) -> {
                            s1.setCount(s1.getCount() + s2.getCount());
                            return s1;
                        })).values()
        );
        form.setItems(items);
        //校验是否是出库
        if (OrderType.OUT.getKey().equals(form.getType())) {
            validator.isStockEnough(form.getItems());
        }
        //计算总金额总数量
        Integer count = 0;
        BigDecimal total = BigDecimal.ZERO;
        for (PurchaseSaleHistoryForm item : form.getItems()) {
            count += item.getCount();
            BigDecimal price = OrderType.OUT.getKey().equals(form.getType()) ? item.getOutPrice() : item.getInPrice();
            total = total.add(price.multiply(BigDecimal.valueOf(item.getCount())));
        }
        entity.setTotalCount(count);
        entity.setTotalAmount(total);
        return super.beforeSave(form, entity);
    }

    @Override
    public boolean saveOne(PurchaseSaleOrderForm form) {
        synchronized (this) {
            return super.saveOne(form);
        }
    }

    @Override
    protected void postSave(PurchaseSaleOrderForm form, PurchaseSaleOrderEntity entity, boolean status) {
        if (!status) {
            return;
        }
        List<PurchaseSaleHistoryForm> items = form.getItems();
        //设置订单ID和类型
        items.forEach(item -> {
            item.setOrderId(entity.getId());
            item.setType(entity.getType());
        });
        //入库
        purchaseSaleHistoryService.saveBatch(PurchaseSaleHistoryConvert.INSTANCE.formToEntity(items));
    }

    /**
     * 查询进销存统计
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return java.util.List<com.gs.shop.erp.model.vo.PurchaseSaleCountVo>
     * @author Pursuer
     * @date 2023/12/10
     * @since 1.0
     **/
    public List<PurchaseSaleCountVo> findPurchaseSaleCount(String startDate, String endDate) {
        return PurchaseSaleCountConvert.INSTANCE.entityToVo(this.baseMapper.selectPurchaseSaleCount(startDate, endDate));
    }
}