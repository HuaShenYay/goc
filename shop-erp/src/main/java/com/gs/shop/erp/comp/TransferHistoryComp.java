package com.gs.shop.erp.comp;

import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gs.shop.erp.mapper.GoodsMapper;
import com.gs.shop.erp.mapper.RepositoryMapper;
import com.gs.shop.erp.model.entity.GoodsEntity;
import com.gs.shop.erp.model.entity.RepositoryEntity;
import com.gs.shop.erp.model.vo.RepositoryStockVo;
import com.gs.shop.erp.model.vo.TransferHistoryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 转仓记录拓展字段填充
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Component
@RequiredArgsConstructor
public class TransferHistoryComp {

    private final GoodsMapper goodsMapper;
    private final RepositoryMapper repositoryMapper;

    /**
     * 填充拓展字段
     *
     * @param dataList 数据集合
     * @return void
     * @author Pursuer
     * @date 2023/12/10
     * @since 1.0
     **/
    public void appendInfo(List<TransferHistoryVo> dataList) {
        if (CollUtil.isEmpty(dataList)) {
            return;
        }
        //填充仓库
        appendRepository(dataList);
        //填充商品
        appendGoods(dataList);
    }

    /**
     * 填充仓库
     *
     * @param dataList 数据集合
     * @return void
     * @author Pursuer
     * @date 2023/12/10
     * @since 1.0
     **/
    public void appendRepository(List<TransferHistoryVo> dataList) {
        //提取仓库ID
        List<Integer> repositoryIds = dataList.stream()
                .map(item -> CollUtil.toList(item.getSource(), item.getTarget()))
                .flatMap(Collection::stream)
                .filter(ObjectUtil::isNotNull).collect(Collectors.toList());
        //判断是否为空
        if (CollUtil.isEmpty(repositoryIds)) {
            return;
        }
        //查询仓库信息
        List<RepositoryEntity> repositoryList = repositoryMapper.selectBatchIds(repositoryIds);
        //转换为Map集合
        Map<Integer, String> repositoryMap = CollStreamUtil.toMap(repositoryList, RepositoryEntity::getId, RepositoryEntity::getName);
        //填充数据
        dataList.forEach(data -> {
            data.setSourceRepository(repositoryMap.get(data.getSource()));
            data.setTargetRepository(repositoryMap.get(data.getTarget()));
        });
    }

    /**
     * 填充商品
     *
     * @param dataList 数据集合
     * @return void
     * @author Pursuer
     * @date 2023/12/10
     * @since 1.0
     **/
    public void appendGoods(List<TransferHistoryVo> dataList) {
        //提取商品ID
        List<Integer> goodIds = dataList.stream().map(TransferHistoryVo::getGoodsId).filter(ObjectUtil::isNotNull).collect(Collectors.toList());
        //判断是否为空
        if (CollUtil.isEmpty(goodIds)) {
            return;
        }
        //查询商品信息
        List<GoodsEntity> goodsList = goodsMapper.selectBatchIds(goodIds);
        //转换为Map集合
        Map<Integer, String> goodsMap = CollStreamUtil.toMap(goodsList, GoodsEntity::getId, GoodsEntity::getName);
        //填充数据
        dataList.forEach(data -> {
            data.setGoodsName(goodsMap.get(data.getGoodsId()));
        });
    }
}
