package com.moobin.configuration.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import com.moobin.configuration.AddField;
import com.moobin.configuration.CoreConfig;
import com.moobin.configuration.MoobinConfiguration;
import com.moobin.core.MoobinException;
import com.moobin.feed.MoobinFeeder;

public class AbstractMoobinConfiguration implements MoobinConfiguration {

	private final List<MoobinConfiguration> inherits = new ArrayList<>();
	private final Map<Class<?>, String> metaEntities = new HashMap<>();
	private final List<AddField<?,?>> addedFields = new ArrayList<>();
	private final Set<Class<?>> cacheRootSet = new HashSet<>();
	private final Set<Class<? extends MoobinFeeder>> feeders = new HashSet<>();
	private static final Map<Class<? extends MoobinConfiguration>, MoobinConfiguration> map 
		= new HashMap<>();
	
	public AbstractMoobinConfiguration() {
		if (map.put(getClass(), this) != null) {
			throw new MoobinException("Configuration created twice: " + getClass());
		}
	}
	
	@Override
	public String getName() {
		return getClass().getName();
	}

	@Override
	public Stream<MoobinConfiguration> getInherits() {
		return inherits.stream();
	}
	
	@Override
	public Set<Class<?>> getTypes() {
		return metaEntities.keySet();
	}
	
	@Override
	public Stream<Class<?>> getCacheRoots() {
		return cacheRootSet.stream();
	}
	
	@Override
	public List<AddField<?, ?>> getAddedFields() {
		return addedFields;
	}
	
	@Override
	public Set<Class<? extends MoobinFeeder>> getFeeders() {
		return feeders;
	}
	
	protected void inherits(Class<? extends MoobinConfiguration> clazz) {
		inherits.add(MoobinConfiguration.getConfig(clazz));
	}

	protected void addMetaEntity(Class<?>... types) {
		addMetaEntity(null, types);
	}

	protected void addMetaEntity(String domain, Class<?>... types) {
		Arrays.asList(types).forEach((t) -> metaEntities.put(t, domain));
	}
	
	protected <T,R> void addMetaDataField(Class<T> type, String name, Class<R> valueType, Function<T,R> method) {
		addedFields.add(new AddField<T,R>(type, name, valueType, method));
	}

	protected <T> void removeMetaDataField(Class<T> type, String name) {
		
	}
	
	protected void feeder(Class<? extends MoobinFeeder> clazz) {
		feeders.add(clazz);
	}
	
	protected void addCacheRoot(Class<?>...types) {
		Arrays.asList(types).forEach(cacheRootSet::add);
	}
	
	
	
}
