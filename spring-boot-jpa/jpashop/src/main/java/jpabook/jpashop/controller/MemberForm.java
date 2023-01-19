package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class MemberForm {
    /* null, 빈문자열 모두 검사 */
    @NotBlank(message = "회원 이름은 필수입니다.")
    private String name;
    private String city;
    private String zipcode;
    private String street;
}
