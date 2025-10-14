package com.gs.shop.erp.convert;

import com.gs.shop.erp.infrustructure.convert.BaseConvert;
import com.gs.shop.erp.model.entity.TransferHistoryEntity;
import com.gs.shop.erp.model.form.TransferHistoryForm;
import com.gs.shop.erp.model.query.TransferHistoryQuery;
import com.gs.shop.erp.model.vo.TransferHistoryVo;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;

/**
 * 转仓记录对象转换类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Mapper
public interface TransferHistoryConvert extends BaseConvert<TransferHistoryEntity, TransferHistoryForm, TransferHistoryVo> {
    /**
     * 实例
     **/
    TransferHistoryConvert INSTANCE = Mappers.getMapper(TransferHistoryConvert.class);

    /**
     * Query转Entity
     *
     * @param query 查询对象
     * @return com.gs.shop.erp.model.entity.TransferHistoryEntity
     * @author Pursuer
     * @date 2023/12/11
     * @since 1.0
     **/
    TransferHistoryEntity queryToEntity(TransferHistoryQuery query);
}