package com.company.mapper;

import java.util.List;
import java.util.Map;

import com.company.domain.BoardVO;
import com.company.domain.Criteria;

public interface BoardMapper {

	//@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	//그냥 저장
	public void insert(BoardVO board);

	//현재 번호 확인 후 저장
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
	//검색
	public List<BoardVO> searchTest(Map<String, Map<String, String>>map);
	
}
