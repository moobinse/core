package com.moobin.tools;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.moobin.core.Core;
import com.moobin.meta.MetaDataField;
import com.moobin.meta.MetaDataField.Type;
import com.moobin.meta.MetaDataObject;

public abstract class GenerateGwtTool {

	public static void generateCacheNavigator(String p) {
			
		Path path = Paths.get(p, "com/moobin/generated/client/cache", "JsCache.java");

	    Charset charset = Charset.forName("UTF-8");
		try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
			writer.write("package com.moobin.generated.client.cache;\n\n");
			writer.write("import java.util.Collection;\n");
			writer.write("import java.util.List;\n");
			writer.write("import com.moobin.client.CacheCallback;\n");
			writer.write("import com.moobin.client.CacheSubscription;\n");
			writer.write("import com.moobin.client.Moobin;\n\n");
		    writer.write("public class JsCache {\n\n");
			for (MetaDataObject<?> meta : Core.get().getMetaDataManager().getMetaData()) {
				writer.write("    public static void get" + meta.getName() + "(String key, CacheCallback<Js" + meta.getName() + "> callback) {\n");
				writer.write("        Moobin.getCache().get(\"" + meta.getName() + "\", key, callback);\n");
				writer.write("    }\n");
				writer.write("    public static void get" + meta.getName() + "List(Collection<String> keys, CacheCallback<List<Js" + meta.getName() + ">> callback) {\n");
				writer.write("        Moobin.getCache().getList(\"" + meta.getName() + "\", keys, callback);\n");
				writer.write("    }\n");
				writer.write("    public static void get" + meta.getName() + "List(CacheCallback<List<Js" + meta.getName() + ">> callback) {\n");
				writer.write("        Moobin.getCache().getList(\"" + meta.getName() + "\", callback);\n");
				writer.write("    }\n");
				writer.write("    public static void subscribe" + meta.getName() + "(String key, CacheSubscription<Js" + meta.getName() + "> subscription) {\n");
				writer.write("        Moobin.getCache().subscribe(\"" + meta.getName() + "\", subscription);\n");
				writer.write("    }\n");
			}
		    writer.write("}");
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void generateCacheItems(String p) {
		
		for (MetaDataObject<?> meta : Core.get().getMetaDataManager().getMetaData()) {
			Path path = Paths.get(p, "com/moobin/generated/client/cache", "Js" + meta.getName() + ".java");
		    Charset charset = Charset.forName("UTF-8");
			try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
				writer.write("package com.moobin.generated.client.cache;\n\n");
				writer.write("import com.google.gwt.core.client.*;\n\n");
				writer.write("import com.moobin.client.CacheCallback;\n");
				writer.write("import com.moobin.client.JsBase;\n\n");
				writer.write("@SuppressWarnings(\"unused\")\n");
			    writer.write("public class Js" + meta.getName() + " extends JsBase {\n\n");
			    writer.write("  protected Js" + meta.getName() + "() { }\n\n");
			    for (MetaDataField<?, ?> f : meta.getFields()) {
			    	String n = f.getName();
					String name = n.substring(0, 1).toUpperCase() + n.substring(1);
					String type = f.getJavaType().getSimpleName() + (f.isArray() ? "[]" : "");
					if (f.getType() == Type.OBJECT) {
						type = "Js" + type;
					}
					writer.write("\n");
					if (f.isArray()) {
						if (f.getJavaType() == int.class) {
							writer.write("    public final JsArrayInteger get" + name + "() {\n");
							writer.write("        return getArrayInteger(\"" + n + "\");\n");
							writer.write("    }\n");
							writer.write("    public final void " + " set" + name + "(JsArrayInteger " + n + ") {\n");
							writer.write("        set(\"" + n + "\", " + n + ");\n");
							writer.write("    }\n");
							writer.write("    public final void " + " set" + name + "(int... " + n + ") {\n");
							writer.write("        JsArrayInteger arr = createArray().cast();\n");
							writer.write("        for (int i : " + n + ") arr.push(i);\n");
							writer.write("	      set" + name + "(arr);\n");
							writer.write("    }\n");
						}
						else if (f.getJavaType() == boolean.class) {
							writer.write("    public final JsArrayBoolean get" + name + "() {\n");
							writer.write("        return getArrayBoolean(\"" + n + "\");\n");
							writer.write("    }\n");
							writer.write("    public final void " + " set" + name + "(JsArrayBoolean " + n + ") {\n");
							writer.write("        set(\"" + n + "\", " + n + ");\n");
							writer.write("    }\n");
						}
						else if (f.getJavaType() == String.class) {
							writer.write("    public final JsArrayString get" + name + "() {\n");
							writer.write("        return getArrayString(\"" + n + "\");\n");
							writer.write("    }\n");
							writer.write("    public final void " + " set" + name + "(JsArrayString " + n + ") {\n");
							writer.write("        set(\"" + n + "\", " + n + ");\n");
							writer.write("    }\n");
						}
						else {
							writer.write("    public final JsArray<Js" + f.getJavaType().getSimpleName() + "> get" + name + "() {\n");
							writer.write("        return getArray(\"" + n + "\");\n");
							writer.write("    }\n");
							writer.write("    public final void " + " set" + name + "(JsArray<Js" + f.getJavaType().getSimpleName() + "> " + n + ") {\n");
							writer.write("        set(\"" + n + "\", " + n + ");\n");
							writer.write("    }\n");
						}
					}
					else {
						writer.write("    public final " + type + " get" + name + "() {\n");
						if (f.getType() == Type.OBJECT) {
							writer.write("        return getObject(\"" + n + "\");\n");
						}
						else if (f.getJavaType() == int.class) {
							writer.write("        return getInteger(\"" + n + "\");\n");
						}
						else if (f.getJavaType() == boolean.class) {
							writer.write("        return getBoolean(\"" + n + "\");\n");
						}
						else {
							writer.write("        return get(\"" + n + "\");\n");
						}
						writer.write("    }\n\n");
						writer.write("    public final void " + " set" + name + "(" + type + " " + n + ") {\n");
						writer.write("        set(\"" + n + "\", " + n + ");\n");
						writer.write("    }\n\n");
						Class<?> ref = f.getReferenceType();
						if (ref != null) {
							System.out.println();
							writer.write("    public final void get" + name + "(CacheCallback<Js" + ref.getSimpleName() + "> callback) {\n");
							writer.write("        get(\"" + ref.getSimpleName() + "\", get" + name + "(), callback);\n");
							writer.write("    }\n\n");
						}
					}
					// TODO: generate key()...
					// TODO: generate getReferencedMember(callback)
				}
			    writer.write("}\n");
			} catch (IOException x) {
			    System.err.format("IOException: %s%n", x);
			}
		}
	}
}
