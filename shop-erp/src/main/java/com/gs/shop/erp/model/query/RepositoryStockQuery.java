package com.gs.shop.erp.model.query;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.gs.shop.erp.mapper.RepositoryStockMapper;
import com.gs.shop.erp.model.entity.RepositoryStockEntity;
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
 * 仓库商品库存查询对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "RepositoryStockQuery对象", description = "仓库商品库存查询对象")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryStockQuery extends AbstractQuery<RepositoryStockEntity> {
    /**
     * 仓库ID集合
     */
    @ApiModelProperty(value = "仓库ID集合")
    private List<Integer> repositoryIds;

    /**
     * 构建查询对象
     *
     * @return com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper<com.gs.shop.erp.model.entity.RepositoryStockEntity>
     * @author Pursuer
     * @date 2024-09-24
     * @since 1.0
     **/
    @Override
    public LambdaQueryChainWrapper<RepositoryStockEntity> build() {
        return new LambdaQueryChainWrapper<>(SpringUtil.getBean(RepositoryStockMapper.class))
                .in(CollUtil.isNotEmpty(repositoryIds), RepositoryStockEntity::getRepositoryId, repositoryIds);
    }
}
