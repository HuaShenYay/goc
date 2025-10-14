package com.gs.shop.erp.controller;

import com.gs.shop.erp.infrustructure.api.BaseApi;
import com.gs.shop.erp.mapper.CustomerMapper;
import com.gs.shop.erp.model.entity.CustomerEntity;
import com.gs.shop.erp.model.vo.CustomerVo;
import com.gs.shop.erp.model.form.CustomerForm;
import com.gs.shop.erp.model.query.CustomerQuery;
import com.gs.shop.erp.service.CustomerService;
import com.gs.shop.erp.convert.CustomerConvert;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户控制器
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Api(tags = "客户接口")
@Validated
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController extends BaseApi<CustomerService, CustomerMapper, CustomerConvert ,CustomerEntity, CustomerQuery, CustomerForm, CustomerVo>{

}
