package com.dictiography.collections;

import java.util.NavigableSet;

public interface IndexedNavigableSet<E> extends NavigableSet<E> {

	E exact(int index);
    
	int entryIndex(E e);
	
}