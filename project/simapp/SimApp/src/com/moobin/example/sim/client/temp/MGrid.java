package com.moobin.example.sim.client.temp;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.moobin.client.CacheCallback;
import com.moobin.client.JsBase;
import com.moobin.client.Moobin;
import com.moobin.common.MCallback;
import com.moobin.common.MetaObjectIf;

public class MGrid extends Composite {

	private ListBox listBox = new ListBox();
	private MetaObjectIf meta;
	private String type;
	
	public MGrid(String type) {
		this.type = type;
		initWidget(listBox);
		Moobin.getMetaManager().getMetaData(type, new MCallback<MetaObjectIf>() {
			public void callback(MetaObjectIf value) {
				setMetaObject(value);
			}
		});
	}

	private void setMetaObject(MetaObjectIf value) {
		meta = value;
		Moobin.getCache().getList(type, new CacheCallback<JsArray<? extends JsBase>>() {
			public void callback(JsArray<? extends JsBase> array) {
				for (int i = 0; i < array.length(); i++) {
					listBox.addItem(array.get(i).get(meta.displayField()));
				} 
			}
		});
	}
	
	
}
