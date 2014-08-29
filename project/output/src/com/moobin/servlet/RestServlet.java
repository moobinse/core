package com.moobin.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gwt.thirdparty.guava.common.base.Charsets;
import com.moobin.core.Core;
import com.moobin.meta.MetaDataObject;
import com.moobin.output.js.MoobinToJson;

@SuppressWarnings("serial")
public class RestServlet extends javax.servlet.http.HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("utf-8");
	    String rest = request.getRequestURI().split("rest/")[1];
	    String[] parts = rest.split("/");
	    String type = parts[0];
	    String key = URLDecoder.decode(parts[1], "utf-8");
	    MetaDataObject<Object> meta = Core.get().getMetaDataManager().getMetaData(type);
	    Object o = Core.get().getCacheManager().getRootMap(meta.getType()).get(key); 
	    if (parts.length > 2) {
	    	String field = parts[2];
	    	Object value = meta.getField(field).get(o);
		    response.getOutputStream().write(MoobinToJson.format(value).getBytes(Charsets.UTF_8));
	    }
	    else {
		    response.getOutputStream().write(MoobinToJson.format(o).getBytes(Charsets.UTF_8));
	    }
	}
	
}
