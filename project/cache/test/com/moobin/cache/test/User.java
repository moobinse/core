package com.moobin.cache.test;

import java.util.function.Function;

import com.moobin.meta.ObjectMetaData;

public class User {
	
	static ObjectMetaData<User> metaData = new ObjectMetaData<User>() {
		
		@Override
		public Function<User, String> getKey() {
			return (user) -> user.name;
		}
		
		@Override
		public Function<User, String> getText() {
			return (user) -> user.name;
		}
	};

	public String userId;
	
	public String name;
	
	public User(String string, String string2) {
		userId = string;
		name = string2;
	}

}
