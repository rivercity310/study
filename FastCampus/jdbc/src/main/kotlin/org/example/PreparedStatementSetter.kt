package org.example

import java.sql.PreparedStatement

@FunctionalInterface
interface PreparedStatementSetter {
    fun setter(pstmt: PreparedStatement)
}