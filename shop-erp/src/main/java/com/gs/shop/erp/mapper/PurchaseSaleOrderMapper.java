package com.gs.shop.erp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.shop.erp.model.entity.PurchaseSaleCountEntity;
import org.apache.ibatis.annotations.Mapper;
import com.gs.shop.erp.model.entity.PurchaseSaleOrderEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 进出库订单数据库操作接口
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Mapper
public interface PurchaseSaleOrderMapper extends BaseMapper<PurchaseSaleOrderEntity> {

    /**
     * 查询进销存统计
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return java.util.List<com.gs.shop.erp.model.entity.PurchaseSaleCountEntity>
     * @author Pursuer
     * @date 2023/12/10
     * @since 1.0
     **/
    List<PurchaseSaleCountEntity> selectPurchaseSaleCount(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
