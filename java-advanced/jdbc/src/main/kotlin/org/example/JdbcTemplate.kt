package org.example

import java.sql.Connection
import java.sql.PreparedStatement

class JdbcTemplate {
    private lateinit var conn: Connection
    private lateinit var pstmt: PreparedStatement

    internal fun executeUpdate(sql: String, pss: PreparedStatementSetter) {
        try {
            conn = ConnectionManager.getConnection()
            pstmt = conn.prepareStatement(sql)
            pss.setter(pstmt)

            pstmt.executeUpdate()
        }

        finally {
            pstmt.close()
            conn.close()
        }
    }

    internal fun findById(sql: String, pss: PreparedStatementSetter): User? {
        try {
            conn = ConnectionManager.getConnection()
            pstmt = conn.prepareStatement(sql)
            pss.setter(pstmt)

            val rs = pstmt.executeQuery()

            if (rs.next()) {
                return User(
                    userId = rs.getString("userId"),
                    password = rs.getString("password"),
                    name = rs.getString("name"),
                    email = rs.getString("email")
                )
            }
        }

        finally {
            pstmt.close()
            conn.close()
        }

        return null
    }
}