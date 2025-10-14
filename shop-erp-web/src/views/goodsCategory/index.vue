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
  title: '商品类别',
  apiPath: 'goods/category',
  keyField: 'id',
  columns: [
    {title: '编号', dataIndex: 'id'},
    {title: '名称', dataIndex: 'name'},
    {title: '描述', dataIndex: 'remark', ellipsis: true}
  ],
  queryConfig: [
    {label: '名称', field: 'name', type: 'input'}
  ],
  action: ['save', 'edit', 'info', 'delete'],
  formRows: [
    [
      {label: '名称', field: 'name', type: 'input', required: true},
    ],
    [
      {label: '描述', field: 'remark', type: 'textarea'}
    ]
  ]
})
</script>

<style scoped lang="scss">

</style>
