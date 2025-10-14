package com.gs.shop.erp.infrustructure.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 通用Convert
 *
 * @author Pursuer
 * @version 1.0
 * @date 2023/11/15
 */
public interface BaseConvert<Entity, Form, Vo> {

    /**
     * Form转Entity
     *
     * @param form 表单
     * @return Entity 实体
     * @author Guo Shuai
     * @date 2022/9/3
     * @since 1.0
     **/
    Entity formToEntity(Form form);

    /**
     * FormList转换为EntityList
     *
     * @param forms 表单集合
     * @return java.util.List<Entity> 实体集合
     * @author Guo Shuai
     * @date 2022/9/3
     * @since 1.0
     **/
    List<Entity> formToEntity(List<Form> forms);

    /**
     * Entity转换为Vo
     *
     * @param entity 实体
     * @return Vo 视图
     * @author Guo Shuai
     * @date 2022/9/3
     * @since 1.0
     **/
    Vo entityToVo(Entity entity);

    /**
     * EntityList转换为VoList
     *
     * @param list 实体列表
     * @return java.util.List<Vo> 视图列表
     * @author Guo Shuai
     * @date 2022/9/3
     * @since 1.0
     **/
    List<Vo> entityToVo(List<Entity> list);

    /**
     * entityPage转换为voPage
     *
     * @param page 实体分页
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<Vo> 视图分页
     * @author Guo Shuai
     * @date 2022/9/3
     * @since 1.0
     **/
    Page<Vo> entityToVo(Page<Entity> page);
}
