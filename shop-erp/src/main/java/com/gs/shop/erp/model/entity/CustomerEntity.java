package com.gs.shop.erp.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import com.gs.shop.erp.infrustructure.model.BaseEntity;


/**
 * 客户实体
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@TableName(value = "customer", autoResultMap = true)
@ApiModel(value = "CustomerEntity对象", description = "客户实体")
public class CustomerEntity extends BaseEntity {

    /**
     * 客户ID
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "客户ID", notes = "字符长度为：10")
    private Integer id;
    /**
     * 客户名称
     */
    @TableField(condition = SqlCondition.LIKE)
    @ApiModelProperty(value = "客户名称", notes = "字符长度为：30")
    private String name;
    /**
     * 客户性别
     */
    @ApiModelProperty(value = "客户性别", notes = "字符长度为：30")
    private Integer sex;
    /**
     * 客户联系电话
     */
    @ApiModelProperty(value = "客户联系电话", notes = "字符长度为：15")
    private String tel;
    /**
     * 客户生日
     */
    @ApiModelProperty(value = "客户生日", notes = "字符长度为：10")
    private LocalDate birthday;
    /**
     * 客户省份
     */
    @TableField(condition = SqlCondition.EQUAL)
    @ApiModelProperty(value = "客户省份", notes = "字符长度为：10")
    private String province;
    /**
     * 客户城市
     */
    @TableField(condition = SqlCondition.EQUAL)
    @ApiModelProperty(value = "客户城市", notes = "字符长度为：10")
    private String city;
    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址", notes = "字符长度为：100")
    private String address;
}
