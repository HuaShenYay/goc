package com.gs.shop.erp.comp;

import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Opt;
import cn.hutool.core.util.ObjectUtil;
import com.gs.shop.erp.mapper.GoodsCategoryMapper;
import com.gs.shop.erp.model.entity.GoodsCategoryEntity;
import com.gs.shop.erp.model.vo.GoodsVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 商品拓展字段填充
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Component
@RequiredArgsConstructor
public class GoodsComp {

    private final GoodsCategoryMapper goodsCategoryMapper;

    /**
     * 填充类别
     *
     * @param dataList 数据集合
     * @return void
     * @author Pursuer
     * @date 2023/12/10
     * @since 1.0
     **/
    public void appendCategory(List<GoodsVo> dataList) {
        //提取类别ID
        List<Integer> categoryIds = dataList.stream().map(GoodsVo::getCategoryId).filter(ObjectUtil::isNotNull).collect(Collectors.toList());
        //判断是否为空
        if (CollUtil.isEmpty(categoryIds)) {
            return;
        }
        //查询类别信息
        List<GoodsCategoryEntity> categoryList = goodsCategoryMapper.selectBatchIds(categoryIds);
        //转换为Map集合
        Map<Integer, String> categoryMap = CollStreamUtil.toMap(categoryList, GoodsCategoryEntity::getId, GoodsCategoryEntity::getName);
        //填充数据
        dataList.forEach(data -> {
            data.setCategoryName(categoryMap.get(data.getCategoryId()));
        });
    }
}
