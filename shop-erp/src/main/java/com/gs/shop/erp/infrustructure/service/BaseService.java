package com.gs.shop.erp.infrustructure.service;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.shop.erp.infrustructure.util.QueryUtil;
import com.gs.shop.erp.infrustructure.annotation.AutoService;
import com.gs.shop.erp.infrustructure.convert.BaseConvert;
import com.gs.shop.erp.infrustructure.model.AbstractQuery;
import com.gs.shop.erp.infrustructure.util.ParamValidator;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;


/**
 * 通用服务
 *
 * @author Guo Shuai
 * @version 1.0
 * @date 2023-11-15
 */
@Slf4j
public class BaseService<M extends BaseMapper<T>, C extends BaseConvert<T, F, V>, T, Q extends AbstractQuery<T>, F, V> extends ServiceImpl<M, T> implements InitializingBean {

    protected C convert;

    /**
     * 分页查询
     *
     * @param query 查询对象
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<V>
     * @author Guo Shuai
     * @date 2023/11/15
     * @since 1.0
     **/
    public Page<V> findPage(Q query) {
        // 验证查询参数
        ParamValidator.notNull(query, "查询参数");
        
        //查询前置
        if (!beforePage(query)) {
            return new Page<>();
        }
        //定义分页对象
        Page<T> pageQuery = new Page<>(query.getPage(), query.getLimit());
        //查询结果
        Page<T> result = reflectQuery((baseService) -> {
            return ReflectUtil.invoke(this.baseMapper, baseService.customPageQueryMethod(), pageQuery, query);
        }, () -> {
            return QueryUtil.build(query).page(pageQuery);
        });
        //转换结果
        Page<V> voPage = convert.entityToVo(result);
        //后置
        postPage(voPage, query);
        //返回结果
        return voPage;
    }

    /**
     * 列表查询
     *
     * @param query 查询对象
     * @return java.util.List<V>
     * @author Guo Shuai
     * @date 2023/11/15
     * @since 1.0
     **/
    public List<V> findList(Q query) {
        // 验证查询参数
        ParamValidator.notNull(query, "查询参数");
        
        //前置
        if (!beforeList(query)) {
            return new ArrayList<>();
        }
        //查询结果
        List<T> result = reflectQuery((yuanManService) -> {
            return ReflectUtil.invoke(this.baseMapper, yuanManService.customListQueryMethod(), query);
        }, QueryUtil.build(query)::list);
        //转换结果
        List<V> voList = convert.entityToVo(result);
        //后置
        postList(voList, query);
        //返回结果
        return voList;
    }

    /**
     * 保存
     *
     * @param form 表单数据
     * @return boolean
     * @author Guo Shuai
     * @date 2023/11/15
     * @since 1.0
     **/
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOne(F form) {
        // 验证表单参数
        ParamValidator.notNull(form, "表单数据");
        
        //转换form对象为entity对象
        T entity = convert.formToEntity(form);
        //前置
        if (!beforeSave(form, entity)) {
            return Boolean.FALSE;
        }
        //入库
        boolean status = super.save(entity);
        //后置
        postSave(form, entity, status);
        //保存
        return status;
    }

    /**
     * 修改
     *
     * @param form 表单数据
     * @return boolean
     * @author Guo Shuai
     * @date 2023/11/15
     * @since 1.0
     **/
    @Transactional(rollbackFor = Exception.class)
    public boolean modify(F form) {
        // 验证表单参数
        ParamValidator.notNull(form, "表单数据");
        
        //转换form对象为entity对象
        T entity = convert.formToEntity(form);
        //前置
        if (!beforeModify(form, entity)) {
            return Boolean.FALSE;
        }
        //更新
        boolean status = super.updateById(entity);
        //后置
        postModify(form, entity, status);
        //返回
        return status;
    }

    /**
     * 根据ID查询
     *
     * @param id 主键
     * @return V
     * @author Guo Shuai
     * @date 2023/11/15
     * @since 1.0
     **/
    public V findById(Serializable id) {
        // 验证ID参数
        ParamValidator.notNull(id, "ID");
        
        T entity = super.getById(id);
        //判断是否为空
        if (ObjectUtil.isNull(entity)) {
            //定义断言条件
            boolean result = true;
            //获取注解
            AutoService yuanManService = AnnotationUtil.getAnnotation(this.getClass(), AutoService.class);
            //判断是否为空
            if (ObjectUtil.isNotNull(yuanManService)) {
                result = yuanManService.notFoundThrowException();
            }
            //判断
            Assert.isFalse(result, "记录未找到");
            //返回Null
            return null;
        }
        //转换
        V vo = convert.entityToVo(entity);
        //后置
        postFindById(vo);
        return vo;
    }

    /**
     * 根据id集合删除
     *
     * @param ids id集合
     * @return boolean
     * @author Guo Shuai
     * @date 2023/11/15
     * @since 1.0
     **/
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(List<? extends Serializable> ids) {
        // 验证ID集合参数
        ParamValidator.notEmpty(ids, "ID集合");
        
        //前置
        if (!beforeRemove(ids)) {
            return Boolean.FALSE;
        }
        //转换
        List<Object> idList = new ArrayList<>(ids.size());
        //获取转换方法
        Function<String, ? extends Serializable> keyType = this.convertKeyType();
        ids.forEach(id -> idList.add(keyType.apply((String) id)));
        //删除
        boolean status = super.removeByIds(idList);
        //后置
        postRemove(ids, status);
        //返回
        return status;
    }

    /**
     * 分页查询前
     *
     * @param query 查询对象
     * @author Guo Shuai
     * @date 2023/11/15
     * @since 1.0
     **/
    protected boolean beforePage(Q query) {
        return Boolean.TRUE;
    }

    /**
     * 分页查询后
     *
     * @param page  查询结果
     * @param query 查询对象
     * @author Guo Shuai
     * @date 2023/11/15
     * @since 1.0
     **/
    protected void postPage(Page<V> page, Q query) {
    }

    /**
     * 列表查询前
     *
     * @param query 查询对象
     * @author Guo Shuai
     * @date 2023/11/15
     * @since 1.0
     **/
    protected boolean beforeList(Q query) {
        return Boolean.TRUE;
    }

    /**
     * 列表查询后
     *
     * @param dataList 数据集合
     * @param query    查询对象
     * @author Guo Shuai
     * @date 2023/11/15
     * @since 1.0
     **/
    protected void postList(List<V> dataList, Q query) {
    }

    /**
     * 新增前
     *
     * @param form   表单数据
     * @param entity 实体
     * @author Guo Shuai
     * @date 2023/11/15
     * @since 1.0
     **/
    protected boolean beforeSave(F form, T entity) {
        return Boolean.TRUE;
    }

    /**
     * 新增后
     *
     * @param form   数据表单
     * @param entity 数据实体
     * @param status 执行结果
     * @author Guo Shuai
     * @date 2023/11/15
     * @since 1.0
     **/
    protected void postSave(F form, T entity, boolean status) {
    }

    /**
     * 更新前
     *
     * @param form   表单数据
     * @param entity 实体
     * @author Guo Shuai
     * @date 2023/11/15
     * @since 1.0
     **/
    protected boolean beforeModify(F form, T entity) {
        return Boolean.TRUE;
    }

    /**
     * 更新后
     *
     * @param form   数据表单
     * @param entity 数据实体
     * @param status 执行结果
     * @author Guo Shuai
     * @date 2023/11/15
     * @since 1.0
     **/
    protected void postModify(F form, T entity, boolean status) {
    }

    /**
     * 根据ID查询后
     *
     * @param v 数据
     * @author Guo Shuai
     * @date 2023/11/15
     * @since 1.0
     **/
    protected void postFindById(V v) {
    }

    /**
     * 删除前
     *
     * @param ids id集合
     * @author Guo Shuai
     * @date 2023/11/15
     * @since 1.0
     **/
    protected boolean beforeRemove(List<? extends Serializable> ids) {
        return Boolean.TRUE;
    }

    /**
     * 删除后
     *
     * @param ids    id集合
     * @param status 删除结果
     * @author Guo Shuai
     * @date 2023/11/15
     * @since 1.0
     **/
    protected void postRemove(List<? extends Serializable> ids, boolean status) {
    }

    /**
     * 反射查询
     *
     * @param custom 自定义
     * @param system 系统
     * @return R
     * @author Guo Shuai
     * @date 2023/11/15
     * @since 1.0
     **/
    private <R> R reflectQuery(Function<AutoService, R> custom, Callable<R> system) {
        //定义返回值
        R result = null;
        //获取注解
        AutoService autoService = AnnotationUtil.getAnnotation(this.getClass(), AutoService.class);
        //判断是否是自定义查询
        if (ObjectUtil.isNotNull(autoService) && StrUtil.isNotBlank(autoService.customPageQueryMethod())) {
            try {
                result = custom.apply(autoService);
            } catch (Exception e) {
                //获取失败原因
                InvocationTargetException exception = (InvocationTargetException) e.getCause();
                String message = exception.getTargetException().getMessage();
                log.error("方法调用失败，原因：{}", message);
                //抛出异常
                Assert.isTrue(Boolean.FALSE, message);
            }
        } else {
            try {
                result = system.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //返回
        return result;
    }

    /**
     * 转换主键类型
     *
     * @return java.util.function.Function<java.util.List < ? extends java.io.Serializable>,java.util.List<? extends java.io.Serializable>>
     * @author Guo Shuai
     * @date 2023/11/15
     * @since 1.0
     **/
    protected Function<String, ? extends Serializable> convertKeyType() {
        return String::valueOf;
    }

    @SuppressWarnings("all")
    @Override
    public void afterPropertiesSet() throws Exception {
        convert = (C) Mappers.getMapper(ClassUtil.getTypeArgument(this.getClass(), 1));
    }
}