package com.gs.shop.erp.infrustructure.api;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.shop.erp.infrustructure.convert.BaseConvert;
import com.gs.shop.erp.infrustructure.model.AbstractQuery;
import com.gs.shop.erp.infrustructure.result.PageResult;
import com.gs.shop.erp.infrustructure.result.Result;
import com.gs.shop.erp.infrustructure.service.BaseService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 抽象api
 *
 * @author Guo Shuai
 * @version 1.0
 * @date 2023-11-15
 */
public class BaseApi<S extends BaseService<M, C, T, Q, F, V>, M extends BaseMapper<T>, C extends BaseConvert<T, F, V>, T, Q extends AbstractQuery<T>, F, V> {

    protected S baseService;

    @ApiOperation(value = "分页查询{}列表")
    @GetMapping("/page")
    public PageResult<V> findPage(Q query) {
        return PageResult.success(baseService.findPage(query));
    }

    @ApiOperation(value = "查询{}列表")
    @GetMapping
    public Result<List<V>> findList(Q query) {
        return Result.success(baseService.findList(query));
    }

    @ApiOperation(value = "根据主键ID查询{}详情")
    @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path", dataType = "String")
    @GetMapping("/{id}")
    public Result<V> findById(@PathVariable String id) {
        return Result.success(baseService.findById(id));
    }

    @ApiOperation(value = "新增{}信息")
    @PostMapping
    public Result<Object> saveOne(@Validated @RequestBody F entity) {
        boolean status = baseService.saveOne(entity);
        return Result.judge(status);
    }

    @ApiOperation(value = "修改{}信息")
    @PutMapping
    public Result<Object> modify(@Validated @RequestBody F entity) {
        boolean status = baseService.modify(entity);
        return Result.judge(status);
    }

    @ApiOperation(value = "根据ID数组逻辑删除{}信息")
    @DeleteMapping
    public Result<Boolean> removeByIds(@RequestBody List<String> ids) {
        boolean status = baseService.removeByIds(ids);
        return Result.judge(status);
    }

    @PostConstruct
    @SuppressWarnings("all")
    public void afterPropertiesSet() throws Exception {
        //初始化实例
        baseService = (S) SpringUtil.getBean(ClassUtil.getTypeArgument(this.getClass(), 0));
    }
}