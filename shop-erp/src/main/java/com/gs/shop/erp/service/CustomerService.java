package com.gs.shop.erp.service;

import com.gs.shop.erp.mapper.CustomerMapper;
import com.gs.shop.erp.model.entity.CustomerEntity;
import com.gs.shop.erp.model.form.CustomerForm;
import com.gs.shop.erp.model.vo.CustomerVo;
import com.gs.shop.erp.model.query.CustomerQuery;
import com.gs.shop.erp.convert.CustomerConvert;
import lombok.RequiredArgsConstructor;
import com.gs.shop.erp.infrustructure.service.BaseService;
import com.gs.shop.erp.infrustructure.annotation.AutoService;

/**
 * 客户业务操作实现类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@AutoService
@RequiredArgsConstructor
public class CustomerService extends BaseService<CustomerMapper, CustomerConvert ,CustomerEntity, CustomerQuery, CustomerForm, CustomerVo> {

}
