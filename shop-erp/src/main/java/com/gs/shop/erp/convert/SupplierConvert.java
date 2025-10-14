package com.gs.shop.erp.convert;

import com.gs.shop.erp.infrustructure.convert.BaseConvert;
import com.gs.shop.erp.model.entity.SupplierEntity;
import com.gs.shop.erp.model.entity.SupplierEntity;
import com.gs.shop.erp.model.form.SupplierForm;
import com.gs.shop.erp.model.query.SupplierQuery;
import com.gs.shop.erp.model.vo.SupplierVo;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;

/**
 * 供货商对象转换类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Mapper
public interface SupplierConvert extends BaseConvert<SupplierEntity, SupplierForm, SupplierVo> {
    /**
     * 实例
     **/
    SupplierConvert INSTANCE = Mappers.getMapper(SupplierConvert.class);

    /**
     * Query转Entity
     *
     * @param query 查询对象
     * @return com.gs.shop.erp.model.entity.SupplierEntity
     * @author Pursuer
     * @date 2023/12/11
     * @since 1.0
     **/
    SupplierEntity queryToEntity(SupplierQuery query);
}