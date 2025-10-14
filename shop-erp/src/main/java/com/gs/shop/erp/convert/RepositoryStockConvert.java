package com.gs.shop.erp.convert;

import com.gs.shop.erp.infrustructure.convert.BaseConvert;
import com.gs.shop.erp.model.entity.RepositoryStockEntity;
import com.gs.shop.erp.model.form.RepositoryStockForm;
import com.gs.shop.erp.model.vo.RepositoryStockVo;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;

/**
 * 仓库商品库存对象转换类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Mapper
public interface RepositoryStockConvert extends BaseConvert<RepositoryStockEntity, RepositoryStockForm, RepositoryStockVo> {
    /**
     * 实例
     **/
    RepositoryStockConvert INSTANCE = Mappers.getMapper(RepositoryStockConvert.class);
}