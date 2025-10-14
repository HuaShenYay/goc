package com.gs.shop.erp.model.query;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.gs.shop.erp.convert.SysUserConvert;
import com.gs.shop.erp.mapper.SysUserMapper;
import com.gs.shop.erp.model.entity.SysUserEntity;
import cn.hutool.extra.spring.SpringUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gs.shop.erp.infrustructure.model.AbstractQuery;


/**
 * 系统用户/业务员查询对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "SysUserQuery对象", description = "系统用户/业务员查询对象")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysUserQuery extends AbstractQuery<SysUserEntity> {
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private Integer sex;

    /**
     * 构建查询对象
     *
     * @return com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper<com.gs.shop.erp.model.entity.SysUserEntity>
     * @author Pursuer
     * @date 2024-09-24
     * @since 1.0
     **/
    @Override
    public LambdaQueryChainWrapper<SysUserEntity> build() {
        return new LambdaQueryChainWrapper<>(SpringUtil.getBean(SysUserMapper.class))
                .setEntity(SysUserConvert.INSTANCE.queryToEntity(this));
    }
}
