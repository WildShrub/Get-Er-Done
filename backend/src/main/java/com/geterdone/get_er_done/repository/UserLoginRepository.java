package com.geterdone.get_er_done.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.geterdone.get_er_done.model.UserLogin;

@Repository
public class UserLoginRepository {

    private final JdbcTemplate jdbc;

    public UserLoginRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // Get user by username
    public UserLogin findByUsername(String username) {
        String sql = "SELECT username, passwordHash FROM userLogin WHERE username = ?";

        return jdbc.queryForObject(
            sql,
            (rs, rowNum) -> new UserLogin(
                rs.getString("username"),
                rs.getString("passwordHash")
            ),
            username
        );
    }

    // Insert or update user
    public void save(UserLogin login) {
        String sql = """
            INSERT INTO userLogin (username, passwordHash)
            VALUES (?, ?)
            ON DUPLICATE KEY UPDATE passwordHash = VALUES(passwordHash)
        """;

        jdbc.update(sql, login.getUsername(), login.getPasswordHash());
    }
}