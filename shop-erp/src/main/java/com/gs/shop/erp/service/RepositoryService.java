package com.gs.shop.erp.service;

import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Opt;
import com.gs.shop.erp.mapper.RepositoryMapper;
import com.gs.shop.erp.model.entity.RepositoryEntity;
import com.gs.shop.erp.model.form.RepositoryForm;
import com.gs.shop.erp.model.query.RepositoryStockQuery;
import com.gs.shop.erp.model.vo.RepositoryStockVo;
import com.gs.shop.erp.model.vo.RepositoryVo;
import com.gs.shop.erp.model.query.RepositoryQuery;
import com.gs.shop.erp.convert.RepositoryConvert;
import com.gs.shop.erp.validator.RepositoryValidator;
import lombok.RequiredArgsConstructor;
import com.gs.shop.erp.infrustructure.service.BaseService;
import com.gs.shop.erp.infrustructure.annotation.AutoService;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 仓库业务操作实现类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@AutoService
@RequiredArgsConstructor
public class RepositoryService extends BaseService<RepositoryMapper, RepositoryConvert, RepositoryEntity, RepositoryQuery, RepositoryForm, RepositoryVo> {

    private final RepositoryStockService repositoryStockService;
    private final RepositoryValidator validator;

    @Override
    protected boolean beforeSave(RepositoryForm form, RepositoryEntity entity) {
        validator.isUnique(form);
        return super.beforeSave(form, entity);
    }

    @Override
    protected boolean beforeModify(RepositoryForm form, RepositoryEntity entity) {
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

    /**
     * 查询库存
     *
     * @return java.util.List<com.gs.shop.erp.model.vo.RepositoryVo>
     * @author Pursuer
     * @date 2023/12/10
     * @since 1.0
     **/
    public List<RepositoryVo> findStocks() {
        //先查询仓库
        List<RepositoryVo> repList = findList(new RepositoryQuery());
        if (CollUtil.isEmpty(repList)) {
            return repList;
        }
        //查询库存
        List<Integer> repIds = CollStreamUtil.toList(repList, RepositoryVo::getId);
        List<RepositoryStockVo> stockList = repositoryStockService.findList(new RepositoryStockQuery().setRepositoryIds(repIds));
        //分组
        Map<Integer, List<RepositoryStockVo>> stockMap = CollStreamUtil.groupByKey(stockList, RepositoryStockVo::getRepositoryId);
        //设置数据
        repList.forEach(item -> {
            List<RepositoryStockVo> stocks = Opt.ofNullable(stockMap.get(item.getId())).orElse(CollUtil.toList());
            //过滤掉库存为0的
            CollUtil.filter(stocks, t -> t.getStock() > 0);
            item.setStocks(stocks);
        });
        //过滤掉没有商品的仓库
        CollUtil.filter(repList, item -> !item.getStocks().isEmpty());
        //返回
        return repList;
    }
}
