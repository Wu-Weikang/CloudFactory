package com.neuedu.exception;

/**
 * ���ͱ������쳣��
 * 
 * @author ��ΰ��
 * @date 2020-7-17
 */
public class TypeIsReferencedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TypeIsReferencedException (String msg) {
		
		super(msg);
		
	}
}
