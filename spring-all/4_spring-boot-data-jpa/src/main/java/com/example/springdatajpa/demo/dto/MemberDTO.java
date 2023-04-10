package com.example.springdatajpa.demo.dto;

import com.example.springdatajpa.demo.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDTO {
    private Long id;
    private String userName;
    private String teamName;

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.userName = member.getUserName();
        this.teamName = null;
    }
}
