package com.company.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.company.dto.CommentDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor   /* ㅣombok을 사용해 final 필드를 가진 생성자를 자동으로 생성 */

public class CommentRepository {
	
	//연결고리
	private final SqlSessionTemplate sql;

	public void save(CommentDTO commentDTO) {
		 sql.insert("Comment.save", commentDTO);
	}

	public List<CommentDTO> findAll(Long boardId) {
		return sql.selectList("Comment.findAll", boardId);  //Mapper name과 대문자,소문자 전부 일치해야한다.
	}

}
