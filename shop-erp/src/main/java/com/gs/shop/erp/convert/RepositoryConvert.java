package com.gs.shop.erp.convert;

import com.gs.shop.erp.infrustructure.convert.BaseConvert;
import com.gs.shop.erp.model.entity.RepositoryEntity;
import com.gs.shop.erp.model.form.RepositoryForm;
import com.gs.shop.erp.model.query.RepositoryQuery;
import com.gs.shop.erp.model.vo.RepositoryVo;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;

/**
 * 仓库对象转换类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Mapper
public interface RepositoryConvert extends BaseConvert<RepositoryEntity, RepositoryForm, RepositoryVo> {
    /**
     * 实例
     **/
    RepositoryConvert INSTANCE = Mappers.getMapper(RepositoryConvert.class);

    /**
     * Query转Entity
     *
     * @param query 查询对象
     * @return com.gs.shop.erp.model.entity.RepositoryEntity
     * @author Pursuer
     * @date 2023/12/11
     * @since 1.0
     **/
    RepositoryEntity queryToEntity(RepositoryQuery query);
}