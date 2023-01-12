package com.example.thingsFlow.service;

import com.example.thingsFlow.entity.Board;
import com.example.thingsFlow.password.Encryption;
import com.example.thingsFlow.repository.BoardRepository;
import com.example.thingsFlow.password.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DeleteService {

    private final BoardRepository boardRepository;
    private final Validation validation;
    @Transactional
    public Board delete(Long id, Map<String, Object> map){
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 게시글이 존재하지 않습니다. id = " + id));
        if (validation.checkHashedPassword((String) map.get("password"), board.getPassword())) {
            boardRepository.delete(board);
            return board;
        } else throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }
}
