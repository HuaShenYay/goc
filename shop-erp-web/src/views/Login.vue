<!--
  Created by IntelliJ WebStorm.
  User: Guo Shuai
  Date: 2024/09/25
  Time: 18:41
  To change this template use File | Settings | File Templates.
-->
<template>
  <a-flex class="login-container">
    <div class="login-item">
      <div class="header">
        <span>致一商店进销存管理系统</span>
      </div>
      <div class="body">
        <div class="bg"></div>
      </div>
      <div class="footer">
        <span>欢迎使用本系统</span>
      </div>
    </div>
    <div class="login-item">
      <div class="login-item-right">
        <div class="header">
          <span>登录</span>
        </div>
        <a-form ref="formRef" :model="form" :rules="rules" class="login-form">
          <a-form-item ref="username" name="username">
            <a-input @keyup.enter="handleLogin" v-model:value="form.username" class="input">
              <template #prefix>
                <UserOutlined/>
              </template>
            </a-input>
          </a-form-item>
          <a-form-item ref="password" name="password">
            <a-input-password @keyup.enter="handleLogin" autocomplete v-model:value="form.password" class="input">
              <template #prefix>
                <LockOutlined/>
              </template>
            </a-input-password>
          </a-form-item>
          <a-form-item>
            <a-button type="primary" class="login-btn" @click="handleLogin">登录</a-button>
          </a-form-item>
        </a-form>
      </div>
    </div>
  </a-flex>
</template>

<script setup>
import {useRouter} from "vue-router";
import {login} from "@/api/login.js";
import {ref} from "vue";

const router = useRouter()

const form = ref({
  username: '',
  password: ''
})

const rules = {
  username: {required: true, message: '请输入账号'},
  password: {required: true, message: '请输入密码'}
}

const formRef = ref()

const handleLogin = () => {
  formRef.value.validate().then(() => {
    login(form.value.username, form.value.password).then(({data}) => {
      localStorage.setItem('user', JSON.stringify(data.user))
      localStorage.setItem('token', data.token)
      router.push('/')
    }).catch(error => {

    })
  }).catch(error => {

  })
}

</script>

<style scoped lang="scss">
.login-container {
  width: 100%;
  height: 100%;

  .login-item {
    width: 50%;
    height: 100%;

    &:first-child {
      background-color: #293146;

      .header {
        height: 10%;
        width: 100%;
        margin: 3% auto;
        padding: 30px;
        color: #FFFFFF;
        font: {
          weight: 1000;
          size: 30px;
        }
      }
    }

    .body {
      width: 100%;
      height: 60%;

      .bg {
        width: 100%;
        height: 100%;
        background-image: url("@/assets/login.svg");
        background-size: 100% 100%;
      }
    }

    .footer {
      height: 10%;
      width: 100%;
      margin: 0 auto;
      text-align: center;
      color: #FFFFFF;
      font: {
        weight: 300;
        size: 30px;
      }
    }

    .login-item-right {
      width: 70%;
      height: 50%;
      margin: 50% auto;
      transform: translateY(-50%);

      .header {
        width: 100%;
        height: 10%;
        text-align: center;
        font: {
          weight: 500;
          size: 30px;
        }
      }

      .login-form {
        text-align: center;
        width: 100%;
        margin: 5% auto;

        .input {
          height: 40px;
        }

        .login-btn {
          width: 100%;
          height: 40px;
        }
      }
    }
  }
}
</style>
