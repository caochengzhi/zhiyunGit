package com.chengzhi.scdp.common;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.chengzhi.scdp.system.manager.ModuleManager;

public class ServletInit extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		String path = ServletInit.class.getResource("/").getPath();
		path = path.substring(0, path.indexOf("classes"))+"module.xml";
		File f = new File(path);
		ModuleManager manager = ModuleManager.getInstance();
		try {
			manager.reload(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
