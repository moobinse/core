package com.moobin.configuration;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import com.moobin.core.MoobinException;
import com.moobin.feed.MoobinFeeder;

public interface MoobinConfiguration {

	String getName();

	Stream<MoobinConfiguration> getInherits();

	Set<Class<?>> getTypes();

	List<AddField<?,?>> getAddedFields();

	Stream<Class<?>> getCacheRoots();

	Set<Class<? extends MoobinFeeder>> getFeeders();
	
	default List<AddField<?,?>> getAddedFields(Class<?> t) {
		List<AddField<?, ?>> list = new ArrayList<>();
		getAddedFields().stream().filter((a) -> a.getType() == t).forEach(list::add);
		return list;
	}

	default Set<Class<?>> getAllTypes() {
		HashSet<Class<?>> set = new HashSet<>();
		getTypes().forEach(set::add);
		getInherits().map((m) -> m.getAllTypes()).forEach((s) -> s.forEach(set::add));
		return set;
	}

	default Stream<Class<?>> getAllCacheRoots() {
		HashSet<Class<?>> set = new HashSet<>();
		getCacheRoots().forEach(set::add);
		getInherits().map((m) -> m.getAllCacheRoots()).forEach((s) -> s.forEach(set::add));
		return set.stream();
	}
	
	default Set<Class<? extends MoobinFeeder>> getAllFeeders() {
		HashSet<Class<? extends MoobinFeeder>> set = new HashSet<>();
		set.addAll(getFeeders());
		getInherits().map((m) -> m.getAllFeeders()).forEach(set::addAll); 
		return set;
	}

	default void start() {
		
	}
	
	static Map<String, MoobinConfiguration> loadedConfigurations = new HashMap<>();
	static MoobinConfiguration getConfig(
			Class<? extends MoobinConfiguration> clazz) {
		MoobinConfiguration config = loadedConfigurations.get(clazz.getName());
		if (config == null) {
			try {
				Constructor<? extends MoobinConfiguration> c = clazz.getDeclaredConstructor();
				c.setAccessible(true);
				config = c.newInstance();
			} catch (InstantiationException | IllegalAccessException | SecurityException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
				throw new MoobinException("Could not load config: " + clazz.getName());
			}
			loadedConfigurations.put(clazz.getName(), config);
		}
		return config;
	}

}
