package com.company.controllor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.dto.CommentDTO;
import com.company.service.CommentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor // Lombok을 사용해 final 필드를 가진 생성자를 자동으로 생성
@RequestMapping("/comment") // 모든 요청에 대해 /comment 경로가 붙도록 설정
public class CommentController {
	
	//CommentService 연결고리
	 private final CommentService commentService;
	
	@PostMapping("/save")
	 public @ResponseBody List<CommentDTO> save(@ModelAttribute CommentDTO commentDTO) {
        System.out.println("commentDTO = " + commentDTO);
        commentService.save(commentDTO);  // CommentService의 save 메서드를 호출하여 댓글 데이터를 저장
		
        //해당 게시글에 작성된 댓글 리스트 가져오기
        List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId());
		return commentDTOList;
	}

}
