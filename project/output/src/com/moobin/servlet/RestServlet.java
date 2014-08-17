package com.moobin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moobin.core.Core;
import com.moobin.meta.MetaDataObject;
import com.moobin.output.js.MoobinToJson;

@SuppressWarnings("serial")
public class RestServlet extends javax.servlet.http.HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    response.setContentType("text/plain");
	    PrintWriter out = response.getWriter();		
	    String rest = request.getRequestURI().split("rest/")[1];
	    String[] parts = rest.split("/");
	    String type = parts[0];
	    String key = parts[1];
	    MetaDataObject<Object> meta = Core.get().getMetaDataManager().getMetaData(type);
	    Object o = Core.get().getCacheManager().getRootMap(meta.getType()).get(key); 
	    if (parts.length > 2) {
	    	String field = parts[2];
	    	Object value = meta.getField(field).get(o);
	    	out.println(MoobinToJson.format(value, 1));
	    }
	    else {
	    	out.println(MoobinToJson.format(o, 1));
	    }
	}
	
}
