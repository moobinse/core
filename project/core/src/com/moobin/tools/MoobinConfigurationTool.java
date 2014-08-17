package com.moobin.tools;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.moobin.configuration.AddField;
import com.moobin.configuration.MoobinConfiguration;

public class MoobinConfigurationTool {

	
	public static void toXml(MoobinConfiguration config, PrintStream out) {
		out.println("<moobin>");
		config.getInherits().forEach((c) -> {
			out.print("  <inherits name=\"");
			out.print(c.getName());
			out.println("\"/>");
		});
		out.println("  <meta>");
		Set<Package> packages = new HashSet<>();
		config.getTypes().stream().map((c) -> c.getPackage()).forEach(packages::add);
		packages.forEach((p) -> {
			out.print("    <package name=\"");
			out.print(p.getName());
			out.println("\"/>");
		});
		config.getTypes().forEach((t) -> {
			out.print("    <entity name=\"");
			out.print(t.getSimpleName());
			List<AddField<?, ?>> addFields = config.getAddedFields(t);
			if (addFields.isEmpty()) {
				out.println("\"/>");
			}
			else {
				out.println("\">");
				addFields.stream().forEach((f) -> {
					out.print("      <add-field type=\"");
					out.print(f.getType().getSimpleName());
					out.print("\" name=\"");
					out.print(f.getName());
					out.print("\" function=\"");
					out.print(f.getFunction());
					out.println("\"/>");
				});
				out.println("    </entity>");
			}
		});
		out.println("  </meta>");
		out.println("  <cache>");
		config.getCacheRoots().forEach((t) -> {
			out.print("    <root name=\"");
			out.print(t.getSimpleName());
			out.println("\"/>");
		});
		out.println("  </meta>");
		config.getFeeders().forEach((c) -> {
			out.print("  <plugin class=\"");
			out.print(c.getName());
			out.println("\"/>");
		});
		out.println("</moobin>");
	}
	
	
}
