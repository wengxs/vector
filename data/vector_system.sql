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
) ENGINE = InnoDB COMMENT = '部门' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB COMMENT = '字典' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB COMMENT = '字典选项' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB COMMENT = '菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 0, '系统管理', 'system', 'MENU', NULL, 101, '/sys', NULL, 0);
INSERT INTO `sys_menu` VALUES (2, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 1, '菜单管理', 'menu', 'MENU', NULL, 1, '/sys/menu', 'sys/menu/index', 0);
INSERT INTO `sys_menu` VALUES (3, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 2, '菜单管理-查询', NULL, 'BUTTON', 'sys:menu:query', 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (4, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 2, '菜单管理-新增', NULL, 'BUTTON', 'sys:menu:add', 2, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (5, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 2, '菜单管理-修改', NULL, 'BUTTON', 'sys:menu:update', 3, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (6, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 2, '菜单管理-删除', NULL, 'BUTTON', 'sys:menu:del', 4, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (7, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 1, '角色管理', 'role', 'MENU', NULL, 2, '/sys/role', 'sys/role/index', 0);
INSERT INTO `sys_menu` VALUES (8, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 7, '角色管理-查询', NULL, 'BUTTON', 'sys:role:query', 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (9, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 7, '角色管理-新增', NULL, 'BUTTON', 'sys:role:add', 2, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (10, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 7, '角色管理-修改', NULL, 'BUTTON', 'sys:role:edit', 3, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (11, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 7, '角色管理-删除', NULL, 'BUTTON', 'sys:role:del', 4, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (12, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 1, '用户管理', 'user', 'MENU', NULL, 3, '/sys/user', 'sys/user/index', 0);
INSERT INTO `sys_menu` VALUES (13, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 12, '用户管理-查询', NULL, 'BUTTON', 'sys:user:query', 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (14, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 12, '用户管理-新增', NULL, 'BUTTON', 'sys:user:add', 2, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (15, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 12, '用户管理-修改', NULL, 'BUTTON', 'sys:user:edit', 3, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (16, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 12, '用户管理-删除', NULL, 'BUTTON', 'sys:user:del', 4, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (17, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 1, '部门管理', 'tree', 'MENU', NULL, 4, '/sys/dept', 'sys/dept/index', 0);
INSERT INTO `sys_menu` VALUES (18, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 17, '部门管理-查询', NULL, 'BUTTON', 'sys:dept:query', 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (19, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 17, '部门管理-新增', NULL, 'BUTTON', 'sys:dept:add', 2, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (20, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 17, '部门管理-修改', NULL, 'BUTTON', 'sys:dept:edit', 3, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (21, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 17, '部门管理-删除', NULL, 'BUTTON', 'sys:dept:del', 4, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (22, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 1, '系统配置', 'setting', 'MENU', NULL, 5, '/sys/setting', 'sys/setting/index', 0);
INSERT INTO `sys_menu` VALUES (23, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 22, '系统配置-查询', NULL, 'BUTTON', 'sys:setting:query', 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (24, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 22, '系统配置-新增', NULL, 'BUTTON', 'sys:setting:add', 2, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (25, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 22, '系统配置-修改', NULL, 'BUTTON', 'sys:setting:edit', 3, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (26, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 22, '系统配置-删除', NULL, 'BUTTON', 'sys:setting:del', 4, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (27, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 1, '字典管理', 'peoples', 'MENU', NULL, 6, '/sys/dict', 'sys/dict/index', 0);
INSERT INTO `sys_menu` VALUES (28, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 27, '字典管理-查询', NULL, 'BUTTON', 'sys:dict:query', 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (29, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 27, '字典管理-新增', NULL, 'BUTTON', 'sys:dict:add', 2, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (30, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 27, '字典管理-修改', NULL, 'BUTTON', 'sys:dict:edit', 3, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (31, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 27, '字典管理-删除', NULL, 'BUTTON', 'sys:dict:del', 4, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (32, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 0, '系统监控', 'monitor', 'MENU', NULL, 102, '/monitor', NULL, 0);
INSERT INTO `sys_menu` VALUES (33, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 32, '系统信息', 'client', 'MENU', NULL, 1, '/monitor/sysInfo', NULL, 0);
INSERT INTO `sys_menu` VALUES (34, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 33, '系统信息-查询', NULL, 'BUTTON', 'monitor:sysInfo:query', 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (35, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 0, '开发工具', 'api', 'MENU', NULL, 103, '/dev', NULL, 0);
INSERT INTO `sys_menu` VALUES (36, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 35, '代码生成', 'api', 'MENU', NULL, 1, '/dev/codeGen', 'dev/codeGen/index', 0);
INSERT INTO `sys_menu` VALUES (37, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 36, '代码生成-查询', NULL, 'BUTTON', 'dev:codeGen:query', 1, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (38, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 36, '代码生成-新增', NULL, 'BUTTON', 'dev:codeGen:add', 2, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (39, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 36, '代码生成-修改', NULL, 'BUTTON', 'dev:codeGen:edit', 3, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (40, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 36, '代码生成-删除', NULL, 'BUTTON', 'dev:codeGen:del', 4, NULL, NULL, 0);

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
) ENGINE = InnoDB COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, '超级管理员', 'admin', 'ALL', '拥有最高级权限');
INSERT INTO `sys_role` VALUES (2, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, '测试', 'test', 'DEPT_AND_CHILD', '测试');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(11) NOT NULL,
  `menu_id` bigint(11) NOT NULL,
  INDEX `idx_role_id`(`role_id`) USING BTREE,
  INDEX `idx_menu_id`(`menu_id`) USING BTREE
) ENGINE = InnoDB COMMENT = '角色菜单' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB COMMENT = '系统设置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_setting
-- ----------------------------
INSERT INTO `sys_setting` VALUES (1, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 'system_name', '系统名称', 'XXX管理系统', 'INPUT', NULL);

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
) ENGINE = InnoDB COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '2025-05-20 13:14:20', 0, '2025-05-20 13:14:20', 1, 'admin', '超级管理员', '$2a$10$Ja/x.8cWHKQ/6kaZLZWu6uj2lD9PBVcPUsIjHpdCluvts0tjwa5je', '13800138000', 'admin@vector.com', 'UNKNOWN', 'ENABLED', 'https://wengxs.oss-cn-shenzhen.aliyuncs.com/20250403/fba712af-9c6f-46d1-905f-56435e7faeb4.jpg', 2, '1111');
INSERT INTO `sys_user` VALUES (2, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 'test', '测试', '$2a$10$uTi7sxCpcqyilAAZM/xCJefmv5BolGrY/Sz.0rJL8KHYa5iza31Iy', '13800138001', 'test@vector.com', 'MALE', 'ENABLED', NULL, 4, '222');
INSERT INTO `sys_user` VALUES (3, '2025-05-20 13:14:20', 1, '2025-05-20 13:14:20', 1, 'test1', '测试1', '$2a$10$8g5EM7S8Zq4dUABZUXCVaex2LoQqZK.A5McZDnvMZcmiCpYaUkcHm', '13800138002', NULL, 'FEMALE', 'ENABLED', 'https://wengxs.oss-cn-shenzhen.aliyuncs.com/20240507/f5f05d76-5ae7-4ac5-bf9e-97f48f23266b.jpg', 3, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  INDEX `idx_user_id`(`user_id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB COMMENT = '用户角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (3, 2);

SET FOREIGN_KEY_CHECKS = 1;
