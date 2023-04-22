package com.example.thingsFlow.update;

import com.example.thingsFlow.dto.UpdateDTO;
import com.example.thingsFlow.entity.Board;
import com.example.thingsFlow.repository.BoardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("띵스플로우 테스트")
@ExtendWith(SpringExtension.class)
public class UpdateTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BoardRepository boardRepository;

    @LocalServerPort
    private int port;

    @AfterEach
    public void clear() {
        boardRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글 수정 테스트")
    void boardUpdateTest() {
        // init
        Board board = setUpOneBoard();

        // given
        UpdateDTO updateDTO = UpdateDTO.builder()
                .id(1L)
                .title("Title_999")
                .content("Content_999")
                .password("1111")
                .build();

        String url = "http://localhost:" + port + "/api/post";

        // when
        HttpEntity<UpdateDTO> requestEntity = new HttpEntity<>(updateDTO);

        ResponseEntity<Board> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Board.class);
        // then
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        List<Board> list = boardRepository.findAll();
        assertThat(list.get(0).getTitle()).isEqualTo("Title_999");
        assertThat(list.get(0).getContent()).isEqualTo("Content_999");
    }

    private Board setUpOneBoard() {
        long id = 1;
        String title = "Title_";
        String content = "Content_";
        String password = "1111";

        return boardRepository.save(Board.updateDTOBuilder()
                .updateDTO(UpdateDTO.builder()
                        .id(id)
                        .title(title+id)
                        .content(content+id)
                        .password(password).build())
                .build());
    }
}