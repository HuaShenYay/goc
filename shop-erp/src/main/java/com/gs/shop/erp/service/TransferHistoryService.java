package com.gs.shop.erp.service;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gs.shop.erp.comp.TransferHistoryComp;
import com.gs.shop.erp.mapper.TransferHistoryMapper;
import com.gs.shop.erp.model.entity.TransferHistoryEntity;
import com.gs.shop.erp.model.form.TransferHistoryForm;
import com.gs.shop.erp.model.vo.TransferHistoryVo;
import com.gs.shop.erp.model.query.TransferHistoryQuery;
import com.gs.shop.erp.convert.TransferHistoryConvert;
import com.gs.shop.erp.validator.TransferHistoryValidator;
import lombok.RequiredArgsConstructor;
import com.gs.shop.erp.infrustructure.service.BaseService;
import com.gs.shop.erp.infrustructure.annotation.AutoService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 转仓记录业务操作实现类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@AutoService
@RequiredArgsConstructor
public class TransferHistoryService extends BaseService<TransferHistoryMapper, TransferHistoryConvert, TransferHistoryEntity, TransferHistoryQuery, TransferHistoryForm, TransferHistoryVo> {

    private final TransferHistoryComp comp;
    private final TransferHistoryValidator validator;

    @Override
    protected void postPage(Page<TransferHistoryVo> page, TransferHistoryQuery query) {
        comp.appendInfo(page.getRecords());
    }

    @Override
    public boolean saveOne(TransferHistoryForm form) {
        synchronized (this) {
            return super.saveOne(form);
        }
    }

    @Override
    protected boolean beforeSave(TransferHistoryForm form, TransferHistoryEntity entity) {
        //合并相同的项
        List<TransferHistoryForm> items = form.getItems();
        items = new ArrayList<>(
                items.stream().collect(Collectors.toMap(item -> {
                    //查看原仓库是否和目标仓库一致
                    Assert.isFalse(item.getSource().equals(item.getTarget()), "原仓库和目标仓库不能一致");
                    return item.getRepositoryStockId() + StrUtil.DASHED + item.getTarget();
                }, Function.identity(), (s1, s2) -> {
                    s1.setCount(s1.getCount() + s2.getCount());
                    return s1;
                })).values()
        );
        form.setItems(items);
        //校验
        validator.isStockEnough(form.getItems());
        //批量入库
        saveBatch(this.convert.formToEntity(form.getItems()));
        //阻止默认的新增
        return Boolean.FALSE;
    }
}