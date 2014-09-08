package com.moobin.core.data;

import com.moobin.annotation.Action;
import com.moobin.annotation.Id;
import com.moobin.annotation.bt.BtDisplay;
import com.moobin.core.Core;
import com.moobin.meta.MetaDataObject;

@Action(MetaAction.GET)
public class MetaObject {

	@Id
	@BtDisplay
	public String name;

	public String keyField;
	
	public String displayField;
	
	public boolean cacheRoot;
	
	public MetaField[] fields;
	
	public MetaAction[] actions;
	
	public MetaObject() {
		
	}
	
	public MetaObject(MetaDataObject<?> source) {
		name = source.getName();
		displayField = source.getDisplayField() != null ? source.getDisplayField().getName() : null;
		keyField = source.getKeyField() != null ? source.getKeyField().getName() : null;
		cacheRoot = source.isCacheRoot();
		fields = new MetaField[source.getFields().size()];
		for (int i = 0; i < fields.length; i++) {
			fields[i] = MetaField.create(source.getFields().get(i));
		}
		actions = new MetaAction[source.getActions().size()];
		for (int i = 0; i < actions.length; i++) {
			actions[i] = new MetaAction(name, source.getActions().get(i));
		}
		Core.get().getCacheManager().add(this);
	}
}
