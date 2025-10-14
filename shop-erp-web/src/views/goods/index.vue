<!--
  Created by IntelliJ WebStorm.
  User: Guo Shuai
  Date: 2024/09/26
  Time: 16:33
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
import {convertDict, getOptions} from "@/util/dict.js";

const dict = getOptions()

const config = reactive({
  title: '商品',
  apiPath: 'goods',
  keyField: 'id',
  columns: [
    {title: '编号', dataIndex: 'id'},
    {title: '名称', dataIndex: 'name'},
    {title: '类别', dataIndex: 'categoryName'},
    {title: '售价(元)', dataIndex: 'outPrice'},
    {title: '规格', dataIndex: 'standard', customRender: ({text}) => convertDict('STANDARD', text)},
    {title: '库存', dataIndex: 'stock'},
    {title: '描述', dataIndex: 'remark', ellipsis: true}
  ],
  queryConfig: [
    {label: '商品名', field: 'name', type: 'input'},
    {
      label: '商品类别',
      field: 'categoryId',
      type: 'select',
      keyField: 'id',
      valueField: 'name',
      apiPath: 'goods/category'
    }
  ],
  action: ['save', 'edit', 'info', 'delete'],
  formRows: [
    [
      {label: '名称', field: 'name', type: 'input', required: true},
      {
        label: '分类',
        field: 'categoryId',
        type: 'select',
        required: true,
        optionApiPath: 'goods/category'
      }
    ],
    [
      {
        label: '售价', field: 'outPrice', type: 'numberInput', required: true, min: 0.01
      },
      {
        label: '规格',
        field: 'standard',
        type: 'select',
        required: true,
        keyField: 'value',
        valueField: 'label',
        generateOptions: () => {
          return new Promise(resolve => {
            return resolve(dict['STANDARD'])
          })
        }
      }
    ],
    [
      {label: '描述', field: 'remark', type: 'textarea'}
    ]
  ]
})

</script>

<style scoped lang="scss">

</style>
