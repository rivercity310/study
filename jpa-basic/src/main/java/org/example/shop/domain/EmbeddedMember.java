package org.example.shop.domain;

import javax.persistence.*;

@Entity
public class EmbeddedMember {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Embedded
    private Period workPeriod;

    @Embedded
    private Address homeAddress;
}
