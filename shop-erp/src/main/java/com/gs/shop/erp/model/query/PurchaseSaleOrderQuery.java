package com.gs.shop.erp.model.query;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.gs.shop.erp.convert.PurchaseSaleOrderConvert;
import com.gs.shop.erp.mapper.PurchaseSaleOrderMapper;
import com.gs.shop.erp.model.entity.OperateLogEntity;
import com.gs.shop.erp.model.entity.PurchaseSaleOrderEntity;
import cn.hutool.extra.spring.SpringUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gs.shop.erp.infrustructure.model.AbstractQuery;


/**
 * 进出库订单查询对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PurchaseSaleOrderQuery对象", description = "进出库订单查询对象")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseSaleOrderQuery extends AbstractQuery<PurchaseSaleOrderEntity> {
    /**
     * 类型：1进/2出
     */
    @ApiModelProperty(value = "类型：1进/2出")
    private Integer type;
    /**
     * 供应商
     */
    @ApiModelProperty(value = "供应商")
    private Integer supplierId;
    /**
     * 业务员
     */
    @ApiModelProperty(value = "业务员")
    private Integer createUserId;
    /**
     * 客户
     */
    @ApiModelProperty(value = "客户")
    private Integer customerId;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private String startDate;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private String endDate;

    /**
     * 构建查询对象
     *
     * @return com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper<com.gs.shop.erp.model.entity.PurchaseSaleOrderEntity>
     * @author Pursuer
     * @date 2024-09-24
     * @since 1.0
     **/
    @Override
    public LambdaQueryChainWrapper<PurchaseSaleOrderEntity> build() {
        return new LambdaQueryChainWrapper<>(SpringUtil.getBean(PurchaseSaleOrderMapper.class))
                .ge(StrUtil.isNotBlank(startDate), PurchaseSaleOrderEntity::getCreateTime, startDate)
                .le(StrUtil.isNotBlank(endDate), PurchaseSaleOrderEntity::getCreateTime, endDate + " 23:59:59")
                .setEntity(PurchaseSaleOrderConvert.INSTANCE.queryToEntity(this));
    }
}
