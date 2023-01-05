package com.example.thingsFlow.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @ToString
@RequiredArgsConstructor
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private String password;

    @CreationTimestamp
    private LocalDateTime time;

    @Builder
    public Board(String title, String content, String password) {
        this.title = title;
        this.content = content;
        this.password = password;
    }
}
