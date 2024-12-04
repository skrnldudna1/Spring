package com.company.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.mapper.TimeMapper;

import lombok.extern.log4j.Log4j;

//스프링 테스트를 위한 설정
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트에서 JUnit을 실행하기 위한 설정
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") // 스프링 설정 파일 위치를 지정
@Log4j  // Lombok을 사용하여 Log4j 로거 객체 자동 생성
public class TimeMapperTests {
	
	// TimeMapper 인터페이스를 자동으로 주입받음
	@Autowired
	private TimeMapper timeMapper;
	
	// 첫 번째 테스트 메서드, 데이터베이스에서 현재 시간을 가져옴
	@Test
	public void testGetTime() {
		log.info(timeMapper.getClass().getName());  // TimeMapper의 실제 클래스 이름을 로그에 기록
		log.info("---------------------------------------------");
		log.info(timeMapper.getTime());  // getTime 메서드를 호출하여 현재 시간을 로그로 출력
	} 
	
	@Test
	public void testGetTime2() {
		log.info("getTime2");
		log.info(timeMapper.getTime2());
	}

}
