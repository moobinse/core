package com.moobin.common;


public interface MetaManagerIf {

	void getMetaData(String type, MCallback<MetaObjectIf> callback);

}
