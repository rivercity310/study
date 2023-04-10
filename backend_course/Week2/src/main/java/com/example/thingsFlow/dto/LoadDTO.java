package com.example.thingsFlow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Getter
@AllArgsConstructor
public class LoadDTO {

    private Long id;
    private String title;
    private String content;
    private String password;
    private LocalDateTime time;
    private String weather;

    public LoadDTO(Long id, String title, String content, String password) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.password = password;
    }
}
