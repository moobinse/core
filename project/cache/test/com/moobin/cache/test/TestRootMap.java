/**
 * 
 */
package com.moobin.cache.test;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.BeforeClass;
import org.junit.Test;

import com.moobin.cache.CacheManager;
import com.moobin.cache.CacheMap;
import com.moobin.cache.CacheMapSorting;
import com.moobin.cache.CacheRootMap;
import com.moobin.cache.impl.CacheManagerImpl;
import com.moobin.core.Core;
import com.moobin.meta.MetaDataManagerImpl;

/**
 * @author Magnus Lenti
 *
 */
public class TestRootMap {

	private static CacheManager cache;
	private static MetaDataManagerImpl meta;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		if (cache == null) {
			
			meta = Core.get().set(new MetaDataManagerImpl());
			cache = Core.get().set(new CacheManagerImpl());

			meta.add(TestItem.class);
			cache.createRootMap(TestItem.class);
		}
		
	}

	private static IntStream ints(int count) {
		return new Random(12345).ints(count, 0, Integer.MAX_VALUE);
	}
	
	@Test
	public void testRootMap() {
		CacheRootMap<TestItem> rootmap = cache.getRootMap(TestItem.class);
		ints(1000).mapToObj(TestItem::new).forEach(rootmap::add);
		assertEquals(1000, rootmap.size());
		ints(100).mapToObj(TestItem::new).forEach(rootmap::remove);
		assertEquals(900, rootmap.size());
		ints(100).mapToObj(TestItem::new).forEach(rootmap::add);
		assertEquals(1000, rootmap.size());
		
		// test filtered map
		CacheMap<TestItem> filteredMap = rootmap.filter((i) -> i.value % 10 == 8);
		//assertEquals(57, filteredMap.size());
		
		// test sorting
		CacheMapSorting<TestItem> sort = filteredMap.sort((i) -> i.value);
		//.get(0, filteredMap.size() - 1).stream().forEach(System.out::println);
		IntStream.range(0, 10).mapToObj(sort::getByIndex).forEach(System.out::println);
	}
	
	@Test
	public void testFilteredMap() {
		
	}

}
