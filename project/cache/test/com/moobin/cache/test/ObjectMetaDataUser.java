package com.moobin.cache.test;

import java.util.function.Function;

import com.moobin.meta.ObjectMetaData;

public interface ObjectMetaDataUser {
	
	public ObjectMetaData<User> UserMetaData = new ObjectMetaData<User>() {
		
		@Override
		public Function<User, String> getText() {
			return (user) -> user.name;
		}
		
		@Override
		public Function<User, String> getKey() {
			return (user) -> user.name;
		}
	};

}
