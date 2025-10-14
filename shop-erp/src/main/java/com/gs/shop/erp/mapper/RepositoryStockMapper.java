package com.gs.shop.erp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.gs.shop.erp.model.entity.RepositoryStockEntity;

/**
 * 仓库商品库存数据库操作接口
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Mapper
public interface RepositoryStockMapper extends BaseMapper<RepositoryStockEntity> {

}
