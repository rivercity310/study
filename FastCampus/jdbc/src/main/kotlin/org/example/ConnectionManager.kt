package org.example

import com.zaxxer.hikari.HikariDataSource
import java.sql.Connection
import javax.sql.DataSource


class ConnectionManager {
    companion object {
        private const val dbName = "org.postgresql.Driver"
        private const val url = "jdbc:postgresql://localhost:5432/jdbc_test"
        private const val username = "postgres"
        private const val password = "1111"
        private const val maxPoolSize = 40
        private var ds: DataSource? = null

        internal fun getDataSource(): DataSource {
            val hikariDataSource = HikariDataSource()
            hikariDataSource.driverClassName = dbName
            hikariDataSource.jdbcUrl = url
            hikariDataSource.username = username
            hikariDataSource.password = password
            hikariDataSource.maximumPoolSize = maxPoolSize
            hikariDataSource.minimumIdle = maxPoolSize

            return hikariDataSource
        }

        internal fun getConnection(): Connection {
            if (ds == null) ds = getDataSource()
            return ds!!.connection
        }
    }
}