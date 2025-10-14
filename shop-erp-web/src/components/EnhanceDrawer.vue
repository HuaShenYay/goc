<!--
  Created by IntelliJ WebStorm.
  User: Guo Shuai
  Date: 2024/09/26
  Time: 11:40
  To change this template use File | Settings | File Templates.
-->
<template>
  <a-drawer
      :title="config.info ? config.title + '信息' : (isModify() ? '编辑' : '新增') + config.title"
      :width="720"
      :open="config.open"
      :body-style="{ paddingBottom: '80px' }"
      :footer-style="{ textAlign: 'right' }"
      @close="onClose(false)"
  >
    <a-form ref="formRef" :disabled="config.info" :model="form" :rules="rules" layout="vertical">
      <a-row :gutter="16" v-for="row in config.rows">
        <a-col v-for="column in row" :span="column.type === 'textarea' || column.type === 'table' ? 24 : 12">
          <a-form-item v-bind:label="column.type !== 'table' ? column.label : ''" :name="column.field"
                       :ref="column.field">
            <a-input
                v-if="column.type === 'input'"
                v-model:value="form[column.field]"
                :placeholder="'请输入' + column.label"
            />
            <a-textarea
                v-if="column.type ==='textarea'"
                v-model:value="form[column.field]"
                :rows="4"
                :placeholder="'请输入' + column.label"
            />
            <a-select v-if="column.type === 'select'" v-model:value="form[column.field]">
              <a-select-option
                  v-for="option in selectMap[column.field]"
                  :key="option.value"
                  :value="option.value"
              >{{ option.label }}
              </a-select-option>
            </a-select>
            <a-radio-group v-if="column.type === 'radio'" v-model:value="form[column.field]">
              <a-radio
                  v-for="option in selectMap[column.field]"
                  :key="option.value"
                  :value="option.value"
              >{{ option.label }}
              </a-radio>
            </a-radio-group>
            <a-input-number
                style="width: 100%;"
                v-bind:min="column.min ? column.min : -Infinity"
                v-bind:max="column.max ? column.max : Infinity"
                v-if="column.type === 'numberInput'"
                v-model:value="form[column.field]"
            />
            <a-date-picker
                style="width: 100%;"
                :placeholder="'请选择' + column.label"
                v-if="column.type === 'date'"
                format="YYYY-MM-DD"
                value-format="YYYY-MM-DD"
                v-bind:disabled-date="!column.disabled ? () => true : column.disabled"
                v-model:value="form[column.field]"
            ></a-date-picker>
            <a-cascader
                v-if="column.type === 'area'"
                :options="areaOptions"
                v-model:value="form[column.field]"
            />

            <a-card v-if="column.type === 'table'" :title="column.label" style="width: 100%;">
              <template #extra>
                <a-button v-if="!config.info" type="primary" @click="handleAddTableRow(column)">添加</a-button>
              </template>

              <div v-if="column.type === 'table'" style="width: 100%;">
                <a-table :columns="column.columns" :data-source="form[column.field]" :pagination="false">

                  <template #bodyCell="t">
                    <template v-if="!config.info && t.column.dataIndex === 'action'">
                    <span>
                      <a style="color: red" @click="handleRemoveTableRow(column, t.index)">删除</a>
                    </span>
                    </template>

                    <a-cascader
                        v-if="t.column.type === 'cascader'"
                        :options="cascaderMap[t.column.dataIndex]"
                        v-model:value="form[column.field][t.index][t.column.dataIndex]"
                    />

                    <a-select v-if="t.column.type === 'select'"
                              v-model:value="form[column.field][t.index][t.column.dataIndex]">
                      <a-select-option
                          v-for="option in selectMap[t.column.dataIndex]"
                          :key="option.value"
                          :value="option.value"
                      >{{ option.label }}
                      </a-select-option>
                    </a-select>

                    <a-input-number
                        v-bind:min="t.column.min ? t.column.min : -Infinity"
                        v-bind:max="t.column.max ? t.column.max : Infinity"
                        style="width: 100%;"
                        v-if="t.column.type === 'numberInput'"
                        v-model:value="form[column.field][t.index][t.column.dataIndex]"
                    />
                  </template>
                  <template #headerCell="t">
                    <span v-bind:class="t.column.required ? 'required' : ''">{{ t.title }}</span>
                  </template>
                </a-table>
              </div>
            </a-card>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
    <template #extra v-if="!config.info">
      <a-space>
        <a-button @click="onClose(false)">取消</a-button>
        <a-button danger @click="handleReset">清空</a-button>
        <a-button type="primary" @click="handleSubmit">提交</a-button>
      </a-space>
    </template>
  </a-drawer>
</template>

<script setup>
import {defineEmits, defineProps, onMounted, reactive, ref, watch} from 'vue'
import {BaseApi} from "@/api/common.js";
import {message} from "ant-design-vue";


const props = defineProps(['config'])
//获取配置
const config = reactive(props.config)
//地区数据
const areaOptions = reactive(config.areaOptions)

const baseApi = new BaseApi(config.apiPath)

const emits = defineEmits(['close'])

const form = ref({})

const formRef = ref()

const rules = reactive({})

const selectMap = reactive({})
const cascaderMap = reactive({})

const onClose = (refresh) => {
  formRef.value.resetFields()
  form.value[config.keyField] = ''
  emits('close', refresh)
}

//设置规则
const setRules = () => {
  if (!config.rows) {
    return
  }
  config.rows.forEach(row => {
    row.forEach(column => {
      if (column.required) {
        const prefix = column.type === 'select' ? '请选择' : '请输入'
        rules[column.field] = {required: true, message: `${prefix}${column.label}`}
      }
      if (column.validator) {
        if (rules[column.field]) {
          rules[column.field].validator = column.validator
        } else {
          rules[column.field] = {validator: column.validator}
        }
      }
    })
  })
}

//提交
const handleSubmit = () => {
  formRef.value.validate().then(() => {
    //处理地区
    if (config.areaConfig) {
      const areaKeys = Object.keys(config.areaConfig)
      areaKeys.forEach(key => {
        if (!form.value[key] || form.value[key].length === 0) {
          return
        }
        config.areaConfig[key].forEach((field, index) => {
          form.value[field] = form.value[key][index]
        })
      })
    }
    //提交前回调
    if (config.callback && Object.hasOwn(config.callback, 'beforeSubmit')) {
      if (!config.callback.beforeSubmit(form.value)) {
        return
      }
    }
    const func = isModify() ? baseApi.modify(form.value) : baseApi.save(form.value)
    func.then(res => {
      if (res.code === 200) {
        message.success('操作成功', 1)
      }
    }).finally(() => {
      onClose(true)
    })
  }).catch(error => {

  })
}

//重置表单
const handleReset = () => {
  if (!config.rows) {
    return
  }
  formRef.value.resetFields()
  //清空表格
  config.rows.forEach(row => {
    row.forEach(column => {
      if (column.type === 'table') {
        form.value[column.field] = []
      }
    })
  })
}

//监听窗口打开时请求数据
watch(() => config.open, async (value, oldValue, onCleanup) => {
  if (!value) {
    return
  }
  if (config.id !== '') {
    await getOptions()
    baseApi.findById(config.id).then(res => {
      for (let key in res.data) {
        form.value[key] = res.data[key]
      }
      //判断是否有区域
      if (config.areaConfig) {
        const keys = Object.keys(config.areaConfig)
        keys.forEach(key => {
          const fields = config.areaConfig[key]
          if (!fields || fields.length === 0) {
            return
          }
          form.value[key] = fields.map(field => form.value[field])
        })
      }
      //判断是否有回调
      if (config.callback && Object.hasOwn(config.callback, 'postFindById')) {
        config.callback.postFindById(form.value)
      }
    })
  } else {
    await getOptions()
  }
})

//通用字典加载
const commonLoadDict = async (column) => {
  if (column.type === 'cascader') {
    //判断是否已经加载过
    if (cascaderMap[column.field]) {
      return
    }
    cascaderMap[column.field] = await column.generateOptions().then(data => {
      return data
    })
  } else if (column.type === 'select' || column.type === 'radio') {
    //判断是否已经加载过
    if (selectMap[column.field]) {
      return
    }
    //判断表格是否已经加载郭，避免重复加载
    if (config.queryDict && config.queryDict[column.field]) {
      selectMap[column.field] = createOptions({field: column.field}, config.queryDict[column.field])
      return
    }
    if (column.optionApiPath) {
      selectMap[column.field] = await baseApi.findOptions(column.optionApiPath).then(res => {
        return createOptions(column, res.data)
      })
    } else {
      selectMap[column.field] = await column.generateOptions().then(data => {
        return createOptions(column, data)
      })
    }
  }
}

//加载下拉框、单选框选项
const getOptions = () => {
  if (!config.rows) {
    return
  }
  config.rows.forEach(row => {
    row.forEach(column => {
      commonLoadDict(column);
      if (column.type === 'table') {
        form.value[column.field] = []
        for (const item of column.columns) {
          item.field = item.dataIndex
          commonLoadDict(item)
        }
        if (config.info) {
          column.columns[column.columns.length - 1].colSpan = 0
        } else {
          column.columns[column.columns.length - 1].colSpan = 1
        }
      }
    })
  })
}

//添加表格行
const handleAddTableRow = (column) => {
  const item = column.columns
  const newObj = {}
  item.forEach(item => {
    if (item.type === 'select' || item.type === 'radio') {
      if (!isModify() && selectMap[item.dataIndex] && selectMap[item.dataIndex].length > 0) {
        newObj[item.dataIndex] = selectMap[item.dataIndex][0].value
      } else {
        newObj[item.dataIndex] = ''
      }
    } else if (item.type === 'cascader') {
      if (!isModify() && cascaderMap[item.dataIndex] && cascaderMap[item.dataIndex].length > 0) {
        const first = cascaderMap[item.dataIndex][0]
        newObj[item.dataIndex] = [first.value]
        if(first.children && first.children.length > 0) {
          newObj[item.dataIndex].push(first.children[0].value)
        }
      }
    } else {
      newObj[item.dataIndex] = ''
    }
  })
  form.value[column.field].push(newObj)
}
//删除表格行
const handleRemoveTableRow = (column, index) => {
  form.value[column.field].splice(index, 1)
}
//设置表格操作列
const setTableAction = () => {
  if (!config.rows) {
    return
  }
  config.rows.forEach(row => {
    row.forEach(column => {
      if (column.type === 'table') {
        //添加操作列
        column.columns.push({
          title: '操作',
          dataIndex: 'action',
          width: 70
        })
      }
    })
  })
}

//创建选项
const createOptions = (config, dataList) => {
  const options = []
  dataList.forEach(data => {
    options.push({
      label: config.keyField ? data[config.valueField] : data.label,
      value: config.valueField ? data[config.keyField] : data.value
    })
  })
  //设置默认值
  //如果是表格来加载，不需要设置默认值
  if (!Object.hasOwn(config, 'dataIndex')) {
    if (options.length > 0 && !isModify() && config.field) {
      form.value[config.field] = options[0].value
    }
  }
  return options
}

//判断是否是编辑
const isModify = () => {
  return form.value[config.keyField]
}

onMounted(() => {
  setRules()
  setTableAction()
})
</script>

<style scoped lang="scss">
@import "@/assets/scss/style";

.required {
  @include requireClass
}
</style>
