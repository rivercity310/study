package com.example.recruitBoard.vo

data class BoardVO (
    var seq: Int? = null,
    var id: Int? = null,
    var reward: Int? = null,
    var position: String? = null,
    var content: String? = null,
    var tech: String? = null,
    var company: CompanyVO? = null,
    var applicants: List<UserVO>? = null,
    var others: List<BoardVO>? = null
)