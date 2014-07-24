package com.moobin.meta.test;

import java.util.function.Function;

import com.moobin.meta.ObjectMetaData;

public class ObjectMetaDataUser implements ObjectMetaData<User> {
	
	@Override
	public Function<User, String> getKey() {
		return (user) -> user.name;
	}
	
	@Override
	public Function<User, String> getText() {
		return (user) -> user.name;
	}
	

}
