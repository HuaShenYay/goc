package com.gs.shop.erp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gs.shop.erp.comp.RepositoryStockComp;
import com.gs.shop.erp.mapper.RepositoryStockMapper;
import com.gs.shop.erp.model.entity.RepositoryStockEntity;
import com.gs.shop.erp.model.form.RepositoryStockForm;
import com.gs.shop.erp.model.vo.RepositoryStockVo;
import com.gs.shop.erp.model.query.RepositoryStockQuery;
import com.gs.shop.erp.convert.RepositoryStockConvert;
import lombok.RequiredArgsConstructor;
import com.gs.shop.erp.infrustructure.service.BaseService;
import com.gs.shop.erp.infrustructure.annotation.AutoService;

import java.util.List;

/**
 * 仓库商品库存业务操作实现类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@AutoService
@RequiredArgsConstructor
public class RepositoryStockService extends BaseService<RepositoryStockMapper, RepositoryStockConvert, RepositoryStockEntity, RepositoryStockQuery, RepositoryStockForm, RepositoryStockVo> {

    private final RepositoryStockComp comp;

    @Override
    protected void postPage(Page<RepositoryStockVo> page, RepositoryStockQuery query) {
        //填充商品
        comp.appendGoods(page.getRecords());
    }

    @Override
    protected void postList(List<RepositoryStockVo> dataList, RepositoryStockQuery query) {
        comp.appendGoods(dataList);
    }
}
