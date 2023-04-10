package jpabook.jpashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
public class Address {
    private String city;
    private String street;
    private String zipcode;

    /* JPA에서 기본 생성자를 필요로 함 (리플렉션, 프록시 등)
    *     -> protected 생성자로 할것
    */
    protected Address() { }
}
