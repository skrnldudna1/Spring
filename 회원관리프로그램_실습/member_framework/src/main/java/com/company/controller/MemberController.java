package com.company.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.dto.MemberDTO;
import com.company.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/")
	  public String findAll(Model model){
	    List<MemberDTO> memberDTOList = memberService.findAll();
	    model.addAttribute("memberList", memberDTOList);
	    return "list";
	  }
	
	@GetMapping("/save")
	public String saveFrom() {
		return "save";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute MemberDTO memberDTO) {
		int saveResult = memberService.save(memberDTO);
		if(saveResult > 0) {
			return "login";
		}else {
			return "save";
		}
		
	}
	
	  @GetMapping("/login")
	  public String loginForm() {
		  return "login"; }
	  
	  @PostMapping("/login")
	  public String login(@ModelAttribute MemberDTO memberDTO,HttpSession session) { 
		  boolean loginResult = memberService.login(memberDTO);
		  if(loginResult) {
			  session.setAttribute("loginEmail", memberDTO.getMemberEmail());
			  return "main";
	  }else {
	  return "login";
	  	}
	  }
	 
	// /member?id=1
	  @GetMapping
	  public String findById(@RequestParam("id") Long id, Model model){
	    MemberDTO memberDTO = memberService.findById(id);
	    model.addAttribute("member", memberDTO);
	    return "detail";
	  }
	
	
}
