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

const config = reactive({
  title: '操作日志',
  apiPath: 'operate/log',
  keyField: 'id',
  columns: [
    {title: '编号', dataIndex: 'id', width: 70},
    {title: 'referrer', dataIndex: 'referrer', ellipsis: true},
    {title: '客户端地址', dataIndex: 'remoteAddress', ellipsis: true},
    {title: '请求方式', dataIndex: 'method', width: 90},
    {title: '接口地址', dataIndex: 'requestUrl', ellipsis: true},
    {title: '接口名称', dataIndex: 'actionName', ellipsis: true},
    {title: '请求参数', dataIndex: 'requestParam', ellipsis: true},
    {title: '用户名', dataIndex: 'username', width: 100},
    {title: '用户手机号', dataIndex: 'phone'},
    {title: '操作时间', dataIndex: 'insertDate', ellipsis: true},
  ],
  queryConfig: [
    {
      label: '操作用户',
      field: 'username',
      type: 'input'
    },
    {
      label: '请求方式',
      field: 'requestType',
      type: 'select',
      keyField: 'value',
      valueField: 'label',
      generateOptions: () => {
        return Promise.resolve([
          {label: '读', value: 'GET'},
          {label: '写', value: 'POST,PUT,PATCH'},
          {label: '删', value: 'DELETE'}
        ])
      }
    },
    {
      label: '操作时间',
      field: 'insertRange',
      type: 'dateRange',
      startField: 'startDate',
      endField: 'endDate',
      defaultValue: [
        moment().startOf('day').subtract(7, 'day').format('YYYY-MM-DD'),
        moment().startOf('day').format('YYYY-MM-DD')
      ]
    }
  ]
})
</script>

<style scoped lang="scss">

</style>
