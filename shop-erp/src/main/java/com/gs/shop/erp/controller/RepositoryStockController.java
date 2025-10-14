package com.gs.shop.erp.controller;

import com.gs.shop.erp.infrustructure.api.BaseApi;
import com.gs.shop.erp.mapper.RepositoryStockMapper;
import com.gs.shop.erp.model.entity.RepositoryStockEntity;
import com.gs.shop.erp.model.vo.RepositoryStockVo;
import com.gs.shop.erp.model.form.RepositoryStockForm;
import com.gs.shop.erp.model.query.RepositoryStockQuery;
import com.gs.shop.erp.service.RepositoryStockService;
import com.gs.shop.erp.convert.RepositoryStockConvert;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 仓库商品库存控制器
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Api(tags = "仓库商品库存接口")
@Validated
@RestController
@RequestMapping("/repository/stock")
@RequiredArgsConstructor
public class RepositoryStockController extends BaseApi<RepositoryStockService, RepositoryStockMapper, RepositoryStockConvert ,RepositoryStockEntity, RepositoryStockQuery, RepositoryStockForm, RepositoryStockVo>{

}
