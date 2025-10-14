package com.gs.shop.erp.model.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


/**
 * 操作日志实体
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@TableName(value = "operate_log", autoResultMap = true)
@ApiModel(value = "OperateLogEntity对象", description = "操作日志实体")
public class OperateLogEntity {

    /**
     * ID主键
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "ID主键", notes = "字符长度为：19")
    private Long id;
    /**
     * referrer地址
     */
    @ApiModelProperty(value = "referrer地址", notes = "字符长度为：255")
    private String referrer;
    /**
     * 客户端地址
     */
    @ApiModelProperty(value = "客户端地址", notes = "字符长度为：255")
    private String remoteAddress;
    /**
     *
     */
    @ApiModelProperty(value = "", notes = "字符长度为：10")
    private String method;
    /**
     * 接口地址
     */
    @ApiModelProperty(value = "接口地址", notes = "字符长度为：500")
    private String requestUrl;
    /**
     * 操作名称
     */
    @ApiModelProperty(value = "操作名称", notes = "字符长度为：50")
    private String actionName;
    /**
     * 请求参数
     */
    @ApiModelProperty(value = "请求参数", notes = "字符长度为：1,073,741,824")
    private String requestParam;
    /**
     * 客户agent
     */
    @ApiModelProperty(value = "客户agent", notes = "字符长度为：500")
    private String userAgent;
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号", notes = "字符长度为：10")
    private Integer userId;
    /**
     * 用户手机号
     */
    @ApiModelProperty(value = "用户手机号", notes = "字符长度为：30")
    private String phone;
    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名", notes = "字符长度为：30")
    private String username;
    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间", notes = "字符长度为：19")
    private LocalDateTime insertDate;
}
