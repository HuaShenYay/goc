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
  title: '转仓记录',
  apiPath: 'transfer/history',
  keyField: 'id',
  columns: [
    {title: '编号', dataIndex: 'id'},
    {title: '原仓库', dataIndex: 'sourceRepository'},
    {title: '目标仓库', dataIndex: 'targetRepository'},
    {title: '商品', dataIndex: 'goodsName'},
    {title: '数量', dataIndex: 'count'},
    {title: '转出时价格', dataIndex: 'price'}
  ],
  queryConfig: [
    {
      label: '原仓库',
      field: 'source',
      type: 'select',
      keyField: 'id',
      valueField: 'name',
      apiPath: 'repository'
    },
    {
      label: '目标仓库',
      field: 'target',
      type: 'select',
      keyField: 'id',
      valueField: 'name',
      apiPath: 'repository'
    },
    {
      label: '商品',
      field: 'goodsId',
      type: 'select',
      keyField: 'id',
      valueField: 'name',
      apiPath: 'goods'
    },
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
              throw new Error('请完整填写转仓数据！')
            }
          }
          //设置属性
          const stock = stockMap.get(item.goods[1])
          item.source = stock.repositoryId
          item.price = stock.inPrice
          item.goodsId = stock.goodsId
          item.repositoryStockId = stock.id
          if (item.source === item.target) {
            throw new Error('原仓库目标仓库不能一样！')
          }
          if (item.count > stock.stock) {
            throw new Error(`${stock.goodsName}：价格(${stock.inPrice}元)转出数量超出库存`)
          }
        })
      } catch (e) {
        message.warn(e.message, 1)
        return false
      }
      return true
    },
    postFindById: (form) => {
      form.goods = [form.source, form.source + '-' + form.goodsId + '-' + form.price]
      form.items = [form]
    }
  },
  formRows: [
    [
      {
        label: '转仓列表', field: 'items', type: 'table', columns: [
          {
            title: '原仓库',
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
                        label: `${item.goodsName}(${item.inPrice}元),库存(${item.stock})`,
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
            title: '新仓库',
            dataIndex: 'target',
            type: 'select',
            required: true,
            keyField: 'id',
            valueField: 'name',
            optionApiPath: 'repository'
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
