package com.neuedu.exception;

/**
 * �˺��Ѵ����쳣��
 * 
 * @author ��ΰ��
 * @date 2020-7-17
 */
public class AlreadyExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public AlreadyExistException (String msg) {
		
		super(msg);
		
	}
}
