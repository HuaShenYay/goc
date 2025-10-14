<!--
  Created by IntelliJ WebStorm.
  User: Guo Shuai
  Date: 2024/09/26
  Time: 16:33
  To change this template use File | Settings | File Templates.
-->
<template>
  <div>
    <enhance-table @custom-action="handleShowStock" :config="config"/>
    <a-drawer title="商品库存" v-model:open="open" :width="720">
      <stock :repository-id="selectedId"/>
    </a-drawer>
  </div>
</template>

<script setup>
import EnhanceTable from "@/components/EnhanceTable.vue";
import {reactive, ref} from "vue";
import Stock from "@/views/repository/stock.vue";

const config = reactive({
  title: '仓库',
  apiPath: 'repository',
  keyField: 'id',
  columns: [
    {title: '编号', dataIndex: 'id'},
    {title: '名称', dataIndex: 'name'},
    {title: '省份', dataIndex: 'province'},
    {title: '城市', dataIndex: 'city'},
    {title: '详细地址', dataIndex: 'address', ellipsis: true}
  ],
  queryConfig: [
    {label: '名称', field: 'name', type: 'input'},
    {label: '省市', field: 'provinceCity', type: 'area'}
  ],
  action: ['save', 'edit', 'info', 'delete'],
  areaConfig: {
    provinceCity: ['province', 'city']
  },
  customAction: {
    'showStock': {
      label: '查看库存'
    }
  },
  formRows: [
    [
      {label: '名称', field: 'name', type: 'input', required: true},
      {label: '省市', field: 'provinceCity', type: 'area', required: true}
    ],
    [
      {label: '详细地址', field: 'address', type: 'textarea', required: true}
    ]
  ]
})

//库存弹窗
const open = ref(false)
const selectedId = ref('')
//查看库存
const handleShowStock = (record, event) => {
  selectedId.value = record.id
  open.value = true
}
</script>

<style scoped lang="scss">

</style>
