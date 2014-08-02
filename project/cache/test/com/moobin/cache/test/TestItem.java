package com.moobin.cache.test;

import com.moobin.annotation.Id;
import com.moobin.annotation.Required;
import com.moobin.annotation.Unique;

public class TestItem {
	
	public TestItem() {
	}

	public TestItem(int next) {
		id = next + "";
		name = "n" + next;
		value = next;
	}

	@Id
	public String id;
	
	@Required
	public String name;
	
	@Unique
	public int value;

	@Override
	public String toString() {
		return id;
	}
}
