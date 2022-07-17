package com.zh.util;

import java.util.HashMap;
import java.util.Map;
/*
 * 自定义的ThreadLocal类
 */

public class MyThreadLocal<T> {

	private Map<Thread, T> map = new HashMap<Thread, T>();

	//将数据对象保存到缓存中
	public void set(T t) {
		map.put(Thread.currentThread(), t);
	}

	//从缓存中获取数据对象
	public T get() {
		T t = null;
		t = map.get(Thread.currentThread());
		return t;
	}

	//删除缓存中的当前数据对象
	public void remove() {
		map.remove(Thread.currentThread());
	}
	
}
