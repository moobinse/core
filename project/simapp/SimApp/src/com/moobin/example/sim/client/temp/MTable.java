package com.moobin.example.sim.client.temp;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.moobin.client.CacheCallback;
import com.moobin.client.JsBase;
import com.moobin.client.JsBase0;
import com.moobin.client.Moobin;
import com.moobin.common.MCallback;
import com.moobin.common.MetaFieldIf;
import com.moobin.common.MetaObjectIf;

public class MTable<T extends JsBase> extends Composite {

	MetaObjectIf meta;
	List<MetaFieldIf> fields = new ArrayList<>();
	Grid table = new Grid();
	FlowPanel panel = new FlowPanel();

	MCallback<MetaObjectIf> onMeta = new MCallback<MetaObjectIf>() {
		@Override
		public void callback(MetaObjectIf value) {
			setMeta(value);
		}
	};

	CacheCallback<JsArray<T>> onArrayData = new CacheCallback<JsArray<T>>() {
		@Override
		public void callback(JsArray<T> value) {
			table.resize(1 + value.length(), fields.size());
			for (int i = 0; i < value.length(); i++) {
				add(i + 1, value.get(i));
			}
		}
	};
	
	public MTable(final String type) {
		Moobin.getMetaManager().getMetaData(type, onMeta);
		panel.add(table);
		initWidget(panel);
		panel.setStyleName("MTable");
		panel.addDomHandler(new ScrollHandler() {
			@Override
			public void onScroll(ScrollEvent event) {
				table.getRowFormatter().getElement(0).getStyle().setPosition(Position.RELATIVE);
				int top = panel.getElement().getScrollTop();
				for (int i = 0; i < table.getCellCount(0); i++) {
					Element td = table.getCellFormatter().getElement(0, i);
					td.getStyle().setPosition(Position.RELATIVE);
					td.getStyle().setTop(top, Unit.PX);
				}
			}
		}, ScrollEvent.getType());
	}
	
	private void setMeta(MetaObjectIf meta) {
		this.meta = meta;
		for (MetaFieldIf field : meta.getFields()) {
			if ("SIMPLE".equals(field.getBusinessType())) {
				fields.add(field);
			}
		}
		table.resize(1, fields.size());
		for (int i = 0; i < fields.size(); i++) {
			String name = fields.get(i).name();
			table.setText(0, i, name);
		}
		Moobin.getCache().getList(meta.name(), onArrayData);
	}
	
	private void add(int r, T t) {
		for (int i = 0; i < fields.size(); i++) {
			table.setWidget(r, i, createLabel(fields.get(i), (JsBase0) t));
		}
	}

	private Widget createLabel(MetaFieldIf field, JsBase0 object) {
		SimplePanel s = new SimplePanel();
		if (field.getJavaType().equals("Integer") || field.getJavaType().equals("int")) {
			Integer value = object.intValue(field.name());
			s.getElement().setInnerText(value == null ? "" : value.toString());
			s.getElement().getStyle().setTextAlign(TextAlign.RIGHT);
		}
		else {
			s.getElement().setInnerText(object.stringValue(field.name()));
		}
		return s;
	}
	
	public final native String get(JsBase o, String p) /*-{ return o[p] + ""; }-*/;
	
	
}
