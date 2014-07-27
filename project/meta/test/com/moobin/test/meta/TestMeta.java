package com.moobin.test.meta;

import com.moobin.core.Core;
import com.moobin.meta.MetaDataManager;
import com.moobin.meta.MetaDataManagerImpl;
import com.moobin.meta.MetaDataObject;
import com.moobin.meta.test.TestItem;

public class TestMeta {

	static {
		Core.set(new MetaDataManagerImpl());
	}
	
	public static void main(String[] args) {
		MetaDataManager metaManager = Core.get().getMetaDataManager();
		metaManager.add(TestItem.class);
		MetaDataObject<TestItem> meta = metaManager.getMetaData(TestItem.class);
		System.out.println(meta.getName());
		meta.getFields().forEach(System.out::println);
	}
}
