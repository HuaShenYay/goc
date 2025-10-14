package com.gs.shop.erp.model.query;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.gs.shop.erp.convert.SupplierConvert;
import com.gs.shop.erp.mapper.SupplierMapper;
import com.gs.shop.erp.model.entity.SupplierEntity;
import cn.hutool.extra.spring.SpringUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gs.shop.erp.infrustructure.model.AbstractQuery;


/**
 * 供货商查询对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "SupplierQuery对象", description = "供货商查询对象")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SupplierQuery extends AbstractQuery<SupplierEntity> {
    /**
     * 关键字
     */
    @ApiModelProperty(value = "关键字")
    private String keyword;
    /**
     * 省份
     */
    @ApiModelProperty(value = "省份")
    private String province;
    /**
     * 城市
     */
    @ApiModelProperty(value = "城市")
    private String city;

    /**
     * 构建查询对象
     *
     * @return com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper<com.gs.shop.erp.model.entity.SupplierEntity>
     * @author Pursuer
     * @date 2024-09-24
     * @since 1.0
     **/
    @Override
    public LambdaQueryChainWrapper<SupplierEntity> build() {
        return new LambdaQueryChainWrapper<>(SpringUtil.getBean(SupplierMapper.class))
                .setEntity(SupplierConvert.INSTANCE.queryToEntity(this))
                .and(StrUtil.isNotBlank(keyword), lambda -> {
                    lambda.like(SupplierEntity::getName, keyword)
                            .or()
                            .like(SupplierEntity::getContact, keyword);
                });
    }
}
