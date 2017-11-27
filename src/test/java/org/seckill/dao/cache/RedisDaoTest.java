package org.seckill.dao.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.SecKill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath:Spring/spring-dao.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisDaoTest {

	@Autowired
	private RedisDao redisDao;

	@Autowired
	private SeckillDao seckillDao;

	@Test
	public void testSeckill() {
		long seckillId = 1000;
		SecKill secKill = redisDao.getSeckill(seckillId);
		if (secKill == null) {
			secKill = seckillDao.queryById(seckillId);
			if (secKill != null) {
				String result = redisDao.putSeckill(secKill);
				System.out.println(result);

				secKill = redisDao.getSeckill(seckillId);
				System.out.println(secKill);
			}
		}
	}

}
