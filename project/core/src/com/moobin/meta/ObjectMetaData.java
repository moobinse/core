package com.moobin.meta;

import java.util.function.Function;

public interface ObjectMetaData<T> {

	Function<T, String> getKey();

	Function<T, String> getText();

}
