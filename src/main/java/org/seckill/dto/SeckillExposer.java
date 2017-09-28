package org.seckill.dto;

public class SeckillExposer {
	/*
	 * 是否开启秒杀
	 */
	private boolean exposed;
	
	/*
	 * 一种加密措施
	 */
	private String md5;
	
	/*
	 * 秒杀id
	 */
	private long seckillId;
	
	/*
	 * 系统当前时间
	 */
	private long nowTime;
	
	/*
	 * 秒杀开启时间
	 */
	private long startTime;
	
	/*
	 * 秒杀结束时间
	 */
	private long endTime;

	public SeckillExposer(boolean exposed, String md5, long seckillId) {
		this.exposed = exposed;
		this.md5 = md5;
		this.seckillId = seckillId;
	}

	public SeckillExposer(boolean exposed,long seckillId, long nowTime, long startTime, long endTime) {
		this.exposed = exposed;
		this.seckillId = seckillId;
		this.nowTime = nowTime;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public SeckillExposer(boolean exposed, long seckillId) {
		this.exposed = exposed;
		this.seckillId = seckillId;
	}
	

	public boolean isExposed() {
		return exposed;
	}

	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public long getNowTime() {
		return nowTime;
	}

	public void setNowTime(long nowTime) {
		this.nowTime = nowTime;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "SeckillExposer [exposed=" + exposed + ", " + (md5 != null ? "md5=" + md5 + ", " : "") + "seckillId="
				+ seckillId + ", nowTime=" + nowTime + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
	
	
}
