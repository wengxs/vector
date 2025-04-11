SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `warehouse_receive`;
CREATE TABLE `warehouse_receive` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `receive_no` varchar(32) NOT NULL COMMENT '收货单号',
  `receive_status` varchar(16) NOT NULL COMMENT '收货状态',
  `biz_type` varchar(16) NOT NULL COMMENT '业务类型',
  `biz_no` varchar(32) NULL COMMENT '业务单号',
  `logistics_name` varchar(16) NULL COMMENT '物流名称',
  `logistics_no` varchar(32) NULL COMMENT '物流单号',
  `signed_time` datetime NULL COMMENT '签收时间',
  `batch_no` varchar(32) NULL COMMENT '批次编号',
  PRIMARY KEY (`id`)
) COMMENT = '收货单';

SET FOREIGN_KEY_CHECKS = 1;


ALTER TABLE `warehouse_receive`
ADD COLUMN `batch_no` varchar(32) NULL COMMENT '批次编号' AFTER `signed_time`;