package com.czw.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Zevi Chan
 * @Date 2016年6月28日
 */
@Controller
public class IndexController {
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(){
		return "index";
	}
}
