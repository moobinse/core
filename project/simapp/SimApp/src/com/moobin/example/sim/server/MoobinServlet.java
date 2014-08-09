package com.moobin.example.sim.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moobin.cache.impl.CacheManagerImpl;
import com.moobin.configuration.MoobinConfiguration;
import com.moobin.configuration.MoobinConfigurationSource;
import com.moobin.configuration.impl.AbstractMoobinConfigurationSource;
import com.moobin.core.Core;
import com.moobin.meta.MetaDataManagerImpl;
import com.moobin.output.js.MoobinToJson;
import com.moobin.output.test.InOutTest;

@SuppressWarnings("serial")
public class MoobinServlet extends HttpServlet {
    
	static {
		MoobinConfigurationSource config = new AbstractMoobinConfigurationSource() {
			{
				addMetaEntity(InOutTest.class);
			}
		};
		Core.get().set(new CacheManagerImpl());
		Core.get().set(new MetaDataManagerImpl());
		Core.get().set(new MoobinConfiguration() {
			@Override
			public MoobinConfigurationSource source() {
				return config;
			}
		});
		Core.get().start();
	}

	protected void service(HttpServletRequest req, HttpServletResponse resp)
    	    throws ServletException, IOException {
    	
		InOutTest o = new InOutTest();
		o.ccy = "SEK";
		o.name = "Moobin";
		o.ints = new int[] { 1,99,8 };
		o.boo = true;
		o.number = 123;
		o.child = new InOutTest();
		o.child.name = "o2";
		
	    resp.setContentType("text/plain");
	    PrintWriter out = resp.getWriter();		
	    out.println(MoobinToJson.format(o));
   	}
    
}