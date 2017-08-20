--创建用户表
create table if not exists sys_user(
	user_id bigint primary key AUTO_INCREMENT,
	login_name varchar(100) not null,
	login_password varchar(100) not null,
	user_name varchar(100) not null,
	sex char(1),
	phone_number varchar(20),
	is_valid  char default 'Y', --当前用户是否有效，Y有效，N失效
	create_date timestamp not null,
	creater varchar(100) not null,
	organization_id bigint not null --组织号，为多租户做准备
)DEFAULT CHARSET=utf8;