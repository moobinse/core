package com.moobin.configuration.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Stream;

import com.moobin.configuration.MoobinConfigurationSource;

public class AbstractMoobinConfigurationSource implements MoobinConfigurationSource {

	private final HashSet<Class<?>> metaEntities = new HashSet<>();
	private final HashSet<Class<?>> cacheRootSet = new HashSet<>();
	
	@Override
	public Stream<Class<?>> getTypes() {
		return metaEntities.stream();
	}
	
	@Override
	public Stream<Class<?>> getCacheRoots() {
		return cacheRootSet.stream();
	}
	
	protected void addMetaEntity(Class<?>... types) {
		Arrays.asList(types).forEach(metaEntities::add);
	}

	protected <T> void addMetaDataField(Class<T> type, String name, Function<T,?> method) {
		
	}

	protected <T> void removeMetaDataField(Class<T> type, String name) {
		
	}
	
	protected void addCacheRoot(Class<?>...types) {
		Arrays.asList(types).forEach(cacheRootSet::add);
	}
	
	
	
}
