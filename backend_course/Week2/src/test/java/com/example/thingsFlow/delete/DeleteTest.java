package com.example.thingsFlow.delete;


import com.example.thingsFlow.dto.DeleteBoardDTO;
import com.example.thingsFlow.entity.Board;
import com.example.thingsFlow.repository.BoardRepository;
import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("게시판 테스트")
@ExtendWith(SpringExtension.class)
public class DeleteTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BoardRepository boardRepository;
    @After
    public void clear() {
        boardRepository.deleteAll();
    }


    @Test
    public void 게시판_글_삭제(){
        // init
        long id = setUpOneBoard().getId();

        // given
        Map<String, Object> map = new HashMap();
        map.put("password", "1111");

        String url = "http://localhost:" + port + "/api/board/" + id;

        // when
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(map);

        ResponseEntity<Board> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Board.class);

        // then
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        List<Board> list = boardRepository.findAll();
        Assertions.assertEquals(list.size(), 0);
    }

    private Board setUpOneBoard() {
        long id = 1;
        String title = "Title_";
        String content = "Content_";
        String password = "1111";

        return boardRepository.save(Board.deleteDTOBuilder()
                .deleteBoardDTO(DeleteBoardDTO.builder()
                        .id(id)
                        .title(title + id)
                        .content(content + id)
                        .password(password).build())
                .build());
    }
}
