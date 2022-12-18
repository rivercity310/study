package com.springbook.biz.view.board;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
    @RequestMapping("/deleteBoard.do")
    public String deleteBoard(BoardVO vo, BoardDAO boardDAO) {
        boardDAO.deleteBoard(vo);
        return "getBoardList.do";
    }

    @RequestMapping("/getBoard.do")
    public String getBoard(BoardVO vo, BoardDAO boardDAO, Model model) {
        model.addAttribute("board", boardDAO.getBoard(vo));
        return "getBoard.jsp";
    }

    @RequestMapping("/getBoardList.do")
    public String getBoardList(BoardDAO boardDAO, Model model) {
        model.addAttribute("boardList", boardDAO.getBoardList());
        return "getBoardList.jsp";
    }

    @RequestMapping(value="/insertBoard.do")
    public String insertBoard(BoardVO vo, BoardDAO boardDAO) {
        boardDAO.insertBoard(vo);
        return "getBoardList.do";
    }

    @RequestMapping("/updateBoard.do")
    public String updateBoard(BoardVO vo, BoardDAO boardDAO) {
        boardDAO.updateBoard(vo);
        return "getBoardList.do";
    }
}
