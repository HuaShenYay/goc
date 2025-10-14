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
 * 系统用户/业务员实体
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@TableName(value = "sys_user", autoResultMap = true)
@ApiModel(value="SysUserEntity对象",description="系统用户/业务员实体")
public class SysUserEntity extends BaseEntity {

	/**
	 * 业务员ID
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value="业务员ID", notes="字符长度为：10")
	private Integer id;
	/**
	 * 姓名
	 */
	@TableField(condition = SqlCondition.LIKE)
	@ApiModelProperty(value="姓名", notes="字符长度为：30")
	private String name;
	/**
	 * 性别 1男/2女
	 */
	@TableField(condition = SqlCondition.EQUAL)
	@ApiModelProperty(value="性别 1男/2女", notes="字符长度为：10")
	private Integer sex;
	/**
	 * 出生日期
	 */
	@ApiModelProperty(value="出生日期", notes="字符长度为：10")
	private LocalDate birthday;
	/**
	 * 手机号
	 */
	@ApiModelProperty(value="手机号", notes="字符长度为：11")
	private String phone;
	/**
	 * 密码
	 */
	@ApiModelProperty(value="密码", notes="字符长度为：100")
	private String password;
	/**
	 * 是否系统用户
	 */
	@ApiModelProperty(value="是否系统用户", notes="字符长度为：3")
	private Boolean isSystem;
}
