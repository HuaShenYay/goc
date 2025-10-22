/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : shop_erp

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 23/12/2024 16:41:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '客户ID',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户名称',
  `sex` int NULL DEFAULT NULL COMMENT '性别 1男/2女',
  `tel` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户联系电话',
  `birthday` date NULL DEFAULT NULL COMMENT '客户生日',
  `province` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户省份',
  `city` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户城市',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建用户ID',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '更新用户ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '客户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (3, '张凯', 1, '15623258031', '1993-05-05', '130000', '130100', '1', 1, 1, '2024-09-26 13:39:55', '2024-09-26 13:39:55', 0);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名称',
  `category_id` int NULL DEFAULT NULL COMMENT '商品分类',
  `out_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '售价',
  `standard` int NULL DEFAULT NULL COMMENT '商品规格',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品描述',
  `stock` int NULL DEFAULT 0 COMMENT '库存',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建用户ID',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '更新用户ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `G_CATEGORY_FOREIGN_KEY`(`category_id`) USING BTREE,
  CONSTRAINT `G_CATEGORY_FOREIGN_KEY` FOREIGN KEY (`category_id`) REFERENCES `goods_category` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (4, '空调', 6, 2999.00, 4, '家用挂机', 29, 1, 1, '2024-09-26 13:56:46', '2024-09-26 14:07:43', 0);
INSERT INTO `goods` VALUES (5, '冰箱', 6, 3999.00, 4, '家用冰箱', 9, 1, 1, '2024-09-26 13:58:06', '2024-09-26 14:07:43', 0);

-- ----------------------------
-- Table structure for goods_category
-- ----------------------------
DROP TABLE IF EXISTS `goods_category`;
CREATE TABLE `goods_category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '商品类别ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品类别名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类描述',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建用户ID',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '更新用户ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品类别' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_category
-- ----------------------------
INSERT INTO `goods_category` VALUES (5, '日用百货', NULL, 1, 1, '2024-09-26 13:29:31', '2024-09-26 13:29:31', 0);
INSERT INTO `goods_category` VALUES (6, '家用电器', '家用电器', 1, 1, '2024-09-26 13:30:06', '2024-09-26 13:30:06', 0);

-- ----------------------------
-- Table structure for operate_log
-- ----------------------------
DROP TABLE IF EXISTS `operate_log`;
CREATE TABLE `operate_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `referrer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'referrer地址',
  `remote_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户端地址',
  `method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方式',
  `request_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '接口地址',
  `action_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作名称',
  `request_param` json NULL COMMENT '请求参数',
  `user_agent` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户agent',
  `user_id` int NOT NULL COMMENT '用户编号',
  `phone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `insert_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  `deleted` tinyint(1) NULL DEFAULT 0,
  UNIQUE INDEX `index`(`id`) USING BTREE,
  INDEX `user_index`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4294 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of operate_log
-- ----------------------------

-- ----------------------------
-- Table structure for purchase_sale_history
-- ----------------------------
DROP TABLE IF EXISTS `purchase_sale_history`;
CREATE TABLE `purchase_sale_history`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '入库/出库ID',
  `order_id` int NULL DEFAULT NULL COMMENT '订单ID',
  `type` int NULL DEFAULT NULL COMMENT '1进/2出',
  `repository_id` int NULL DEFAULT NULL COMMENT '仓库ID',
  `goods_id` int NULL DEFAULT NULL COMMENT '商品ID',
  `in_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '进价',
  `out_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '卖价',
  `count` int NULL DEFAULT NULL COMMENT '数量',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建用户ID',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '更新用户ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `PS_GOODS_FOREIGN_KEY`(`goods_id`) USING BTREE,
  INDEX `PS_REPO_FOREIGN_KEY`(`repository_id`) USING BTREE,
  INDEX `PS_ORDER_FOREIGN_KEY`(`order_id`) USING BTREE,
  CONSTRAINT `PS_GOODS_FOREIGN_KEY` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `PS_ORDER_FOREIGN_KEY` FOREIGN KEY (`order_id`) REFERENCES `purchase_sale_order` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `PS_REPO_FOREIGN_KEY` FOREIGN KEY (`repository_id`) REFERENCES `repository` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '入库/出库记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchase_sale_history
-- ----------------------------
INSERT INTO `purchase_sale_history` VALUES (63, 31, 1, 3, 4, 2000.00, NULL, 10, 1, 1, '2024-09-26 14:00:20', '2024-09-26 14:00:20', 0);
INSERT INTO `purchase_sale_history` VALUES (64, 31, 1, 4, 4, 2000.00, NULL, 20, 1, 1, '2024-09-26 14:00:20', '2024-09-26 14:00:20', 0);
INSERT INTO `purchase_sale_history` VALUES (65, 32, 1, 3, 5, 2500.00, NULL, 10, 1, 1, '2024-09-26 14:07:17', '2024-09-26 14:07:17', 0);
INSERT INTO `purchase_sale_history` VALUES (66, 33, 2, 3, 5, 2500.00, 3999.00, 1, 1, 1, '2024-09-26 14:07:43', '2024-09-26 14:07:43', 0);
INSERT INTO `purchase_sale_history` VALUES (67, 33, 2, 3, 4, 2000.00, 2999.00, 1, 1, 1, '2024-09-26 14:07:43', '2024-09-26 14:07:43', 0);

-- ----------------------------
-- Table structure for purchase_sale_order
-- ----------------------------
DROP TABLE IF EXISTS `purchase_sale_order`;
CREATE TABLE `purchase_sale_order`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `type` int NULL DEFAULT 1 COMMENT '1进/2出',
  `customer_id` int NULL DEFAULT NULL COMMENT '客户ID',
  `supplier_id` int NULL DEFAULT NULL COMMENT '供货商ID',
  `total_count` int NULL DEFAULT NULL COMMENT '总数量',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建用户ID',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '更新用户ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `PSO_SUPPLIER_ID_FOREIGN_KEY`(`supplier_id`) USING BTREE,
  INDEX `PSO_CUSTOMER_ID_FOREIGN_KEY`(`customer_id`) USING BTREE,
  CONSTRAINT `PSO_CUSTOMER_ID_FOREIGN_KEY` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `PSO_SUPPLIER_ID_FOREIGN_KEY` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '进出库订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchase_sale_order
-- ----------------------------
INSERT INTO `purchase_sale_order` VALUES (31, 1, NULL, 3, 30, 60000.00, 1, 1, '2024-09-26 14:00:20', '2024-09-26 14:00:20', 0);
INSERT INTO `purchase_sale_order` VALUES (32, 1, NULL, 3, 10, 25000.00, 1, 1, '2024-09-26 14:07:17', '2024-09-26 14:07:17', 0);
INSERT INTO `purchase_sale_order` VALUES (33, 2, 3, NULL, 2, 6998.00, 1, 1, '2024-09-26 14:07:43', '2024-09-26 14:07:43', 0);

-- ----------------------------
-- Table structure for repository
-- ----------------------------
DROP TABLE IF EXISTS `repository`;
CREATE TABLE `repository`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '仓库ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库名',
  `province` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '城市',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建用户ID',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '更新用户ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '仓库' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repository
-- ----------------------------
INSERT INTO `repository` VALUES (3, '武汉仓库', '420000', '420100', '洪山区珞狮南路', 1, 1, '2024-09-26 13:41:29', '2024-09-26 13:41:29', 0);
INSERT INTO `repository` VALUES (4, '襄阳仓库', '420000', '420600', '樊城区', 1, 1, '2024-09-26 13:44:49', '2024-09-26 13:44:49', 0);

-- ----------------------------
-- Table structure for repository_stock
-- ----------------------------
DROP TABLE IF EXISTS `repository_stock`;
CREATE TABLE `repository_stock`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '仓库商品ID',
  `repository_id` int NULL DEFAULT NULL COMMENT '仓库ID',
  `goods_id` int NULL DEFAULT NULL COMMENT '商品ID',
  `in_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '进价',
  `stock` int NULL DEFAULT NULL COMMENT '库存',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建用户ID',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '更新用户ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `RS_GOODS_FOREIGN_KEY`(`goods_id`) USING BTREE,
  INDEX `RS_REPO_FOREIGN_KEY`(`repository_id`) USING BTREE,
  CONSTRAINT `RS_GOODS_FOREIGN_KEY` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `RS_REPO_FOREIGN_KEY` FOREIGN KEY (`repository_id`) REFERENCES `repository` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '仓库商品库存' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repository_stock
-- ----------------------------
INSERT INTO `repository_stock` VALUES (22, 3, 4, 2000.00, 10, 1, 1, '2024-09-26 14:00:20', '2024-09-26 14:07:43', 0);
INSERT INTO `repository_stock` VALUES (23, 4, 4, 2000.00, 19, 1, 1, '2024-09-26 14:00:20', '2024-09-26 14:00:20', 0);
INSERT INTO `repository_stock` VALUES (24, 3, 5, 2500.00, 9, 1, 1, '2024-09-26 14:07:17', '2024-09-26 14:07:43', 0);

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '供货商ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供货商名称',
  `contact` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人',
  `tel` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `province` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '城市',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建用户ID',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '更新用户ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '供货商' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES (3, '美的', '张三', '13294258778', '420000', '420100', '江夏区', 1, 1, '2024-09-26 13:33:29', '2024-09-26 13:33:29', 0);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '业务员ID',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` int NULL DEFAULT NULL COMMENT '性别 1男/2女',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `is_system` tinyint(1) NULL DEFAULT 0 COMMENT '是否系统用户',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建用户ID',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '更新用户ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统用户/业务员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'GS', 1, '1998-01-08', '12345678911', '$2a$10$6iE6gvw4jKduO2Xo5t1LAeEXCiLbitVnSBkq14VtbhMWHr3cO9GV.', 1, 1, 1, '2024-09-26 17:49:13', '2024-09-26 10:07:06', 0);
INSERT INTO `sys_user` VALUES (6, '张三', 1, '2000-03-01', '13294258711', '$2a$10$maxgxi.wAtMeZA8VTzf7aOs9OnkQGJkIHHP0qpkAfSr1AMJNrNCu.', 0, 1, 1, '2024-09-26 13:27:19', '2024-09-26 13:27:19', 0);

-- ----------------------------
-- Table structure for transfer_history
-- ----------------------------
DROP TABLE IF EXISTS `transfer_history`;
CREATE TABLE `transfer_history`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '转仓ID',
  `source` int NULL DEFAULT NULL COMMENT '源仓库ID',
  `target` int NULL DEFAULT NULL COMMENT '目标仓库ID',
  `goods_id` int NULL DEFAULT NULL COMMENT '商品ID',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `count` int NULL DEFAULT NULL COMMENT '转仓数量',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建用户ID',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '更新用户ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `T_SOURCE_FOREIGN_KEY`(`source`) USING BTREE,
  INDEX `T_TARGET_FOREIGN_KEY`(`target`) USING BTREE,
  INDEX `T_GOODS_FOREIGN_KEY`(`goods_id`) USING BTREE,
  CONSTRAINT `T_GOODS_FOREIGN_KEY` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `T_SOURCE_FOREIGN_KEY` FOREIGN KEY (`source`) REFERENCES `repository` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `T_TARGET_FOREIGN_KEY` FOREIGN KEY (`target`) REFERENCES `repository` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '转仓记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of transfer_history
-- ----------------------------
INSERT INTO `transfer_history` VALUES (22, 4, 3, 4, 2000.00, 1, 1, 1, '2024-09-26 14:12:49', '2024-09-26 14:12:49', 0);

-- ----------------------------
-- Procedure structure for COUNT_PURCHASE_SALE
-- ----------------------------
DROP PROCEDURE IF EXISTS `COUNT_PURCHASE_SALE`;
delimiter ;;
CREATE PROCEDURE `COUNT_PURCHASE_SALE`(startDate VARCHAR(15), endDate VARCHAR(15))
BEGIN
	#Routine body goes here...
	DECLARE s VARCHAR(15);
	DECLARE e VARCHAR(15);
	
	IF startDate IS NULL || TRIM(startDate) = '' THEN
		SET s = DATE_SUB(CURDATE(), INTERVAL 7 DAY);
	ELSE
		SET s = startDate;
	END IF;
	
	IF endDate IS NULL || TRIM(endDate) = ''  THEN
		SET e = CURDATE();
	ELSE
		SET e = endDate;
	END IF;
	
	SELECT g.name goods,
SUM(CASE psh.type WHEN 1 THEN 1 ELSE 0 END) `purchase`,
SUM(CASE psh.type WHEN 2 THEN 1 ELSE 0 END) `sale`
FROM goods g LEFT JOIN purchase_sale_history psh ON psh.goods_id = g.id AND DATE_FORMAT(psh.create_time, '%Y-%m-%d') >= s AND DATE_FORMAT(psh.create_time, '%Y-%m-%d') <= e GROUP BY g.id,g.`name`;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table purchase_sale_history
-- ----------------------------
DROP TRIGGER IF EXISTS `PSH_SAVE_AFTER_TRIGGER`;
delimiter ;;
CREATE TRIGGER `PSH_SAVE_AFTER_TRIGGER` AFTER INSERT ON `purchase_sale_history` FOR EACH ROW BEGIN
	DECLARE stockId INT;
	-- 查询库存ID
	SELECT id INTO stockId FROM repository_stock WHERE (repository_id, goods_id, in_price) = (NEW.repository_id, NEW.goods_id, NEW.in_price);
	-- 判断是入库还是出库
  IF (NEW.type = 1) THEN
		-- 更新分库库存
		IF stockId IS NULL THEN
			INSERT INTO repository_stock VALUES(null, NEW.repository_id, NEW.goods_id, NEW.in_price, NEW.count, NEW.create_user_id, NEW.update_user_id, NEW.create_time, NEW.update_time, 0);
		ELSE
			UPDATE repository_stock SET stock = stock + NEW.count, update_time = NEW.update_time, update_user_id = NEW.update_user_id WHERE id = stockId;
		END IF;
		-- 更新总库存
		UPDATE goods set stock = stock + NEW.count, update_time = NEW.update_time, update_user_id = NEW.update_user_id WHERE id = NEW.goods_id;
	ELSE
		-- 更新分库库存
		UPDATE repository_stock SET stock = stock - NEW.count, update_time = NEW.update_time, update_user_id = NEW.update_user_id WHERE id = stockId AND stock - NEW.count >= 0;
		-- 更新总库库存
		UPDATE goods SET stock = stock - NEW.count, update_time = NEW.update_time, update_user_id = NEW.update_user_id WHERE id = NEW.goods_id AND stock - NEW.count >= 0;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table purchase_sale_history
-- ----------------------------
DROP TRIGGER IF EXISTS `PSH_BEFORE_BEFORE_TRIGGER`;
delimiter ;;
CREATE TRIGGER `PSH_BEFORE_BEFORE_TRIGGER` BEFORE INSERT ON `purchase_sale_history` FOR EACH ROW BEGIN
	DECLARE price DECIMAL(10, 2);
	
	IF (NEW.type = 2) THEN
		SELECT out_price INTO price FROM goods WHERE id = NEW.goods_id;
		SET NEW.out_price = price;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table transfer_history
-- ----------------------------
DROP TRIGGER IF EXISTS `TH_SAVE_AFTER_TRIGGER`;
delimiter ;;
CREATE TRIGGER `TH_SAVE_AFTER_TRIGGER` AFTER INSERT ON `transfer_history` FOR EACH ROW BEGIN
	DECLARE tid INT;
	-- 查询目标库是否存在该商品
	SELECT id INTO tid FROM repository_stock WHERE (repository_id, goods_id, in_price) = (NEW.target, NEW.goods_id, NEW.price);
	-- 判断是否为空
	IF tid IS NULL THEN
		INSERT INTO repository_stock VALUES(null, NEW.target, NEW.goods_id, NEW.price, NEW.count, NEW.create_user_id, NEW.update_user_id, NEW.create_time, NEW.update_time, 0);
	ELSE
		UPDATE repository_stock SET stock = stock + NEW.count WHERE id = tid;
	END IF;
	-- 更新原仓库库存
	UPDATE repository_stock SET stock = stock - NEW.count WHERE repository_id = NEW.source AND goods_id = NEW.goods_id AND in_price = NEW.price AND stock - NEW.count >= 0;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
