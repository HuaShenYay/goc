package com.gs.shop.erp.validator;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.gs.shop.erp.mapper.RepositoryMapper;
import com.gs.shop.erp.mapper.RepositoryStockMapper;
import com.gs.shop.erp.model.entity.RepositoryEntity;
import com.gs.shop.erp.model.entity.RepositoryStockEntity;
import com.gs.shop.erp.model.form.RepositoryForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.List;

/**
 * 仓库校验类
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Component
@RequiredArgsConstructor
public class RepositoryValidator {

    private final RepositoryMapper mapper;
    private final RepositoryStockMapper repositoryStockMapper;

    /**
     * 校验唯一性
     *
     * @param form 表单数据
     * @return void
     * @author Pursuer
     * @date 2023/12/9
     * @since 1.0
     **/
    public void isUnique(RepositoryForm form) {
        Long count = new LambdaQueryChainWrapper<>(mapper)
                .eq(RepositoryEntity::getName, form.getName())
                .eq(RepositoryEntity::getProvince, form.getProvince())
                .eq(RepositoryEntity::getCity, form.getCity())
                .ne(ObjectUtil.isNotNull(form.getId()), RepositoryEntity::getId, form.getId())
                .count();
        Assert.isTrue(count == 0, "该城市已经存在该仓库了");
    }

    /**
     * 是否可删除
     *
     * @param ids ID集合
     * @return void
     * @author Pursuer
     * @date 2023/12/9
     * @since 1.0
     **/
    public void canRemove(List<? extends Serializable> ids) {
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        Long count = new LambdaQueryChainWrapper<>(repositoryStockMapper)
                .in(RepositoryStockEntity::getRepositoryId, ids)
                .count();
        Assert.isTrue(count == 0, "该仓库已经产生了商品库存！");
    }
}
