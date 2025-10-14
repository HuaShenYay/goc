package com.gs.shop.erp.model.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import com.gs.shop.erp.infrustructure.model.BaseEntity;


/**
 * 供货商实体
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@TableName(value = "supplier", autoResultMap = true)
@ApiModel(value = "SupplierEntity对象", description = "供货商实体")
public class SupplierEntity extends BaseEntity {

    /**
     * 供货商ID
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "供货商ID", notes = "字符长度为：10")
    private Integer id;
    /**
     * 供货商名称
     */
    @ApiModelProperty(value = "供货商名称", notes = "字符长度为：50")
    private String name;
    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人", notes = "字符长度为：30")
    private String contact;
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话", notes = "字符长度为：15")
    private String tel;
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
