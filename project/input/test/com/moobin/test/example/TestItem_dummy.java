package com.moobin.test.example;

import java.util.function.Function;

public class TestItem_dummy implements Function<TestItem, String> {
	
	@Override
	public String apply(TestItem t) {
		return "dummy";
	}

}
