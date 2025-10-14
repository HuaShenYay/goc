package com.gs.shop.erp.service;

import com.gs.shop.erp.mapper.PurchaseSaleHistoryMapper;
import com.gs.shop.erp.model.entity.PurchaseSaleHistoryEntity;
import com.gs.shop.erp.model.form.PurchaseSaleHistoryForm;
import com.gs.shop.erp.model.vo.PurchaseSaleHistoryVo;
import com.gs.shop.erp.model.query.PurchaseSaleHistoryQuery;
import com.gs.shop.erp.convert.PurchaseSaleHistoryConvert;
import lombok.RequiredArgsConstructor;
import com.gs.shop.erp.infrustructure.service.BaseService;
import com.gs.shop.erp.infrustructure.annotation.AutoService;

/**
 * 入库/出库记录业务操作实现类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@AutoService
@RequiredArgsConstructor
public class PurchaseSaleHistoryService extends BaseService<PurchaseSaleHistoryMapper, PurchaseSaleHistoryConvert ,PurchaseSaleHistoryEntity, PurchaseSaleHistoryQuery, PurchaseSaleHistoryForm, PurchaseSaleHistoryVo> {

}
