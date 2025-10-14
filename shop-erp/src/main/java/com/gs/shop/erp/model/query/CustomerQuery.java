package com.gs.shop.erp.model.query;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.gs.shop.erp.convert.CustomerConvert;
import com.gs.shop.erp.mapper.CustomerMapper;
import com.gs.shop.erp.model.entity.CustomerEntity;
import cn.hutool.extra.spring.SpringUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gs.shop.erp.infrustructure.model.AbstractQuery;


/**
 * 客户查询对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "CustomerQuery对象", description = "客户查询对象")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerQuery extends AbstractQuery<CustomerEntity> {
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
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
     * @return com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper<com.gs.shop.erp.model.entity.CustomerEntity>
     * @author Pursuer
     * @date 2024-09-24
     * @since 1.0
     **/
    @Override
    public LambdaQueryChainWrapper<CustomerEntity> build() {
        return new LambdaQueryChainWrapper<>(SpringUtil.getBean(CustomerMapper.class))
                .setEntity(CustomerConvert.INSTANCE.queryToEntity(this));
    }
}
