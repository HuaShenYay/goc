package com.gs.shop.erp.controller;

import com.gs.shop.erp.infrustructure.api.BaseApi;
import com.gs.shop.erp.mapper.OperateLogMapper;
import com.gs.shop.erp.model.entity.OperateLogEntity;
import com.gs.shop.erp.model.vo.OperateLogVo;
import com.gs.shop.erp.model.form.OperateLogForm;
import com.gs.shop.erp.model.query.OperateLogQuery;
import com.gs.shop.erp.service.OperateLogService;
import com.gs.shop.erp.convert.OperateLogConvert;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志控制器
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Api(tags = "操作日志接口")
@Validated
@RestController
@RequestMapping("/operate/log")
@RequiredArgsConstructor
public class OperateLogController extends BaseApi<OperateLogService, OperateLogMapper, OperateLogConvert ,OperateLogEntity, OperateLogQuery, OperateLogForm, OperateLogVo>{

}
