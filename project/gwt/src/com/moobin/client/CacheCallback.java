package com.moobin.client;

public interface CacheCallback<T> {
	void callback(T value);
}