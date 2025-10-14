package com.gs.shop.erp.model.vo;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import com.gs.shop.erp.infrustructure.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 仓库视图对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Data
@ToString(callSuper = true)
@ApiModel(value="RepositoryVo对象",description="仓库视图对象")
public class RepositoryVo extends BaseEntity{

	/**
	 * 仓库ID
	 */
	@ApiModelProperty(value="仓库ID", notes="字符长度为：10")
	private Integer id;
	/**
	 * 仓库名
	 */
	@ApiModelProperty(value="仓库名", notes="字符长度为：50")
	private String name;
	/**
	 * 省份
	 */
	@ApiModelProperty(value="省份", notes="字符长度为：10")
	private String province;
	/**
	 * 城市
	 */
	@ApiModelProperty(value="城市", notes="字符长度为：10")
	private String city;
	/**
	 * 详细地址
	 */
	@ApiModelProperty(value="详细地址", notes="字符长度为：100")
	private String address;

	/**
	 * 库存
	 */
	@ApiModelProperty(value = "库存")
	private List<RepositoryStockVo> stocks;
}
