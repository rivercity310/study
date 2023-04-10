package com.example.restapi.domain

import jakarta.persistence.*

@Embeddable
class Address(
    var city: String? = null,
    var street: String? = null,
    var zipcode: String? = null
)