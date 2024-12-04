package com.company.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.company.dto.MemberDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
	
	private final SqlSessionTemplate sql;
	
	public int save(MemberDTO memberDTO) {
		System.out.println("memberDTO = " + memberDTO);
		return sql.insert("Member.save", memberDTO);
	}

}
