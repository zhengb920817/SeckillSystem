package org.seckill.dao.cache;

import org.seckill.entity.SecKill;
import org.seckill.entity.SuccessKilled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDao {
	private final JedisPool jedisPool;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private RuntimeSchema<SecKill> schema = RuntimeSchema.createFrom(SecKill.class);
	private RuntimeSchema<SuccessKilled> successKilledSchema = RuntimeSchema.createFrom(SuccessKilled.class);

	public RedisDao(String host, int port) {
		// TODO Auto-generated constructor stub
		jedisPool = new JedisPool(host, port);
	}

	public String putSuccessKilledByIdAndUserPhone(final int id, final long userPhone,
			final SuccessKilled successKilled) {
		try {
			Jedis jedis = jedisPool.getResource();
			try {
				String key = id + ":" + userPhone;
				byte[] bytes = ProtostuffIOUtil.toByteArray(successKilled, successKilledSchema,
						LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE * 2));
				return jedis.setex(key.getBytes(), 60 * 60, bytes);
			} finally {
				// TODO: handle finally clause
				jedis.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public SuccessKilled getSuccessKillByIdAndUserPhone(final int successKilledId, final long userPhone) {
		try {
			Jedis jedis = jedisPool.getResource();
			try {
				String key = successKilledId + ":" + userPhone;
				byte[] bytes = jedis.get(key.getBytes());
				if (bytes != null) {
					SuccessKilled successKilled = successKilledSchema.newMessage();
					ProtostuffIOUtil.mergeFrom(bytes, successKilled, successKilledSchema);
					return successKilled;
				}
			} finally {
				// TODO: handle finally clause
				jedis.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}

		return null;
	}

	public SecKill getSeckill(long seckillId) {
		// redis操作逻辑
		try {
			Jedis jedis = jedisPool.getResource();
			try {
				String key = "seckill:" + seckillId;
				byte[] bytes = jedis.get(key.getBytes());
				if (bytes != null) {
					SecKill secKill = schema.newMessage();
					ProtostuffIOUtil.mergeFrom(bytes, secKill, schema);
					return secKill;
				}
			} finally {
				// TODO: handle finally clause
				jedis.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public String putSeckill(SecKill secKill) {
		try {
			Jedis jedis = jedisPool.getResource();
			try {
				String key = "seckill:" + secKill.getSeckillId();
				byte[] bytes = ProtostuffIOUtil.toByteArray(secKill, schema,
						LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
				int timeOut = 60 * 60;
				String result = jedis.setex(key.getBytes(), timeOut, bytes);
				return result;
			} finally {
				// TODO: handle finally clause
				jedis.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}
		return null;
	}
}
