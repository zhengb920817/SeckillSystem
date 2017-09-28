package org.seckill.entity;

import java.util.Date;

public class SuccessKilled {
	//秒杀商品id
	private long seckillId;
	//用户手机号
	private long userPhone;
	//秒杀状态 -1：失败 0：成功 1：已付款
	private short state;
	//创建时间
	private Date createTime;
	//秒杀商品对应实体
	private SecKill secKill;

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public long getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public String toString() {
		return "SuccessKill [seckillId=" + seckillId + ", userPhone=" + userPhone + ", state=" + state + ", createTime="
				+ createTime + "]";
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public SecKill getSecKill() {
		return secKill;
	}

	public void setSecKill(SecKill secKill) {
		this.secKill = secKill;
	}
}
