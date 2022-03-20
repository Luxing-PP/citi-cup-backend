drop database if exists CITIDatabase;

create database if not exists CITIDatabase;

use CITIDatabase;

create table if not exists hs_300_stock
(
	code varchar(50) not null comment '股票代码'
		primary key,
	name varchar(50) not null
)
	charset = utf8mb4;

create table if not exists permission
(
	id      int auto_increment
		primary key,
	name    varchar(20) null,
	path    varchar(30) null comment '·',
	comment varchar(50) null
)
	engine = BLACKHOLE
	charset = utf8;

create table if not exists product
(
	id           bigint auto_increment
		primary key,
	product_code varchar(30) not null,
	product_name varchar(30) not null,
	constraint product_code
		unique (product_code)
)
	charset = utf8mb4;

create table if not exists product_npv
(
	id           bigint auto_increment
		primary key,
	product_code varchar(30)   not null,
	time         datetime      not null,
	npv          decimal(6, 2) not null
)
	charset = utf8mb4;

create table if not exists quiz_question
(
	question_id      int unsigned auto_increment
		primary key,
	question_type    int          not null,
	question_content varchar(200) not null,
	question_option  varchar(200) not null,
	image_path       varchar(250) null,
	constraint question_id
		unique (question_id)
)
	charset = utf8;

create table if not exists role
(
	id      int         not null
		primary key,
	name    varchar(50) null,
	comment varchar(50) null
)
	charset = utf8;

create table if not exists role_permission
(
	role_id       int not null,
	permission_id int not null,
	primary key (role_id, permission_id)
)
	charset = utf8;

create table if not exists stock_day
(
	id     int auto_increment
		primary key,
	code   varchar(50)    not null,
	open   decimal(7, 2)  null,
	close  decimal(7, 2)  null,
	date   date           null,
	peTTM  decimal(8, 4)  null,
	pbMRQ  decimal(8, 4)  null,
	volume decimal(13, 2) null,
	constraint code
		unique (code, date)
)
	charset = utf8mb4;

create table if not exists sz_50_stock
(
	code varchar(50) not null
		primary key,
	name varchar(50) not null
)
	charset = utf8mb4;

create table if not exists test
(
	user_id       int unsigned auto_increment
		primary key,
	user_email    varchar(40) not null,
	user_password varchar(20) not null
)
	charset = utf8mb4;

create table if not exists user
(
	user_id       int unsigned auto_increment
		primary key,
	user_password varchar(100) not null,
	user_phone    varchar(11)  not null,
	user_name     varchar(50)  not null,
	email         varchar(50)  not null,
	constraint user_user_phone_uindex
		unique (user_phone)
)
	charset = utf8;

create table if not exists user_answer_time
(
	id           int unsigned auto_increment
		primary key,
	user_id      int unsigned not null,
	answer_times int unsigned null,
	answer_time  date         not null,
	constraint user_id
		foreign key (user_id) references user (user_id)
)
	charset = utf8;

create index answer_times
	on user_answer_time (answer_times);

create table if not exists quiz_answer
(
	answer_id     int unsigned auto_increment
		primary key,
	question_id   int unsigned not null,
	user_id       int unsigned not null,
	question_type int unsigned not null,
	answer_path   varchar(500) null,
	answer_times  int unsigned not null,
	constraint answer_id
		unique (answer_id),
	constraint quiz_answer_ibfk_1
		foreign key (question_id) references quiz_question (question_id)
			on delete cascade,
	constraint quiz_answer_ibfk_2
		foreign key (answer_times) references user_answer_time (answer_times)
			on delete cascade
)
	charset = utf8;

create index answer_times
	on quiz_answer (answer_times);

create index question_id
	on quiz_answer (question_id);



create index user_answer_time_id
	on user_answer_time (id);

create table if not exists user_role
(
	user_id int not null,
	role_id int not null,
	primary key (user_id, role_id)
)
	charset = utf8;

create table if not exists zz_500_stock
(
	code varchar(50) not null comment '股票代码'
		primary key,
	name varchar(50) not null
)
	charset = utf8mb4;

