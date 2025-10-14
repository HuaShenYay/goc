<!--
  Created by IntelliJ WebStorm.
  User: Guo Shuai
  Date: 2024/09/26
  Time: 8:59
  To change this template use File | Settings | File Templates.
-->
<template>
  <div class="table-container">
    <a-card v-if="showAction('save') || config.queryConfig">
      <a-row :gutter="16">
        <a-col v-for="item in config.queryConfig" :space="12">
          <!-- 输入 -->
          <a-input
              allow-clear
              v-if="item.type === 'input'"
              @input="handleQueryInput(item)"
              :placeholder="`请输入${item.label}`"
              v-model:value="params[item.field]"
          />
          <!-- 选择 -->
          <a-select
              style="width: 200px"
              allow-clear
              v-if="item.type === 'select'"
              v-model:value="params[item.field]"
              @change="getDataSource"
              :placeholder="`请选择${item.label}`">
            <a-select-option
                v-for="option in queryDict[item.field]"
                :key="option.value"
                :value="option.value"
            >{{ option.label }}
            </a-select-option>
          </a-select>
          <!-- 日期范围 -->
          <a-range-picker
              v-if="item.type === 'dateRange'"
              v-model:value="rangeParam[item.field]"
              format="YYYY-MM-DD"
              value-format="YYYY-MM-DD"
              @change="handleDateRangeChange(item, true)"
              :placeholder="['开始日期', '结束日期']"
          >
          </a-range-picker>
          <!-- 地区 -->
          <a-cascader
              allow-clear
              :placeholder="`请选择${item.label}`"
              v-if="item.type === 'area'"
              :options="areaOptions"
              v-model:value="rangeParam[item.field]"
              @change="handleCascaderChange(item, true)"
          />
        </a-col>
        <a-col :space="12">
          <a-button v-if="config.queryConfig" @click="handleCleanQueryParams">
            <UndoOutlined/>
            清空查询条件
          </a-button>
        </a-col>
        <a-col :space="12">
          <a-button v-if="showAction('save')" type="primary" @click="handleAddClick">新增{{
              config.title
            }}
          </a-button>
        </a-col>
      </a-row>
    </a-card>
    <a-card>
      <a-table
          :custom-row="customRowAction"
          :data-source="dataSource"
          :pagination="pagination"
          :loading="loading"
          :row-key="record => record[config.keyField]"
          :columns="columns">
        <template #bodyCell="{column, record}" v-if="displayAction()">
          <template v-if="column.dataIndex === 'action'">
            <span v-if="showAction('edit')">
              <a @click.stop="handleEditClick(record[config.keyField])">编辑</a>
            </span>
            <span v-bind:style="showAction('edit') ? {'margin-left': '10px'} : {}" v-if="showAction('delete')">
              <a style="color: red" @click.stop="handleDeleteClick(record[config.keyField])">删除</a>
            </span>
            <span v-bind:style="showAction('edit') || showAction('delete') || index > 0 ? {'margin-left': '10px'} : {}"
                  v-for="(item,index) in Object.keys(config.customAction)"
                  :key="index"
            >
              <a
                  v-if="!config.customAction[item].condition || config.customAction[item].condition(record)"
                  @click.stop="handleCustomClick(record, item)">{{ config.customAction[item].label }}
              </a>
            </span>
          </template>
        </template>
      </a-table>
    </a-card>
    <enhance-drawer :config="drawerConfig" @close="handleClose"/>
  </div>
</template>

<script setup>
import {defineProps, defineEmits, onMounted, reactive, ref, watch} from 'vue'
import EnhanceDrawer from "@/components/EnhanceDrawer.vue";
import {BaseApi} from "@/api/common.js";
import {message} from "ant-design-vue";
import areaData from 'china-area-data';

//获取props
const props = defineProps(['config'])
//获取配置
const config = props.config;
config.customAction = config.customAction ? config.customAction : {}
//获取加载数据的方法
const baseApi = new BaseApi(config.apiPath)
const queryDict = reactive({})
//数据源
let dataSource = reactive([])
//列
const columns = reactive(config.columns)
//事件
const emits = defineEmits(['customAction'])
//地区数据
const areaDict = reactive(new Map())
const areaOptions = reactive([])
//按钮展示判断
const showAction = (action) => {
  return config.action && config.action.indexOf(action) !== -1
}
//表格操作列是否展示
const displayAction = () => {
  return showAction('edit') || showAction('delete') || (config.customAction && Object.keys(config.customAction).length > 0)
}
//判断是否要操作列
if (displayAction()) {
  columns.push({
    title: '操作',
    dataIndex: 'action'
  })
}

//查询参数
const params = ref({
  page: 1,
  limit: 5
})
const rangeParam = ref({})
//分页对象
const pagination = ref({
  current: 1,
  pageSize: config.pageSize | 5,
  total: 2,
  position: ['bottomCenter'],
  showQuickJumper: true,
  onChange: (page) => {
    pagination.value.current = page
    getDataSource()
  }
})
//加载
const loading = ref(true)
//抽屉参数
const drawerConfig = reactive({
  open: false,
  title: config.title,
  id: '',
  info: false,
  keyField: config.keyField,
  apiPath: config.apiPath,
  rows: config.formRows,
  areaConfig: config.areaConfig,
  areaOptions: areaOptions,
  callback: config.callback,
  queryDict: queryDict
})

//获取数据
const getDataSource = () => {
  loading.value = true
  params.value.limit = pagination.value.pageSize
  params.value.page = pagination.value.current
  if (config.queryParams) {
    for (let key in config.queryParams) {
      params.value[key] = config.queryParams[key]
    }
  }
  baseApi.findPage(params.value).then(res => {
    dataSource = res.data
    pagination.value.total = res.total
  }).finally(() => {
    loading.value = false
  })
}

//表格事件
const customRowAction = (record) => {
  return !showAction('info') ? {} : {
    onClick: (event) => {
      drawerConfig.open = true
      drawerConfig.info = true
      drawerConfig.id = record[config.keyField]
    }
  }
}

//添加按钮
const handleAddClick = () => {
  drawerConfig.open = true
}

//编辑按钮
const handleEditClick = (id) => {
  drawerConfig.open = true
  drawerConfig.id = id
}

//删除按钮
const handleDeleteClick = (id) => {
  baseApi.removeById(id).then(res => {
    if (res.code === 200) {
      message.success('删除成功', 1)
      //重置页码
      pagination.value.current = 1
      getDataSource()
    }
  })
}

//自定义按钮点击事件
const handleCustomClick = (record, event) => {
  emits('customAction', record, event)
}

//处理抽屉关闭
const handleClose = (refresh) => {
  drawerConfig.open = false
  drawerConfig.info = false
  drawerConfig.id = ''
  if (refresh) {
    getDataSource()
  }
}

//初始化地区数据
const initAreaData = () => {
  //省份
  const province = areaData['86']
  Object.keys(province).forEach(key => {
    areaDict.set(key, province[key])
    areaOptions.push({
      value: key,
      label: province[key],
      children: Object.keys(areaData[key]).map(city => {
        areaDict.set(city, areaData[key][city])
        return {
          value: city,
          label: areaData[key][city],
        }
      })
    })
  })
  const areaFields = Object.values(config.areaConfig).flat()
  //地区数据自定义转换
  columns.forEach(column => {
    if (areaFields.includes(column.dataIndex)) {
      column['customRender'] = ({text}) => {
        return areaDict.get(text)
      }
    }
  })
}

//初始化查询字典
const initQueryDict = () => {
  if (!config.queryConfig) {
    return
  }
  config.queryConfig.forEach(async item => {
    if (item.type === 'select') {
      if (item.apiPath) {
        queryDict[item.field] = await baseApi.findOptions(item.apiPath).then(({data}) => {
          return data.map(t => {
            return {'label': t[item.valueField], 'value': t[item.keyField]}
          })
        })
      } else if (item.generateOptions) {
        queryDict[item.field] = await item.generateOptions().then(data => {
          return data.map(t => {
            return {'label': t[item.valueField], 'value': t[item.keyField]}
          })
        })
      }
    }
    //设置默认值
    if (item.defaultValue) {
      const value = item.defaultValue
      if ((item.type === 'dateRange' || item.type === 'cascader') && value && value.length > 0) {
        rangeParam.value[item.field] = value
        //处理范围参数
        if (item.type === 'dateRange') {
          handleDateRangeChange(item, false)
        } else if (item.type === 'cascader') {
          handleCascaderChange(item, false)
        }
      } else {
        params.value[item.field] = value
      }
    }
  })
}

//查询参数，日期范围变化处理
const handleDateRangeChange = (item, refresh) => {
  const range = rangeParam.value[item.field]
  if (!range || range.length <= 0) {
    params.value[item.startField] = null
    params.value[item.endField] = null
  } else {
    params.value[item.startField] = range[0]
    params.value[item.endField] = range[1]
  }
  if (refresh) {
    getDataSource()
  }
}
//查询参数，级联框变化
const handleCascaderChange = (item, refresh) => {
  const array = rangeParam.value[item.field]
  if (!array || array.length <= 0) {
    params.value[config.areaConfig[item.field][0]] = null
    params.value[config.areaConfig[item.field][1]] = null
  } else {
    params.value[config.areaConfig[item.field][0]] = array[0]
    params.value[config.areaConfig[item.field][1]] = array[1]
  }
  if (refresh) {
    getDataSource()
  }
}
//查询参数，监听输入框停止输入
const handleQueryInput = (item) => {
  if (item.timer) {
    clearTimeout(item.timer)
  }
  item.timer = setTimeout(() => {
    if (params.value[item.field].trim() === '') {
      params.value[item.field] = null
    }
    getDataSource()
  }, 300)
}
//清空查询条件
const handleCleanQueryParams = () => {
  for (let key in params.value) {
    if (key === 'page' || key === 'limit') {
      continue
    }
    params.value[key] = null
  }
  for (let key in rangeParam.value) {
    rangeParam.value[key] = []
  }
  getDataSource()
}

onMounted(() => {
  //初始化地区数据
  if (config.areaConfig) {
    initAreaData()
  }
  //初始化查询字典
  if (config.queryConfig) {
    initQueryDict()
  }
  getDataSource()
  if (config.bus) {
    //绑定刷新事件
    config.bus.on('refresh', (e) => {
      getDataSource()
    })
  }
})

</script>

<style scoped lang="scss">

</style>
