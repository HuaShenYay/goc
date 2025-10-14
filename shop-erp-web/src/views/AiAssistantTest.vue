<template>
  <div class="ai-test-container">
    <h2>AI助手功能测试</h2>
    
    <el-card class="test-card">
      <div slot="header">
        <span>AI助手连接测试</span>
      </div>
      
      <el-form :model="testForm" label-width="120px">
        <el-form-item label="测试问题">
          <el-input 
            v-model="testForm.question" 
            placeholder="请输入要测试的问题，例如：查询商品库存"
            style="width: 300px;"
          ></el-input>
        </el-form-item>
        
        <el-form-item>
          <el-button 
            type="primary" 
            @click="testAiAssistant" 
            :loading="isTesting"
            :disabled="!testForm.question.trim()"
          >
            测试AI助手
          </el-button>
          <el-button 
            @click="testHealthCheck" 
            :loading="isCheckingHealth"
            style="margin-left: 10px;"
          >
            健康检查
          </el-button>
        </el-form-item>
      </el-form>
      
      <el-divider></el-divider>
      
      <div v-if="testResult">
        <h3>测试结果</h3>
        <el-alert 
          :type="testResult.success ? 'success' : 'error'" 
          :closable="false"
        >
          <div v-if="testResult.success">
            <p><strong>AI助手回答：</strong></p>
            <p>{{ testResult.data }}</p>
          </div>
          <div v-else>
            <p><strong>错误信息：</strong></p>
            <p>{{ testResult.message }}</p>
          </div>
        </el-alert>
      </div>
      
      <div v-if="healthResult">
        <h3>健康检查结果</h3>
        <el-alert 
          :type="healthResult.success ? 'success' : 'error'" 
          :closable="false"
        >
          <div>
            <p>{{ healthResult.message }}</p>
          </div>
        </el-alert>
      </div>
    </el-card>
  </div>
</template>

<script>
import { aiChat, aiHealthCheck } from '@/api/ai.js'

export default {
  name: 'AiAssistantTest',
  data() {
    return {
      testForm: {
        question: ''
      },
      isTesting: false,
      isCheckingHealth: false,
      testResult: null,
      healthResult: null
    }
  },
  methods: {
    async testAiAssistant() {
      if (!this.testForm.question.trim()) {
        this.$message.warning('请输入测试问题')
        return
      }
      
      this.isTesting = true
      this.testResult = null
      
      try {
        const response = await aiChat(this.testForm.question)
        this.testResult = response
        if (response.success) {
          this.$message.success('AI助手响应成功')
        } else {
          this.$message.error(response.message || 'AI助手服务异常')
        }
      } catch (error) {
        console.error('测试AI助手失败:', error)
        this.testResult = {
          success: false,
          message: '网络错误或服务不可用'
        }
        this.$message.error('测试失败，请检查网络连接和服务状态')
      } finally {
        this.isTesting = false
      }
    },
    
    async testHealthCheck() {
      this.isCheckingHealth = true
      this.healthResult = null
      
      try {
        const response = await aiHealthCheck()
        this.healthResult = response
        if (response.success) {
          this.$message.success('健康检查通过')
        } else {
          this.$message.error('健康检查失败')
        }
      } catch (error) {
        console.error('健康检查失败:', error)
        this.healthResult = {
          success: false,
          message: '网络错误或服务不可用'
        }
        this.$message.error('健康检查失败，请检查网络连接和服务状态')
      } finally {
        this.isCheckingHealth = false
      }
    }
  }
}
</script>

<style scoped>
.ai-test-container {
  padding: 20px;
  max-width: 800px;
  margin: 0 auto;
}

.test-card {
  margin-top: 20px;
}

.el-form-item {
  margin-bottom: 20px;
}
</style>