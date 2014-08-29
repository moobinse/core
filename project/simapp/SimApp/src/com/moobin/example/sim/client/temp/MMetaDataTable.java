package com.moobin.example.sim.client.temp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.moobin.client.CacheCallback;
import com.moobin.client.Moobin;
import com.moobin.generated.client.cache.JsMetaObject;

public class MMetaDataTable extends Composite implements CacheCallback<JsArray<JsMetaObject>> {

	private final FlexTable table = new FlexTable();
	
	public MMetaDataTable() {
		initWidget(table);
		Moobin.getCache().getList("MetaObject", this);
	}

	public void callback(JsArray<JsMetaObject> array) {
		List<JsMetaObject> list = new ArrayList<>();
		for (int i = 0; i < array.length(); i++) {
			list.add(array.get(i));
		}
		Collections.sort(list, new Comparator<JsMetaObject>() {
			@Override
			public int compare(JsMetaObject o1, JsMetaObject o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (int i = 0; i < list.size(); i++) {
			JsMetaObject m = list.get(i);
			table.setWidget(i, 0, label(m));
			table.setText(i, 1, m.getDisplayField());
			table.setText(i, 2, m.getFields().length() + "");
		}
		table.setWidget(0, 3, new SimplePanel());
	}

	private Widget label(final JsMetaObject m) {
		Label label = new Label(m.getName());
		label.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				table.setWidget(0, 3, new MMetaDataView(m));
			}
		});
		return label;
	}
	static Button button;
	static MMetaDataTable view;
	
	public static Button getButton() {
		if (button == null) {
			button = new Button("MetaData");
			button.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					if (view == null) {
						view = new MMetaDataTable();
						RootPanel.get().add(view);
					}
					else {
						view.removeFromParent();
						view = null;
					}
				}
			});
		}
		return button;
	}
	
	
	
}
