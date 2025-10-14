package com.gs.shop.erp.convert;

import com.gs.shop.erp.infrustructure.convert.BaseConvert;
import com.gs.shop.erp.model.entity.PurchaseSaleOrderEntity;
import com.gs.shop.erp.model.form.PurchaseSaleOrderForm;
import com.gs.shop.erp.model.query.PurchaseSaleOrderQuery;
import com.gs.shop.erp.model.vo.PurchaseSaleOrderVo;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;

/**
 * 进出库订单对象转换类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Mapper
public interface PurchaseSaleOrderConvert extends BaseConvert<PurchaseSaleOrderEntity, PurchaseSaleOrderForm, PurchaseSaleOrderVo> {
    /**
     * 实例
     **/
    PurchaseSaleOrderConvert INSTANCE = Mappers.getMapper(PurchaseSaleOrderConvert.class);

    /**
     * Query转Entity
     *
     * @param query 查询对象
     * @return com.gs.shop.erp.model.entity.PurchaseSaleOrderEntity
     * @author Pursuer
     * @date 2023/12/11
     * @since 1.0
     **/
    PurchaseSaleOrderEntity queryToEntity(PurchaseSaleOrderQuery query);
}