/*
 Navicat PostgreSQL Data Transfer

 Source Server         : gDOS本地【localhost】
 Source Server Type    : PostgreSQL
 Source Server Version : 100002
 Source Host           : localhost:5432
 Source Catalog        : gx_activiti_sample
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 100002
 File Encoding         : 65001

 Date: 01/03/2020 22:21:43
*/


-- ----------------------------
-- Table structure for t_leaveform
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_leaveform";
CREATE TABLE "public"."t_leaveform" (
  "id" int8 NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "uid" int8,
  "days" int4,
  "reason" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(255) COLLATE "pg_catalog"."default",
  "utime" int8,
  "type" int4
)
;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_user";
CREATE TABLE "public"."t_user" (
  "id" int8 NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "password" varchar(255) COLLATE "pg_catalog"."default",
  "role" int4,
  "purview" int8,
  "alias" varchar(255) COLLATE "pg_catalog"."default",
  "avatar" varchar(255) COLLATE "pg_catalog"."default",
  "ctime" timestamp(6),
  "utime" timestamp(6)
)
;

-- ----------------------------
-- Primary Key structure for table t_leaveform
-- ----------------------------
ALTER TABLE "public"."t_leaveform" ADD CONSTRAINT "t_leaveform_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_user
-- ----------------------------
ALTER TABLE "public"."t_user" ADD CONSTRAINT "t_user_pkey" PRIMARY KEY ("id");
