package com.gs.shop.erp.convert;

import com.gs.shop.erp.infrustructure.convert.BaseConvert;
import com.gs.shop.erp.model.entity.OperateLogEntity;
import com.gs.shop.erp.model.form.OperateLogForm;
import com.gs.shop.erp.model.vo.OperateLogVo;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;

/**
 * 操作日志对象转换类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Mapper
public interface OperateLogConvert extends BaseConvert<OperateLogEntity, OperateLogForm, OperateLogVo> {
    /**
     * 实例
     **/
    OperateLogConvert INSTANCE = Mappers.getMapper(OperateLogConvert.class);
}