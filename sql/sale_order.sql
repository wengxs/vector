SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `sale_order`;
CREATE TABLE `sale_order`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `order_no` varchar(32) NOT NULL COMMENT '订单编号',
  `order_status` varchar(16) NOT NULL COMMENT '订单状态',
  `platform` varchar(16) NULL COMMENT '销售平台',
  `shop_name` varchar(32) NULL COMMENT '店铺名称',
  `buyer` varchar(32) NULL COMMENT '买家名称',
  `mobile` varchar(32) NULL COMMENT '买家电话',
  `area_code` varchar(32) NULL COMMENT '买家地区编码',
  `area` varchar(32) NULL COMMENT '买家地区',
  `address` varchar(32) NULL COMMENT '买家地址',
  `order_remake` varchar(256) NULL COMMENT '订单备注',
  `amount` decimal(10, 2) NULL COMMENT '总金额',
  `pay_time` datetime NULL COMMENT '付款时间',
  `ship_time` datetime NULL COMMENT '发货时间',
  `sign_time` datetime NULL COMMENT '签收时间',
  `finish_time` datetime NULL COMMENT '完结时间',
  `cancel_time` datetime NULL COMMENT '取消时间',
  PRIMARY KEY (`id`)
) COMMENT = '销售单';

SET FOREIGN_KEY_CHECKS = 1;
