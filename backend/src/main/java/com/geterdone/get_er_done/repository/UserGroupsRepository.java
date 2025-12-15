package com.geterdone.get_er_done.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.geterdone.get_er_done.model.UserGroups;

@Repository
public class UserGroupsRepository {

    private final JdbcTemplate jdbc;

    public UserGroupsRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public UserGroups findByUsername(String username) {
        String sql = "SELECT username, groupsID FROM UserGroups WHERE username = ?";

        return jdbc.queryForObject(
            sql,
            (rs, rowNum) -> new UserGroups(
                rs.getString("username"),
                rs.getObject("groupsID", java.util.List.class)
            ),
            username
        );
    }

    public void save(UserGroups userGroups) {
        String sql = """
            INSERT INTO UserGroups (username, groupIDs)
            VALUES (?, ?)
            ON DUPLICATE KEY UPDATE groupIDs = VALUES(groupIDs)
        """;

        jdbc.update(sql, userGroups.getUsername(), userGroups.getGroupsID());
    }
}