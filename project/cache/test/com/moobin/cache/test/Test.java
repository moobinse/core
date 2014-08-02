package com.moobin.cache.test;

import java.util.stream.IntStream;

public class Test {
	
	public static void main(String[] args) {
		IntStream.iterate(1, (i) -> i * 2).filter((i) -> i < 1000).forEach(System.out::println);
	}
	
	static long test1(int c) {
		long t = System.currentTimeMillis();
		for (Integer i = 0; i < c; i++) {
			test(i);
		}
		return System.currentTimeMillis() - t;
	}
	
	static long test2(int c) {
		IntStream is = IntStream.range(0, c);
		long t = System.currentTimeMillis();
		is.forEach(Test::test);
		return System.currentTimeMillis() - t;
	}
	
	static long j = 5;
	static void test(int i) {
	
	}

}
