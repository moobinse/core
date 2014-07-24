package com.moobin.cache.test;

import java.util.function.Function;

import com.moobin.cache.impl.CacheMap;

public class MapTest {


	CacheMap<User> map = new CacheMap((u) -> true, User.metaData.getText());
	
	void test() {
		map.add(new User("u2", "aued"));
		map.add(new User("u88", "euay"));
		map.add(new User("u4", "euaou3"));
		map.add(new User("u3", "uae6"));
		map.add(new User("u1", "du.p,"));
		dump();
	}
	public static void main(String[] args) {
		new MapTest().test();
		Function<User, String> f1 = (user) -> user.name;
		Function<User, String> f2 = (user) -> user.name;
		System.out.println(f1);
		System.out.println(f2);
		System.out.println(f1.equals(f2));
	}
	
	void dump() {
		for (int i = 0; i < map.size(); i++) {
			System.out.print(map.get(i).name + " ");
		}
		System.out.println();
	}
}
