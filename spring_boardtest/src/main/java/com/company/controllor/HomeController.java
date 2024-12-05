package com.company.controllor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;


@Controller
@Log4j
public class HomeController {
	@RequestMapping({"", "/"})    //복수계의 주소 사용시
	public String home() {
		log.info("Welcome home!");
		return "index";
	}	
}
