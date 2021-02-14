package com.github.iryabov.achievements

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class UserService(val jdbc: JdbcTemplate) {
    fun loadUser(email: String, name: String, avatar: String) {
        val count: Int = jdbc.queryForObject("select count(*) from achievements.member m where m.email = ?", Int::class.java, email)
        if (count > 0) {
            jdbc.update("update achievements.member set name = ?, avatar = ? where email = ?", name, avatar, email)
        } else {
            jdbc.update("insert into achievements.member (email, name, avatar) values (?, ?, ?)", email, name, avatar)
        }
    }
}