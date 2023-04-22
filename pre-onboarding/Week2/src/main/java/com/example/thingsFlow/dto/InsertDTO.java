package com.example.thingsFlow.dto;

import com.example.thingsFlow.entity.Board;
import lombok.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class InsertDTO {
    private String title;
    private String content;
    private String password;
    private String weather;

    @Builder
    public InsertDTO(String title, String content, String password, String weather) {
        this.title = title;
        this.content = content;
        this.password = password;
        this.weather = weather;
    }
}