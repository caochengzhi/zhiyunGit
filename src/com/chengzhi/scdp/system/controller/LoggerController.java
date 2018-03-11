package com.chengzhi.scdp.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 日志管理
 * @author beisi
 *
 */
@Controller
@RequestMapping("/logManager")
public class LoggerController {

	@RequestMapping(value = "/toSearch", method = {RequestMethod.GET})
	public String toSearch(){
		return "system/loggerSearch";
	}
}
