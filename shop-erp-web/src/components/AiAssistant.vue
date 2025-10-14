<template>
  <div class="ai-assistant-container">
    <!-- 悬浮按钮 -->
    <div 
      class="ai-toggle-button" 
      @click="toggleAssistant"
      :style="{ backgroundColor: isHealthy ? '#409eff' : '#909399' }"
    >
      <template v-if="isMinimized">
        <QuestionCircleOutlined style="font-size: 20px;" />
      </template>
      <template v-else>
        <CloseOutlined style="font-size: 20px;" />
      </template>
      <span v-if="!isMinimized" class="ai-title">AI助手</span>
    </div>

    <!-- AI助手对话框 -->
    <div v-if="!isMinimized" class="ai-assistant-dialog">
      <div class="ai-header">
        <h3>AI智能助手</h3>
        <a-button 
          type="text" 
          @click="minimize"
          shape="circle"
        >
          <MinusOutlined />
        </a-button>
      </div>

      <div class="ai-content">
        <div class="ai-messages" ref="messagesContainer">
          <div 
            v-for="(message, index) in messages" 
            :key="index"
            :class="['message', message.type]"
          >
            <div class="message-content">
              <div class="avatar" :class="message.type">
                <UserOutlined v-if="message.type === 'user'" />
                <RobotOutlined v-else />
              </div>
              <div class="text">{{ message.content }}</div>
            </div>
          </div>
          <div v-if="isLoading" class="message ai">
            <div class="message-content">
              <div class="avatar ai">
                <RobotOutlined />
              </div>
              <div class="text">
                <LoadingOutlined /> 正在思考中...
              </div>
            </div>
          </div>
        </div>

        <div class="ai-input">
          <a-input
            v-model:value="inputMessage"
            placeholder="请输入您的问题..."
            @pressEnter="sendMessage"
            :disabled="isLoading || !isHealthy"
          >
            <template #addonAfter>
              <a-button 
                type="primary"
                @click="sendMessage"
                :disabled="isLoading || !isHealthy"
              >
                <SendOutlined />
              </a-button>
            </template>
          </a-input>
          
          <!-- 常见问题示例 -->
          <div class="ai-examples" v-if="messages.length <= 1">
            <div class="ai-example-title">您可以问：</div>
            <div class="ai-example-buttons">
              <a-button size="small" @click="sendExample('当前库存不足的商品有哪些？')">库存不足</a-button>
              <a-button size="small" @click="sendExample('上月销售总额是多少？')">上月销售</a-button>
              <a-button size="small" @click="sendExample('最畅销的商品是什么？')">畅销商品</a-button>
              <a-button size="small" @click="sendExample('查询商品名称')">商品信息</a-button>
              <a-button size="small" @click="sendExample('最近的进货订单有哪些？')">进货订单</a-button>
              <a-button size="small" @click="sendExample('客户信息查询')">客户信息</a-button>
            </div>
          </div>
          
          <div class="ai-status">
            <a-tag :color="isHealthy ? 'green' : 'red'" size="small">
              {{ isHealthy ? '服务正常' : '服务异常' }}
            </a-tag>
            <a-button 
              v-if="!isHealthy" 
              size="small" 
              @click="checkHealth" 
              style="margin-left: 10px;"
            >
              重新检查
            </a-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { 
  QuestionCircleOutlined,
  CloseOutlined,
  MinusOutlined,
  UserOutlined,
  RobotOutlined,
  LoadingOutlined,
  SendOutlined
} from '@ant-design/icons-vue';
import { aiChat, aiHealthCheck } from '@/api/ai.js'

export default {
  name: 'AiAssistant',
  components: {
    QuestionCircleOutlined,
    CloseOutlined,
    MinusOutlined,
    UserOutlined,
    RobotOutlined,
    LoadingOutlined,
    SendOutlined
  },
  data() {
    return {
      isMinimized: true,
      isHealthy: false,
      isLoading: false,
      inputMessage: '',
      messages: [
        {
          type: 'ai',
          content: '您好！我是您的AI助手，可以帮您查询库存、销售数据等信息。请问有什么可以帮助您的吗？'
        }
      ]
    }
  },
  mounted() {
    this.checkHealth()
    // 定期检查服务健康状态
    this.healthCheckInterval = setInterval(this.checkHealth, 30000)
  },
  beforeUnmount() {
    if (this.healthCheckInterval) {
      clearInterval(this.healthCheckInterval)
    }
  },
  methods: {
    toggleAssistant() {
      this.isMinimized = !this.isMinimized
      this.$nextTick(() => {
        this.scrollToBottom()
      })
    },
    minimize() {
      this.isMinimized = true
    },
    async checkHealth() {
      try {
        const response = await aiHealthCheck()
        this.isHealthy = response.success
        if (!response.success) {
          console.error('AI助手健康检查失败:', response.message)
          // 添加错误消息到聊天记录
          if (this.messages.length <= 1) {
            const errorMessage = {
              type: 'ai',
              content: `服务暂时不可用: ${response.message}`
            }
            this.messages.push(errorMessage)
          }
        }
      } catch (error) {
        this.isHealthy = false
        console.error('AI助手健康检查失败:', error)
        // 添加错误消息到聊天记录
        if (this.messages.length <= 1) {
          const errorMessage = {
            type: 'ai',
            content: '服务暂时不可用，请稍后再试。'
          }
          this.messages.push(errorMessage)
        }
      }
    },
    async sendMessage() {
      if (!this.inputMessage.trim() || this.isLoading || !this.isHealthy) {
        return
      }

      // 添加用户消息
      const userMessage = {
        type: 'user',
        content: this.inputMessage
      }
      this.messages.push(userMessage)

      // 清空输入框
      const query = this.inputMessage
      this.inputMessage = ''

      // 显示加载状态
      this.isLoading = true
      this.$nextTick(() => {
        this.scrollToBottom()
      })

      try {
        // 调用AI接口
        const response = await aiChat(query)
        if (response.success) {
          // 添加AI回复
          const aiMessage = {
            type: 'ai',
            content: response.data
          }
          this.messages.push(aiMessage)
        } else {
          throw new Error(response.message || 'AI助手服务异常')
        }
      } catch (error) {
        console.error('AI助手调用失败:', error)
        // 添加错误消息
        const errorMessage = {
          type: 'ai',
          content: '抱歉，AI助手暂时无法回答您的问题，请稍后再试。'
        }
        this.messages.push(errorMessage)
      } finally {
        this.isLoading = false
        this.$nextTick(() => {
          this.scrollToBottom()
        })
      }
    },
    sendExample(query) {
      this.inputMessage = query;
      this.sendMessage();
    },
    scrollToBottom() {
      this.$nextTick(() => {
        const container = this.$refs.messagesContainer
        if (container) {
          container.scrollTop = container.scrollHeight
        }
      })
    }
  }
}
</script>

<style scoped>
.ai-assistant-container {
  position: fixed;
  bottom: 20px;
  right: 20px;
  z-index: 9999;
}

.ai-toggle-button {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  cursor: pointer;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
}

.ai-toggle-button:hover {
  transform: scale(1.1);
}

.ai-toggle-button .ai-title {
  margin-left: 5px;
  font-size: 12px;
  white-space: nowrap;
}

.ai-assistant-dialog {
  width: 400px;
  height: 500px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.15);
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.ai-header {
  padding: 15px;
  background: #409eff;
  color: white;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.ai-header h3 {
  margin: 0;
  font-size: 16px;
}

.ai-content {
  flex: 1;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.ai-messages {
  flex: 1;
  padding: 15px;
  overflow-y: auto;
  display: flex;
  flex-direction: column;
}

.message {
  margin-bottom: 15px;
  display: flex;
}

.message.user {
  justify-content: flex-end;
}

.message.ai {
  justify-content: flex-start;
}

.message-content {
  display: flex;
  align-items: flex-start;
  max-width: 80%;
}

.avatar {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0 10px;
  flex-shrink: 0;
}

.avatar.user {
  background-color: #409eff;
  color: white;
}

.avatar.ai {
  background-color: #67c23a;
  color: white;
}

.text {
  padding: 10px;
  border-radius: 6px;
  word-wrap: break-word;
  line-height: 1.5;
}

.message.user .text {
  background-color: #409eff;
  color: white;
  border-top-right-radius: 0;
}

.message.ai .text {
  background-color: #f1f1f1;
  border-top-left-radius: 0;
}

.ai-input {
  padding: 15px;
  border-top: 1px solid #eee;
}

.ai-examples {
  margin-top: 10px;
}

.ai-example-title {
  font-size: 12px;
  color: #999;
  margin-bottom: 5px;
}

.ai-example-buttons {
  display: flex;
  gap: 5px;
  flex-wrap: wrap;
}

.ai-example-buttons .ant-btn {
  font-size: 12px;
}

.ai-status {
  margin-top: 10px;
  text-align: right;
  display: flex;
  justify-content: flex-end;
  align-items: center;
}

@media (max-width: 768px) {
  .ai-assistant-dialog {
    width: calc(100vw - 40px);
    height: calc(100vh - 40px);
  }
}
</style>