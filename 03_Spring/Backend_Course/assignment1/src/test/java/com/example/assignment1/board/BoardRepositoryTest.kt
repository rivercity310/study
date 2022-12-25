package com.example.assignment1.board

import com.example.assignment1.board.impl.BoardDAO
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BoardRepositoryTest {
    @Autowired
    private lateinit var boardDAO: BoardDAO

    private fun equals(expected: BoardVO, actual: BoardVO) {
        if (expected.id != null) assertEquals(expected.id, actual.id)
        if (expected.name != null) assertEquals(expected.name, actual.name)
        if (expected.content != null) assertEquals(expected.content, actual.content)
        if (expected.seq != null) assertEquals(expected.seq, actual.seq)
        if (expected.reward != null) assertEquals(expected.reward, actual.reward)
        if (expected.nation != null) assertEquals(expected.nation, actual.nation)
        if (expected.location != null) assertEquals(expected.location, actual.location)
        if (expected.position != null) assertEquals(expected.position, actual.position)
        if (expected.tech != null) assertEquals(expected.tech, actual.tech)
    }

    @Test
    @DisplayName("보드 삽입 테스트")
    fun insertBoard() {
        // given
        val vo = BoardVO()
        vo.id = 1
        vo.reward = 2000000
        vo.name = "카카오"
        vo.nation = "한국"
        vo.location = "판교"
        vo.position = "Backend Developer"
        vo.content = "백엔드 개발자 모십니다"
        vo.tech = "Java Spring"

        // when
        boardDAO.insertBoard(vo)
        val getVO = boardDAO.getBoard(BoardVO(seq=9))

        // then
        equals(vo, getVO)

        // finally
        boardDAO.deleteBoard(getVO)
    }

    @Test
    @DisplayName("보드 업데이트 테스트 (SEQ번째 데이터를 수정한 뒤 다시 가져왔을 때 값이 동일한가?)")
    fun updateBoard() {
        // given
        val vo = BoardVO()
        vo.position = "Frontend Developer"
        vo.reward = 2500000
        vo.content = "프론트 개발자 모십니다"
        vo.tech = "Javascript"
        vo.seq = 5

        // when
        boardDAO.updateBoard(vo)
        val getVO = boardDAO.getBoard(vo)

        // then
        equals(vo, getVO)
    }

    @Test
    @DisplayName("보드 삭제 테스트")
    @Disabled       // 필요한 경우에만 테스트 오픈
    fun deleteBoard() {
        assertEquals(Unit, boardDAO.deleteBoard(BoardVO(56)))
    }

    @Test
    @DisplayName("보드리스트 가져오기 테스트 -> searchKeyword가 null이면 전체 리스트를, 아니면 검색 결과를 가져오는지 확인")
    fun getBoardList() {
        // given
        val vo = BoardVO()
        vo.searchKeyword = ""

        // when/then 1
        assertEquals(6, boardDAO.getBoardList(vo).size)

        // given
        vo.searchKeyword = "카"

        // when/then 2
        assertEquals(1, boardDAO.getBoardList(vo).size)

        // given
        vo.searchKeyword = "네이"

        // when/then 3
        assertEquals(5, boardDAO.getBoardList(vo).size)
    }

    @Test
    @DisplayName("사명이 동일한 리스트만 가져오되 seq가 같은 것을 제외하고 가져오는지 테스트, 자기 자신을 제외하기 때문에 (searchKeyword로 해당 키워드를 검색한 결과의 개수 - 1)과 같으면 통과")
    fun getBoardContentList() {
        // given
        val vo = BoardVO()
        vo.seq = 1
        vo.name = "네이버"
        vo.searchKeyword = "네이버"

        // when/then
        assertEquals(boardDAO.getBoardList(vo).size - 1, boardDAO.getBoardContentList(vo).size)
    }
}