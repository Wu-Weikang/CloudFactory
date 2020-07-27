package com.neuedu.exception;

/**
 * 账号已存在异常类
 * 
 * @author 伍伟康
 * @date 2020-7-17
 */
public class AlreadyExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public AlreadyExistException (String msg) {
		
		super(msg);
		
	}
}
