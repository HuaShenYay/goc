package com.gs.shop.erp.controller;

import com.gs.shop.erp.infrustructure.api.BaseApi;
import com.gs.shop.erp.infrustructure.result.Result;
import com.gs.shop.erp.mapper.RepositoryMapper;
import com.gs.shop.erp.model.entity.RepositoryEntity;
import com.gs.shop.erp.model.vo.RepositoryVo;
import com.gs.shop.erp.model.form.RepositoryForm;
import com.gs.shop.erp.model.query.RepositoryQuery;
import com.gs.shop.erp.service.RepositoryService;
import com.gs.shop.erp.convert.RepositoryConvert;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 仓库控制器
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Api(tags = "仓库接口")
@Validated
@RestController
@RequestMapping("/repository")
@RequiredArgsConstructor
public class RepositoryController extends BaseApi<RepositoryService, RepositoryMapper, RepositoryConvert, RepositoryEntity, RepositoryQuery, RepositoryForm, RepositoryVo> {

    @ApiOperation(value = "查询仓库库存")
    @GetMapping("/stocks")
    public Result<List<RepositoryVo>> findStocks() {
        return Result.success(this.baseService.findStocks());
    }
}
