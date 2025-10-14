#!/bin/bash

# 进销存管理系统启动脚本

echo "正在启动进销存管理系统..."

# 启动后端服务
echo "正在启动后端服务..."
cd shop-erp
mvn spring-boot:run &
BACKEND_PID=$!
cd ..

# 等待后端服务启动
sleep 10

# 启动前端服务
echo "正在启动前端服务..."
cd shop-erp-web
npm run dev &
FRONTEND_PID=$!
cd ..

echo "系统启动完成！"
echo "后端服务 PID: $BACKEND_PID"
echo "前端服务 PID: $FRONTEND_PID"
echo "请在浏览器中访问 http://localhost:5173 使用系统"

# 等待用户按键
echo "按任意键停止服务..."
read -n 1 -s

# 停止服务
echo "正在停止服务..."
kill $BACKEND_PID
kill $FRONTEND_PID

echo "服务已停止"