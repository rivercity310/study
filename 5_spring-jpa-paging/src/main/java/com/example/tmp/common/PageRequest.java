package com.example.tmp.common;

import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
public final class PageRequest {
    private int page;
    private int size;
    private Sort.Direction direction;

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size) {
        int default_size = 10;
        int max_size = 50;
        this.size = size > max_size ? default_size : size;
    }

    public void setDirection(Sort.Direction direction) {
        if (direction == null) this.direction = Sort.Direction.ASC;
        else this.direction = direction;
    }

    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page - 1, size, direction, "createdAt");
    }
}
