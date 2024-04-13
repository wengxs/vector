SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `warehouse_receive_detail`;
CREATE TABLE `warehouse_receive_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `receive_id` bigint NOT NULL COMMENT '收货单ID',
  `product_id` bigint NOT NULL COMMENT '产品ID',
  `qty` int NOT NULL COMMENT '应收数量',
  `received_qty` int NULL COMMENT '实收数量',
  `qualified_qty` int NULL COMMENT '合格数量',
  `unqualified_qty` int NULL COMMENT '不合格数量',
  PRIMARY KEY (`id`)
) COMMENT = '收货明细';

SET FOREIGN_KEY_CHECKS = 1;
