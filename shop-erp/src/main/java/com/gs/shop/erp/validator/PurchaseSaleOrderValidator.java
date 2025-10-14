package com.gs.shop.erp.validator;

import com.gs.shop.erp.model.form.PurchaseSaleHistoryForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 进出库订单校验类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Component
@RequiredArgsConstructor
public class PurchaseSaleOrderValidator extends BaseStockValidator<PurchaseSaleHistoryForm> {

}
