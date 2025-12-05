package com.geterdone.get_er_done.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.geterdone.get_er_done.model.UserTask;

@Repository
public class UserTaskRepository {

    private final JdbcTemplate jdbc;

    public UserTaskRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public UserTask findByUsername(String username) {
        String sql = "SELECT username, tasksJson FROM userTask WHERE username = ?";

        return jdbc.queryForObject(
            sql,
            (rs, rowNum) -> new UserTask(
                rs.getString("username"),
                rs.getString("tasksJson")
            ),
            username
        );
    }

    public void save(UserTask tasks) {
        String sql = """
            INSERT INTO userTask (username, tasksJson)
            VALUES (?, ?)
            ON DUPLICATE KEY UPDATE tasksJson = VALUES(tasksJson)
        """;

        jdbc.update(sql, tasks.getUsername(), tasks.getTasksJson());
    }
}