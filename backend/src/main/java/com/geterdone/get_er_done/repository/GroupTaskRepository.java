package com.geterdone.get_er_done.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.geterdone.get_er_done.model.GroupTask;

@Repository
public class GroupTaskRepository {
    
    private final JdbcTemplate jdbc;

    public GroupTaskRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public GroupTask findByGroupID(String groupID) {
        String sql = "SELECT groupID, tasksJson FROM groupTask WHERE groupID = ?";

        return jdbc.queryForObject(
            sql,
            (rs, rowNum) -> new GroupTask(
                rs.getString("groupID"),
                rs.getString("tasksJson")
            ),
            groupID
        );
    }

    public void save(GroupTask tasks) {
        String sql = """
            INSERT INTO userTask (groupID, tasksJson)
            VALUES (?, ?)
            ON DUPLICATE KEY UPDATE tasksJson = VALUES(tasksJson)
        """;

        jdbc.update(sql, tasks.getGroupID(), tasks.getTasksJson());
    }
}
