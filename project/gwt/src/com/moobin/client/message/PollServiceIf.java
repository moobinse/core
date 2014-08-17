package com.moobin.client.message;

import com.moobin.client.CacheCallback;
import com.moobin.client.cache.impl.CacheRequest;

public interface PollServiceIf {

	static class Singleton {
		
		static PollServiceIf me;
		
		public static PollServiceIf get() {
			return me;
		}
		
		public static void set(PollServiceIf s) {
			me = s;
		}
	}

	void add(CacheRequest request, CacheCallback<?> callback);

}
