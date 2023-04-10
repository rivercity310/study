package com.example.recruitBoard.board

import com.example.recruitBoard.service.BoardService
import com.example.recruitBoard.vo.BoardVO
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback

@SpringBootTest
class BoardControllerTest {
    @Autowired
    private lateinit var boardService: BoardService

    private fun equals(expected: BoardVO, actual: BoardVO) {
        if (expected.id != null) assertEquals(expected.id, actual.id)
        if (expected.content != null) assertEquals(expected.content, actual.content)
        if (expected.seq != null) assertEquals(expected.seq, actual.seq)
        if (expected.reward != null) assertEquals(expected.reward, actual.reward)
        if (expected.position != null) assertEquals(expected.position, actual.position)
        if (expected.tech != null) assertEquals(expected.tech, actual.tech)
    }

    @Test
    @DisplayName("채용공고 상세보기 테스트")
    @Disabled
    fun getBoardTest() {
        // when : 첫번째 글, 회사 id가 1인 글을 가져왔을 때 이 회사가 올린 다른 공고가 정상적으로 나오는지 확인
        // 현재 보고 있는 글(seq = 1)을 제외한 글목록 리스트가 정상적으로 들어있는지 테스트
        val getVO = boardService.getBoard(seq = 5)
        assertEquals(3, getVO.id)

        if (getVO.others == null) Assertions.fail<String>("other 목록을 가져오지 못함")
        else {
            assertEquals(2, getVO.others!!.size)
            getVO.others?.forEach { vo ->
                if (getVO.seq == vo.seq) Assertions.fail<String>("현재 공고와 같은 페이지를 가져옴")
            }
        }
    }

    @Test
    @DisplayName("검색 테스트")
    @Disabled
    fun searchBoardTest() {
        // when : searchKeyword가 주어졌을 때, name, tech, position, nation, location 중 일치하는 목록이 있는 row를 모두 가져온다
        val searchKeywords: List<String> = listOf("네이", "카카", "한국", "Spring", "Java", "판교", "end")
        val expected: List<Int> = listOf(3, 0, 3, 2, 3, 3, 3)

        searchKeywords.forEachIndexed { i, keywords ->
            val boards = boardService.searchBoard(keywords)
            assertEquals(expected[i], boards.size)
        }
    }

    @Test
    @Rollback
    @DisplayName("채용공고가 정상적으로 등록되는지 테스트")
    @Disabled
    fun insertBoardTest() {
        /** given
        {
            "id": 1,
            "reward": 2000000,
            "position": "backend",
            "content": "백엔드 개발자 모십니다",
            "tech": "Java Spring"
        }
         */

        // given
        val vo = BoardVO(
            id = 1,
            reward = 2000000,
            position = "backend",
            content = "백엔드 개발자 모십니다",
            tech = "Java Spring"
        )

        // when
        val responseCode = boardService.insertBoard(vo)

        // then : 등록된 개수가 1개인가
        assertEquals(1, responseCode)

        // then2 : 등록된 보드를 가져왔을 때 모든 값 일치?
        equals(vo, boardService.getBoard(seq = 12))
    }

    @Test
    @DisplayName("채용공고가 정상적으로 삭제되는지 테스트")
    @Rollback
    @Disabled
    // @Disabled 필요한 경우에만 테스트 활성화
    fun deleteBoardTest() {
        val responseCode = boardService.deleteBoard(11)
        assertEquals(1, responseCode)
    }

    @Test
    @Rollback
    @DisplayName("채용공고가 정상적으로 수정되는지 테스트")
    @Disabled
    fun updateBoardTest() {
        val vo = BoardVO(
            seq = 12,
            reward = 2500000,
            position = "frontend",
            content = "프론트 개발자 모십니다",
            tech = "Javascript"
        )

        val responseCode = boardService.updateBoard(vo)

        assertEquals(1, responseCode)
        equals(vo, boardService.getBoard(seq = 12))
    }

    @Test
    @DisplayName("채용공고 목록을 성공적으로 가져오는지 테스트")
    fun getBoardListTest() {
        // 목록의 전체 개수와 비교 : 2회 실행 224ms
        assertEquals(96, boardService.getBoardList().size)
    }

    @Test
    @DisplayName("채용공고 목록을 성공적으로 가져오는지 테스트2")
    fun getBoardListTest2() {
        assertEquals(96, boardService.getBoardList().size)
    }
}