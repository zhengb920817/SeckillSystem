package org.seckill.exception;

/*
 * 重复秒杀异常
 */
public class RepeatKillException extends SeckillException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7588706798824576584L;
	
	public RepeatKillException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
	
	public RepeatKillException(String message,Throwable cause) {
		// TODO Auto-generated constructor stub
		super(message, cause);
	}
}
