/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50741 (5.7.41-log)
 Source Host           : localhost:3306
 Source Schema         : vector_biz_product

 Target Server Type    : MySQL
 Target Server Version : 50741 (5.7.41-log)
 File Encoding         : 65001

 Date: 12/04/2025 01:37:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pms_attr
-- ----------------------------
DROP TABLE IF EXISTS `pms_attr`;
CREATE TABLE `pms_attr`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
  `category_id` bigint(20) NOT NULL COMMENT '分类ID',
  `attr_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '属性名称',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '商品属性' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_attr
-- ----------------------------
INSERT INTO `pms_attr` VALUES (1, '2024-06-06 23:30:24', 1, '2024-06-06 23:30:24', 1, 0, '颜色', 0);
INSERT INTO `pms_attr` VALUES (2, '2024-06-06 23:30:24', 1, '2024-06-06 23:30:24', 1, 0, '尺寸', 0);

-- ----------------------------
-- Table structure for pms_attr_val
-- ----------------------------
DROP TABLE IF EXISTS `pms_attr_val`;
CREATE TABLE `pms_attr_val`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
  `attr_id` bigint(20) NOT NULL COMMENT '属性ID',
  `attr_value` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '属性值',
  `sort` int(11) NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '商品属性值' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_attr_val
-- ----------------------------

-- ----------------------------
-- Table structure for pms_category
-- ----------------------------
DROP TABLE IF EXISTS `pms_category`;
CREATE TABLE `pms_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
  `category_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '分类名称',
  `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '图标',
  `parent_id` bigint(20) NOT NULL COMMENT '父级ID',
  `level` int(11) NOT NULL DEFAULT 0 COMMENT '层级',
  `sort` int(11) NOT NULL COMMENT '排序',
  `displayed` tinyint(4) NOT NULL DEFAULT 0 COMMENT '展示状态(0=不展示,1=展示)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '商品分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_category
-- ----------------------------
INSERT INTO `pms_category` VALUES (1, '2024-05-03 23:00:22', 1, '2024-05-04 00:00:11', 1, '分类1', '111', 0, 1, 1, 1);
INSERT INTO `pms_category` VALUES (2, '2024-05-04 00:00:05', 1, '2024-05-04 00:13:27', 1, '分类2', '1222', 1, 2, 1, 1);

-- ----------------------------
-- Table structure for pms_product
-- ----------------------------
DROP TABLE IF EXISTS `pms_product`;
CREATE TABLE `pms_product`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
  `product_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '产品名称',
  `factory` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生产厂家',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '产品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_product
-- ----------------------------
INSERT INTO `pms_product` VALUES (1, '2024-03-15 20:53:56', 1, '2024-03-15 20:53:56', 1, '测试产品', '测试工厂');
INSERT INTO `pms_product` VALUES (3, '2024-05-04 16:09:31', 1, '2024-05-04 16:11:45', 1, '小米吹风筒1', '米家');

-- ----------------------------
-- Table structure for pms_sku
-- ----------------------------
DROP TABLE IF EXISTS `pms_sku`;
CREATE TABLE `pms_sku`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
  `spu_id` bigint(20) NOT NULL COMMENT 'spuID',
  `image` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '图片',
  `price` decimal(12, 2) NOT NULL COMMENT '价格',
  `stock` int(11) NOT NULL COMMENT '库存',
  `on_sale` tinyint(4) NOT NULL DEFAULT 0 COMMENT '上架状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '商品分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_sku
-- ----------------------------
INSERT INTO `pms_sku` VALUES (2, '2024-06-06 23:30:24', 1, '2024-06-06 23:30:24', 1, 3, 'https://wengxs.oss-cn-shenzhen.aliyuncs.com/20240606/a3cb56ff-5afb-405d-8088-9533d8bc7748.jpeg', 1.00, 3, 0);
INSERT INTO `pms_sku` VALUES (3, '2024-06-06 23:30:24', 1, '2024-06-06 23:30:24', 1, 3, 'https://wengxs.oss-cn-shenzhen.aliyuncs.com/20240606/a3cb56ff-5afb-405d-8088-9533d8bc7748.jpeg', 2.00, 4, 0);
INSERT INTO `pms_sku` VALUES (4, '2024-06-06 23:30:24', 1, '2024-06-06 23:30:24', 1, 3, 'https://wengxs.oss-cn-shenzhen.aliyuncs.com/20240606/a3cb56ff-5afb-405d-8088-9533d8bc7748.jpeg', 3.00, 6, 0);
INSERT INTO `pms_sku` VALUES (5, '2024-06-06 23:30:24', 1, '2024-06-06 23:30:24', 1, 3, 'https://wengxs.oss-cn-shenzhen.aliyuncs.com/20240606/a3cb56ff-5afb-405d-8088-9533d8bc7748.jpeg', 4.00, 5, 0);

-- ----------------------------
-- Table structure for pms_spu
-- ----------------------------
DROP TABLE IF EXISTS `pms_spu`;
CREATE TABLE `pms_spu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
  `spu_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '商品标题',
  `spu_status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '商品状态',
  `default_image` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '默认图片',
  `category_id` bigint(20) NOT NULL COMMENT '分类ID',
  `sale_count` int(11) NOT NULL DEFAULT 0 COMMENT '总销量',
  `approve_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
  `on_time` datetime NULL DEFAULT NULL COMMENT '上架时间',
  `off_time` datetime NULL DEFAULT NULL COMMENT '下架时间',
  `reject_reason` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '驳回原因',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '商品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_spu
-- ----------------------------
INSERT INTO `pms_spu` VALUES (3, '2024-06-06 23:30:24', 1, '2025-02-27 17:27:26', 1, 'qqq', 'DRAFT', 'https://wengxs.oss-cn-shenzhen.aliyuncs.com/20240606/a3cb56ff-5afb-405d-8088-9533d8bc7748.jpeg', 2, 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for pms_spu_description
-- ----------------------------
DROP TABLE IF EXISTS `pms_spu_description`;
CREATE TABLE `pms_spu_description`  (
  `spu_id` bigint(20) NOT NULL COMMENT '商品ID',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '商品描述',
  PRIMARY KEY (`spu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '商品描述' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_spu_description
-- ----------------------------
INSERT INTO `pms_spu_description` VALUES (3, '');

-- ----------------------------
-- Table structure for pms_spu_image
-- ----------------------------
DROP TABLE IF EXISTS `pms_spu_image`;
CREATE TABLE `pms_spu_image`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `spu_id` bigint(20) NOT NULL COMMENT '商品ID',
  `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '图片链接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '商品图片' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_spu_image
-- ----------------------------
INSERT INTO `pms_spu_image` VALUES (19, 3, 'https://wengxs.oss-cn-shenzhen.aliyuncs.com/20240606/a3cb56ff-5afb-405d-8088-9533d8bc7748.jpeg');
INSERT INTO `pms_spu_image` VALUES (20, 3, 'https://wengxs.oss-cn-shenzhen.aliyuncs.com/20240606/0178b934-04be-418e-9c81-e1add703a284.jpg');
INSERT INTO `pms_spu_image` VALUES (21, 3, 'https://wengxs.oss-cn-shenzhen.aliyuncs.com/20240606/0d83c27f-049c-46e1-a115-3124a25772ca.jpg');
INSERT INTO `pms_spu_image` VALUES (22, 3, 'https://wengxs.oss-cn-shenzhen.aliyuncs.com/20240606/dfb19012-7efe-4ecf-a204-df8e4cead8d9.jpg');
INSERT INTO `pms_spu_image` VALUES (23, 3, 'https://wengxs.oss-cn-shenzhen.aliyuncs.com/20240606/2746429a-94ec-4c9f-a0b6-4e9adb21a699.jpg');
INSERT INTO `pms_spu_image` VALUES (24, 3, 'https://wengxs.oss-cn-shenzhen.aliyuncs.com/20240606/db08967d-ee85-44cd-a64f-1e16ff80b832.jpeg');

-- ----------------------------
-- Table structure for pms_spu_sku_attr_relation
-- ----------------------------
DROP TABLE IF EXISTS `pms_spu_sku_attr_relation`;
CREATE TABLE `pms_spu_sku_attr_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
  `spu_id` bigint(20) NOT NULL COMMENT 'SPU ID',
  `sku_id` bigint(20) NOT NULL COMMENT 'SKU ID',
  `attr_id` bigint(20) NOT NULL COMMENT '属性ID',
  `attr_value` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '属性值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '商品属性关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_spu_sku_attr_relation
-- ----------------------------
INSERT INTO `pms_spu_sku_attr_relation` VALUES (1, '2024-06-06 23:30:24', 1, '2024-06-06 23:30:24', 1, 3, 2, 1, '白色');
INSERT INTO `pms_spu_sku_attr_relation` VALUES (2, '2024-06-06 23:30:24', 1, '2024-06-06 23:30:24', 1, 3, 2, 2, '大');
INSERT INTO `pms_spu_sku_attr_relation` VALUES (3, '2024-06-06 23:30:24', 1, '2024-06-06 23:30:24', 1, 3, 3, 1, '白色');
INSERT INTO `pms_spu_sku_attr_relation` VALUES (4, '2024-06-06 23:30:24', 1, '2024-06-06 23:30:24', 1, 3, 3, 2, '小');
INSERT INTO `pms_spu_sku_attr_relation` VALUES (5, '2024-06-06 23:30:24', 1, '2024-06-06 23:30:24', 1, 3, 4, 1, '黑色');
INSERT INTO `pms_spu_sku_attr_relation` VALUES (6, '2024-06-06 23:30:24', 1, '2024-06-06 23:30:24', 1, 3, 4, 2, '大');
INSERT INTO `pms_spu_sku_attr_relation` VALUES (7, '2024-06-06 23:30:24', 1, '2024-06-06 23:30:24', 1, 3, 5, 1, '黑色');
INSERT INTO `pms_spu_sku_attr_relation` VALUES (8, '2024-06-06 23:30:24', 1, '2024-06-06 23:30:24', 1, 3, 5, 2, '小');

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
