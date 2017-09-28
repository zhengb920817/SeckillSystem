package org.seckill.service.impl;

import java.util.Date;
import java.util.List;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillExposer;
import org.seckill.entity.SecKill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
public class SeckillSeviceImpl implements SeckillService {

	@Autowired
	private SeckillDao seckillDao;

	@Autowired
	private SuccessKilledDao sucessKilledDao;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final static String md5slat = "qqwqdadadeeeq123";

	private String getMd5BySeckillId(long seckillId) {
		String base = md5slat + '/' + seckillId;

		String md5Value = DigestUtils.md5DigestAsHex(base.getBytes());

		return md5Value;
	}

	private Date getNowDateTime() {
		return new Date();
	}

	@Override
	public List<SecKill> getSeckillList() {
		// TODO Auto-generated method stub
		return seckillDao.queryAll(0, 4);
	}

	@Override
	public SecKill getById(long seckillId) {
		// TODO Auto-generated method stub
		return seckillDao.queryById(seckillId);
	}

	@Override
	public SeckillExposer exportSeckillUrl(long seckillId) {
		SecKill secKill = seckillDao.queryById(seckillId);
		if (secKill == null) {
			return new SeckillExposer(false, seckillId);
		}

		Date startTime = secKill.getStartTime();
		Date endTime = secKill.getEndTime();
		Date nowTime = getNowDateTime();

		if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime() ) {
			return new SeckillExposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}

		String md5 = getMd5BySeckillId(seckillId);
		return new SeckillExposer(true, md5, seckillId);

	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
	public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {
		// TODO Auto-generated method stub
		if (md5 == null || !md5.equals(getMd5BySeckillId(seckillId))) {
			throw new SeckillException("seckill date rewrite");
		}

		Date nowTime = getNowDateTime();

		try {
			int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
			if (updateCount <= 0) {
				throw new SeckillCloseException("seckill is closed");
			} else {
				int insertCount = sucessKilledDao.insertSuccessKilled(seckillId, userPhone);
				if (insertCount <= 0) {
					throw new RepeatKillException("seckill repeated");
				} else {
					SuccessKilled successKilled = sucessKilledDao.queryByIdWithSeckill(seckillId, userPhone);
					return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
				}
			}
		} catch (SeckillCloseException e1) {
			throw e1;
		} catch (RepeatKillException e2) {
			throw e2;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
			// 所有编译期异常转化为运行期异常
			throw new SeckillException("seckill inner erro :" + e.getMessage());
		}
	}

}
