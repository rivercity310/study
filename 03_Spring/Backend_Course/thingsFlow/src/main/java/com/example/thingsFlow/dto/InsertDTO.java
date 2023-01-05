package com.example.thingsFlow.dto;

import lombok.*;

@NoArgsConstructor
@Data
public class InsertDTO {
    private String title;
    private String content;
    private String password;

    @Builder
    public InsertDTO(String title, String content, String password) {
        this.title = title;
        this.content = content;
        this.password = password;
    }
}
