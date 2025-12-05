package com.geterdone.get_er_done.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.geterdone.get_er_done.model.UserSchedule;

@Repository
public class UserScheduleRepository {

    private final JdbcTemplate jdbc;

    public UserScheduleRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public UserSchedule findByUsername(String username) {
        String sql = "SELECT username, userSchedule FROM userSchedules WHERE username = ?";

        return jdbc.queryForObject(
            sql,
            (rs, rowNum) -> new UserSchedule(
                rs.getString("username"),
                rs.getString("userSchedule")
            ),
            username
        );
    }

    public void save(UserSchedule schedule) {
        String sql = """
            INSERT INTO userSchedules (username, userSchedule)
            VALUES (?, ?)
            ON DUPLICATE KEY UPDATE userSchedule = VALUES(userSchedule)
        """;

        jdbc.update(sql, schedule.getUsername(), schedule.getUserScheduleJson());
    }
}