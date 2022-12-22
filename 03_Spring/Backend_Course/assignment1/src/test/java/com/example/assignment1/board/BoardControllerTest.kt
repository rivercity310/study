package com.example.assignment1.board

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BoardControllerTest {

    @Autowired
    private lateinit var boardController: BoardController

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

        assertEquals( "getBoardList.do", boardController.insertBoard(vo))
    }

    @Test
    fun deleteBoard() {
    }

    @Test
    fun updateBoard() {
    }

    @Test
    fun getBoard() {
    }

    @Test
    fun getBoardContent() {
    }

    @Test
    fun getBoardList() {
    }
}