package com.gs.shop.erp.controller;

import com.gs.shop.erp.infrustructure.api.BaseApi;
import com.gs.shop.erp.mapper.PurchaseSaleHistoryMapper;
import com.gs.shop.erp.model.entity.PurchaseSaleHistoryEntity;
import com.gs.shop.erp.model.vo.PurchaseSaleHistoryVo;
import com.gs.shop.erp.model.form.PurchaseSaleHistoryForm;
import com.gs.shop.erp.model.query.PurchaseSaleHistoryQuery;
import com.gs.shop.erp.service.PurchaseSaleHistoryService;
import com.gs.shop.erp.convert.PurchaseSaleHistoryConvert;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 入库/出库记录控制器
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Api(tags = "入库/出库记录接口")
@Validated
@RestController
@RequestMapping("/purchase/sale/history")
@RequiredArgsConstructor
public class PurchaseSaleHistoryController extends BaseApi<PurchaseSaleHistoryService, PurchaseSaleHistoryMapper, PurchaseSaleHistoryConvert ,PurchaseSaleHistoryEntity, PurchaseSaleHistoryQuery, PurchaseSaleHistoryForm, PurchaseSaleHistoryVo>{

}
