package com.moobin.configuration;

import java.util.HashSet;
import java.util.stream.Stream;

public interface MoobinConfigurationSource {

	default Stream<MoobinConfigurationSource> inherits() {
		return Stream.empty();
	}

	default Stream<Class<?>> getTypes() {
		return Stream.empty();
	}

	default Stream<Class<?>> getAllTypes() {
		HashSet<Class<?>> set = new HashSet<Class<?>>();
		getTypes().forEach(set::add);
		inherits().map((m) -> m.getAllTypes()).forEach((s) -> s.forEach(set::add));
		return set.stream();
	}

	default Stream<Class<?>> getCacheRoots() {
		return Stream.empty();
	}

	default Stream<Class<?>> getAllCacheRoots() {
		HashSet<Class<?>> set = new HashSet<Class<?>>();
		getCacheRoots().forEach(set::add);
		inherits().map((m) -> m.getAllCacheRoots()).forEach((s) -> s.forEach(set::add));
		return set.stream();
	}

	default void start() {
		
	}
	
}
