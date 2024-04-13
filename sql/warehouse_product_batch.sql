SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `warehouse_product_batch`;
CREATE TABLE `warehouse_product_batch` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `product_lot_id` bigint NOT NULL COMMENT '产品批号ID',
  `batch_no` varchar(32) NOT NULL COMMENT '批次编号',
  `qualified` int NOT NULL COMMENT '合格状态(0=不合格,1=合格)',
  `received_qty` int NOT NULL COMMENT '收货数量',
  `usable_qty` int NOT NULL COMMENT '可用数量',
  `receive_id` bigint NULL COMMENT '收货单ID',
  `receive_detail_id` bigint NULL COMMENT '收货单明细ID',
  PRIMARY KEY (`id`)
) COMMENT = '产品批次';

SET FOREIGN_KEY_CHECKS = 1;
