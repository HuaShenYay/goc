# 进销存管理系统 - AI智能助手功能

## 功能介绍

本系统在原有进销存管理功能的基础上，增加了AI智能数据问答助手功能。该功能结合系统的业务数据和数据库内容，通过集成硅基流动AI大模型API实现智能问答。

## 功能特性

1. **悬浮式AI助手控件**：在每个页面右下角添加悬浮式的AI助手控件，方便随时使用
2. **自然语言查询**：支持使用自然语言查询库存、销售、采购等业务数据
3. **智能回答**：AI助手能够理解业务问题并提供准确的回答
4. **健康检查**：提供AI助手服务的健康检查功能

## 技术架构

### 后端技术栈
- Java 17
- Spring Boot 2.5.4
- MyBatis Plus
- Maven
- 硅基流动AI API

### 前端技术栈
- Vue 3
- Element Plus
- Vite

## 配置说明

### 硅基流动AI API配置

在[application.yml](file:///c:/Users/25656/Documents/%E4%B8%93%E4%B8%9A%E5%AE%9E%E4%B9%A0%E9%A1%B9%E7%9B%AE%E4%BB%A3%E7%A0%81/%E8%BF%9B%E9%94%80%E5%AD%98%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9Fb24015107%20%E5%BC%A0%E8%90%8C%E8%90%8C/%E8%BF%9B%E9%94%80%E5%AD%98%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F/%E6%BA%90%E7%A0%81/shop-erp/src/main/resources/application.yml)文件中配置硅基流动AI API的密钥和相关信息：

```yaml
siliconflow:
  api-key: YOUR_SILICONFLOW_API_KEY
  api-url: https://api.siliconflow.cn/v1
  model: Qwen/QwQ-32B
```

### 数据库配置

确保数据库连接信息正确配置：

```yaml
spring:
  datasource:
    url: jdbc:mysql:///shop_erp?characterEncoding=UTF8
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: 123456
```

## 部署说明

### 后端部署

1. 确保已安装Java 17和Maven
2. 在[application.yml](file:///c:/Users/25656/Documents/%E4%B8%93%E4%B8%9A%E5%AE%9E%E4%B9%A0%E9%A1%B9%E7%9B%AE%E4%BB%A3%E7%A0%81/%E8%BF%9B%E9%94%80%E5%AD%98%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9Fb24015107%20%E5%BC%A0%E8%90%8C%E8%90%8C/%E8%BF%9B%E9%94%80%E5%AD%98%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F/%E6%BA%90%E7%A0%81/shop-erp/src/main/resources/application.yml)中配置正确的数据库连接和AI API密钥
3. 运行以下命令编译和启动应用：
   ```bash
   cd shop-erp
   mvn clean package
   java -jar target/shop-erp-0.0.1-SNAPSHOT.jar
   ```

### 前端部署

1. 确保已安装Node.js和npm
2. 安装依赖：
   ```bash
   cd shop-erp-web
   npm install
   ```
3. 启动开发服务器：
   ```bash
   npm run dev
   ```
4. 或构建生产版本：
   ```bash
   npm run build
   ```

## 使用说明

### AI助手使用

1. 在系统任意页面的右下角找到AI助手悬浮按钮
2. 点击按钮打开AI助手对话框
3. 输入自然语言问题，例如：
   - "查询所有商品的库存情况"
   - "哪个商品的库存最多？"
   - "查询北京仓库的商品库存"
4. AI助手会自动分析问题并提供相应的回答

### 支持的查询类型

- 商品库存查询
- 商品价格信息
- 仓库信息查询
- 销售统计分析
- 采购记录查询

## API接口

### AI助手接口

- `POST /ai/chat` - AI助手问答接口
- `GET /ai/health` - AI助手健康检查接口

## 注意事项

1. 请妥善保管硅基流动AI API密钥，不要泄露给他人
2. AI助手的回答基于系统当前的业务数据
3. 为保证安全，AI助手只能执行SELECT查询，不能执行修改数据的操作
4. 如遇到AI助手服务异常，请检查网络连接和API密钥配置

## 故障排除

### AI助手无法响应

1. 检查网络连接是否正常
2. 验证硅基流动AI API密钥是否正确配置
3. 检查后端服务是否正常运行
4. 查看后端日志获取更多错误信息

### 查询结果不准确

1. 尝试更明确地描述问题
2. 检查系统中的数据是否完整和准确
3. 如果问题持续存在，请联系系统管理员

## 联系方式

如有任何问题或建议，请联系系统开发团队。