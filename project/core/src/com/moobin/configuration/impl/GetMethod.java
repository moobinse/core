package com.moobin.configuration.impl;

import java.lang.reflect.Field;
import java.util.function.Function;

public class GetMethod<T,R> implements Function<T,R> {

	private final Function<T, R> function;

	public GetMethod(Function<T, R> f) {
		this.function = f;
	}
	
	@Override
	public R apply(T t) {
		return function.apply(t);
	}
	
	@Override
	public String toString() {
		Class<?> clazz = getClass().getEnclosingClass();
		if (clazz != null) {
			for (Field f : clazz.getFields()) {
				try {
					if (f.get(null) == this) {
						return clazz.getName() + "." + f.getName();
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// try next
				}
			};
		}
		return getClass().getSimpleName();
	}


}
