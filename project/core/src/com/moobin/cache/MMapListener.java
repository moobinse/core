package com.moobin.cache;
@FunctionalInterface public interface MMapListener<T> {
	void onEvent(String key, T oldValue, T newValue);
}

