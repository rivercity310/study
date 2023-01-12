package com.example.thingsFlow.service;

import com.example.thingsFlow.password.Encryption;
import com.example.thingsFlow.dto.UpdateDTO;
import com.example.thingsFlow.entity.Board;
import com.example.thingsFlow.repository.BoardRepository;
import com.example.thingsFlow.password.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UpdateService {
    private final BoardRepository boardRepository;
    private final Validation validation;
    @Transactional
    public Board updateBoard(UpdateDTO updateDTO) {
        Board board = boardRepository.findById(updateDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id = " + updateDTO.getId()));
        if (validation.checkHashedPassword(updateDTO.getPassword(), board.getPassword())) {
            board.update(updateDTO);
            return board;
        } else throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }
    @Transactional
    public Long validatePassword(Long id, Map map) {
        if (validation.checkHashedPassword(
                (String) map.get("password"),
                boardRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("해당하는 게시글이 존재하지 않습니다. id = " + id)).getPassword())) {
            return 1L;
        } else throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }
}
