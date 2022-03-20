create database if not exists `CITITest` default charset utf8mb4;

use CITITest;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS user;
create table user
(
	user_id       int unsigned auto_increment,
	user_phone    varchar(11) not null,
	user_password varchar(30) not null,
	constraint user_pk
		primary key (user_id)
) engine = InnoDB
  charset = utf8;
create unique index user_user_phone_uindex on user (user_phone);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS product;
create table product
(
	`id`           BIGINT      NOT NULL AUTO_INCREMENT,
	`product_code` varchar(30) NOT NULL UNIQUE,
	`product_name` varchar(30) NOT NULL,
	constraint product_pk primary key (id)
) engine = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for product_npv
-- 日净值
-- ----------------------------
DROP TABLE IF EXISTS `product_npv`;
create table `product_npv`
(
	`id`           BIGINT        NOT NULL AUTO_INCREMENT,
	`product_code` varchar(30)   NOT NULL,
	`time`         datetime(0)   NOT NULL,
	`npv`          DECIMAL(6, 2) NOT NULL,
	constraint product_pk primary key (id)
) engine = InnoDB
  AUTO_INCREMENT = 15
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;


drop table if exists quiz_answer;
drop table if exists quiz_question;
drop table if exists quiz;
drop table if exists user;

-- ----------------------------
-- Table structure for user
-- ----------------------------
create table user
(
	user_id       int unsigned auto_increment,
	user_phone    varchar(11) not null unique,
	user_type     int         not null,
	user_password varchar(30) not null,
	constraint user_pk
		primary key (user_id)
) engine = InnoDB
  charset = utf8;


-- ----------------------------
-- Table structure for quizPO
-- ----------------------------
create table quiz
(
	quiz_id          int unsigned not null unique auto_increment,
	quiz_status      int          not null,
	quiz_create_time date         not null,
	quiz_start_time  date         not null,
	quiz_end_time    date         not null,
	quiz_title       varchar(40)  not null unique,
	constraint quiz_pk
		primary key (quiz_id),
	unique key (quiz_title)
) engine = InnoDB
  charset = utf8;


-- ----------------------------
-- Table structure for quiz
-- ----------------------------
create table quiz_question
(
	question_id          int unsigned not null unique auto_increment,
	quiz_id              int unsigned not null,
	question_create_time date         not null,
	question_type        int          not null,
	question_content     varchar(40)  not null,
	question_option      varchar(200) not null,
	foreign key (quiz_id) references quiz (quiz_id) on delete cascade,
	constraint quiz_question_pk
		primary key (question_id)
) engine = InnoDB
  charset = utf8;


-- ----------------------------
-- Table structure for quiz answer
-- ----------------------------
create table quiz_answer
(
	answer_id     int unsigned not null unique auto_increment,
	quiz_id       int unsigned not null,
	question_id   int unsigned not null,
	user_id       int unsigned not null,
	question_type int unsigned not null,
	answer_time   date         not null,
	answer        varchar(500) not null,
	foreign key (quiz_id) references quiz (quiz_id) on delete cascade,
	foreign key (question_id) references quiz_question (question_id) on delete cascade,
	constraint quiz_answer_pk
		primary key (answer_id)
) engine = InnoDB
  charset = utf8;

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
	`user_id` int(11) NOT NULL,
	`role_id` int(11) NOT NULL,
	PRIMARY KEY (`user_id`, `role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`
(
	`role_id`       int(11) NOT NULL,
	`permission_id` int(11) NOT NULL,
	PRIMARY KEY (`role_id`, `permission_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
	`id`      int(11) NOT NULL,
	`name`    varchar(50) DEFAULT NULL COMMENT '名称',
	`comment` varchar(50) DEFAULT NULL COMMENT '备注',
	PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`
(
	`id`      int(11) NOT NULL,
	`name`    varchar(20) DEFAULT NULL COMMENT '名称',
	`path`    varchar(30) DEFAULT NULL COMMENT '路径',
	`comment` varchar(50) DEFAULT NULL COMMENT '备注',
	PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

SET FOREIGN_KEY_CHECKS = 1;

