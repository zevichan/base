package com.czw.spring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.czw.spring.web.bean.Spittle;
import com.czw.spring.web.dao.SpittleRepository;
import com.czw.spring.web.exception.SpittleNotFoundException;

/**
 * @author Zevi Chan
 * @Date 2016年6月29日
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {

	private SpittleRepository spittleRepository;

	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {
		this.spittleRepository = spittleRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String spittles(Model model) {
		model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE, 20));
		return "spittles";
	}
	
	
	public String saveSpittles(Spittle spittle,Model model){
		try {
			
		} catch (Exception e) {
		}
		return "";
	}

}
