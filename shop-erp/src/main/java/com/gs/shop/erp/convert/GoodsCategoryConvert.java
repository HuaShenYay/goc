package com.gs.shop.erp.convert;

import com.gs.shop.erp.infrustructure.convert.BaseConvert;
import com.gs.shop.erp.model.entity.GoodsCategoryEntity;
import com.gs.shop.erp.model.form.GoodsCategoryForm;
import com.gs.shop.erp.model.vo.GoodsCategoryVo;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;

/**
 * 商品类别对象转换类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Mapper
public interface GoodsCategoryConvert extends BaseConvert<GoodsCategoryEntity, GoodsCategoryForm, GoodsCategoryVo> {
    /**
     * 实例
     **/
    GoodsCategoryConvert INSTANCE = Mappers.getMapper(GoodsCategoryConvert.class);
}