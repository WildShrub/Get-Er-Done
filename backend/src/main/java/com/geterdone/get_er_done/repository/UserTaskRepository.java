package com.geterdone.get_er_done.repository;

import org.springframework.dao.EmptyResultDataAccessException;
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
        System.out.println("UserTaskRepository: Finding existing tasks for user: " + username);                                     //for testing
        String sql = """
            SELECT username, tasks_json
            FROM usertasklists
            WHERE username = ?
        """;

        System.out.println("UserTaskRepository: Executing SQL query to find tasks for user: " + username);                                     //for testing
        try {
            return jdbc.queryForObject(
                sql,
                (rs, rowNum) -> new UserTask(
                    rs.getString("username"),
                    rs.getString("tasks_json")
                ),
                username
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void save(UserTask tasks) {
        String sql = """
            INSERT INTO userTaskList (username, tasks_json)
            VALUES (?, ?)
            ON DUPLICATE KEY UPDATE tasks_json = VALUES(tasks_json)
        """;

        System.out.println("UserTaskRepository: Saving tasks for user: " + tasks.getUsername());                                     //for testing
        jdbc.update(sql, tasks.getUsername(), tasks.getTasksJson());
        System.out.println("UserTaskRepository: Tasks saved for user: " + tasks.getUsername());                                     //for testing
    }
}
