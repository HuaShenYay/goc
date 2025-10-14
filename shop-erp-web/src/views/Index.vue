<!--
  Created by IntelliJ WebStorm.
  User: Guo Shuai
  Date: 2024/09/25
  Time: 20:17
  To change this template use File | Settings | File Templates.
-->
<template>
  <a-layout class="main">
    <a-layout-sider v-model:collapsed="state.collapsed" :trigger="null" collapsible class="sider">
      <div class="logo">
        <AccountBookTwoTone/>
        <span>进销存管理</span>
      </div>
      <a-menu v-model:selectedKeys="state.selectedKeys" v-model:openKeys="state.openKeys" theme="dark" mode="inline">
        <a-menu-item v-for="menu in menuItems" :key="menu.key">
          <component :is="menu.meta.icon"></component>
          <span>
            <router-link :to="menu.key">{{ menu.meta.title }}</router-link>
          </span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout class="right">
      <a-layout-header class="header">
        <menu-unfold-outlined
            v-if="state.collapsed"
            class="trigger"
            @click="() => (state.collapsed = !state.collapsed)"
        />
        <menu-unfold-outlined v-else class="trigger" @click="() => (state.collapsed = !state.collapsed)"/>
        <a-popover placement="bottom">
          <template #content>
            <a-list :data-source="headerOperateOptions" item-layout="horizontal" size="small">
              <template #renderItem="{item}">
                <a-list-item @click="item.action()" class="operate-item">{{ item.title }}</a-list-item>
              </template>
            </a-list>
          </template>
          <a-avatar class="avatar" :size="64">
            {{ header.avatar }}
          </a-avatar>
        </a-popover>
      </a-layout-header>
      <a-layout-content class="content">
        <router-view/>
      </a-layout-content>
    </a-layout>

    <a-modal v-model:open="open" title="修改密码" @ok="updatePassword">
      <a-divider></a-divider>
      <a-form :label-col="{span: 6}" style="margin: 0 auto; width: 80%;" :rules="rules" :model="form" ref="formRef">
        <a-form-item label="原密码" ref="password" name="password">
          <a-input-password autocomplete v-model:value="form.password"/>
        </a-form-item>
        <a-form-item ref="newPassword" name="newPassword">
          <template #label>
            <span>新密码</span>
          </template>
          <a-input-password autocomplete v-model:value="form.newPassword"/>
        </a-form-item>
        <a-form-item ref="reNewPassword" name="reNewPassword">
          <template #label>
            <span>重复新密码</span>
          </template>
          <a-input-password autocomplete v-model:value="form.reNewPassword"/>
        </a-form-item>
      </a-form>
      <a-divider></a-divider>
      <template #cancelText>取消</template>
      <template #okText>确定</template>
    </a-modal>
  </a-layout>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {menus} from '@/router'
import {useRouter} from "vue-router";
import {updatePassword as updatePwd} from "@/api/user.js";
import {message} from "ant-design-vue";

const router = useRouter()

//头像气泡操作配置
const headerOperateOptions = reactive([
  {title: '修改密码', action: () => handleUpdatePwd()},
  {title: '退出登录', action: () => handleQuit()}
])

//修改密码弹窗
const open = ref(false)
const form = ref({
  password: '',
  newPassword: '',
  reNewPassword: ''
})
const newPwdValidator = (desc) => {
  return (rule, value) => {
    if (!value || !value.trim()) {
      return Promise.reject(`请输入${desc}`)
    }
    if (form.value.newPassword !== form.value.reNewPassword) {
      return Promise.reject('两次密码输入不一致')
    }
    return Promise.resolve()
  }
}
const rules = reactive({
  'password': {required: true, whitespace: true, message: '请输入原密码'},
  'newPassword': {required: true, validator: newPwdValidator('新密码')},
  'reNewPassword': {required: true, validator: newPwdValidator('重复新密码')}
})
const formRef = ref()

//菜单控制配置
const state = reactive({
  collapsed: false,
  selectedKeys: [location.hash.substring(1)],
  openKeys: [location.hash.substring(1)]
});

//菜单
const menuItems = ref([])
//头像
const user = JSON.parse(localStorage.getItem('user'))
const header = reactive({
  avatar: user ? user.name : ''
})

//退出登录
const handleQuit = () => {
  localStorage.removeItem('user')
  localStorage.removeItem('token')
  router.push('/login')
}

//修改密码弹窗
const handleUpdatePwd = () => {
  if(formRef.value) {
    formRef.value.resetFields()
  }
  open.value = true
}

//修改密码提交
const updatePassword = () => {
  formRef.value.validate().then(() => {
    if(form.value.password === form.value.newPassword) {
      message.warn('新老密码不能一致', 1)
      return
    }
    updatePwd(form.value).then(res => {
      if (res.code === 200) {
        message.success('修改成功', 1)
        //重新用新密码登录
        handleQuit()
      }
    })
  }).catch(error => {

  })
}

onMounted(() => {
  menus.forEach(menu => {
    menuItems.value.push({
      key: menu.path,
      meta: menu.meta
    })
  })
})
</script>

<style scoped lang="scss">
@import "@/assets/scss/style";

.main {
  width: 100%;
  height: 100%;

  .sider {
    width: 20%;
    height: 100%;

    .logo {
      height: 32px;
      margin: 16px;
      padding-top: 3px;
      font: {
        size: 23px;
      }
      color: #FFFFFF;
      overflow: hidden;

      span {
        margin-left: 3px;
      }
    }
  }

  .right {
    width: 80%;
    height: 100%;

    .header {
      display: flex;
      justify-content: space-between;
      width: 100%;
      height: 10%;
      background-color: #FFFFFF;
      padding: 0 2% 0 0;

      .avatar {
        background-color: #f56a00;
        vertical-align: middle;
        margin-top: 2px;
      }

      .trigger {
        font-size: 18px;
        line-height: 64px;
        padding: 0 24px;
        cursor: pointer;
        transition: color 0.3s;

        &:hover {
          color: #1890ff;
        }
      }
    }

    .content {
      margin: 1% auto;
      width: 98%;
      height: 88%;
      background: #fff;
    }
  }
}

.operate-item {
  cursor: pointer;

  &:hover {
    background-color: aliceblue;
  }
}

.required {
  @include requireClass
}
</style>
