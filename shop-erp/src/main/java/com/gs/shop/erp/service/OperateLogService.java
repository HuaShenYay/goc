package com.gs.shop.erp.service;

import com.gs.shop.erp.mapper.OperateLogMapper;
import com.gs.shop.erp.model.entity.OperateLogEntity;
import com.gs.shop.erp.model.form.OperateLogForm;
import com.gs.shop.erp.model.vo.OperateLogVo;
import com.gs.shop.erp.model.query.OperateLogQuery;
import com.gs.shop.erp.convert.OperateLogConvert;
import lombok.RequiredArgsConstructor;
import com.gs.shop.erp.infrustructure.service.BaseService;
import com.gs.shop.erp.infrustructure.annotation.AutoService;

/**
 * 操作日志业务操作实现类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@AutoService
@RequiredArgsConstructor
public class OperateLogService extends BaseService<OperateLogMapper, OperateLogConvert ,OperateLogEntity, OperateLogQuery, OperateLogForm, OperateLogVo> {

}
