DELIMITER $$
--创建存储过程
--ROW_COUNT() 上一句SQL的返回过程
CREATE PROCEDURE `seckill`.`execute_seckill`
	(in v_seckill_id bigint, in v_phone bigint,
		in v_kill_time timestamp,out r_result int) 
	BEGIN
		DECLARE insert_count int default 0;
		START TRANSACTION;
		insert ignore into success_killed
			(seckill_id,user_phone,create_time)
			values (v_seckill_id, v_phone, v_kill_time);
		select ROW_COUNT() into insert_count;
		
		IF (insert_count = 0) THEN
			ROLLBACK;
			set r_result = -1;
		ELSEIF(insert_count < 0) THEN
			rollback;
			set r_result = -2;
		ELSE 
			update seckill 
			set number = number - 1
			where seckill_id = v_seckill_id 	
			and end_time > v_kill_time 
			and start_time < v_kill_time 
			and number > 0;
		select row_count() into insert_count;
		IF(insert_count = 0) then
			ROLLBACK;
			set r_result = 0;
		ELSEIF (insert_count <0) then	
			ROLLBACK;
			set r_result = -2;
		ELSE 
			COMMIT;
			set r_result = 1;
		END if;
		END if; 
		END;
	$$
	--存储过程定义结束
	
	DELIMITER ;
	
	set @r_result = -3;
	--执行存储过程
	call execute_seckill(1003,18668214719,now(),@r_result);
	
	--获取结果
	select @r_result;
	
	
	--存储过程
	--1:存储过程优化：事务行级锁持有时间
	--2：不要过度依赖存储过程
	--3：简单的逻辑可以使用存储过程
	--4：QPS：一个简单的秒杀6000/QPS
	
	
	
	
	