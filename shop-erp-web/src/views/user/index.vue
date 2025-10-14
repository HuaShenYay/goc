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
import moment from "moment";

const dict = getOptions()
const user = JSON.parse(localStorage.getItem('user'))

const config = reactive({
  title: '业务员',
  apiPath: 'sys/user',
  keyField: 'id',
  columns: [
    {title: '编号', dataIndex: 'id'},
    {title: '姓名', dataIndex: 'name'},
    {title: '性别', dataIndex: 'sex', customRender: ({text}) => convertDict('SEX', text)},
    {title: '生日', dataIndex: 'birthday'},
    {title: '手机号', dataIndex: 'phone'}
  ],
  queryConfig: [
    {label: '姓名', field: 'name', type: 'input'},
    {
      label: '性别',
      field: 'sex',
      type: 'select',
      keyField: 'value',
      valueField: 'label',
      generateOptions: () => {
        return Promise.resolve(dict['SEX'])
      }
    }
  ],
  action: ['info'],
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
      {
        label: '生日', field: 'birthday', type: 'date', required: true, disabled: ({$d}) => {
          return moment($d).isAfter(new Date());
        }
      },
      {label: '手机号', field: 'phone', type: 'numberInput', required: true}
    ]
  ]
})
//判断是否管理员
if(user && user.isSystem) {
  config.action.push('save')
  config.action.push('edit')
}
</script>

<style scoped lang="scss">

</style>
