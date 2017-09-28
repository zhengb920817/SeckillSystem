package org.seckill.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SecKill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Spring/spring-dao.xml")
public class SeckillDaoTest {

	@Resource
	private SeckillDao seckillDao;

	@Test
	public void testReduceNumber() {
		Date killDate = new Date();
		int updateCount = seckillDao.reduceNumber(1000,killDate);
		System.out.println(updateCount);
	}

	@Test
	public void testQueryById() {
		long seckillId = 1000;
		SecKill secKill = seckillDao.queryById(seckillId);
		System.out.println(secKill);
	}

	@Test
	public void testQueryAll() {
		List<SecKill> seckillList = seckillDao.queryAll(0, 100);
//		System.out.println(seckillList);
		for (SecKill secKill : seckillList) {
			System.out.println(secKill);
		}
	}
	
	@Test
	public void testIncreaseNumber() {
		int count = seckillDao.increaseNumber(1000);
		System.out.println(count);
	}

}
