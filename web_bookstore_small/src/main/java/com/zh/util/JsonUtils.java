package com.zh.util;

import com.google.gson.Gson;

public class JsonUtils {

	/**
	 * 将一个对象转换成一个json字符串
	 * @param data
	 * @return
	 */
	public static String toJson(Object data) {
		Gson gson = new Gson();
		return gson.toJson(data);
	}
	
}
