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
import moment from "moment/moment.js";
import {convertDict, getOptions} from "@/util/dict.js";

const dict = getOptions()

const config = reactive({
  title: '客户',
  apiPath: 'customer',
  keyField: 'id',
  columns: [
    {title: '编号', dataIndex: 'id'},
    {title: '姓名', dataIndex: 'name'},
    {title: '性别', dataIndex: 'sex', customRender: ({text}) => convertDict('SEX', text)},
    {title: '联系电话', dataIndex: 'tel'},
    {title: '生日', dataIndex: 'birthday'},
    {title: '省份', dataIndex: 'province'},
    {title: '城市', dataIndex: 'city'},
    {title: '详细地址', dataIndex: 'address', ellipsis: true}
  ],
  queryConfig: [
    {label: '姓名', field: 'name', type: 'input'},
    {label: '省市', field: 'provinceCity', type: 'area'}
  ],
  action: ['save', 'edit', 'info'],
  areaConfig: {
    provinceCity: ['province', 'city']
  },
  formRows: [
    [
      {label: '姓名', field: 'name', type: 'input', required: true},
      {
        label: '性别',
        field: 'sex',
        type: 'radio',
        required: true,
        keyField: 'value',
        valueField: 'label',
        generateOptions: () => {
          return new Promise(resolve => {
            return resolve(dict['SEX'])
          })
        }
      }
    ],
    [
      {label: '联系电话', field: 'tel', type: 'input', required: true},
      {
        label: '生日', field: 'birthday', type: 'date', required: true, disabled: ({$d}) => {
          return moment($d).isAfter(new Date());
        }
      }
    ],
    [
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
