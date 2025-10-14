package com.gs.shop.erp.controller;

import com.gs.shop.erp.infrustructure.api.BaseApi;
import com.gs.shop.erp.mapper.SupplierMapper;
import com.gs.shop.erp.model.entity.SupplierEntity;
import com.gs.shop.erp.model.vo.SupplierVo;
import com.gs.shop.erp.model.form.SupplierForm;
import com.gs.shop.erp.model.query.SupplierQuery;
import com.gs.shop.erp.service.SupplierService;
import com.gs.shop.erp.convert.SupplierConvert;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 供货商控制器
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Api(tags = "供货商接口")
@Validated
@RestController
@RequestMapping("/supplier")
@RequiredArgsConstructor
public class SupplierController extends BaseApi<SupplierService, SupplierMapper, SupplierConvert ,SupplierEntity, SupplierQuery, SupplierForm, SupplierVo>{

}
