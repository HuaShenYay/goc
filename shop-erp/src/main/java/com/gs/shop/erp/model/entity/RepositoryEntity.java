package com.gs.shop.erp.model.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import com.gs.shop.erp.infrustructure.model.BaseEntity;


/**
 * 仓库实体
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@TableName(value = "repository", autoResultMap = true)
@ApiModel(value = "RepositoryEntity对象", description = "仓库实体")
public class RepositoryEntity extends BaseEntity {

    /**
     * 仓库ID
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "仓库ID", notes = "字符长度为：10")
    private Integer id;
    /**
     * 仓库名
     */
    @TableField(condition = SqlCondition.LIKE)
    @ApiModelProperty(value = "仓库名", notes = "字符长度为：50")
    private String name;
    /**
     * 省份
     */
    @TableField(condition = SqlCondition.EQUAL)
    @ApiModelProperty(value = "省份", notes = "字符长度为：10")
    private String province;
    /**
     * 城市
     */
    @TableField(condition = SqlCondition.EQUAL)
    @ApiModelProperty(value = "城市", notes = "字符长度为：10")
    private String city;
    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址", notes = "字符长度为：100")
    private String address;
}
