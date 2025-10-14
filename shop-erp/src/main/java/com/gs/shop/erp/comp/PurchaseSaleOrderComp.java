package com.gs.shop.erp.comp;

import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Opt;
import cn.hutool.core.util.ObjectUtil;
import com.gs.shop.erp.mapper.CustomerMapper;
import com.gs.shop.erp.mapper.SupplierMapper;
import com.gs.shop.erp.mapper.SysUserMapper;
import com.gs.shop.erp.model.entity.*;
import com.gs.shop.erp.model.query.PurchaseSaleHistoryQuery;
import com.gs.shop.erp.model.vo.GoodsVo;
import com.gs.shop.erp.model.vo.PurchaseSaleHistoryVo;
import com.gs.shop.erp.model.vo.PurchaseSaleOrderVo;
import com.gs.shop.erp.service.PurchaseSaleHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 进出库订单拓展字段填充
 *
 * @author Pursuer
 * @version 1.0
 * @date 2024-09-24
 */
@Component
@RequiredArgsConstructor
public class PurchaseSaleOrderComp {

    private final CustomerMapper customerMapper;
    private final SupplierMapper supplierMapper;
    private final SysUserMapper sysUserMapper;
    private final PurchaseSaleHistoryService purchaseSaleHistoryService;

    /**
     * 填充拓展信息
     *
     * @param dataList 数据集合
     * @return void
     * @author Pursuer
     * @date 2023/12/10
     * @since 1.0
     **/
    public void appendInfo(List<PurchaseSaleOrderVo> dataList) {
        if (CollUtil.isEmpty(dataList)) {
            return;
        }
        //填充客户
        appendCustomer(dataList);
        //填充供应商
        appendSupplier(dataList);
        //填充用户
        appendUser(dataList);
    }

    /**
     * 填充用户
     *
     * @param dataList 数据集合
     * @return void
     * @author Pursuer
     * @date 2023/12/10
     * @since 1.0
     **/
    public void appendUser(List<PurchaseSaleOrderVo> dataList) {
        //提取用户ID
        List<Integer> userIds = dataList.stream().map(PurchaseSaleOrderVo::getCreateUserId).filter(ObjectUtil::isNotNull).collect(Collectors.toList());
        //判断是否为空
        if (CollUtil.isEmpty(userIds)) {
            return;
        }
        //查询用户信息
        List<SysUserEntity> userList = sysUserMapper.selectBatchIds(userIds);
        //转换为Map集合
        Map<Integer, String> userMap = CollStreamUtil.toMap(userList, SysUserEntity::getId, SysUserEntity::getName);
        //填充数据
        dataList.forEach(data -> {
            data.setUsername(userMap.get(data.getCreateUserId()));
        });
    }

    /**
     * 填充客户
     *
     * @param dataList 数据集合
     * @return void
     * @author Pursuer
     * @date 2023/12/10
     * @since 1.0
     **/
    public void appendSupplier(List<PurchaseSaleOrderVo> dataList) {
        //提取供应商ID
        List<Integer> supplierIds = dataList.stream().map(PurchaseSaleOrderVo::getSupplierId).filter(ObjectUtil::isNotNull).collect(Collectors.toList());
        //判断是否为空
        if (CollUtil.isEmpty(supplierIds)) {
            return;
        }
        //查询供应商信息
        List<SupplierEntity> supplierList = supplierMapper.selectBatchIds(supplierIds);
        //转换为Map集合
        Map<Integer, String> supplierMap = CollStreamUtil.toMap(supplierList, SupplierEntity::getId, SupplierEntity::getName);
        //填充数据
        dataList.forEach(data -> {
            data.setSupplierName(supplierMap.get(data.getSupplierId()));
        });
    }

    /**
     * 填充客户
     *
     * @param dataList 数据集合
     * @return void
     * @author Pursuer
     * @date 2023/12/10
     * @since 1.0
     **/
    public void appendCustomer(List<PurchaseSaleOrderVo> dataList) {
        //提取客户ID
        List<Integer> customerIds = dataList.stream().map(PurchaseSaleOrderVo::getCustomerId).filter(ObjectUtil::isNotNull).collect(Collectors.toList());
        //判断是否为空
        if (CollUtil.isEmpty(customerIds)) {
            return;
        }
        //查询客户信息
        List<CustomerEntity> customerList = customerMapper.selectBatchIds(customerIds);
        //转换为Map集合
        Map<Integer, String> customerMap = CollStreamUtil.toMap(customerList, CustomerEntity::getId, CustomerEntity::getName);
        //填充数据
        dataList.forEach(data -> {
            data.setCustomerName(customerMap.get(data.getCustomerId()));
        });
    }

    /**
     * 填充历史
     *
     * @param dataList 数据集合
     * @return void
     * @author Pursuer
     * @date 2023/12/10
     * @since 1.0
     **/
    public void appendHistory(List<PurchaseSaleOrderVo> dataList) {
        if (CollUtil.isEmpty(dataList)) {
            return;
        }
        //提取ID
        List<Integer> ids = CollStreamUtil.toList(dataList, PurchaseSaleOrderVo::getId);
        //查询详情
        List<PurchaseSaleHistoryVo> histories = purchaseSaleHistoryService.findList(new PurchaseSaleHistoryQuery().setOrderIds(ids));
        //分组
        Map<Integer, List<PurchaseSaleHistoryVo>> historyMap = CollStreamUtil.groupByKey(histories, PurchaseSaleHistoryVo::getOrderId);
        //设置数据
        dataList.forEach(data -> {
            data.setItems(Opt.ofNullable(historyMap.get(data.getId())).orElse(CollUtil.toList()));
        });
    }
}
