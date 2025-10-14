package com.gs.shop.erp.convert;

import com.gs.shop.erp.infrustructure.convert.BaseConvert;
import com.gs.shop.erp.model.entity.CustomerEntity;
import com.gs.shop.erp.model.form.CustomerForm;
import com.gs.shop.erp.model.query.CustomerQuery;
import com.gs.shop.erp.model.vo.CustomerVo;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;

/**
 * 客户对象转换类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Mapper
public interface CustomerConvert extends BaseConvert<CustomerEntity, CustomerForm, CustomerVo> {
    /**
     * 实例
     **/
    CustomerConvert INSTANCE = Mappers.getMapper(CustomerConvert.class);

    /**
     * Query转Entity
     *
     * @param query 查询对象
     * @return com.gs.shop.erp.model.entity.CustomerEntity
     * @author Pursuer
     * @date 2023/12/11
     * @since 1.0
     **/
    CustomerEntity queryToEntity(CustomerQuery query);
}