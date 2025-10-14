# 进销存管理系统后端服务运行脚本

Write-Host "正在启动进销存管理系统后端服务..."

# 检查Java是否安装
try {
    $javaVersion = java -version 2>&1
    Write-Host "Java版本: $javaVersion"
} catch {
    Write-Host "错误: 未找到Java运行环境，请先安装Java 17或更高版本。" -ForegroundColor Red
    exit 1
}

# 进入后端项目目录
Set-Location -Path "shop-erp"

# 检查是否已编译项目
if (!(Test-Path "target/*.jar")) {
    Write-Host "未找到编译后的JAR文件。" -ForegroundColor Yellow
    Write-Host "请先使用Maven编译项目:" -ForegroundColor Yellow
    Write-Host "  mvn clean package" -ForegroundColor Yellow
    Write-Host "或者使用IDE运行项目。" -ForegroundColor Yellow
    exit 1
}

# 查找JAR文件
$jarFile = Get-ChildItem -Path "target" -Filter "*.jar" | Select-Object -First 1

if ($null -eq $jarFile) {
    Write-Host "错误: 未找到可运行的JAR文件。" -ForegroundColor Red
    exit 1
}

Write-Host "正在运行后端服务: $($jarFile.Name)"
Write-Host "服务地址: http://localhost:8080/shop-erp"
Write-Host "按 Ctrl+C 停止服务"

# 运行JAR文件
java -jar $jarFile.FullName