package com.gs.shop.erp.model.query;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.gs.shop.erp.constant.enums.MethodsEnum;
import com.gs.shop.erp.mapper.OperateLogMapper;
import com.gs.shop.erp.model.entity.OperateLogEntity;
import cn.hutool.extra.spring.SpringUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gs.shop.erp.infrustructure.model.AbstractQuery;

import java.util.Arrays;


/**
 * 操作日志查询对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "OperateLogQuery对象", description = "操作日志查询对象")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperateLogQuery extends AbstractQuery<OperateLogEntity> {

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private String startDate;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private String endDate;
    /**
     * 请求方式
     */
    @ApiModelProperty(value = "请求方式")
    private String requestType;
    /**
     * 操作用户
     */
    @ApiModelProperty(value = "操作用户")
    private String username;

    /**
     * 构建查询对象
     *
     * @return com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper<com.gs.shop.erp.model.entity.OperateLogEntity>
     * @author Pursuer
     * @date 2024-09-24
     * @since 1.0
     **/
    @Override
    public LambdaQueryChainWrapper<OperateLogEntity> build() {
        Object[] requestTypeArray = null;
        if (StrUtil.isNotBlank(requestType)) {
            requestTypeArray = Arrays.stream(requestType.split(StrUtil.COMMA)).toArray();
        }
        return new LambdaQueryChainWrapper<>(SpringUtil.getBean(OperateLogMapper.class))
                .ge(StrUtil.isNotBlank(startDate), OperateLogEntity::getInsertDate, startDate)
                .le(StrUtil.isNotBlank(endDate), OperateLogEntity::getInsertDate, endDate + " 23:59:59")
                .like(StrUtil.isNotBlank(username), OperateLogEntity::getUsername, username)
                .in(ObjectUtil.isNotNull(requestTypeArray), OperateLogEntity::getMethod, requestTypeArray)
                .orderByDesc(OperateLogEntity::getInsertDate);
    }
}
