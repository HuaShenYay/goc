<!--
  Created by IntelliJ WebStorm.
  User: Guo Shuai
  Date: 2024/09/26
  Time: 12:24
  To change this template use File | Settings | File Templates.
-->
<template>
  <div>
    <enhance-table :config="config"/>
  </div>
</template>

<script setup>
import EnhanceTable from "@/components/EnhanceTable.vue";
import {reactive} from "vue";
import {message} from "ant-design-vue";

const config = reactive({
  title: '入库',
  apiPath: 'purchase/sale/order',
  queryParams: {type: 1},
  keyField: 'id',
  columns: [
    {title: '编号', dataIndex: 'id'},
    {title: '供应商', dataIndex: 'supplierName'},
    {title: '数量', dataIndex: 'totalCount'},
    {title: '总金额(元)', dataIndex: 'totalAmount'},
    {title: '业务员', dataIndex: 'username'},
    {title: '时间', dataIndex: 'createTime'}
  ],
  queryConfig: [
    {
      label: '业务员',
      field: 'createUserId',
      type: 'select',
      keyField: 'id',
      valueField: 'name',
      apiPath: 'sys/user'
    },
    {
      label: '供应商',
      field: 'supplierId',
      type: 'select',
      keyField: 'id',
      valueField: 'name',
      apiPath: 'supplier'
    },
    {
      label: '日期',
      field: 'createDateRange',
      type: 'dateRange',
      startField: 'startDate',
      endField: 'endDate'
    }
  ],
  action: ['save', 'info'],
  callback: {
    beforeSubmit: (form) => {
      //校验数据是否为空
      if(!form.items.length) {
        message.warn('请添加商品数据！', 1)
        return false
      }
      form.items.forEach(item => {
        for (let key in item) {
          if(!item[key] && key !== 'action') {
            message.warn('请完整填写商品数据！', 1)
            return false
          }
        }
      })
      //设置默认值
      form.type = 1
      return true
    }
  },
  formRows: [
    [
      {
        label: '供应商',
        field: 'supplierId',
        type: 'select',
        required: true,
        keyField: 'id',
        valueField: 'name',
        optionApiPath: 'supplier'
      }
    ],
    [
      {
        label: '商品列表', field: 'items', type: 'table', columns: [
          {
            title: '商品',
            dataIndex: 'goodsId',
            type: 'select',
            required: true,
            keyField: 'id',
            valueField: 'name',
            optionApiPath: 'goods'
          },
          {
            title: '仓库',
            dataIndex: 'repositoryId',
            type: 'select',
            required: true,
            keyField: 'id',
            valueField: 'name',
            optionApiPath: 'repository'
          },
          {
            title: '进价',
            dataIndex: 'inPrice',
            type: 'numberInput',
            required: true,
            min: 0.01,
            width: 150
          },
          {
            title: '数量',
            dataIndex: 'count',
            type: 'numberInput',
            required: true,
            min: 1,
            width: 100
          }
        ]
      }
    ]
  ]
})

</script>

<style scoped lang="scss">

</style>
