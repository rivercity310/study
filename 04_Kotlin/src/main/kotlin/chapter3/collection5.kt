package chapter3

// 코드 다듬기: 로컬 함수와 확장

class User(val id: Int, val name: String, val address: String)

// 1. 검증 로직을 로컬 함수로 들옥하여 코드 중복을 없애는 방법
fun saveUser(user: User) {

    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't Save user ${user.id}: empty $fieldName")
        }
    }

    validate(user.name, "Name")
    validate(user.address, "Address")
}

// 2. 검증 로직을 확장 함수로 -> 확장 함수 안에 로컬 함수로 정의
fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't Save user ${this.id}: empty $fieldName")
        }
    }

    validate(name, "Name")
    validate(address, "Address")
}

fun saveUser2(user: User) {
    user.validateBeforeSave()
}