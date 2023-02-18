package org.example

import java.sql.PreparedStatement

class UserDao {
    companion object {
        private val jdbcTemplate = JdbcTemplate()
        private const val USER_CREATE = "INSERT INTO USERS VALUES(?, ?, ?, ?)"
        private const val USER_FIND = "SELECT * FROM USERS WHERE userId = ?"
    }

    internal fun create(user: User) {
        jdbcTemplate.executeUpdate(USER_CREATE, object : PreparedStatementSetter {
            override fun setter(pstmt: PreparedStatement) {
                pstmt.setString(1, user.userId)
                pstmt.setString(2, user.password)
                pstmt.setString(3, user.name)
                pstmt.setString(4, user.email)
            }
        })
    }

    internal fun findByUserId(uId: String): User? {
        return jdbcTemplate.findById(USER_FIND, object : PreparedStatementSetter {
            override fun setter(pstmt: PreparedStatement) {
                pstmt.setString(1, uId)
            }
        })
    }
}
