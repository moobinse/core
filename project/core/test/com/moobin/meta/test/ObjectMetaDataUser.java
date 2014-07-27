package com.moobin.meta.test;


public class ObjectMetaDataUser extends MetaDataObjectImpl<TestUser> {

	public ObjectMetaDataUser() {
		super(TestUser.class, (u) -> u.userId, (u) -> u.name);
	}

}
