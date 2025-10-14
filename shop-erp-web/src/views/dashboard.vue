<!--
  Created by IntelliJ WebStorm.
  User: Guo Shuai
  Date: 2024/09/26
  Time: 19:06
  To change this template use File | Settings | File Templates.
-->
<template>
  <a-card title="进销存统计" style="width: 100%;height: 100%; overflow-y: hidden" :body-style="{width: '100%', height: '100%'}">
    <template #extra>
      <a-range-picker
          :allow-clear="false"
          v-model:value="dateRange"
          format="YYYY-MM-DD"
          value-format="YYYY-MM-DD"
          @change="handleChange"
          :placeholder="['开始日期', '结束日期']"
      >
      </a-range-picker>
    </template>
    <div id="charts"></div>
  </a-card>
</template>

<script setup>
//日期范围
import {onMounted, ref} from "vue";
import moment from "moment";
import {findPurchaseSaleCount} from "@/api/count.js";
import * as echarts from 'echarts/core';
import {
  DatasetComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent
} from 'echarts/components';
import {BarChart} from 'echarts/charts';
import {CanvasRenderer} from 'echarts/renderers';

echarts.use([
  DatasetComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent,
  BarChart,
  CanvasRenderer
]);

//日期范围
const dateRange = ref([
  moment().startOf('day').subtract(7, 'day').format('YYYY-MM-DD'),
  moment().startOf('day').format('YYYY-MM-DD')
])

//图表参数
const option = {
  legend: {},
  tooltip: {},
  dataset: {
    source: []
  },
  xAxis: {type: 'category'},
  yAxis: {},
  series: [{type: 'bar'}, {type: 'bar'}]
};

//初始化图
const initCharts = () => {
  const startDate = dateRange && dateRange.value.length > 0 ? dateRange.value[0] : ''
  const endDate = dateRange && dateRange.value.length > 0 ? dateRange.value[1] : ''
  findPurchaseSaleCount(startDate, endDate).then(({data}) => {
    const items = []
    data.forEach(item => {
      items.push([item.goods, item.purchase, item.sale])
    })
    option.dataset.source = [['product', '进', '出']].concat(items)
    const charts = echarts.init(document.querySelector('#charts'))
    charts.setOption(option)
  })
}

//日期发生变化
const handleChange = () => {
  initCharts()
}

onMounted(() => {
  initCharts()
})
</script>

<style scoped lang="scss">
#charts {
  width: 100%;
  height: 100%;
}
</style>
