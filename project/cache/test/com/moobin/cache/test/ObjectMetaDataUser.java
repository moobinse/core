package com.moobin.cache.test;

import java.util.function.Function;

import com.moobin.meta.MetaDataObject;

public interface ObjectMetaDataUser {
	
	public MetaDataObject<User> UserMetaData = new MetaDataObject<User>() {
		
		@Override
		public Function<User, String> displayFunction() {
			return (user) -> user.name;
		}
		
		@Override
		public Function<User, String> keyFunction() {
			return (user) -> user.name;
		}
	};

}
