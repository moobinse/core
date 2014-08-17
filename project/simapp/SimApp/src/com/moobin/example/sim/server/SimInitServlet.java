package com.moobin.example.sim.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.moobin.x.sim.configuration.Sim;

@SuppressWarnings("serial")
public class SimInitServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		Sim.main(null);
	}
}