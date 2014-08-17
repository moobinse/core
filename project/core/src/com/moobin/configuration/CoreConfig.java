package com.moobin.configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.moobin.core.data.CacheRequest;
import com.moobin.core.data.CacheResponse;
import com.moobin.core.data.MetaField;
import com.moobin.core.data.MetaObject;
import com.moobin.core.data.PollRequest;
import com.moobin.core.data.PollResponse;
import com.moobin.feed.MoobinFeeder;

public class CoreConfig implements MoobinConfiguration {

	private final Set<Class<?>> types = new HashSet<>();
	private final List<Class<?>> cacheRoots = new ArrayList<>();
	
	public CoreConfig() {
		types.add(MetaObject.class);
		types.add(MetaField.class);
		types.add(CacheRequest.class);
		types.add(PollRequest.class);
		types.add(PollResponse.class);
		types.add(CacheResponse.class);
		cacheRoots.add(MetaObject.class);
	}
	
	@Override
	public String getName() {
		return getClass().getName();
	}
	
	@Override
	public Set<Class<?>> getTypes() {
		return types;
	}
	
	@Override
	public Stream<Class<?>> getCacheRoots() {
		return cacheRoots.stream();
	}
	
	@Override
	public Stream<MoobinConfiguration> getInherits() {
		return Stream.empty();
	}

	@Override
	public List<AddField<?,?>> getAddedFields() {
		return Collections.emptyList();
	}
	
	@Override
	public Set<Class<? extends MoobinFeeder>> getFeeders() {
		return Collections.emptySet();
	}
	
}
