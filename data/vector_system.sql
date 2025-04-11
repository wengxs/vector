/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50741 (5.7.41-log)
 Source Host           : localhost:3306
 Source Schema         : vector_system

 Target Server Type    : MySQL
 Target Server Version : 50741 (5.7.41-log)
 File Encoding         : 65001

 Date: 12/04/2025 01:40:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
  `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '上级部门ID',
  `ancestors` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '祖级列表',
  `dept_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
  `leader` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门领导',
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `dept_status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'ENABLED' COMMENT '部门状态',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`dept_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, '2025-02-27 18:38:49', 0, '2025-02-27 18:38:49', 0, 0, '0', 'XX有限公司', 'BOSS', NULL, NULL, 'ENABLED', NULL, 0);
INSERT INTO `sys_dept` VALUES (2, '2025-02-28 02:43:46', 1, '2025-02-28 02:43:46', 1, 1, '0,1', '技术部', 'XX', '13112341234', 'xx@xx.com', 'DISABLED', '1111', 1);
INSERT INTO `sys_dept` VALUES (3, '2025-02-28 03:47:56', 1, '2025-02-28 20:11:32', 1, 4, '0,4', '销售部', NULL, NULL, NULL, 'ENABLED', NULL, 2);
INSERT INTO `sys_dept` VALUES (4, '2025-02-28 20:03:28', 1, '2025-02-28 20:03:28', 1, 0, '0', '子公司', '1', NULL, NULL, 'ENABLED', NULL, 2);
INSERT INTO `sys_dept` VALUES (5, '2025-02-28 20:04:26', 1, '2025-02-28 20:11:32', 1, 4, '0,4', '采购部', NULL, NULL, NULL, 'ENABLED', NULL, 1);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
  `dict_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '字典名称',
  `dict_key` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '字典键',
  `dict_status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'ENABLED' COMMENT '字典状态',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_dict_key`(`dict_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, '2025-03-01 01:27:42', 1, '2025-03-01 01:27:42', 1, '系统用户状态', 'sys_user_status', 'ENABLED', '系统用户状态');
INSERT INTO `sys_dict` VALUES (2, '2025-03-04 01:34:13', 1, '2025-03-04 01:34:13', 1, '系统用户性别', 'sys_user_gender', 'ENABLED', NULL);

-- ----------------------------
-- Table structure for sys_dict_option
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_option`;
CREATE TABLE `sys_dict_option`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
  `dict_key` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '字典键',
  `option_label` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '标签',
  `option_value` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '项值',
  `sort` int(11) NOT NULL DEFAULT 1 COMMENT '排序',
  `option_status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'ENABLED' COMMENT '选项状态',
  `style` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '样式',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_dict_key`(`dict_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '字典选项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_option
-- ----------------------------
INSERT INTO `sys_dict_option` VALUES (1, '2025-03-03 23:35:30', 1, '2025-03-03 23:35:30', 1, 'sys_user_status', '启用', 'ENABLED', 1, 'ENABLED', 'success');
INSERT INTO `sys_dict_option` VALUES (2, '2025-03-04 01:30:44', 1, '2025-03-04 01:30:44', 1, 'sys_user_status', '禁用', 'DISABLED', 2, 'ENABLED', 'info');
INSERT INTO `sys_dict_option` VALUES (3, '2025-03-04 01:39:06', 1, '2025-03-04 01:39:06', 1, 'sys_user_gender', '未知', 'UNKNOWN', 1, 'ENABLED', 'info');
INSERT INTO `sys_dict_option` VALUES (4, '2025-03-04 01:39:37', 1, '2025-03-04 01:39:37', 1, 'sys_user_gender', '男', 'MALE', 2, 'ENABLED', 'primary');
INSERT INTO `sys_dict_option` VALUES (5, '2025-03-04 01:39:50', 1, '2025-03-04 01:39:50', 1, 'sys_user_gender', '女', 'FEMALE', 3, 'ENABLED', 'danger');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '上级ID',
  `menu_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单权限名称',
  `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型（MENU=菜单,BUTTON=按钮）',
  `permission` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `path` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路径URL',
  `component` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'VUE页面组件',
  `hidden` tinyint(1) NOT NULL DEFAULT 0 COMMENT '隐藏菜单',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent_id`(`parent_id`) USING BTREE,
  INDEX `idx_type`(`type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 104 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '2020-05-31 23:44:45', 1, '2024-05-12 16:33:38', 1, 0, '系统管理', 'system', 'MENU', '', 99, '/sys', '', 0);
INSERT INTO `sys_menu` VALUES (2, '2020-06-05 01:53:41', 1, '2025-02-28 00:45:42', 1, 1, '用户管理', 'user', 'MENU', '', 3, '/sys/user', 'sys/user/index', 0);
INSERT INTO `sys_menu` VALUES (3, '2020-06-05 01:55:28', 1, '2025-02-28 00:45:50', 1, 1, '角色管理', 'role', 'MENU', '', 4, '/sys/role', 'sys/role/index', 0);
INSERT INTO `sys_menu` VALUES (4, '2020-06-05 01:55:29', 1, '2025-02-28 00:45:29', 1, 1, '菜单管理', 'menu', 'MENU', '', 2, '/sys/menu', 'sys/menu/index', 0);
INSERT INTO `sys_menu` VALUES (5, '2020-06-05 01:55:29', 1, '2025-02-28 00:44:50', 1, 1, '系统配置', 'setting', 'MENU', '', 1, '/sys/setting', 'sys/setting/index', 0);
INSERT INTO `sys_menu` VALUES (6, '2020-06-05 01:55:29', 1, '2024-05-04 21:28:51', 1, 0, '系统监控', 'monitor', 'MENU', '', 100, '/monitor', '', 0);
INSERT INTO `sys_menu` VALUES (7, '2020-06-05 01:56:00', 1, '2024-05-04 21:28:22', 1, 6, '系统信息', 'client', 'MENU', '', 1, '/monitor/sysInfo', '', 0);
INSERT INTO `sys_menu` VALUES (8, '2020-06-07 20:46:27', 1, '2020-06-07 20:46:27', 1, 2, '查询', NULL, 'BUTTON', 'sys:user:query', 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (9, '2020-06-07 20:52:11', 1, '2020-06-07 20:52:11', 1, 2, '新增', NULL, 'BUTTON', 'sys:user:add', 2, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (10, '2020-06-07 20:52:56', 1, '2020-06-07 21:14:00', 1, 2, '修改', '', 'BUTTON', 'sys:user:edit', 3, '', '', 0);
INSERT INTO `sys_menu` VALUES (12, '2020-06-07 21:15:47', 1, '2020-06-07 21:15:47', 1, 2, '删除', NULL, 'BUTTON', 'sys:user:del', 4, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (13, '2020-06-21 20:18:43', 1, '2024-05-01 22:35:35', 1, 65, '代码生成', 'api', 'MENU', NULL, 1, '/gen/code', 'gen/code/index', 0);
INSERT INTO `sys_menu` VALUES (14, '2023-04-05 06:50:25', 1, '2024-05-01 13:24:32', 1, 0, '产品管理', 'document', 'MENU', NULL, 1, '/pms', NULL, 0);
INSERT INTO `sys_menu` VALUES (15, '2023-04-05 06:52:30', 1, '2024-05-04 21:31:16', 1, 14, '品牌列表', 'publish', 'MENU', 'pms:brand:list', 1, '/pms/brand', 'pms/brand/index', 0);
INSERT INTO `sys_menu` VALUES (19, '2023-04-15 12:01:19', 1, '2024-05-04 21:29:58', 1, 14, '分类管理', 'tree', 'MENU', 'pms:category:list', 1, '/pms/category', 'pms/category/index', 0);
INSERT INTO `sys_menu` VALUES (20, '2023-04-15 12:01:19', 1, '2023-04-15 12:01:19', 1, 19, '分类管理查询', NULL, 'BUTTON', 'pms:category:query', 0, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (21, '2023-04-15 12:01:19', 1, '2023-04-15 12:01:19', 1, 19, '分类管理新增', NULL, 'BUTTON', 'pms:category:add', 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (22, '2023-04-15 12:01:19', 1, '2023-04-15 12:01:19', 1, 19, '分类管理修改', NULL, 'BUTTON', 'pms:category:edit', 2, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (23, '2023-04-15 12:01:19', 1, '2023-04-15 12:02:22', 1, 19, '分类管理删除', NULL, 'BUTTON', 'pms:category:del', 3, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (24, '2023-04-15 12:01:19', 1, '2023-04-15 12:01:19', 1, 19, '分类管理导出', NULL, 'BUTTON', 'pms:category:export', 4, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (25, '2023-04-15 12:01:19', 1, '2023-04-15 12:01:19', 1, 19, '分类管理导入', NULL, 'BUTTON', 'pms:category:import', 5, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (26, '2023-04-18 01:51:46', 1, '2024-05-04 15:58:15', 1, 14, '产品信息', 'document', 'MENU', NULL, 3, '/pms/product', 'pms/product/index', 0);
INSERT INTO `sys_menu` VALUES (27, '2023-04-18 01:51:46', 1, '2024-05-04 16:00:36', 1, 26, '产品信息-查询', NULL, 'BUTTON', 'pms:product:query', 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (28, '2023-04-18 01:51:46', 1, '2024-05-04 16:00:53', 1, 26, '产品信息-新增', NULL, 'BUTTON', 'pms:product:add', 2, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (29, '2023-04-18 01:51:46', 1, '2024-05-04 16:01:21', 1, 26, '产品信息-删除', NULL, 'BUTTON', 'pms:product:del', 4, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (30, '2023-04-18 01:52:53', 1, '2023-04-18 01:54:14', 1, 15, '品牌查询', NULL, 'BUTTON', 'pms:brand:query', 0, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (31, '2023-04-18 01:53:26', 1, '2023-04-18 01:54:25', 1, 15, '品牌新增', NULL, 'BUTTON', 'pms:brand:add', 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (32, '2023-04-18 01:53:59', 1, '2023-04-18 01:54:30', 1, 15, '品牌修改', NULL, 'BUTTON', 'pms:brand:edit', 2, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (33, '2023-04-18 01:54:54', 1, '2023-04-18 01:54:54', 1, 15, '品牌删除', NULL, 'BUTTON', 'pms:brand:del', 3, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (34, '2023-04-18 01:56:38', 1, '2024-05-04 16:01:06', 1, 26, '产品信息-修改', NULL, 'BUTTON', 'pms:product:edit', 3, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (35, '2023-04-18 03:40:20', 1, '2024-05-04 21:17:49', 1, 0, '经营管理', 'eye-open', 'MENU', NULL, 2, '/bms', NULL, 0);
INSERT INTO `sys_menu` VALUES (36, '2023-04-18 03:41:53', 1, '2024-05-04 21:24:57', 1, 35, '店铺列表', 'fullscreen-exit', 'MENU', 'bms:shop:list', 1, '/bms/shop', 'bms/shop/index', 0);
INSERT INTO `sys_menu` VALUES (37, '2023-04-18 03:41:53', 1, '2024-05-04 21:25:08', 1, 36, '店铺列表-查询', NULL, 'BUTTON', 'bms:shop:query', 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (38, '2023-04-18 03:41:53', 1, '2024-05-04 21:25:39', 1, 36, '店铺列表-新增', NULL, 'BUTTON', 'bms:shop:add', 2, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (39, '2023-04-18 03:41:53', 1, '2024-05-04 21:25:35', 1, 36, '店铺列表-修改', NULL, 'BUTTON', 'bms:shop:edit', 3, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (40, '2023-04-18 03:41:53', 1, '2024-05-04 21:25:29', 1, 36, '店铺列表-删除', NULL, 'BUTTON', 'bms:shop:del', 4, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (41, '2024-05-01 13:50:33', 1, '2024-05-01 13:51:09', 1, 3, '角色管理-查询', NULL, 'BUTTON', 'sys:role:query', 1, '', NULL, 0);
INSERT INTO `sys_menu` VALUES (42, '2024-05-01 13:51:57', 1, '2024-05-01 13:51:57', 1, 3, '角色管理-新增', NULL, 'BUTTON', 'sys:role:add', 2, '', NULL, 0);
INSERT INTO `sys_menu` VALUES (43, '2024-05-01 13:52:48', 1, '2024-05-01 13:52:48', 1, 3, '角色管理-修改', NULL, 'BUTTON', 'sys:role:edit', 3, '', NULL, 0);
INSERT INTO `sys_menu` VALUES (44, '2024-05-01 13:53:23', 1, '2024-05-01 13:53:23', 1, 3, '角色管理-删除', NULL, 'BUTTON', 'sys:role:del', 4, '', NULL, 0);
INSERT INTO `sys_menu` VALUES (45, '2024-05-01 13:54:00', 1, '2024-05-01 13:54:00', 1, 4, '菜单管理-查询', NULL, 'BUTTON', 'sys:menu:query', 1, '', NULL, 0);
INSERT INTO `sys_menu` VALUES (46, '2024-05-01 13:54:19', 1, '2024-05-01 13:54:19', 1, 4, '菜单管理-新增', NULL, 'BUTTON', 'sys:menu:add', 2, '', NULL, 0);
INSERT INTO `sys_menu` VALUES (47, '2024-05-01 13:54:40', 1, '2024-05-01 13:54:40', 1, 4, '菜单管理-修改', NULL, 'BUTTON', 'sys:menu:update', 3, '', NULL, 0);
INSERT INTO `sys_menu` VALUES (48, '2024-05-01 13:55:02', 1, '2024-05-01 13:55:02', 1, 4, '菜单管理-删除', NULL, 'BUTTON', 'sys:menu:del', 4, '', NULL, 0);
INSERT INTO `sys_menu` VALUES (49, '2024-05-01 13:55:59', 1, '2024-05-01 13:55:59', 1, 5, '系统配置-修改', NULL, 'BUTTON', 'sys:setting:update', 1, '', NULL, 0);
INSERT INTO `sys_menu` VALUES (65, '2024-05-01 22:34:58', 1, '2024-05-01 22:34:58', 1, 0, '生成工具', 'api', 'MENU', NULL, 101, '/gen', NULL, 0);
INSERT INTO `sys_menu` VALUES (66, '2024-05-05 01:05:46', 1, '2024-05-05 01:06:19', 1, 14, '商品信息', 'moon', 'MENU', NULL, 4, '/pms/spu', 'pms/spu/index', 0);
INSERT INTO `sys_menu` VALUES (67, '2024-05-05 01:05:46', 1, '2024-05-05 01:06:28', 1, 66, '商品信息-查询', NULL, 'BUTTON', 'pms:spu:query', 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (68, '2024-05-05 01:05:46', 1, '2024-05-05 01:06:36', 1, 66, '商品信息-修改', NULL, 'BUTTON', 'pms:spu:edit', 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (69, '2024-05-05 01:05:46', 1, '2024-05-05 01:06:43', 1, 66, '商品信息-新增', NULL, 'BUTTON', 'pms:spu:add', 2, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (70, '2024-05-05 01:05:46', 1, '2024-05-05 01:06:49', 1, 66, '商品信息-删除', NULL, 'BUTTON', 'pms:spu:del', 3, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (71, '2024-05-12 16:34:43', 1, '2024-05-12 16:34:43', 1, 0, '供应链管理', 'link', 'MENU', NULL, 3, '/scm', NULL, 0);
INSERT INTO `sys_menu` VALUES (72, '2024-05-12 16:36:35', 1, '2024-05-12 16:36:35', 1, 71, '供应商', 'peoples', 'MENU', NULL, 1, '/scm/supplier', 'scm/supplier/index', 0);
INSERT INTO `sys_menu` VALUES (73, '2024-05-12 16:36:35', 1, '2024-05-12 16:36:35', 1, 72, '供应商-查询', NULL, 'BUTTON', 'scm:supplier:query', 0, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (74, '2024-05-12 16:36:35', 1, '2024-05-12 16:36:35', 1, 72, '供应商-新增', NULL, 'BUTTON', 'scm:supplier:add', 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (75, '2024-05-12 16:36:35', 1, '2024-05-12 16:36:35', 1, 72, '供应商-修改', NULL, 'BUTTON', 'scm:supplier:edit', 2, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (76, '2024-05-12 16:36:35', 1, '2024-05-12 16:36:35', 1, 72, '供应商-删除', NULL, 'BUTTON', 'scm:supplier:del', 3, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (77, '2024-05-12 16:37:33', 1, '2024-05-12 16:37:33', 1, 71, '采购订单', 'edit', 'MENU', NULL, 1, '/scm/purchase', 'scm/purchase/index', 0);
INSERT INTO `sys_menu` VALUES (78, '2024-05-12 16:37:33', 1, '2024-05-12 16:37:33', 1, 77, '采购订单-查询', NULL, 'BUTTON', 'scm:purchase:query', 0, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (79, '2024-05-12 16:37:33', 1, '2024-05-12 16:37:33', 1, 77, '采购订单-新增', NULL, 'BUTTON', 'scm:purchase:add', 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (80, '2024-05-12 16:37:33', 1, '2024-05-12 16:37:33', 1, 77, '采购订单-修改', NULL, 'BUTTON', 'scm:purchase:edit', 2, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (81, '2024-05-12 16:37:33', 1, '2024-05-12 19:14:00', 1, 77, '采购订单-取消', NULL, 'BUTTON', 'scm:purchase:cancel', 3, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (82, '2024-05-13 23:02:22', 1, '2024-05-13 23:02:22', 1, 0, '仓储系统', 'close_other', 'MENU', NULL, 4, '/wms', NULL, 0);
INSERT INTO `sys_menu` VALUES (83, '2024-05-13 23:03:21', 1, '2024-05-13 23:03:21', 1, 82, '收货单', 'monitor', 'MENU', NULL, 1, '/wms/receive', 'wms/receive/index', 0);
INSERT INTO `sys_menu` VALUES (84, '2024-05-13 23:03:21', 1, '2024-05-13 23:03:21', 1, 83, '收货单-查询', NULL, 'BUTTON', 'wms:receive:query', 0, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (85, '2024-05-13 23:03:21', 1, '2024-05-13 23:03:21', 1, 83, '收货单-新增', NULL, 'BUTTON', 'wms:receive:add', 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (86, '2024-05-13 23:03:21', 1, '2024-05-13 23:03:21', 1, 83, '收货单-修改', NULL, 'BUTTON', 'wms:receive:edit', 2, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (87, '2024-05-13 23:03:21', 1, '2024-05-13 23:03:21', 1, 83, '收货单-删除', NULL, 'BUTTON', 'wms:receive:del', 3, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (88, '2024-06-02 15:46:10', 1, '2024-06-03 22:19:44', 1, 14, '新增商品', 'close', 'MENU', NULL, 11, '/pms/spu/add', 'pms/spu/add', 1);
INSERT INTO `sys_menu` VALUES (89, '2025-02-28 00:40:20', 1, '2025-02-28 01:03:36', 1, 1, '部门管理', 'tree', 'MENU', NULL, 5, '/sys/dept', 'sys/dept/index', 0);
INSERT INTO `sys_menu` VALUES (90, '2025-02-28 00:40:20', 1, '2025-02-28 00:42:09', 1, 89, '部门管理-查询', NULL, 'BUTTON', 'sys:dept:query', 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (91, '2025-02-28 00:40:20', 1, '2025-02-28 00:42:14', 1, 89, '部门管理-新增', NULL, 'BUTTON', 'sys:dept:add', 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (92, '2025-02-28 00:40:20', 1, '2025-02-28 00:42:19', 1, 89, '部门管理-修改', NULL, 'BUTTON', 'sys:dept:edit', 2, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (93, '2025-02-28 00:41:18', 1, '2025-02-28 00:41:18', 1, 89, '部门管理-删除', NULL, 'BUTTON', 'sys:dept:del', 3, '', NULL, 0);
INSERT INTO `sys_menu` VALUES (94, '2025-02-28 00:44:01', 1, '2025-02-28 00:46:03', 1, 1, '字典管理', 'peoples', 'MENU', NULL, 6, '/sys/dict', 'sys/dict/index', 0);
INSERT INTO `sys_menu` VALUES (95, '2025-02-28 00:44:01', 1, '2025-02-28 00:44:01', 1, 94, '字典管理-查询', NULL, 'BUTTON', 'sys:dict:query', 0, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (96, '2025-02-28 00:44:01', 1, '2025-02-28 00:44:01', 1, 94, '字典管理-新增', NULL, 'BUTTON', 'sys:dict:add', 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (97, '2025-02-28 00:44:01', 1, '2025-02-28 00:44:01', 1, 94, '字典管理-修改', NULL, 'BUTTON', 'sys:dict:edit', 2, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (98, '2025-02-28 00:44:01', 1, '2025-02-28 00:44:01', 1, 94, '字典管理-删除', NULL, 'BUTTON', 'sys:dict:del', 3, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (99, '2025-04-02 02:29:16', 1, '2025-04-02 02:29:16', 1, 0, '第三方管理', 'sunny', 'MENU', NULL, 102, '/third', NULL, 0);
INSERT INTO `sys_menu` VALUES (100, '2025-04-02 02:30:30', 1, '2025-04-02 02:38:41', 1, 99, '短信日志', 'document', 'MENU', NULL, 1, '/third/smsLog', 'third/smsLog/index', 0);
INSERT INTO `sys_menu` VALUES (101, '2025-04-02 02:30:30', 1, '2025-04-02 02:30:30', 1, 100, '短信日志-查询', NULL, 'BUTTON', 'third:query', 0, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (102, '2025-04-02 02:30:30', 1, '2025-04-02 02:30:30', 1, 100, '短信日志-删除', NULL, 'BUTTON', 'third:del', 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (103, '2025-04-02 02:30:30', 1, '2025-04-02 02:30:30', 1, 100, '短信日志-导出', NULL, 'BUTTON', 'third:export', 2, NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
  `role_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色标识',
  `data_scope` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'ALL' COMMENT '数据权限',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`role_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '2023-03-12 02:03:22', 1, '2023-03-12 02:03:22', 1, '超级管理员', 'admin', 'ALL', '拥有最高级权限');
INSERT INTO `sys_role` VALUES (2, '2023-04-03 21:19:47', 1, '2023-04-03 21:19:47', 1, '测试', 'test', 'DEPT_AND_CHILD', 'ggg ');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(11) NOT NULL,
  `menu_id` bigint(11) NOT NULL,
  INDEX `idx_role_id`(`role_id`) USING BTREE,
  INDEX `idx_menu_id`(`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (2, 15);
INSERT INTO `sys_role_menu` VALUES (2, 30);
INSERT INTO `sys_role_menu` VALUES (2, 31);
INSERT INTO `sys_role_menu` VALUES (2, 32);
INSERT INTO `sys_role_menu` VALUES (2, 33);
INSERT INTO `sys_role_menu` VALUES (2, 27);
INSERT INTO `sys_role_menu` VALUES (2, 35);
INSERT INTO `sys_role_menu` VALUES (2, 36);
INSERT INTO `sys_role_menu` VALUES (2, 37);
INSERT INTO `sys_role_menu` VALUES (2, 38);
INSERT INTO `sys_role_menu` VALUES (2, 39);
INSERT INTO `sys_role_menu` VALUES (2, 40);
INSERT INTO `sys_role_menu` VALUES (2, 8);
INSERT INTO `sys_role_menu` VALUES (2, 9);
INSERT INTO `sys_role_menu` VALUES (2, 10);
INSERT INTO `sys_role_menu` VALUES (2, 14);
INSERT INTO `sys_role_menu` VALUES (2, 26);
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);

-- ----------------------------
-- Table structure for sys_setting
-- ----------------------------
DROP TABLE IF EXISTS `sys_setting`;
CREATE TABLE `sys_setting`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
  `key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '键',
  `label` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '标签',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '值',
  `input_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'INPUT' COMMENT '输入类型',
  `dict_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '字典类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '系统设置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_setting
-- ----------------------------
INSERT INTO `sys_setting` VALUES (1, '2023-03-12 02:12:29', 1, '2023-03-12 02:12:29', 1, 'system_name', '系统名称', 'XXX管理系统', 'INPUT', NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `nickname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户昵称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `gender` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'UNKNOWN' COMMENT '性别',
  `user_status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'ENABLED' COMMENT '状态',
  `avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username`) USING BTREE,
  UNIQUE INDEX `uk_mobile`(`mobile`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '2020-02-09 23:11:17', 0, '2025-04-03 16:15:04', 1, 'admin', '超级管理员', '$2a$10$Ja/x.8cWHKQ/6kaZLZWu6uj2lD9PBVcPUsIjHpdCluvts0tjwa5je', '13800138000', 'admin@vector.com', 'UNKNOWN', 'ENABLED', 'https://wengxs.oss-cn-shenzhen.aliyuncs.com/20250403/fba712af-9c6f-46d1-905f-56435e7faeb4.jpg', 2, '1111');
INSERT INTO `sys_user` VALUES (2, '2023-03-28 01:41:04', 1, '2023-03-28 01:41:04', 1, 'test', '测试', '$2a$10$uTi7sxCpcqyilAAZM/xCJefmv5BolGrY/Sz.0rJL8KHYa5iza31Iy', '13800138001', 'test@vector.com', 'MALE', 'ENABLED', NULL, 4, '222');
INSERT INTO `sys_user` VALUES (3, '2024-04-30 00:10:55', 1, '2024-04-30 00:10:55', 1, 'test1', '测试1', '$2a$10$8g5EM7S8Zq4dUABZUXCVaex2LoQqZK.A5McZDnvMZcmiCpYaUkcHm', '13800138002', NULL, 'FEMALE', 'ENABLED', 'https://wengxs.oss-cn-shenzhen.aliyuncs.com/20240507/f5f05d76-5ae7-4ac5-bf9e-97f48f23266b.jpg', 3, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  INDEX `idx_user_id`(`user_id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (3, 2);

SET FOREIGN_KEY_CHECKS = 1;
