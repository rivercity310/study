package com.example.thingsFlow.loadTest;

import com.example.thingsFlow.dto.LoadDTO;
import com.example.thingsFlow.dto.UpdateDTO;
import com.example.thingsFlow.entity.Board;
import com.example.thingsFlow.repository.BoardRepository;
import com.example.thingsFlow.service.LoadService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("채용 공고 상세 조회 테스트")
@ExtendWith(SpringExtension.class)
public class LoadTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private LoadService loadService;
    @LocalServerPort
    private int port;

    @AfterEach
    public void clear() {
        boardRepository.deleteAll();
    }

    @Test
    @DisplayName("무한 스크롤 API 테스트")
    void infinityScrollTest() throws Exception {
        // init
        setup();

        // given
        int page = 0;
        int size = 20;

        Slice<Board> slice = loadService.load(page, size);

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/board")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN)
                .characterEncoding(StandardCharsets.UTF_8.displayName());

        MockHttpServletResponse response = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // then
        Assertions.assertEquals(slice.getContent().get(0).getId(), 60);
    }

    private void setup() throws InterruptedException {
        List<Board> list = new ArrayList<>();
        long id = 1;
        String title = "Title_";
        String content = "Content_";
        String password = "Password_";
        for (int i = 0; i < 60; i++) {
            list.add(Board.updateDTOBuilder()
                    .updateDTO(new UpdateDTO(id + i, title + i, content + i, password + i))
                    .build());
            Thread.sleep(1000);
        }

        boardRepository.saveAll(list);
    }
}
