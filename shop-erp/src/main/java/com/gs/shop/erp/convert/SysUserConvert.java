package com.gs.shop.erp.convert;

import com.gs.shop.erp.infrustructure.convert.BaseConvert;
import com.gs.shop.erp.model.entity.SysUserEntity;
import com.gs.shop.erp.model.form.SysUserForm;
import com.gs.shop.erp.model.query.SysUserQuery;
import com.gs.shop.erp.model.vo.SysUserVo;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;

/**
 * 系统用户/业务员对象转换类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Mapper
public interface SysUserConvert extends BaseConvert<SysUserEntity, SysUserForm, SysUserVo> {
    /**
     * 实例
     **/
    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    /**
     * Query转Entity
     *
     * @param query 查询对象
     * @return com.gs.shop.erp.model.entity.SysUserEntity
     * @author Pursuer
     * @date 2023/12/11
     * @since 1.0
     **/
    SysUserEntity queryToEntity(SysUserQuery query);
}