/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50741 (5.7.41-log)
 Source Host           : localhost:3306
 Source Schema         : vector_biz_warehouse

 Target Server Type    : MySQL
 Target Server Version : 50741 (5.7.41-log)
 File Encoding         : 65001

 Date: 12/04/2025 01:37:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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

-- ----------------------------
-- Table structure for wms_product_batch
-- ----------------------------
DROP TABLE IF EXISTS `wms_product_batch`;
CREATE TABLE `wms_product_batch`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
  `product_lot_id` bigint(20) NOT NULL COMMENT '产品批号ID',
  `batch_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '批次编号',
  `qualified` int(11) NOT NULL COMMENT '合格状态(0=不合格,1=合格)',
  `received_qty` int(11) NOT NULL COMMENT '收货数量',
  `usable_qty` int(11) NOT NULL COMMENT '可用数量',
  `receive_id` bigint(20) NULL DEFAULT NULL COMMENT '收货单ID',
  `receive_detail_id` bigint(20) NULL DEFAULT NULL COMMENT '收货单明细ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '产品批次' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_product_batch
-- ----------------------------
INSERT INTO `wms_product_batch` VALUES (1, '2024-03-20 02:01:21', 1, '2024-03-20 02:01:21', 1, 1, 'PB2024032000002', 1, 5, 5, 2, 3);
INSERT INTO `wms_product_batch` VALUES (2, '2024-03-20 02:01:21', 1, '2024-03-20 02:01:21', 1, 2, 'PB2024032000002', 1, 5, 5, 2, 3);
INSERT INTO `wms_product_batch` VALUES (3, '2024-03-20 02:01:21', 1, '2024-03-20 02:01:21', 1, 2, 'PB2024032000002', 0, 2, 2, 2, 3);
INSERT INTO `wms_product_batch` VALUES (4, '2024-03-20 02:01:21', 1, '2024-03-20 02:01:21', 1, 3, 'PB2024032000002', 1, 15, 15, 2, 4);
INSERT INTO `wms_product_batch` VALUES (5, '2024-03-20 02:04:14', 1, '2024-03-20 02:04:14', 1, 3, 'PB2024032000003', 1, 15, 15, 3, 5);

-- ----------------------------
-- Table structure for wms_product_lot
-- ----------------------------
DROP TABLE IF EXISTS `wms_product_lot`;
CREATE TABLE `wms_product_lot`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
  `product_id` bigint(20) NOT NULL COMMENT '产品ID',
  `lot_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '生产批号',
  `prod_date` date NOT NULL COMMENT '生产日期',
  `expired_date` date NOT NULL COMMENT '有效日期',
  `total_qty` int(11) NOT NULL DEFAULT 0 COMMENT '总数量',
  `qualified_qty` int(11) NOT NULL DEFAULT 0 COMMENT '合格数量',
  `unqualified_qty` int(11) NOT NULL DEFAULT 0 COMMENT '不合格数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '产品批号' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_product_lot
-- ----------------------------
INSERT INTO `wms_product_lot` VALUES (1, '2024-03-20 02:01:21', 1, '2024-03-20 02:01:21', 1, 1, 'A001', '2024-01-01', '2028-01-01', 5, 5, 0);
INSERT INTO `wms_product_lot` VALUES (2, '2024-03-20 02:01:21', 1, '2024-03-20 02:01:21', 1, 1, 'A002', '2024-02-01', '2028-02-01', 7, 5, 2);
INSERT INTO `wms_product_lot` VALUES (3, '2024-03-20 02:01:21', 1, '2024-03-20 02:04:14', 1, 2, 'B001', '2023-02-01', '2028-02-01', 30, 30, 0);

-- ----------------------------
-- Table structure for wms_product_stock
-- ----------------------------
DROP TABLE IF EXISTS `wms_product_stock`;
CREATE TABLE `wms_product_stock`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
  `product_id` bigint(20) NOT NULL COMMENT '产品ID',
  `total_stock` int(11) NOT NULL DEFAULT 0 COMMENT '总库存',
  `usable_stock` int(11) NOT NULL DEFAULT 0 COMMENT '可用库存',
  `locked_stock` int(11) NOT NULL DEFAULT 0 COMMENT '锁定库存',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '产品库存' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_product_stock
-- ----------------------------
INSERT INTO `wms_product_stock` VALUES (1, '2024-03-20 02:01:21', 1, '2024-03-20 02:01:21', 1, 1, 10, 10, 0);
INSERT INTO `wms_product_stock` VALUES (2, '2024-03-20 02:01:21', 1, '2024-03-20 02:04:14', 1, 2, 30, 3, 27);

-- ----------------------------
-- Table structure for wms_receive
-- ----------------------------
DROP TABLE IF EXISTS `wms_receive`;
CREATE TABLE `wms_receive`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
  `receive_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '收货单号',
  `receive_status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '收货状态',
  `biz_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '业务类型',
  `biz_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '业务单号',
  `logistics_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '物流名称',
  `logistics_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '物流单号',
  `signed_time` datetime NULL DEFAULT NULL COMMENT '签收时间',
  `batch_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '批次编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '收货单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_receive
-- ----------------------------
INSERT INTO `wms_receive` VALUES (2, '2024-03-18 16:28:14', 1, '2024-03-20 02:19:44', 1, 'WR2024031800002', 'FINISHED', 'PURCHASE', 'P2024031700002', '顺丰快递', 'SF0001', '2024-03-20 02:19:45', 'PB2024032000002');
INSERT INTO `wms_receive` VALUES (3, '2024-03-19 23:27:49', 1, '2024-03-20 02:19:32', 1, 'WR2024031900001', 'FINISHED', 'PURCHASE', 'P2024031900001', NULL, NULL, '2024-03-20 02:19:32', 'PB2024032000003');
INSERT INTO `wms_receive` VALUES (4, '2024-05-13 21:21:22', 0, '2024-05-13 23:08:20', 0, 'WR2024051300001', 'SENT_OUT', 'PURCHASE', 'SP2024051300002', 'XX物流', '1715606092508', NULL, NULL);
INSERT INTO `wms_receive` VALUES (5, '2024-05-13 23:08:20', 0, '2024-05-13 23:08:20', 0, 'WR2024051300002', 'SENT_OUT', 'PURCHASE', 'SP2024051300002', 'XX物流', '1715606482061', NULL, NULL);
INSERT INTO `wms_receive` VALUES (6, '2024-06-02 13:48:12', 0, '2024-06-02 13:48:12', 0, 'WR2024060200001', 'SENT_OUT', 'PURCHASE', 'SP2024051300002', 'XX物流', '1715606482061', NULL, NULL);
INSERT INTO `wms_receive` VALUES (7, '2024-06-02 16:23:04', 0, '2024-06-02 16:23:04', 0, 'WR2024060200002', 'SENT_OUT', 'PURCHASE', 'SP2024051300002', 'XX物流', '1715606092508', NULL, NULL);
INSERT INTO `wms_receive` VALUES (8, '2024-06-03 22:18:30', 0, '2024-06-03 22:18:30', 0, 'WR2024060300001', 'SENT_OUT', 'PURCHASE', 'SP2024051300002', 'XX物流', '1715606092508', NULL, NULL);
INSERT INTO `wms_receive` VALUES (9, '2024-06-06 22:53:26', 0, '2024-06-06 22:53:26', 0, 'WR2024060600001', 'SENT_OUT', 'PURCHASE', 'SP2024051300002', 'XX物流', '1715606482061', NULL, NULL);
INSERT INTO `wms_receive` VALUES (10, '2024-06-06 23:29:28', 0, '2024-06-06 23:29:28', 0, 'WR2024060600002', 'SENT_OUT', 'PURCHASE', 'SP2024051300002', 'XX物流', '1715606092508', NULL, NULL);

-- ----------------------------
-- Table structure for wms_receive_detail
-- ----------------------------
DROP TABLE IF EXISTS `wms_receive_detail`;
CREATE TABLE `wms_receive_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
  `receive_id` bigint(20) NOT NULL COMMENT '收货单ID',
  `product_id` bigint(20) NOT NULL COMMENT '产品ID',
  `qty` int(11) NOT NULL COMMENT '应收数量',
  `received_qty` int(11) NULL DEFAULT NULL COMMENT '实收数量',
  `qualified_qty` int(11) NULL DEFAULT NULL COMMENT '合格数量',
  `unqualified_qty` int(11) NULL DEFAULT NULL COMMENT '不合格数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '收货明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_receive_detail
-- ----------------------------
INSERT INTO `wms_receive_detail` VALUES (3, '2024-03-18 16:28:14', 1, '2024-03-20 02:01:21', 1, 2, 1, 13, 12, 10, 2);
INSERT INTO `wms_receive_detail` VALUES (4, '2024-03-18 16:28:14', 1, '2024-03-20 02:01:21', 1, 2, 2, 15, 15, 15, 0);
INSERT INTO `wms_receive_detail` VALUES (5, '2024-03-19 23:27:49', 1, '2024-03-20 02:04:14', 1, 3, 2, 15, 15, 15, 0);
INSERT INTO `wms_receive_detail` VALUES (6, '2024-05-13 21:21:22', 0, '2024-05-13 21:21:22', 0, 4, 3, 2, NULL, NULL, NULL);
INSERT INTO `wms_receive_detail` VALUES (7, '2024-05-13 23:08:20', 0, '2024-05-13 23:08:20', 0, 5, 3, 2, NULL, NULL, NULL);
INSERT INTO `wms_receive_detail` VALUES (8, '2024-06-02 13:48:12', 0, '2024-06-02 13:48:12', 0, 6, 3, 2, NULL, NULL, NULL);
INSERT INTO `wms_receive_detail` VALUES (9, '2024-06-02 16:23:04', 0, '2024-06-02 16:23:04', 0, 7, 3, 2, NULL, NULL, NULL);
INSERT INTO `wms_receive_detail` VALUES (10, '2024-06-03 22:18:31', 0, '2024-06-03 22:18:31', 0, 8, 3, 2, NULL, NULL, NULL);
INSERT INTO `wms_receive_detail` VALUES (11, '2024-06-06 22:53:26', 0, '2024-06-06 22:53:26', 0, 9, 3, 2, NULL, NULL, NULL);
INSERT INTO `wms_receive_detail` VALUES (12, '2024-06-06 23:29:28', 0, '2024-06-06 23:29:28', 0, 10, 3, 2, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
