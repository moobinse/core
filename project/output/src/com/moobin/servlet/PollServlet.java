package com.moobin.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moobin.cache.CacheRootMap;
import com.moobin.core.Core;
import com.moobin.core.data.CacheRequest;
import com.moobin.core.data.CacheResponse;
import com.moobin.core.data.PollRequest;
import com.moobin.core.data.PollResponse;
import com.moobin.meta.MetaDataObject;
import com.moobin.output.js.MoobinFromJson;
import com.moobin.output.js.MoobinToJson;

import sun.misc.IOUtils;

@SuppressWarnings("serial")
public class PollServlet extends javax.servlet.http.HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    response.setContentType("text/plain");
	    
	    StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        String line = bufferedReader.readLine();
        while(line != null){
            inputStringBuilder.append(line);inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
        String json = "{cacheRequests: " + inputStringBuilder.toString() + "}" ;
        PollRequest r = MoobinFromJson.parse(PollRequest.class, json);
        PollResponse pollRsp = new PollResponse();
        pollRsp.cacheResponses = new CacheResponse[r.cacheRequests.length];
        for (int i = 0; i < r.cacheRequests.length; i++) {
        	CacheRequest cacheReq = r.cacheRequests[i];
        	CacheResponse rsp = new CacheResponse();
        	rsp.handle = cacheReq.handle;
        	
    	    MetaDataObject<Object> meta = Core.get().getMetaDataManager().getMetaData(cacheReq.type);
    	    CacheRootMap<? extends Object> rootMap = Core.get().getCacheManager().getRootMap(meta.getType());
    	    if (cacheReq.key == null) {
    	    	rsp.value = new ArrayList<>(rootMap.values()).toArray();
    	    }
    	    else {
    	    	rsp.value = rootMap.get(cacheReq.key);
    	    }
    	    pollRsp.cacheResponses[i] = rsp;
        }
        System.out.println(MoobinToJson.format(pollRsp));
	    response.getOutputStream().print(MoobinToJson.format(pollRsp));
	}
	
}
