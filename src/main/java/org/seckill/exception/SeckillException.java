package org.seckill.exception;

public class SeckillException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SeckillException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
	
	public SeckillException(String message, Throwable cause) {
		// TODO Auto-generated constructor stub
		super(message,cause);
	}
}
