package com.company.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MemberDTO {
	private Long id;
	private String memberEmail;
	private String memberPassword;
	private String memberName;
	private int memberAge; 
	private String memberMobile;
}
