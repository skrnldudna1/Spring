package com.company.vo;

import lombok.Data;

@Data
//번호 (bno)와 소유주(owner), 등급(grade)를 지정
public class Ticket {
	
	private Integer tno;
	private String owner;
	private String grade;

}
