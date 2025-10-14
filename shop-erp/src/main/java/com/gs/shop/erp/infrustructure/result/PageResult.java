package com.gs.shop.erp.infrustructure.result;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.List;

/**
 * 分页响应
 *
 * @author Guo Shuai
 * @version 1.0
 * @date 2023-11-15
 */
public class PageResult<T> extends Result<List<T>> implements Serializable {

    public static <T> PageResult<T> success(IPage<T> page) {
        PageResult<T> result = new PageResult<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setData(page.getRecords());
        result.setTotal(page.getTotal());
        result.setMsg(ResultCode.SUCCESS.getMsg());
        return result;
    }
}
