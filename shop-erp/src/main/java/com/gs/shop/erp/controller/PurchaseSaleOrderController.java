package com.gs.shop.erp.controller;

import com.gs.shop.erp.infrustructure.api.BaseApi;
import com.gs.shop.erp.infrustructure.result.Result;
import com.gs.shop.erp.mapper.PurchaseSaleOrderMapper;
import com.gs.shop.erp.model.entity.PurchaseSaleOrderEntity;
import com.gs.shop.erp.model.vo.PurchaseSaleCountVo;
import com.gs.shop.erp.model.vo.PurchaseSaleOrderVo;
import com.gs.shop.erp.model.form.PurchaseSaleOrderForm;
import com.gs.shop.erp.model.query.PurchaseSaleOrderQuery;
import com.gs.shop.erp.service.PurchaseSaleOrderService;
import com.gs.shop.erp.convert.PurchaseSaleOrderConvert;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 进出库订单控制器
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Api(tags = "进出库订单接口")
@Validated
@RestController
@RequestMapping("/purchase/sale/order")
@RequiredArgsConstructor
public class PurchaseSaleOrderController extends BaseApi<PurchaseSaleOrderService, PurchaseSaleOrderMapper, PurchaseSaleOrderConvert, PurchaseSaleOrderEntity, PurchaseSaleOrderQuery, PurchaseSaleOrderForm, PurchaseSaleOrderVo> {

    @ApiOperation(value = "查询进销存统计")
    @GetMapping("count")
    public Result<List<PurchaseSaleCountVo>> findPurchaseSaleCount(String startDate, String endDate) {
        return Result.success(this.baseService.findPurchaseSaleCount(startDate, endDate));
    }
}
