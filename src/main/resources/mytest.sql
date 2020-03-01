/*
 Navicat Premium Data Transfer

 Source Server         : local_mysql
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost
 Source Database       : mytest

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : utf-8

 Date: 03/01/2020 10:59:36 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `http_log`
-- ----------------------------
DROP TABLE IF EXISTS `http_log`;
CREATE TABLE `http_log` (
  `guid` decimal(19,2) NOT NULL,
  `app_proto` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `app_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `code` decimal(19,2) DEFAULT NULL,
  `d_city` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `d_country` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `d_iso_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `download_fileid` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `dst_ip` bigint(20) DEFAULT NULL,
  `dst_mac` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `dst_port` int(11) DEFAULT NULL,
  `dst_str_ip` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `files_file_len` tinyblob,
  `files_file_name` tinyblob,
  `files_file_type` tinyblob,
  `files_file_uuid` tinyblob,
  `flow_id` decimal(19,2) DEFAULT NULL,
  `flow_id_str` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `flow_time` decimal(19,2) DEFAULT NULL,
  `guid_str` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ip_ver` int(11) DEFAULT NULL,
  `log_date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `log_time` decimal(19,2) DEFAULT NULL,
  `log_time_str` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `method` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `plugin_id` decimal(19,2) DEFAULT NULL,
  `_post_data` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `proto` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `referrer` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `req_c_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `res_c_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `s_city` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `s_country` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `s_iso_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sensor_id` int(11) DEFAULT NULL,
  `server` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `src_ip` bigint(20) DEFAULT NULL,
  `src_mac` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `src_port` int(11) DEFAULT NULL,
  `src_str_ip` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `u_agnet` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `uri` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `via` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `x_cache` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `x_powered_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(32) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `score` float(11,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `student`
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES (null, '张三', '23', '93.00'), (null, '张三', '23', '93.00');
COMMIT;

-- ----------------------------
--  Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` bigint(19) NOT NULL,
  `age` decimal(19,2) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `mobile_number` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `nick_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `tb_user`
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES ('1', '12.00', '1', '123', 'jese', '123456', 'ssss');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
