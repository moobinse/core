package com.dictiography.collections;

import java.util.Map;
import java.util.NavigableMap;

public interface IndexedNavigableMap<K, V> extends NavigableMap<K, V> {

	K exactKey(int index);
    
	Map.Entry<K, V> exactEntry(int index);
    
	int keyIndex(K k);
	
}