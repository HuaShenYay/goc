package com.gs.shop.erp.model.query;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.gs.shop.erp.convert.GoodsConvert;
import com.gs.shop.erp.mapper.GoodsMapper;
import com.gs.shop.erp.model.entity.GoodsEntity;
import cn.hutool.extra.spring.SpringUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gs.shop.erp.infrustructure.model.AbstractQuery;


/**
 * 商品查询对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "GoodsQuery对象", description = "商品查询对象")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoodsQuery extends AbstractQuery<GoodsEntity> {
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 类别
     */
    @ApiModelProperty(value = "类别")
    private Integer categoryId;

    /**
     * 构建查询对象
     *
     * @return com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper<com.gs.shop.erp.model.entity.GoodsEntity>
     * @author Pursuer
     * @date 2024-09-24
     * @since 1.0
     **/
    @Override
    public LambdaQueryChainWrapper<GoodsEntity> build() {
        return new LambdaQueryChainWrapper<>(SpringUtil.getBean(GoodsMapper.class))
                .setEntity(GoodsConvert.INSTANCE.queryToEntity(this));
    }
}
