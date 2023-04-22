package com.example.thingsFlow.service;

import com.example.thingsFlow.entity.Board;
import com.example.thingsFlow.repository.BoardRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LoadService {

    private final BoardRepository boardRepository;

    @Transactional
    public Slice<Board> load(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdTime"));
        return boardRepository.findAll(pageable);
    }
}
