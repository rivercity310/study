package gdsc.session.controller

import gdsc.session.entity.Board
import gdsc.session.service.BoardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
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

    @GetMapping("/board/view")      // http://localhost:8080/board/view?id=1  -> 쿼리스트링의 id가 파라미터로 전달됨
    fun boardView(model: Model, id: Int): String {
        model.addAttribute("board", boardService.boardView(id));
        return "boardView";
    }

    @GetMapping("/board/list")
    fun boardList(model: Model, @PageableDefault(page = 0, size = 10, sort = ["id"], direction = Sort.Direction.DESC) pageable: Pageable, searchKeyword: String?): String {
        val list = if (searchKeyword == null) boardService.boardList(pageable)
        else boardService.boardSearchList(searchKeyword, pageable)

        val nowPage = list.pageable.pageNumber
        val startPage = maxOf(nowPage - 4, 1)
        val endPage = minOf(nowPage + 5, list.totalPages)

        model.addAttribute("list", list)
        model.addAttribute("nowPage", nowPage)
        model.addAttribute("startPage", startPage)
        model.addAttribute("endPage", endPage)

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
