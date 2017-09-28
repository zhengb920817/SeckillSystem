package org.seckill.exception;

/*
 * 秒杀关闭异常
 */
public class SeckillCloseException extends SeckillException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -850110949024776554L;

	public SeckillCloseException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}

	public SeckillCloseException(String message, Throwable cause) {
		// TODO Auto-generated constructor stub
		super(message, cause);
	}
}
