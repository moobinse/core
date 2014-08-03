package com.moobin.test.example;

import com.moobin.annotation.Id;

public class TestItem {

	@Id
	public String id;

	public int value;
	
	@Override
	public String toString() {
		return id;
	}
	
}
