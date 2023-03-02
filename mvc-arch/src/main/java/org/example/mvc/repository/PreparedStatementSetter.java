package org.example.mvc.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedStatementSetter {
    void setter(PreparedStatement pstmt) throws SQLException;
}
