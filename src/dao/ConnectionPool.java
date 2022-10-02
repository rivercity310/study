package dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static DataSource _ds = null;

    public static Connection get() throws NamingException, SQLException {
        if (_ds == null)
            _ds = (DataSource)(new InitialContext()).lookup("java:comp/env/jdbc/mysns");

        return _ds.getConnection();
    }
}
