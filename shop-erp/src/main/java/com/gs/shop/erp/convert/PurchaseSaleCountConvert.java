package com.gs.shop.erp.convert;

import com.gs.shop.erp.model.entity.PurchaseSaleCountEntity;
import com.gs.shop.erp.model.vo.PurchaseSaleCountVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 进销存统计转换器
 *
 * @author Pursuer
 * @version 1.0
 * @date 2023/12/10
 */
@Mapper
public interface PurchaseSaleCountConvert {
    /**
     * 实例
     */
    PurchaseSaleCountConvert INSTANCE = Mappers.getMapper(PurchaseSaleCountConvert.class);

    /**
     * Entity转Vo
     *
     * @param entityList 实体数据集合
     * @return java.util.List<com.gs.shop.erp.model.vo.PurchaseSaleCountVo>
     * @author Pursuer
     * @date 2023/12/10
     * @since 1.0
     **/
    List<PurchaseSaleCountVo> entityToVo(List<PurchaseSaleCountEntity> entityList);
}
