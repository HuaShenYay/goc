# Shop ERP 数据库结构分析报告

## 1. 概述
本报告基于提供的 SQL 脚本（shop_erp.sql）对数据库结构进行分析。该数据库设计用于一个小型企业资源规划（ERP）系统，专注于商店或仓库的管理，涵盖客户、商品、供货商、仓库、用户、进出库订单、库存管理、转仓记录以及操作日志等功能。数据库使用 MySQL 引擎（InnoDB），支持外键约束、触发器和存储过程，以确保数据一致性和自动化处理。

数据库包含 11 个表、1 个存储过程和 3 个触发器。核心功能包括：
- **基础数据管理**：客户、供货商、商品、仓库和用户的 CRUD 操作。
- **库存管理**：商品库存的实时更新，包括进出库和转仓。
- **订单管理**：进货和销售订单的记录与处理。
- **日志与统计**：操作日志记录和进销存统计。

日期为 2024-12-23 生成的脚本，包含一些示例数据。以下逐一分析各表、字段及其功能。

## 2. 表结构分析
每个表包括主键（id，通常为自增整数）、常见字段（如 create_user_id、update_user_id、create_time、update_time、deleted 用于审计和软删除），以及特定业务字段。deleted 字段默认为 0，支持逻辑删除。

### 2.1 customer（客户表）
- **字段**：
  - id (INT, 主键)：客户ID。
  - name (VARCHAR(30))：客户名称。
  - sex (INT)：性别（1男/2女）。
  - tel (VARCHAR(15))：联系电话。
  - birthday (DATE)：生日。
  - province (VARCHAR(10))：省份代码。
  - city (VARCHAR(10))：城市代码。
  - address (VARCHAR(100))：详细地址。
  - 审计字段：create_user_id, update_user_id, create_time, update_time, deleted。
- **功能**：管理客户信息，支持客户档案的创建、查询、更新和删除。用于销售订单关联客户（例如，出库订单）。

### 2.2 goods（商品表）
- **字段**：
  - id (INT, 主键)：商品ID。
  - name (VARCHAR(50))：商品名称。
  - category_id (INT)：商品分类ID（外键引用 goods_category.id）。
  - out_price (DECIMAL(10,2))：售价。
  - standard (INT)：商品规格。
  - remark (VARCHAR(255))：商品描述。
  - stock (INT, 默认0)：总库存。
  - 审计字段：create_user_id, update_user_id, create_time, update_time, deleted。
- **功能**：管理商品基本信息和总库存。支持商品的添加、查询、更新和删除。库存通过触发器自动更新，与进出库和转仓功能联动。

### 2.3 goods_category（商品类别表）
- **字段**：
  - id (INT, 主键)：类别ID。
  - name (VARCHAR(50))：类别名称。
  - remark (VARCHAR(255))：分类描述。
  - 审计字段：create_user_id, update_user_id, create_time, update_time, deleted。
- **功能**：管理商品分类，支持多级或简单分类的维护。用于商品表的 category_id 外键关联，便于商品归类和查询。

### 2.4 operate_log（操作日志表）
- **字段**：
  - id (BIGINT, 主键)：日志ID。
  - referrer (VARCHAR(255))：来源地址。
  - remote_address (VARCHAR(255))：客户端地址。
  - method (VARCHAR(10))：请求方式（e.g., GET）。
  - request_url (VARCHAR(500))：接口地址。
  - action_name (VARCHAR(50))：操作名称。
  - request_param (JSON)：请求参数。
  - user_agent (VARCHAR(500))：用户代理。
  - user_id (INT)：用户ID。
  - phone (VARCHAR(30))：用户手机号。
  - username (VARCHAR(30))：用户姓名。
  - insert_date (DATETIME, 默认当前时间)：操作时间。
  - deleted (TINYINT, 默认0)。
- **功能**：记录系统操作日志，主要用于审计和追踪用户行为（如 API 调用）。支持分页查询日志，便于安全监控和问题排查。

### 2.5 purchase_sale_history（入库/出库记录表）
- **字段**：
  - id (INT, 主键)：记录ID。
  - order_id (INT)：订单ID（外键引用 purchase_sale_order.id）。
  - type (INT)：1入库/2出库。
  - repository_id (INT)：仓库ID（外键引用 repository.id）。
  - goods_id (INT)：商品ID（外键引用 goods.id）。
  - in_price (DECIMAL(10,2))：进价。
  - out_price (DECIMAL(10,2))：卖价。
  - count (INT)：数量。
  - 审计字段：create_user_id, update_user_id, create_time, update_time, deleted。
- **功能**：记录具体商品的进出库历史。支持订单拆分（一个订单多条记录）。通过触发器自动更新库存，确保数据一致性。

### 2.6 purchase_sale_order（进出库订单表）
- **字段**：
  - id (INT, 主键)：订单ID。
  - type (INT, 默认1)：1进货/2销售。
  - customer_id (INT)：客户ID（外键引用 customer.id，用于销售）。
  - supplier_id (INT)：供货商ID（外键引用 supplier.id，用于进货）。
  - total_count (INT)：总数量。
  - total_amount (DECIMAL(10,2))：总金额。
  - 审计字段：create_user_id, update_user_id, create_time, update_time, deleted。
- **功能**：管理进货和销售订单。支持关联客户/供货商，计算订单总额。结合 history 表实现订单明细和库存联动。

### 2.7 repository（仓库表）
- **字段**：
  - id (INT, 主键)：仓库ID。
  - name (VARCHAR(50))：仓库名称。
  - province (VARCHAR(10))：省份代码。
  - city (VARCHAR(10))：城市代码。
  - address (VARCHAR(100))：详细地址。
  - 审计字段：create_user_id, update_user_id, create_time, update_time, deleted。
- **功能**：管理仓库信息，支持多仓库架构。用于库存分配和转仓操作。

### 2.8 repository_stock（仓库商品库存表）
- **字段**：
  - id (INT, 主键)：库存ID。
  - repository_id (INT)：仓库ID（外键引用 repository.id）。
  - goods_id (INT)：商品ID（外键引用 goods.id）。
  - in_price (DECIMAL(10,2))：进价。
  - stock (INT)：分仓库库存。
  - 审计字段：create_user_id, update_user_id, create_time, update_time, deleted。
- **功能**：管理每个仓库的具体商品库存（分价位库存）。支持多仓库库存查询和更新，与总库存（goods.stock）联动。

### 2.9 supplier（供货商表）
- **字段**：
  - id (INT, 主键)：供货商ID。
  - name (VARCHAR(50))：供货商名称。
  - contact (VARCHAR(30))：联系人。
  - tel (VARCHAR(15))：联系电话。
  - province (VARCHAR(10))：省份代码。
  - city (VARCHAR(10))：城市代码。
  - address (VARCHAR(100))：详细地址。
  - 审计字段：create_user_id, update_user_id, create_time, update_time, deleted。
- **功能**：管理供货商信息，支持进货订单关联。

### 2.10 sys_user（系统用户/业务员表）
- **字段**：
  - id (INT, 主键)：用户ID。
  - name (VARCHAR(30))：姓名。
  - sex (INT)：性别（1男/2女）。
  - birthday (DATE)：出生日期。
  - phone (VARCHAR(11))：手机号（用于登录）。
  - password (VARCHAR(100))：加密密码。
  - is_system (TINYINT, 默认0)：是否系统用户。
  - 审计字段：create_user_id, update_user_id, create_time, update_time, deleted。
- **功能**：管理系统用户和业务员，支持认证和权限控制（e.g., 系统用户 vs. 普通业务员）。

### 2.11 transfer_history（转仓记录表）
- **字段**：
  - id (INT, 主键)：转仓ID。
  - source (INT)：源仓库ID（外键引用 repository.id）。
  - target (INT)：目标仓库ID（外键引用 repository.id）。
  - goods_id (INT)：商品ID（外键引用 goods.id）。
  - price (DECIMAL(10,2))：价格。
  - count (INT)：转仓数量。
  - 审计字段：create_user_id, update_user_id, create_time, update_time, deleted。
- **功能**：记录仓库间商品转移。通过触发器自动更新源/目标仓库库存。

## 3. 其他组件分析
### 3.1 存储过程：COUNT_PURCHASE_SALE
- **参数**：startDate (VARCHAR(15))、endDate (VARCHAR(15))。
- **功能**：统计指定日期范围内的商品采购（purchase）和销售（sale）数量。默认统计最近7天。输出格式：商品名称、采购次数、销售次数。用于进销存报表和分析。

### 3.2 触发器
- **PSH_SAVE_AFTER_TRIGGER**（purchase_sale_history 插入后）：自动更新仓库库存和总库存（入库加、出库减）。
- **PSH_BEFORE_BEFORE_TRIGGER**（purchase_sale_history 插入前）：为出库记录自动设置 out_price（从 goods 表获取）。
- **TH_SAVE_AFTER_TRIGGER**（transfer_history 插入后）：自动更新源/目标仓库库存（减源、加目标）。
- **功能**：确保库存实时一致，防止手动错误。支持事务性操作。

## 4. 整体功能概述
- **核心流程**：
  - **进货**：创建进货订单（type=1），关联供货商，记录历史，自动加库存。
  - **销售**：创建销售订单（type=2），关联客户，记录历史，自动减库存。
  - **转仓**：记录转移，自动调整仓库库存（总库存不变）。
  - **库存查询**：支持总库存和分仓库库存查看。
- **安全与审计**：所有表支持软删除和审计字段。操作日志记录所有 API 调用。
- **扩展性**：外键约束确保数据完整性。存储过程支持自定义统计。适合小型商店ERP，可扩展到多用户、多仓库场景。
- **潜在改进**：添加角色权限表、更多统计过程，或集成支付模块。

## 5. 结论
该数据库设计简洁高效，聚焦于进销存核心功能，适用于小型零售或仓库管理系统。通过触发器和存储过程实现了自动化和数据一致性。示例数据展示了实际使用场景（如空调、冰箱的进出库）。若需进一步优化，可考虑添加索引提升查询性能或集成视图简化报表。