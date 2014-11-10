package com.moobin.tools;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.moobin.core.Core;
import com.moobin.core.data.MetaAction;
import com.moobin.meta.MetaDataField;
import com.moobin.meta.MetaDataObject;

public abstract class GenerateViewsTool {

	protected static void generateDefaultViews(String p) {
			
		Path path = Paths.get(p, "resources", "views.xml");
	    Charset charset = Charset.forName("UTF-8");
		try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
			writer.write("<?xml version=\"1.0\"?>\n");
			writer.write("  <root>\n");
		    writer.write("    <views>\n\n");
		    List<MetaDataObject<?>> list = new ArrayList<>(Core.get().getMetaDataManager().getMetaData());
		    Collections.sort(list, (a,b) -> a.getName().compareTo(b.getName()));
			for (MetaDataObject<?> meta : list) {
				System.out.println(meta.getType().getSimpleName());
				generateGetViews(meta, writer);
				generateAddViews(meta, writer);
			}
			writer.write("    </views>\n");
			writer.write("  </root>\n");
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void generateGetViews(MetaDataObject<?> meta, BufferedWriter writer) throws IOException {
		if (!meta.hasAction(MetaAction.GET)) {
			return;
		}
		writer.write("      <view model=\"" + meta.getName() + "\" action=\"get\">\n");
		generateKeyFields(writer, meta);
		generateSimpleFields(writer, meta);
		for (MetaDataField<?, ?> field : meta.getObjectFields()) {
			writer.write("        <div name=\"" + field.getName() + "\">\n");
			writer.write("        </div>\n");
		}
		for (MetaDataField<?, ?> field : meta.getObjectArrayFields()) {
			writer.write("        <list name=\"" + field.getName() + "\" type=\"" + field.getJavaType().getSimpleName() + "\">\n");
			MetaDataObject<?> listType = Core.get().getMetaDataManager().getMetaData(field.getJavaType());
			if (listType != null) {
				for (MetaDataField<?, ?> listfield : listType.getSimpleFields()) {
					writer.write("          <property name=\"" + listfield.getName() + "\"/>\n");
				}
			}
			writer.write("        </list>\n");
		}
		writer.write("      </view>\n\n");
	}
	
	
	private static void generateAddViews(MetaDataObject<?> meta, BufferedWriter writer) throws IOException {
		if (!meta.hasAction(MetaAction.ADD)) {
			return;
		}
		writer.write("      <view model=\"" + meta.getName() + "\" action=\"add\">\n");
		writer.write("        <context type=\"" + meta.getName() + "\">$model = $context</context>\n");
		for (MetaDataField<?, ?> field : meta.getSimpleFields()) {
			if (field.getReferenceType() != null) {
				writer.write("        <context type=\"" + field.getReferenceType().getSimpleName() + "\">$model.organisation = $context.memberId</context>\n");
			}
		}
		generateKeyFields(writer, meta);
		generateSimpleFields(writer, meta);
		for (MetaDataField<?, ?> field : meta.getObjectFields()) {
			writer.write("        <div name=\"" + field.getName() + "\">\n");
			writer.write("        </div>\n");
		}
		for (MetaDataField<?, ?> field : meta.getObjectArrayFields()) {
			writer.write("        <list name=\"" + field.getName() + "\" type=\"" + field.getJavaType().getSimpleName() + "\">\n");
			MetaDataObject<?> listType = Core.get().getMetaDataManager().getMetaData(field.getJavaType());
			if (listType != null) {
				for (MetaDataField<?, ?> listfield : listType.getSimpleFields()) {
					writer.write("          <property name=\"" + listfield.getName() + "\"/>\n");
				}
			}
			writer.write("        </list>\n");
		}
		writer.write("      </view>\n\n");
	}

	private static void generateSimpleFields(BufferedWriter writer,
			MetaDataObject<?> meta) throws IOException {
		boolean first = true;
		for (MetaDataField<?, ?> field : meta.getSimpleFields()) {
			if (field != meta.getDisplayField() && field != meta.getKeyField()) {
				if (first) {
					writer.write("        <div id=\"simple-fields\">\n");
				}
				writer.write("          <property name=\"" + field.getName() + "\"/>\n");
				first = false;
			}
		}
		if (!first) {
			writer.write("        </div>\n");
		}
	}

	private static void generateKeyFields(BufferedWriter writer, MetaDataObject<?> meta) throws IOException {
		if (meta.getKeyField() != null || meta.getDisplayField() != null) {
			writer.write("        <div id=\"key-fields\">\n");
			if (meta.getKeyField() != null) {
				writer.write("          <property name=\"" + meta.getKeyField().getName() + "\"/>\n");
			}
			if (meta.getDisplayField() != null && meta.getDisplayField() != meta.getKeyField()) {
				writer.write("          <property name=\"" + meta.getDisplayField().getName() + "\"/>\n");
			}
			writer.write("        </div>\n");
		}
	}
	
}
