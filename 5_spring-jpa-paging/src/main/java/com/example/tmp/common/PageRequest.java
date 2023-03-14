package com.example.tmp.common;

import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
public final class PageRequest {
    private final int page;
    private final int size;
    private final Sort.Direction direction;

    public PageRequest(int page, int size, Sort.Direction direction) {
        this.page = page <= 0 ? 1 : page;
        this.size = size > 50 ? 10 : size;
        this.direction = direction == null ? Sort.Direction.ASC : direction;
    }

    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page - 1, size, direction, "createdAt");
    }
}
