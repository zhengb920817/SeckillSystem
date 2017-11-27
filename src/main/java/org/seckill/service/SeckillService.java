package org.seckill.service;

import java.util.List;

import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillExposer;
import org.seckill.entity.SecKill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

/*
 * 站在使用者的角度设计接口
 * 三个方面:方法定义的粒度 ，参数，返回类型
 */
public interface SeckillService {
	/*
	 * 查询所有秒杀记录
	 */
	List<SecKill> getSeckillList();

	/*
	 * 查询单个秒杀记录
	 */
	SecKill getById(long seckillId);

	/*
	 * 秒杀开启时输出秒杀接口地址 否则输出系统时间和秒杀时间
	 */
	SeckillExposer exportSeckillUrl(long seckillId);

	/*
	 * 执行秒杀操作
	 */
	SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException;
	
	SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5);
	
}
