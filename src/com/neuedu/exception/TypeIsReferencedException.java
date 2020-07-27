package com.neuedu.exception;

/**
 * 类型被引用异常类
 * 
 * @author 伍伟康
 * @date 2020-7-17
 */
public class TypeIsReferencedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TypeIsReferencedException (String msg) {
		
		super(msg);
		
	}
}
