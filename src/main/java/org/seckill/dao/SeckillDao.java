package org.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SecKill;

public interface SeckillDao {
	/*
	 * 减库存
	 */
	int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);
	
	/*
	 * 增加库存
	 */
	int increaseNumber(@Param("seckillId") long seckillId);

	/*
	 * 根据id获取商品详情
	 */
	SecKill queryById(long seckillId);

	/*
	 * 请求全部商品信息
	 */
	List<SecKill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
