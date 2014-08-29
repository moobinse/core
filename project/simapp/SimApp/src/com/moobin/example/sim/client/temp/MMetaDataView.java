package com.moobin.example.sim.client.temp;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.moobin.generated.client.cache.JsMetaField;
import com.moobin.generated.client.cache.JsMetaObject;

public class MMetaDataView extends Composite {

	Grid grid = new Grid(3, 2);
	FlexTable table = new FlexTable();
	
	public MMetaDataView(JsMetaObject m) {
		FlowPanel panel = new FlowPanel();
		panel.add(new Label(m.getName()));
		panel.add(grid);
		grid.setText(0, 0, "Key Field");
		grid.setText(0, 1, m.getKeyField());
		grid.setText(1, 0, "Display Field");
		grid.setText(1, 1, m.getDisplayField());
		grid.setText(2, 0, "Cache Root");
		grid.setText(2, 1, m.getCacheRoot() + "");
		panel.add(table);
		initWidget(panel);
		
		for (int i = 0; i < m.getFields().length(); i++) {
			JsMetaField f = m.getFields().get(i);
			table.setText(i, 0, f.getName());
			table.setText(i, 1, f.getBusinessType());
			table.setText(i, 2, f.getJavaType());
		}
		getStyleElement().getStyle().setPosition(Position.ABSOLUTE);
		setStyleName("MetaObjectView");
	}

}
