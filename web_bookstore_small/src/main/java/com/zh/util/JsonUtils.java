package com.zh.util;

import com.google.gson.Gson;

public class JsonUtils {

	/**
	 * ��һ������ת����һ��json�ַ���
	 * @param data
	 * @return
	 */
	public static String toJson(Object data) {
		Gson gson = new Gson();
		return gson.toJson(data);
	}
	
}
