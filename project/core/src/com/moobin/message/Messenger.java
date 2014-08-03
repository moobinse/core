package com.moobin.message;

public interface Messenger {

	default <T> Response<T> get(Class<T> type, String key) {
		return request(RequestType.get, type, null, key);
	}

	default <T> Response<T> get(Class<T> type) {
		return request(RequestType.get, type, null, null);
	}
	
	default <T> Response<T> update(T item) {
		return request(RequestType.udpate, null, item, null);
	}
	
	default <T> Response<T> remove(Class<T> type, String key) {
		return request(RequestType.remove, type, null, key);
	}
	
	default Response<?> execute(Object request) {
		return request(RequestType.execute, null, request, null);
	}

	<T> Response<T> request(RequestType requestType, Class<T> type, T item, String key);
	
}
