package com.gs.shop.erp.model.query;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.gs.shop.erp.mapper.GoodsCategoryMapper;
import com.gs.shop.erp.model.entity.GoodsCategoryEntity;
import cn.hutool.extra.spring.SpringUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gs.shop.erp.infrustructure.model.AbstractQuery;


/**
 * 商品类别查询对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "GoodsCategoryQuery对象", description = "商品类别查询对象")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoodsCategoryQuery extends AbstractQuery<GoodsCategoryEntity> {
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 构建查询对象
     *
     * @return com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper<com.gs.shop.erp.model.entity.GoodsCategoryEntity>
     * @author Pursuer
     * @date 2024-09-24
     * @since 1.0
     **/
    @Override
    public LambdaQueryChainWrapper<GoodsCategoryEntity> build() {
        return new LambdaQueryChainWrapper<>(SpringUtil.getBean(GoodsCategoryMapper.class))
                .like(StrUtil.isNotBlank(name), GoodsCategoryEntity::getName, name);
    }
}
