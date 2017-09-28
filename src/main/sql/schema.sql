create DATABASE seckill;

use seckill;

create table seckill(
	`seckkill_id` BIGINT not null AUTO_INCREMENT COMMENT '商品库存id',
	`name` varchar(120) not null COMMENT '商品名称',
	`number` int not null comment '库存数量',
	`start_time` timestamp not null comment '秒杀开启时间',
	`end_time` timestamp not null comment '秒杀结束时间',
	`create_time` timestamp not null default CURRENT_TIMESTAMP COMMENT '创建时间',
	
	PRIMARY KEY(seckkill_id),
	key idx_start_time(start_time),
	key idx_end_time(end_time),
	key idx_create_time(create_time)
 )ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8  COMMENT='秒杀库存表'
 
 insert into seckill(name,number,start_time,end_time) values
 	('1000元秒杀小米4',100,'2017-08-01 00:00:00','2017-08-31 00:00:00'),
 	('1000元秒杀iPhone6',100,'2017-08-01 00:00:00','2017-08-31 00:00:00'),
 	('500元秒杀iPad2',100,'2017-08-01 00:00:00','2017-08-31 00:00:00'),
 	('200元秒杀红米note',100,'2017-08-01 00:00:00','2017-08-31 00:00:00'),
 	('1000元秒杀荣耀8',100,'2017-08-01 00:00:00','2017-08-31 00:00:00');
 	
 	
create table success_killed(
	`seckill_id` bigint not null COMMENT '秒杀商品id',
	`user_phone` bigint not null COMMENT '用户手机号',
	`state` tinyint not null default -1 comment '状态 -1：失败 0：成功 1：已付款',
	`create_time` timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
	
	primary key(seckill_id,user_phone),
	key idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='秒杀成功明细表';

create table seckill_product(
	p_id int(11) not null auto_increment comment '商品id',
	p_name varchar(100) not null comment '商品名称',
	p_price decimal(10,2) not null comment '商品价格',
	p_inventory bigint not null comment '商品库存',
	primary key(p_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMEENT = '商品明细表';


