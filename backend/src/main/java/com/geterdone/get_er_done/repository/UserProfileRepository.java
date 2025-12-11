package com.geterdone.get_er_done.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.geterdone.get_er_done.model.UserProfile;

@Repository
public class UserProfileRepository {

    private final JdbcTemplate jdbc;

    public UserProfileRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public UserProfile findByUsername(String username) {
        String sql = "SELECT username, email, pfp FROM UserProfile WHERE username = ?";

        return jdbc.queryForObject(
            sql,
            (rs, rowNum) -> new UserProfile(
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("pfp")
            ),
            username
        );
    }

    public void save(UserProfile userGroups) {
        String sql = """
            INSERT INTO UserProfile (username, email, pfp)
            VALUES (?, ?, ?)
            ON DUPLICATE KEY UPDATE email = VALUES(email), pfp = VALUES(pfp)
        """;

        jdbc.update(sql, userGroups.getUsername(), userGroups.getEmail(), userGroups.getPfp());
    }
}