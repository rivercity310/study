package com.example.restapi.domain.item

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("book")
class Book(
    var author: String? = null,
    var isbn: String? = null
): Item()