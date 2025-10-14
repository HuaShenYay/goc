<!--
  Created by IntelliJ WebStorm.
  User: Guo Shuai
  Date: 2024/09/26
  Time: 18:00
  To change this template use File | Settings | File Templates.
-->
<template>
  <div>
    <enhance-table :config="config"/>
  </div>
</template>

<script setup>
import EnhanceTable from "@/components/EnhanceTable.vue";
import {reactive, defineProps, watch} from "vue";
import mitt from "mitt";

const props = defineProps(['repositoryId'])

const config = reactive({
  title: '商品库存',
  apiPath: '/repository/stock',
  bus: mitt(),
  keyField: 'id',
  pageSize: 10,
  queryParams: {repositoryIds: props.repositoryId},
  columns: [
    {title: '商品编号', dataIndex: 'goodsId'},
    {title: '商品名', dataIndex: 'goodsName'},
    {title: '进价', dataIndex: 'inPrice'},
    {title: '库存', dataIndex: 'stock'}
  ]
})

watch(() => props.repositoryId, (value, oldValue, onCleanup) => {
  config.queryParams.repositoryIds = props.repositoryId
  config.bus.emit('refresh')
})
</script>

<style scoped lang="scss">

</style>
