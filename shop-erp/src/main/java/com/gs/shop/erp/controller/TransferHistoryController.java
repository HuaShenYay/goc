package com.gs.shop.erp.controller;

import com.gs.shop.erp.infrustructure.api.BaseApi;
import com.gs.shop.erp.infrustructure.result.Result;
import com.gs.shop.erp.mapper.TransferHistoryMapper;
import com.gs.shop.erp.model.entity.TransferHistoryEntity;
import com.gs.shop.erp.model.vo.TransferHistoryVo;
import com.gs.shop.erp.model.form.TransferHistoryForm;
import com.gs.shop.erp.model.query.TransferHistoryQuery;
import com.gs.shop.erp.service.TransferHistoryService;
import com.gs.shop.erp.convert.TransferHistoryConvert;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 转仓记录控制器
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Api(tags = "转仓记录接口")
@Validated
@RestController
@RequestMapping("/transfer/history")
@RequiredArgsConstructor
public class TransferHistoryController extends BaseApi<TransferHistoryService, TransferHistoryMapper, TransferHistoryConvert, TransferHistoryEntity, TransferHistoryQuery, TransferHistoryForm, TransferHistoryVo> {

    @Override
    public Result<Object> saveOne(@Validated @RequestBody TransferHistoryForm entity) {
        super.saveOne(entity);
        return Result.success();
    }
}
