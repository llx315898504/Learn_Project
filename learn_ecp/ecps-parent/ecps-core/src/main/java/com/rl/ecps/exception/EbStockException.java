package com.rl.ecps.exception;

/**
 * 库存操作异常
 * 
 * @author 86150
 *
 */
public class EbStockException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EbStockException() {
		super();
	}

	public EbStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EbStockException(String message, Throwable cause) {
		super(message, cause);
	}

	public EbStockException(String message) {
		super(message);
	}

	public EbStockException(Throwable cause) {
		super(cause);
	}

}