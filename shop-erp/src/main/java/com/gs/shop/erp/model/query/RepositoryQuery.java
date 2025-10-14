package com.gs.shop.erp.model.query;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.gs.shop.erp.convert.RepositoryConvert;
import com.gs.shop.erp.mapper.RepositoryMapper;
import com.gs.shop.erp.model.entity.RepositoryEntity;
import cn.hutool.extra.spring.SpringUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gs.shop.erp.infrustructure.model.AbstractQuery;


/**
 * 仓库查询对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "RepositoryQuery对象", description = "仓库查询对象")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryQuery extends AbstractQuery<RepositoryEntity> {
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
     * @return com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper<com.gs.shop.erp.model.entity.RepositoryEntity>
     * @author Pursuer
     * @date 2024-09-24
     * @since 1.0
     **/
    @Override
    public LambdaQueryChainWrapper<RepositoryEntity> build() {
        return new LambdaQueryChainWrapper<>(SpringUtil.getBean(RepositoryMapper.class))
                .setEntity(RepositoryConvert.INSTANCE.queryToEntity(this));
    }
}
