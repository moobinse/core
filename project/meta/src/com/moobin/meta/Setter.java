package com.moobin.meta;

import java.lang.reflect.Array;
import java.util.List;

import com.moobin.core.MoobinException;

class Setter {

	public static Object fromString(String s, Class<?> javaType) {
		if (javaType == String.class) return s;
		if (javaType == int.class) return Integer.valueOf(s);
		if (javaType == Integer.class) return Integer.valueOf(s);
		if (javaType == boolean.class) return Boolean.valueOf(s);
		if (javaType == Boolean.class) return Boolean.valueOf(s);
		throw new MoobinException("Not implemented");
	}

	public static Object arrayFromString(String value, Class<?> clazz) {
		if (value.isEmpty()) {
			return Array.newInstance(clazz, 0);
		}
		String[] arr = value.split(",");
		Object ret = Array.newInstance(clazz, arr.length);
		for (int i = 0; i < arr.length; i++) {
			Array.set(ret, i, fromString(arr[i], clazz));
		}
		return ret;
	}
	
	public static Object arrayFromList(List<?> list, Class<?> javaType) {
		Object array = Array.newInstance(javaType, list.size());
		for (int i = 0; i < list.size(); i++) {
			Array.set(array, i, list.get(i));
		}
		return array;
	}

}
