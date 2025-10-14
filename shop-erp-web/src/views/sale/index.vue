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
import {getStockOptions} from "@/api/reposotiry.js";

const stockMap = new Map()

const config = reactive({
  title: '出库',
  apiPath: 'purchase/sale/order',
  queryParams: {type: 2},
  keyField: 'id',
  columns: [
    {title: '编号', dataIndex: 'id'},
    {title: '客户', dataIndex: 'customerName'},
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
      label: '客户',
      field: 'customerId',
      type: 'select',
      keyField: 'id',
      valueField: 'name',
      apiPath: 'customer'
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
      if (!form.items.length) {
        message.warn('请添加商品数据！', 1)
        return false
      }
      try {
        form.items.forEach(item => {
          for (let key in item) {
            if (!item[key] && key !== 'action') {
              throw new Error('请完整填写商品数据！')
            }
          }
          //设置属性
          const stock = stockMap.get(item.goods[1])
          item.repositoryId = stock.repositoryId
          item.inPrice = stock.inPrice
          item.goodsId = stock.goodsId
          item.repositoryStockId = stock.id
          item.outPrice = stock.outPrice
          if(item.count > stock.stock) {
            throw new Error(`${stock.goodsName}：价格(${stock.inPrice}元)输入数量超出库存`)
          }
        })
      } catch (e) {
        message.warn(e.message, 1)
        return false
      }
      //设置默认值
      form.type = 2
      return true
    },
    postFindById: (form) => {
      form.items.forEach(item => {
        item.goods = [item.repositoryId, item.repositoryId + '-' + item.goodsId + '-' + item.inPrice]
      })
    }
  },
  formRows: [
    [
      {
        label: '客户',
        field: 'customerId',
        type: 'select',
        required: true,
        keyField: 'id',
        valueField: 'name',
        optionApiPath: 'customer'
      }
    ],
    [
      {
        label: '商品列表', field: 'items', type: 'table', columns: [
          {
            title: '商品',
            dataIndex: 'goods',
            type: 'cascader',
            required: true,
            keyField: 'id',
            valueField: 'name',
            generateOptions: async () => {
              return await getStockOptions().then(({data}) => {
                const options = []
                data.forEach(t => {
                  options.push({
                    label: t.name,
                    value: t.id,
                    children: t.stocks.map(item => {
                      const key = item.repositoryId + '-' + item.goodsId + '-' + item.inPrice
                      stockMap.set(key, item)
                      return {
                        label: `${item.goodsName}(进${item.inPrice}元)(售${item.outPrice}元),库存(${item.stock})`,
                        value: key
                      }
                    })
                  })
                })
                return options;
              })
            }
          },
          {
            title: '数量',
            dataIndex: 'count',
            type: 'numberInput',
            required: true,
            min: 1
          }
        ]
      }
    ]
  ]
})

</script>

<style scoped lang="scss">

</style>
