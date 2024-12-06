package com.company.controllor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.dto.BoardDTO;
import com.company.dto.CommentDTO;
import com.company.dto.PageDTO;
import com.company.service.BoardService;
import com.company.service.CommentService;

import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;


@Controller
@RequiredArgsConstructor // Lombok을 사용해 final 필드를 가진 생성자를 자동으로 생성
@RequestMapping("/board") // 모든 요청에 대해 /board 경로가 붙도록 설정
public class BoardController {
    
    // BoardService 인스턴스를 주입받습니다. 서비스 레이어에서 비즈니스 로직 처리
    private final BoardService boardService;
    private final CommentService commentService;
    
    // 게시판 글 작성 화면을 요청하는 메서드
    @GetMapping("/save")
    public String saveForm() {
        // "save"라는 뷰 페이지로 이동 (글 작성 화면)
        return "save";
    }
    
    // 게시판 글 저장을 처리하는 메서드
    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        
        // BoardDTO 객체를 사용하여 글을 저장하는 로직을 호출
        int saveResult = boardService.save(boardDTO);
        
        // 저장 결과가 성공적인 경우 (saveResult > 0) 게시판 리스트 페이지로 리다이렉트
        if(saveResult > 0) {
            return "redirect:/board/paging";
        } else {
            // 저장 실패 시 다시 글 작성 페이지로 이동
            return "save";
        }
    }
    
    @GetMapping("/")
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }

    @GetMapping
    public String findById(@RequestParam("id") Long id,
                           @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                           Model model) {

        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        model.addAttribute("page", page);
        List<CommentDTO> commentDTOList = commentService.findAll(id);
        model.addAttribute("commentList", commentDTOList);
        return "detail";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        boardService.delete(id);
        return "redirect:/board/";
    }

    @GetMapping("/update")
    public String updateForm(@RequestParam("id") Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "update";
    }
    
    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        boardService.update(boardDTO);
        BoardDTO dto = boardService.findById(boardDTO.getId());
        model.addAttribute("board", dto);
        return "detail";
    
    }
    
    // 페이징
    // /board/paging?page=2
    // 처음 페이지 요청은 1페이지를 보여줌
    @GetMapping("/paging")
    public String paging(Model model,
                         @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        System.out.println("page = " + page);
        // 해당 페이지에서 보여줄 글 목록
        List<BoardDTO> pagingList = boardService.pageList(page);
        System.out.println("pageList = " + pagingList);
        PageDTO pageDTO = boardService.pagingParam(page);
        model.addAttribute("boardList", pagingList);
        model.addAttribute("paging", pageDTO);
        
        return "paging";
    }

}
