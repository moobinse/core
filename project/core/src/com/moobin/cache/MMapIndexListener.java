package com.moobin.cache;
@FunctionalInterface public interface MMapIndexListener {
		void onEvent(int removeIndex, int addIndex);
	}
