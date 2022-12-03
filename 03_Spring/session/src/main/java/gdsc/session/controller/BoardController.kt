package gdsc.session.controller

import gdsc.session.entity.Board
import gdsc.session.service.BoardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class BoardController(@Autowired val boardService: BoardService) {

    @GetMapping("/board/write")
    fun main(): String = "boardWrite"

    @PostMapping("/board/writepro")
    fun boardWritePro(board: Board): String {
        boardService.write(board)
        return ""
    }
    @GetMapping("/board/view")      // http://localhost:8080/board/view?id=1
    fun boardView(model: Model, id: Int): String {
        model.addAttribute("board", boardService.boardView(id));
        return "boardView";
    }

    @GetMapping("/board/list")
    fun boardList(model: Model): String {
        model.addAttribute("list", boardService.boardList())
        return "boardList"
    }

    @GetMapping("/board/delete")
    fun boardDelete(id: Int): String {
        boardService.boardDelete(id)
        return "redirect:/board/list"
    }

    @GetMapping("/board/modify/{id}")
    fun boardModify(@PathVariable("id") id: Int, model: Model): String {
        model.addAttribute("board", boardService.boardView(id))
        return "boardModify"
    }

    @PostMapping("/board/update/{id}")
    fun boardUpdate(@PathVariable("id") id: Int, board: Board): String {
        var boardTemp = boardService.boardView(id)
        boardTemp.title = board.title
        boardTemp.content = board.content

        boardService.write(boardTemp)
        return "redirect:/board/list"
    }
}
