package com.example.assignment1.board

import com.example.assignment1.board.impl.BoardDAO
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BoardControllerTest {

    @Autowired
    private lateinit var boardDAO: BoardDAO

    @Test
    fun insertBoard() {
        val vo = BoardVO()
        vo.id = 1
        vo.reward = 2000000
        vo.name = "카카오"
        vo.nation = "한국"
        vo.location = "판교"
        vo.position = "Backend Developer"
        vo.content = "백엔드 개발자 모십니다"
        vo.tech = "Java Spring"

        assertEquals( Unit, boardDAO.insertBoard(vo))
    }

    @Test
    fun updateBoard() {
        val vo = BoardVO()
        vo.position = "Frontend Developer"
        vo.reward = 2500000
        vo.content = "프론트 개발자 모십니다"
        vo.tech = "Javascript"
        vo.seq = 51

        boardDAO.updateBoard(vo)
        assertEquals(51, boardDAO.getBoard(BoardVO(51)).seq)
    }

    @Test
    fun deleteBoard() {
        assertEquals(Unit, boardDAO.deleteBoard(BoardVO(48)))
    }
}