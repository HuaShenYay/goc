package com.gs.shop.erp.convert;

import com.gs.shop.erp.infrustructure.convert.BaseConvert;
import com.gs.shop.erp.model.entity.GoodsEntity;
import com.gs.shop.erp.model.entity.GoodsEntity;
import com.gs.shop.erp.model.form.GoodsForm;
import com.gs.shop.erp.model.query.GoodsQuery;
import com.gs.shop.erp.model.vo.GoodsVo;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;

/**
 * 商品对象转换类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Mapper
public interface GoodsConvert extends BaseConvert<GoodsEntity, GoodsForm, GoodsVo> {
    /**
     * 实例
     **/
    GoodsConvert INSTANCE = Mappers.getMapper(GoodsConvert.class);

    /**
     * Query转Entity
     *
     * @param query 查询对象
     * @return com.gs.shop.erp.model.entity.GoodsEntity
     * @author Pursuer
     * @date 2023/12/11
     * @since 1.0
     **/
    GoodsEntity queryToEntity(GoodsQuery query);
}