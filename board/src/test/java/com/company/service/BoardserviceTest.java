package com.company.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.domain.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardserviceTest {
	
	@Autowired
	private BoardService service;
	
	// 서비스 객체가 제대로 주입됐는지 확인
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	// 새로운 게시물을 등록하는 기능 테스트
	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		// 실행전 존재하는 번호인지 확인
		board.setBno(6L);   // 게시물 번호 설정
		board.setTitle("수정된 제목~");  // 제목 설정
		board.setContent("수정된 내용~");  // 내용 설정
		board.setWriter("newbie");  // 작성자 설정

		service.register(board);
		log.info("생성된 게시물의 번호: " + board.getBno());
	}

	// 게시물 목록을 가져오는 기능 테스트
	/*
	 * @Test public void testGetList() {
	 * 
	 * service.getList().forEach(board -> log.info(board)); }
	 */
	
	// 특정 번호의 게시물을 가져오는 기능 테스트
	@Test
	public void testGet() {
		
		log.info(service.get(1L));  // 1번 게시물 가져오기
	}
	
	// 게시물을 수정하는 기능 테스트
	@Test
	public void testUpdate() {
		
		BoardVO board = service.get(2L);
		
		if(board == null) {  // 게시물이 없으면 종료
			return;
		}
		board.setTitle("제목 수정합니다"); // 제목 수정
		log.info("MODIFY RESULT: " + service.modify(board)); // 수정 결과 로그 출력
	}
	
	// 게시물을 삭제하는 기능 테스트
	@Test
	public void testDelete() {
		
		//게시물 번호의 존재 여부를 확인하고 테스트할 것
		log.info("LEMOVE RESULT: " + service.remove(11L));
	}
}
