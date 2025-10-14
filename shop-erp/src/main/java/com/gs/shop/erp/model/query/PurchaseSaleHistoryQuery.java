package com.gs.shop.erp.model.query;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.gs.shop.erp.mapper.PurchaseSaleHistoryMapper;
import com.gs.shop.erp.model.entity.PurchaseSaleHistoryEntity;
import cn.hutool.extra.spring.SpringUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gs.shop.erp.infrustructure.model.AbstractQuery;
import lombok.experimental.Accessors;

import java.util.List;


/**
 * 入库/出库记录查询对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "PurchaseSaleHistoryQuery对象", description = "入库/出库记录查询对象")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseSaleHistoryQuery extends AbstractQuery<PurchaseSaleHistoryEntity> {
    /**
     * 订单ID集合
     */
    @ApiModelProperty(value = "订单ID集合")
    private List<Integer> orderIds;

    /**
     * 构建查询对象
     *
     * @return com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper<com.gs.shop.erp.model.entity.PurchaseSaleHistoryEntity>
     * @author Pursuer
     * @date 2024-09-24
     * @since 1.0
     **/
    @Override
    public LambdaQueryChainWrapper<PurchaseSaleHistoryEntity> build() {
        return new LambdaQueryChainWrapper<>(SpringUtil.getBean(PurchaseSaleHistoryMapper.class))
                .in(ObjectUtil.isNotEmpty(orderIds), PurchaseSaleHistoryEntity::getOrderId, orderIds);
    }
}
