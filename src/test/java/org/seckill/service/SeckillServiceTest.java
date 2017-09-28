package org.seckill.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillExposer;
import org.seckill.entity.SecKill;
import org.seckill.exception.RepeatKillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysql.jdbc.integration.jboss.ExtendedMysqlExceptionSorter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:Spring/spring-dao.xml",
		"classpath:Spring/spring-service.xml"})
public class SeckillServiceTest {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SeckillService seckillService;

	@Test
	public void testGetSeckillList() {
		List<SecKill> list = seckillService.getSeckillList();
		logger.info("list={}",list);
	}

	@Test
	public void testGetById() {
		SecKill secKill = seckillService.getById(1000);
		logger.info("seckill={}",secKill);
	}

	@Test
	public void testExportSeckillUrl() {
		SeckillExposer exposer = seckillService.exportSeckillUrl(1000);
		System.out.println(exposer);
		logger.info("exposer = {} ", exposer);
	}

	@Test
	public void testExecuteSeckill() {
		long id = 1000;
		long userPhone = 13588056736L; 
		String md5 = "556b7d0c1adeb39acd6a618918de6198";
		
		try {
			SeckillExecution seckillExecution = seckillService.executeSeckill(id, userPhone, md5);
		} catch (RepeatKillException e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
	}

}
