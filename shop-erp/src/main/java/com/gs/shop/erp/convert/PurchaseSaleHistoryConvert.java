package com.gs.shop.erp.convert;

import com.gs.shop.erp.infrustructure.convert.BaseConvert;
import com.gs.shop.erp.model.entity.PurchaseSaleHistoryEntity;
import com.gs.shop.erp.model.form.PurchaseSaleHistoryForm;
import com.gs.shop.erp.model.vo.PurchaseSaleHistoryVo;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;

/**
 * 入库/出库记录对象转换类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Mapper
public interface PurchaseSaleHistoryConvert extends BaseConvert<PurchaseSaleHistoryEntity, PurchaseSaleHistoryForm, PurchaseSaleHistoryVo> {
    /**
     * 实例
     **/
    PurchaseSaleHistoryConvert INSTANCE = Mappers.getMapper(PurchaseSaleHistoryConvert.class);
}