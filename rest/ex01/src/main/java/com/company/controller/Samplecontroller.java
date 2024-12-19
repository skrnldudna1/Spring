package com.company.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.ibatis.javassist.tools.reflect.Sample;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.domain.SampleDTO;
import com.company.domain.SampleDTOList;
import com.company.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller     // 이 클래스가 스프링 MVC에서 컨트롤러 역할을 한다고 지정
@RequestMapping("/sample/*")   //"/sample/"로 시작하는 URL 요청을 이 컨트롤러에서 처리하도록 경로 지정
@Log4j
public class Samplecontroller {
	
	@RequestMapping("")
	public void basic() {
		log.info("basic...................."); // 로그 메시지를 기록. 메서드가 호출될 때 로그에 해당 메시지를 출력
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info(""+ dto); // 로그 메시지를 기록. 메서드가 호출될 때 로그에 해당 메시지를 출력
	
		return "ex01";
	}
	
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age")int age) {
		log.info("name: "+ name); // 로그 메시지를 기록. 메서드가 호출될 때 로그에 해당 메시지를 출력
		log.info("age: "+ age); 
	
		return "ex02";
	}

	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids ) {
		log.info("ids" + ids); // 로그 메시지를 기록. 메서드가 호출될 때 로그에 해당 메시지를 출력
	
		return "ex02List";
	}
	
	//Array (배열) : 같은 속성이 나란할때
	//List (컬렉션) : 여러 속성이 나란할 때 
	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids ) {
		log.info("array ids" + Arrays.toString(ids)); // 로그 메시지를 기록. 메서드가 호출될 때 로그에 해당 메시지를 출력
	
		return "ex02Array";
	}
	
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos: " + list); // 로그 메시지를 기록. 메서드가 호출될 때 로그에 해당 메시지를 출력
	
		return "ex02Bean";
	}
	
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo: "+ todo);
		return "ex03";
	}
	
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		log.info("dto: "+ dto);
		log.info("page: "+ page);
		return "/sample/ex04";
	}
	
	@GetMapping ("/ex05")
	public void ex05() {
		log.info("/ex05...........");
	}
	
}
