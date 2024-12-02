package com.company.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.dto.MemberDTO;
import com.company.service.MemberService;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/member")  //멤버로
@RequiredArgsConstructor
public class HomeController {
	
	private final MemberService memberService;
	
	@GetMapping("/")
	public String index(){
		return "index";
	}
	
	@GetMapping("/home")
	public String home(){
		return "home";
	}
	
	//save 보여주기
	@GetMapping ("/save")
	public String saveForm() {
		return "save";
	
	}
	//경로가 같아도 Get,Post는 상관 없다.
	//삽입
	@PostMapping ("/save")
	public String save(@ModelAttribute MemberDTO memberDTO) {
		
		// memberService를 사용하여 멤버 정보를 저장하고 그 결과를 saveResult에 저장
		int saveResult = memberService.save(memberDTO);
		
		// 저장 결과에 따라 다른 뷰를 반환
		if(saveResult > 0 ) {
			// 저장이 성공적일 경우 로그인 페이지로 이동
			return "login";
		}else {
			 // 저장에 실패할 경우 다시 저장 페이지로 이동
			return "save";
		}
		
	}
	
}
	
