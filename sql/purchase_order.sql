SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `purchase_order`;
CREATE TABLE `purchase_order`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `order_no` varchar(32) NOT NULL COMMENT '订单编号',
  `order_status` varchar(16) NOT NULL COMMENT '订单状态',
  `supplier_id` bigint NOT NULL COMMENT '供应商ID',
  `purchase_remake` varchar(256) NULL COMMENT '采购备注',
  `amount` decimal(10, 4) NOT NULL COMMENT '总金额',
  PRIMARY KEY (`id`)
) COMMENT = '采购单';

SET FOREIGN_KEY_CHECKS = 1;
