package com.moobin.cache;

import java.util.stream.Stream;

public interface MoobinCacheManager {

	void add(Object item);

	void addStream(Stream<? extends Object> stream);
	
}
