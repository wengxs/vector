/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50741 (5.7.41-log)
 Source Host           : localhost:3306
 Source Schema         : vector_third

 Target Server Type    : MySQL
 Target Server Version : 50741 (5.7.41-log)
 File Encoding         : 65001

 Date: 12/04/2025 01:40:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for third_sms_log
-- ----------------------------
DROP TABLE IF EXISTS `third_sms_log`;
CREATE TABLE `third_sms_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
  `platform` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '短信平台',
  `mobile` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '手机号码',
  `template_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '模版代码',
  `template_param` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '模版参数',
  `result` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '发送结果',
  `exception` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '异常信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '短信发送记录' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
