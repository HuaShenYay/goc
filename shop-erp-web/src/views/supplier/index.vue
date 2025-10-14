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

const config = reactive({
  title: '供应商',
  apiPath: 'supplier',
  keyField: 'id',
  columns: [
    {title: '编号', dataIndex: 'id'},
    {title: '名称', dataIndex: 'name'},
    {title: '联系人', dataIndex: 'contact'},
    {title: '联系电话', dataIndex: 'tel'},
    {title: '省份', dataIndex: 'province'},
    {title: '城市', dataIndex: 'city'},
    {title: '详细地址', dataIndex: 'address', ellipsis: true}
  ],
  queryConfig: [
    {label: '名称或联系人', field: 'keyword', type: 'input'},
    {label: '省市', field: 'provinceCity', type: 'area'}
  ],
  action: ['save', 'edit', 'info', 'delete'],
  areaConfig: {
    provinceCity: ['province', 'city']
  },
  formRows: [
    [
      {label: '名称', field: 'name', type: 'input', required: true},
      {label: '联系人', field: 'contact', type: 'input', required: true},
    ],
    [
      {label: '联系电话', field: 'tel', type: 'input', required: true},
      {label: '省市', field: 'provinceCity', type: 'area', required: true},
    ],
    [
      {label: '详细地址', field: 'address', type: 'textarea', required: true}
    ]
  ]
})

</script>

<style scoped lang="scss">

</style>
