package com.example.restapi.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.validation.constraints.NotEmpty

@Entity
class Member(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    val id: Long? = null,

    @Column(name = "member_name")
    @NotEmpty
    var name: String? = null,

    @Embedded
    var address: Address? = null,

    // @JsonIgnore -> 엔티티에 이런 로직이 들어가는 것은 좋지 않다
    @OneToMany(mappedBy = "member")
    var orders: List<Order> = arrayListOf()
)