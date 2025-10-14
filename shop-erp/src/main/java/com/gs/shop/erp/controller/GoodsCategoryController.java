package com.gs.shop.erp.controller;

import com.gs.shop.erp.infrustructure.api.BaseApi;
import com.gs.shop.erp.mapper.GoodsCategoryMapper;
import com.gs.shop.erp.model.entity.GoodsCategoryEntity;
import com.gs.shop.erp.model.vo.GoodsCategoryVo;
import com.gs.shop.erp.model.form.GoodsCategoryForm;
import com.gs.shop.erp.model.query.GoodsCategoryQuery;
import com.gs.shop.erp.service.GoodsCategoryService;
import com.gs.shop.erp.convert.GoodsCategoryConvert;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品类别控制器
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Api(tags = "商品类别接口")
@Validated
@RestController
@RequestMapping("/goods/category")
@RequiredArgsConstructor
public class GoodsCategoryController extends BaseApi<GoodsCategoryService, GoodsCategoryMapper, GoodsCategoryConvert ,GoodsCategoryEntity, GoodsCategoryQuery, GoodsCategoryForm, GoodsCategoryVo>{

}
