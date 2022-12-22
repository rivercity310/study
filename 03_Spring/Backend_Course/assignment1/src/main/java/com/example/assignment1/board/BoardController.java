package com.example.assignment1.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({ "board", "boardList", "boardContent", "boardContentList" })
public class BoardController {
    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping("/insertBoard.do")
    public String insertBoard(BoardVO vo) {
        boardService.insertBoard(vo);
        return "getBoardList.do";
    }

    @RequestMapping("/deleteBoard.do")
    public String deleteBoard(BoardVO vo) {
        boardService.deleteBoard(vo);
        return "getBoardList.do";
    }

    @PostMapping("/updateBoard.do")
    public String updateBoard(@ModelAttribute("board") BoardVO vo) {
        boardService.updateBoard(vo);
        return "getBoardList.do";
    }

    @RequestMapping("/getBoard.do")
    public String getBoard(BoardVO vo, Model model) {
        model.addAttribute("board", boardService.getBoard(vo));
        return "getBoard.jsp";
    }

    @RequestMapping("/getBoardContent.do")
    public String getBoardContent(BoardVO vo, Model model) {
        model.addAttribute("boardContent", boardService.getBoard(vo));
        model.addAttribute("boardContentList", boardService.getBoardContentList(vo));
        return "getBoardContent.jsp";
    }

    @RequestMapping("/getBoardList.do")
    public String getBoardList(BoardVO vo, Model model) {
        model.addAttribute("boardList", boardService.getBoardList(vo));
        return "getBoardList.jsp";
    }
}
