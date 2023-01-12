package com.example.recruitBoard.controller

import com.example.recruitBoard.service.BoardService
import com.example.recruitBoard.vo.BoardVO
import com.example.recruitBoard.vo.UserVO
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController("boardController")
open class BoardController(val boardService: BoardService) {
    /* 전체 채용공고 목록을 가져옵니다 */
    @GetMapping("/api/getBoardList.do")
    @ResponseBody
    fun getBoardList() = boardService.getBoardList()

    /* 상세 채용공고를 가져옵니다 */
    @GetMapping("/api/getBoard.do")
    @ResponseBody
    fun getBoard(@RequestParam("seq") seq: Int) = boardService.getBoard(seq)

    /* 채용공고를 등록합니다 */
    @PostMapping("/api/insertBoard.do")
    fun insertBoard(@RequestBody vo: BoardVO) = boardService.insertBoard(vo)

    /* 채용공고를 삭제합니다 */
    @DeleteMapping("/api/deleteBoard.do")
    fun deleteBoard(@RequestParam("seq") seq: Int) = boardService.deleteBoard(seq)

    /* 채용공고를 수정합니다 */
    @PutMapping("/api/updateBoard.do")
    fun updateBoard(@RequestBody vo: BoardVO) = boardService.updateBoard(vo)

    /* 채용공고를 검색합니다 (회사명, 지역, 포지션, 기술) */
    @GetMapping("/api/searchBoard.do")
    @ResponseBody
    fun searchBoard(@RequestParam("searchKeyword") searchKeyword: String?) = boardService.searchBoard(searchKeyword)

    /* 채용공고에 지원합니다 */
    @PostMapping("/api/applyBoard.do")
    fun applyBoard(@RequestBody json: ObjectNode) {
        val board = json.get("board")
        val user = json.get("user")

        val boardVO = BoardVO(
            seq = board.get("seq").asInt(),
            id = board.get("id").asInt(),
            reward = board.get("reward").asInt(),
            position = board.get("position").asText(),
            content = board.get("content").asText(),
            tech = board.get("tech").asText()
        )

        val userVO = UserVO(
            boardSeq = user.get("boardSeq").asInt(),
            userId = user.get("userId").asText(),
            userName = user.get("userName").asText(),
            userAge = user.get("userAge").asInt()
        )

        boardService.applyBoard(boardVO, userVO)
    }
}