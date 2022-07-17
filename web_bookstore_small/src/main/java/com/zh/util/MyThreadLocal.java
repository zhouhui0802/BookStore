package com.zh.util;

import java.util.HashMap;
import java.util.Map;
/*
 * �Զ����ThreadLocal��
 */

public class MyThreadLocal<T> {

	private Map<Thread, T> map = new HashMap<Thread, T>();

	//�����ݶ��󱣴浽������
	public void set(T t) {
		map.put(Thread.currentThread(), t);
	}

	//�ӻ����л�ȡ���ݶ���
	public T get() {
		T t = null;
		t = map.get(Thread.currentThread());
		return t;
	}

	//ɾ�������еĵ�ǰ���ݶ���
	public void remove() {
		map.remove(Thread.currentThread());
	}
	
}
