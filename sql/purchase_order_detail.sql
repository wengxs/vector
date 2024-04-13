SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `purchase_order_detail`;
CREATE TABLE `purchase_order_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `order_id` bigint NOT NULL COMMENT '采购单ID',
  `product_id` bigint NOT NULL COMMENT '产品ID',
  `price` decimal(10, 4) NOT NULL COMMENT '单价',
  `qty` int NOT NULL COMMENT '采购数量',
  `amount` decimal(10, 4) NOT NULL COMMENT '总金额',
  `received_qty` int NULL COMMENT '收货数量',
  PRIMARY KEY (`id`)
) COMMENT = '采购明细';

SET FOREIGN_KEY_CHECKS = 1;
