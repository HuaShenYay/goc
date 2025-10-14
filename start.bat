@echo off
title 进销存管理系统启动脚本

echo 正在启动进销存管理系统...

REM 启动后端服务
echo 正在启动后端服务...
cd shop-erp
start "后端服务" mvn spring-boot:run
cd ..

REM 等待后端服务启动
timeout /t 10 /nobreak >nul

REM 启动前端服务
echo 正在启动前端服务...
cd shop-erp-web
start "前端服务" npm run dev
cd ..

echo 系统启动完成！
echo 请在浏览器中访问 http://localhost:5173 使用系统
echo 按任意键停止服务...
pause >nul

echo 正在停止服务...
taskkill /f /im java.exe
taskkill /f /im node.exe

echo 服务已停止