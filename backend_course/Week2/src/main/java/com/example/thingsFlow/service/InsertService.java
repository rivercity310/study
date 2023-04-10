package com.example.thingsFlow.service;

import com.example.thingsFlow.password.Encryption;
import com.example.thingsFlow.dto.InsertDTO;
import com.example.thingsFlow.entity.Board;
import com.example.thingsFlow.repository.BoardRepository;
import com.example.thingsFlow.parser.WeatherAPIParser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("boardService")
@AllArgsConstructor
public class InsertService {
    private final BoardRepository boardRepository;
    private final Encryption encryption;

    @Transactional
    public Board insert(InsertDTO insertDTO) {
        InsertDTO dto = getDTO(insertDTO);
        Board board = new Board(dto);
        return boardRepository.save(board);
    }

    private InsertDTO getDTO(InsertDTO insertDTO) {
        WeatherAPIParser weatherAPIParser = WeatherAPIParser.getInstance();
        String weather = weatherAPIParser.getCurrentWeather();
        return InsertDTO.builder()
                .title(insertDTO.getTitle())
                .content(insertDTO.getContent())
                .password(encryption.hashingPassword(insertDTO.getPassword()))
                .weather(weather).build();
    }
}