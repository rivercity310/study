package travelator

import java.util.*

// 간단한 값 타입  --> data class를 사용하여 equals, hashCode, toString 생략 가능
data class EmailAddress_kt(val localPart: String, val domain: String) {
    override fun toString(): String { return "$localPart@$domain" }

    // Kotlin 가변 인자 함수 (vararg)
    fun test(vararg a: Int) {
        for (v in a) print("$v ")
        println()
    }

    companion object {
        // 문자열을 파싱해 EmailAddress를 만드는 정적 팩터리 메서드 -> 주 생성자를 호출
        @JvmStatic
        fun parse(value: String): EmailAddress_kt {
            val atIndex = value.lastIndexOf('@')
            require(!(atIndex < 1 || atIndex == value.length - 1)) { "EmailAddress must be two parts separated by @" }
            return EmailAddress_kt(value.substring(0, atIndex), value.substring(atIndex + 1))
        }
    }

    /*
   override fun equals(o: Any?): Boolean {
       if (this === o) return true
       if (o == null || javaClass != o.javaClass) return false     // getClass() -> javaClass
       val that = o as EmailAddress_kt
       return localPart == that.localPart && domain == that.domain
   }

   override fun hashCode(): Int {
       return Objects.hash(localPart, domain)
   }
   */
}