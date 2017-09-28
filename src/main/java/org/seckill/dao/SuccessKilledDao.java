package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {
	
	/*
	 * 插入秒杀成果记录
	 */
	int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

	/*
	 * 查询秒杀记录
	 */
	SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
	
	/*
	 * 删除秒杀记录
	 */
	int deleteSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
