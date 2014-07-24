package com.moobin.meta;

public interface MetaDataManager {

	public <T> ObjectMetaData<T> getMetaData(Class<T> pType);
	
}
