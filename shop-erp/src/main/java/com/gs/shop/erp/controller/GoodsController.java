package com.gs.shop.erp.controller;

import com.gs.shop.erp.infrustructure.api.BaseApi;
import com.gs.shop.erp.mapper.GoodsMapper;
import com.gs.shop.erp.model.entity.GoodsEntity;
import com.gs.shop.erp.model.vo.GoodsVo;
import com.gs.shop.erp.model.form.GoodsForm;
import com.gs.shop.erp.model.query.GoodsQuery;
import com.gs.shop.erp.service.GoodsService;
import com.gs.shop.erp.convert.GoodsConvert;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品控制器
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Api(tags = "商品接口")
@Validated
@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodsController extends BaseApi<GoodsService, GoodsMapper, GoodsConvert ,GoodsEntity, GoodsQuery, GoodsForm, GoodsVo>{

}
