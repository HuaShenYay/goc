package com.gs.shop.erp.infrustructure.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 基础分页请求对象
 *
 * @author Pursuer
 * @version 1.0
 * @date 2023/11/15
 */
@Data
@ApiModel(value = "基础分页请求对象")
public class BasePageQuery {
    /**
     * 页码
     */
    @ApiModelProperty(value = "页码", example = "1")
    private int page = 1;
    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数", example = "10")
    private int limit = 10;
}
