package com.moobin.cache.test;

import java.util.function.Function;

import com.moobin.meta.MetaDataObject;

public class User {
	
	static MetaDataObject<User> metaData = new MetaDataObject<User>() {
		
		@Override
		public Function<User, String> keyFunction() {
			return (user) -> user.name;
		}
		
		@Override
		public Function<User, String> displayFunction() {
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
