package org.example

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator

class UserDaoTest {
    @BeforeEach
    fun setUp() {
        val populator = ResourceDatabasePopulator()
        populator.addScript(ClassPathResource("db_schema.sql"))
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource())
    }

    @Test
    fun createTest() {
        val userDao = UserDao()
        userDao.create(User("wizard", "11", "이름", "이메일"))

        val user: User = userDao.findByUserId("wizard") ?: fail("찾을 수 없음")
        println("user = $user")

        assertThat(user).isEqualTo(User("wizard", "11", "이름", "이메일"))
    }
}