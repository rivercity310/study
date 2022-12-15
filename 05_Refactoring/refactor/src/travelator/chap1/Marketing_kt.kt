package travelator.chap1

class Marketing_kt {
    fun isHotmailAddress_kt(address: EmailAddress_java): Boolean {
        // .domain으로 자바 프로퍼티에 접근 (getDomain() 호출)
        return address.domain.equals("hotmail.com", ignoreCase = true)
    }
}
