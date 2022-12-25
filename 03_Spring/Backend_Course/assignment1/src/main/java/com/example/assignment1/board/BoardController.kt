package com.example.assignment1.board

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
open class BoardController(private val boardService: BoardService) {
    @RequestMapping("/insertBoard.do")
    fun insertBoard(vo: BoardVO): String {
        boardService.insertBoard(vo)
        return "getBoardList.do"
    }

    @RequestMapping("/deleteBoard.do")
    fun deleteBoard(vo: BoardVO): String {
        boardService.deleteBoard(vo)
        return "getBoardList.do"
    }

    @RequestMapping("/updateBoard.do")
    fun updateBoard(vo: BoardVO): String {
        boardService.updateBoard(vo)
        return "getBoardList.do"
    }

    @RequestMapping("/getBoard.do")
    fun getBoard(vo: BoardVO, model: Model): String {
        model.addAttribute("board", boardService.getBoard(vo))
        return "getBoard.jsp"
    }

    @RequestMapping("/getBoardContent.do")
    fun getBoardContent(vo: BoardVO, model: Model): String {
        model.addAttribute("boardContent", boardService.getBoard(vo))
        model.addAttribute("boardContentList", boardService.getBoardContentList(vo))
        return "getBoardContent.jsp"
    }

    @RequestMapping("/getBoardList.do")
    fun getBoardList(vo: BoardVO, model: Model): String {
        model.addAttribute("boardList", boardService.getBoardList(vo))
        return "getBoardList.jsp"
    }
}