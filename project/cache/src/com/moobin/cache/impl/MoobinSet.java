package com.moobin.cache.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

import com.moobin.core.MoobinException;

public class MoobinSet<T> {
	
	private final Set<T> set = new HashSet<>();
	
	public void add(T item) {
		if (!set.add(item)) {
			throw new MoobinException("");
		}
	}
	
	public void remove(T item) {
		set.remove(item);
	}
	
	public Stream<T> stream() {
		return set.stream();
	}

	public void forEach(Consumer<T> consumer) {
		stream().forEach(consumer);
	}

	public boolean isEmpty() {
		return set.isEmpty();
	}

}
