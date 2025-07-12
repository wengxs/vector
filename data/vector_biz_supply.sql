/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50741 (5.7.41-log)
 Source Host           : localhost:3306
 Source Schema         : vector_biz_supply

 Target Server Type    : MySQL
 Target Server Version : 50741 (5.7.41-log)
 File Encoding         : 65001

 Date: 12/04/2025 01:37:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for scm_purchase
-- ----------------------------
DROP TABLE IF EXISTS `scm_purchase`;
CREATE TABLE `scm_purchase`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
  `purchase_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '订单编号',
  `purchase_status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '订单状态',
  `supplier_id` bigint(20) NOT NULL COMMENT '供应商ID',
  `purchase_remake` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '采购备注',
  `amount` decimal(10, 4) NULL DEFAULT NULL COMMENT '订单金额',
  `signed_time` datetime NULL DEFAULT NULL COMMENT '签约时间',
  `finished_time` datetime NULL DEFAULT NULL COMMENT '完结时间',
  `canceled_time` datetime NULL DEFAULT NULL COMMENT '取消时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '采购单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scm_purchase
-- ----------------------------
INSERT INTO `scm_purchase` VALUES (2, '2024-03-17 01:08:12', 1, '2024-03-20 02:19:44', 1, 'P2024031700002', 'FINISHED', 1, '采购备注采购备注采购备注采购备注采购备注采购备注采购备注', NULL, '2024-03-18 16:28:15', '2024-03-20 02:19:45', NULL);
INSERT INTO `scm_purchase` VALUES (3, '2024-03-19 23:25:52', 1, '2024-03-20 02:19:32', 1, 'P2024031900001', 'FINISHED', 1, '采购备注', 183.3315, '2024-03-19 23:27:49', '2024-03-20 02:19:32', NULL);
INSERT INTO `scm_purchase` VALUES (4, '2024-05-13 00:26:44', 1, '2024-05-13 00:28:26', 1, 'SP2024051300001', 'CANCELED', 1, '测试供应商测试供应商测试供应商测试供应商测试供应商测试供应商测试供应商测试供应商', 6.0000, NULL, NULL, '2024-05-13 00:28:26');
INSERT INTO `scm_purchase` VALUES (5, '2024-05-13 19:55:58', 1, '2024-05-13 21:21:22', 1, 'SP2024051300002', 'SENT', 1, NULL, 2.0000, '2024-05-13 20:39:58', NULL, NULL);

-- ----------------------------
-- Table structure for scm_purchase_detail
-- ----------------------------
DROP TABLE IF EXISTS `scm_purchase_detail`;
CREATE TABLE `scm_purchase_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
  `purchase_id` bigint(20) NOT NULL COMMENT '采购单ID',
  `product_id` bigint(20) NOT NULL COMMENT '产品ID',
  `price` decimal(10, 4) NOT NULL COMMENT '单价',
  `qty` int(11) NOT NULL COMMENT '采购数量',
  `amount` decimal(10, 4) NOT NULL COMMENT '总金额',
  `received_qty` int(11) NULL DEFAULT NULL COMMENT '收货数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '采购明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scm_purchase_detail
-- ----------------------------
INSERT INTO `scm_purchase_detail` VALUES (1, '2024-03-17 01:08:12', 1, '2024-03-17 01:08:12', 1, 2, 1, 2.2000, 13, 28.6000, NULL);
INSERT INTO `scm_purchase_detail` VALUES (2, '2024-03-17 01:08:12', 1, '2024-03-17 01:08:12', 1, 2, 2, 12.2221, 15, 183.3315, NULL);
INSERT INTO `scm_purchase_detail` VALUES (3, '2024-03-19 23:25:52', 1, '2024-03-19 23:25:52', 1, 3, 2, 12.2221, 15, 183.3315, NULL);
INSERT INTO `scm_purchase_detail` VALUES (6, '2024-05-13 00:27:27', 1, '2024-05-13 00:27:27', 1, 4, 3, 2.0000, 3, 6.0000, NULL);
INSERT INTO `scm_purchase_detail` VALUES (7, '2024-05-13 19:55:58', 1, '2024-05-13 19:55:58', 1, 5, 3, 1.0000, 2, 2.0000, NULL);

-- ----------------------------
-- Table structure for scm_supplier
-- ----------------------------
DROP TABLE IF EXISTS `scm_supplier`;
CREATE TABLE `scm_supplier`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
  `supplier_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '供应商',
  `contacts` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '联系人',
  `mobile` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '联系电话',
  `province` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '所在省份',
  `address` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '详细地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '供应商信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scm_supplier
-- ----------------------------
INSERT INTO `scm_supplier` VALUES (1, '2024-03-16 23:15:59', 1, '2024-03-16 23:15:59', 1, '测试供应商', '测试员', '13800138000', '广东省', '广东省');
INSERT INTO `scm_supplier` VALUES (2, '2024-05-12 16:40:58', 1, '2024-05-12 16:40:58', 1, '广东XXX有限公司', 'qwe', '11111111111', '广东省', '广州');

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `context` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undo_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
