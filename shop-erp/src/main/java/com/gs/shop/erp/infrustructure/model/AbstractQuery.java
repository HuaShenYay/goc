package com.gs.shop.erp.infrustructure.model;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;

/**
 * 抽象查询对象
 *
 * @author Guo Shuai
 * @version 1.0
 * @date 2023-11-15
 */
public abstract class AbstractQuery<T> extends BasePageQuery {
    /**
     * 构建查询对象
     *
     * @return com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper<T>
     * @author Guo Shuai
     * @date 2022/12/24
     * @since 1.0
     **/
    public abstract LambdaQueryChainWrapper<T> build();
}
