package com.company.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.vo.SampleVO;
import com.company.vo.Ticket;

import lombok.extern.log4j.Log4j;

@Controller
@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	//URL매핑
	@GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
	public String getText() {
		
		log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕하세용";
	}
	
	@GetMapping(value ="/getSample",
			produces = {MediaType.APPLICATION_JSON_VALUE})
	
	public SampleVO getSample() {
		return new SampleVO(112,"스타", "로드");
	}
	
	@GetMapping(value = "/getSample2")
	public SampleVO getSample2() {
		return new SampleVO(113, "로켓", "라쿤");
	}

	@GetMapping(value = "/getList")
	public List<SampleVO> getList() {
		//10번 반복 (1~10)   i전달 되면 10개 객체생성 리스트 생성
		return IntStream.range(1,10).mapToObj(i -> new SampleVO(i, i+ "First", i + "Last"))
				.collect(Collectors.toList()); //리스트생성
	}
	
	@GetMapping(value = "/getMap")
	public Map<String, SampleVO> getMap() {
		
		//HashMap 객체를 생성하여 SampleVO 객체를 저장할 준비
		Map<String, SampleVO> map = new HashMap<> ();
		
		//"First"라는 키와 새로운 SampleVO 객체 (번호,이룸,직책)를 맵에 추가
		map.put("First", new SampleVO(111,"그루트", "주니어"));
		
		//생성한 맵을 반환하여 클라이언트로 전달
		return map;
	}
	
	
	//HTTP GET 요청을 처리, 키("height", "weight")라는 두 개의 요청 파라미터를 기반으로
	//특정 조건(키가 150미만인지 여부) 에 따라 다른 HTTP 상태 코드를 반환 하는 기능
	@GetMapping(value = "/check", params = {"height", "weight"})
	public ResponseEntity<SampleVO> check(Double height, Double weight) {
		
		// 요청받은 height와 weight 값을 사용해 SampleVO 객체 생성
		SampleVO vo = new SampleVO(000, "" + height, "" + weight);
		
		// HTTP 응답(ResponseEntity)을 저장할 변수를 초기화
		ResponseEntity<SampleVO> result = null;
		
		// 조건: height가 150 미만인 경우
		if (height < 150) {
			
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
			
		}else {
			
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		
		return result;
	}
	
	// GET 요청 경로 변수 {cat}와 {pid}를 추출
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(
			
			@PathVariable("cat")String cat,   // URL에서 {cat} 부분을 추출하여 cat 변수에 저장
			@PathVariable("pid") Integer pid
			) {
		return new String[] {"category: " + cat, "productid: " + pid};
		// cat,pid 변수 값을 문자열로 가공
	}
	
	//JSON 데이터를 자바 객체로 변환한 뒤, 다시 클라이언에게 응답으로 반환
	//JUnit과 spring-test를 이용해서 테스트 (SampleControllerTests에서 테스트)
	@PostMapping("/ticket")  //json -> java 객체로
	public Ticket convert(@RequestBody Ticket ticket) {
		
		//Ticket 객체의 정보를 로그로 출력
		log.info("convert.......ticket"+ ticket);
		
		return ticket;
	}

}
