package com.gs.shop.erp.model.query;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.gs.shop.erp.convert.TransferHistoryConvert;
import com.gs.shop.erp.mapper.TransferHistoryMapper;
import com.gs.shop.erp.model.entity.TransferHistoryEntity;
import cn.hutool.extra.spring.SpringUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gs.shop.erp.infrustructure.model.AbstractQuery;


/**
 * 转仓记录查询对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "TransferHistoryQuery对象", description = "转仓记录查询对象")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferHistoryQuery extends AbstractQuery<TransferHistoryEntity> {
    /**
     * 源仓库ID
     */
    @ApiModelProperty(value = "源仓库ID", notes = "字符长度为：10")
    private Integer source;
    /**
     * 目标仓库ID
     */
    @ApiModelProperty(value = "目标仓库ID", notes = "字符长度为：10")
    private Integer target;
    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID", notes = "字符长度为：10")
    private Integer goodsId;

    /**
     * 构建查询对象
     *
     * @return com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper<com.gs.shop.erp.model.entity.TransferHistoryEntity>
     * @author Pursuer
     * @date 2024-09-24
     * @since 1.0
     **/
    @Override
    public LambdaQueryChainWrapper<TransferHistoryEntity> build() {
        return new LambdaQueryChainWrapper<>(SpringUtil.getBean(TransferHistoryMapper.class))
                .setEntity(TransferHistoryConvert.INSTANCE.queryToEntity(this));
    }
}
