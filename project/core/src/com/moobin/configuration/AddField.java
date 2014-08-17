package com.moobin.configuration;

import java.util.function.Function;

public class AddField<T,R> {

	private Class<T> type;
	private String name;
	private Class<R> valueType;
	private Function<T, R> function;

	public AddField(Class<T> type, String name, Class<R> valueType,
			Function<T, R> method) {
		
		this.type = type;
		this.name = name;
		this.valueType = valueType;
		this.function = method;
	}

	public Class<T> getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public Class<R> getValueType() {
		return valueType;
	}
	
	public Function<T, R> getFunction() {
		return function;
	}
	
}
