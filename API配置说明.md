# API配置说明

## 当前API请求流程

### 前端请求路径
前端API调用使用相对路径：
- AI聊天接口: `/ai/chat`
- 健康检查接口: `/ai/health`

### 请求构建过程
1. **基础路径配置**: 在[request.js](file:///c:/Users/25656/Documents/%E4%B8%93%E4%B8%9A%E5%AE%9E%E4%B9%A0%E9%A1%B9%E7%9B%AE%E4%BB%A3%E7%A0%81/%E8%BF%9B%E9%94%80%E5%AD%98%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9Fb24015107%20%E5%BC%A0%E8%90%8C%E8%90%8C/%E8%BF%9B%E9%94%80%E5%AD%98%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F/%E6%BA%90%E7%A0%81/shop-erp-web/src/util/request.js)中配置了baseURL:
   ```javascript
   baseURL: import.meta.env.VITE_BASE_API || '/shop-erp'
   ```

2. **环境变量配置**: 在[.env](file:///c:/Users/25656/Documents/%E4%B8%93%E4%B8%9A%E5%AE%9E%E4%B9%A0%E9%A1%B9%E7%9B%AE%E4%BB%A3%E7%A0%81/%E8%BF%9B%E9%94%80%E5%AD%98%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9Fb24015107%20%E5%BC%A0%E8%90%8C%E8%90%8C/%E8%BF%9B%E9%94%80%E5%AD%98%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F/%E6%BA%90%E7%A0%81/shop-erp-web/.env)文件中配置了:
   ```bash
   VITE_BASE_API=/shop-erp
   ```

3. **最终请求路径**: 
   - AI聊天接口完整路径: `/shop-erp/ai/chat`
   - 健康检查接口完整路径: `/shop-erp/ai/health`

### 请求代理配置
在[vite.config.js](file:///c:/Users/25656/Documents/%E4%B8%93%E4%B8%9A%E5%AE%9E%E4%B9%A0%E9%A1%B9%E7%9B%AE%E4%BB%A3%E7%A0%81/%E8%BF%9B%E9%94%80%E5%AD%98%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9Fb24015107%20%E5%BC%A0%E8%90%8C%E8%90%8C/%E8%BF%9B%E9%94%80%E5%AD%98%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F/%E6%BA%90%E7%A0%81/shop-erp-web/vite.config.js)中配置了代理:
```javascript
server: {
  proxy: {
    '/shop-erp': {
      target: 'http://localhost:8080',
      changeOrigin: true,
      rewrite: (path) => path.replace(/^\/shop-erp/, '/shop-erp')
    }
  }
}
```

### 完整请求流程
1. 前端发起请求到 `/shop-erp/ai/chat`
2. Vite开发服务器接收到请求
3. 根据代理配置，将请求转发到 `http://localhost:8080/shop-erp/ai/chat`
4. 后端Spring Boot应用接收并处理请求
5. 返回响应给前端

### 端口配置说明
- **前端服务**: 默认运行在5175端口（可能根据占用情况自动调整）
- **后端服务**: 运行在8080端口
- **实际请求**: `http://localhost:5175/shop-erp/ai/chat` → `http://localhost:8080/shop-erp/ai/chat`

## 验证配置是否正确

### 1. 检查环境变量
确保[.env](file:///c:/Users/25656/Documents/%E4%B8%93%E4%B8%9A%E5%AE%9E%E4%B9%A0%E9%A1%B9%E7%9B%AE%E4%BB%A3%E7%A0%81/%E8%BF%9B%E9%94%80%E5%AD%98%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9Fb24015107%20%E5%BC%A0%E8%90%8C%E8%90%8C/%E8%BF%9B%E9%94%80%E5%AD%98%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F/%E6%BA%90%E7%A0%81/shop-erp-web/.env)文件中配置正确:
```
VITE_BASE_API=/shop-erp
```

### 2. 检查代理配置
确保[vite.config.js](file:///c:/Users/25656/Documents/%E4%B8%93%E4%B8%9A%E5%AE%9E%E4%B9%A0%E9%A1%B9%E7%9B%AE%E4%BB%A3%E7%A0%81/%E8%BF%9B%E9%94%80%E5%AD%98%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9Fb24015107%20%E5%BC%A0%E8%90%8C%E8%90%8C/%E8%BF%9B%E9%94%80%E5%AD%98%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F/%E6%BA%90%E7%A0%81/shop-erp-web/vite.config.js)中的代理配置正确:
```javascript
proxy: {
  '/shop-erp': {
    target: 'http://localhost:8080',
    changeOrigin: true,
    rewrite: (path) => path.replace(/^\/shop-erp/, '/shop-erp')
  }
}
```

### 3. 检查API调用
确保[ai.js](file:///c:/Users/25656/Documents/%E4%B8%93%E4%B8%9A%E5%AE%9E%E4%B9%A0%E9%A1%B9%E7%9B%AE%E4%BB%A3%E7%A0%81/%E8%BF%9B%E9%94%80%E5%AD%98%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9Fb24015107%20%E5%BC%A0%E8%90%8C%E8%90%8C/%E8%BF%9B%E9%94%80%E5%AD%98%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F/%E6%BA%90%E7%A0%81/shop-erp-web/src/api/ai.js)中的API调用使用相对路径:
```javascript
export function aiChat(query) {
    return request({
        url: '/ai/chat',
        method: 'POST',
        params: { query }
    })
}
```

## 常见问题排查

### 1. 请求发送到错误的端口
如果发现请求发送到了类似 `http://localhost:5176/shop-erp/ai/chat`，请检查：
- 浏览器地址栏是否正确
- 前端服务是否在正确的端口运行
- 是否有浏览器缓存问题

### 2. 代理未生效
如果请求没有被正确代理到8080端口，请检查：
- Vite配置是否正确
- 前端服务是否重启
- 控制台是否有代理相关的错误信息

### 3. 跨域问题
如果出现跨域错误，请检查：
- 后端是否配置了CORS
- Vite代理配置是否正确
- 请求头是否正确设置

## 测试方法

### 1. 使用浏览器开发者工具
打开浏览器开发者工具，在Network标签页查看请求详情，确认：
- 请求URL是否正确
- 请求是否被正确代理
- 响应状态码和内容

### 2. 使用AI测试页面
访问系统中的AI测试页面，测试连接和聊天功能。

### 3. 直接访问后端接口
使用Postman或curl直接访问后端接口:
```bash
# 健康检查
curl http://localhost:8080/shop-erp/ai/health

# AI聊天
curl -X POST "http://localhost:8080/shop-erp/ai/chat?query=你好"
```