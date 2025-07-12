/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50741 (5.7.41-log)
 Source Host           : localhost:3306
 Source Schema         : vector_oauth2_server

 Target Server Type    : MySQL
 Target Server Version : 50741 (5.7.41-log)
 File Encoding         : 65001

 Date: 12/04/2025 01:39:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth2_authorization
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_authorization`;
CREATE TABLE `oauth2_authorization`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `registered_client_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `principal_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `authorization_grant_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `authorized_scopes` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `attributes` blob NULL,
  `state` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authorization_code_value` blob NULL,
  `authorization_code_issued_at` timestamp NULL DEFAULT NULL,
  `authorization_code_expires_at` timestamp NULL DEFAULT NULL,
  `authorization_code_metadata` blob NULL,
  `access_token_value` blob NULL,
  `access_token_issued_at` timestamp NULL DEFAULT NULL,
  `access_token_expires_at` timestamp NULL DEFAULT NULL,
  `access_token_metadata` blob NULL,
  `access_token_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `access_token_scopes` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `oidc_id_token_value` blob NULL,
  `oidc_id_token_issued_at` timestamp NULL DEFAULT NULL,
  `oidc_id_token_expires_at` timestamp NULL DEFAULT NULL,
  `oidc_id_token_metadata` blob NULL,
  `refresh_token_value` blob NULL,
  `refresh_token_issued_at` timestamp NULL DEFAULT NULL,
  `refresh_token_expires_at` timestamp NULL DEFAULT NULL,
  `refresh_token_metadata` blob NULL,
  `user_code_value` blob NULL,
  `user_code_issued_at` timestamp NULL DEFAULT NULL,
  `user_code_expires_at` timestamp NULL DEFAULT NULL,
  `user_code_metadata` blob NULL,
  `device_code_value` blob NULL,
  `device_code_issued_at` timestamp NULL DEFAULT NULL,
  `device_code_expires_at` timestamp NULL DEFAULT NULL,
  `device_code_metadata` blob NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth2_authorization
-- ----------------------------

-- ----------------------------
-- Table structure for oauth2_authorization_consent
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_authorization_consent`;
CREATE TABLE `oauth2_authorization_consent`  (
  `registered_client_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `principal_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `authorities` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`registered_client_id`, `principal_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth2_authorization_consent
-- ----------------------------

-- ----------------------------
-- Table structure for oauth2_registered_client
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_registered_client`;
CREATE TABLE `oauth2_registered_client`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `client_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `client_id_issued_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `client_secret` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `client_secret_expires_at` timestamp NULL DEFAULT NULL,
  `client_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `client_authentication_methods` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `authorization_grant_types` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `redirect_uris` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `post_logout_redirect_uris` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `scopes` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `client_settings` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `token_settings` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth2_registered_client
-- ----------------------------
INSERT INTO `oauth2_registered_client` VALUES ('fb79fa20-867e-470d-9c03-32eb35584e71', 'vector-admin', '2025-03-26 17:47:16', '$2a$10$wJpy5ZMz4JIidQivY5oOCOHgdSjx1ZGGMNmEh5itASNZM4PxhF9Uu', NULL, '后台管理系统', 'client_secret_basic', 'refresh_token,client_credentials,password,authorization_code', 'http://127.0.0.1:8080/authorized', 'http://127.0.0.1:8080/logged-out', 'openid,profile', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",86400.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",3600.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000],\"settings.token.device-code-time-to-live\":[\"java.time.Duration\",300.000000000]}');
INSERT INTO `oauth2_registered_client` VALUES ('ab2ab9dd-7e9e-40b4-ae9a-2529b1229957', 'vector-app', '2025-03-26 17:47:17', '$2a$10$5JsfJ0C4rRrvLGRdE9NbpeABwAN3eFOqeIknMwDBJdD6/2ilTh6kO', NULL, 'App', 'client_secret_basic', 'refresh_token,client_credentials,password,authorization_code,sms_code', 'http://127.0.0.1:8080/authorized', 'http://127.0.0.1:8080/logged-out', 'openid,profile', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",86400.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",3600.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000],\"settings.token.device-code-time-to-live\":[\"java.time.Duration\",300.000000000]}');

SET FOREIGN_KEY_CHECKS = 1;
