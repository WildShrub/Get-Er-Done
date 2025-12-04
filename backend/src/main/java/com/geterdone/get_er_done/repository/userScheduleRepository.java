package com.geterdone.get_er_done.repository;

import com.geterdone.get_er_done.model.UserSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserScheduleRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public UserSchedule get(String username) {
        String sql = "SELECT * FROM userSchedules WHERE username = ?";

        return jdbc.queryForObject(sql, new Object[]{username}, (rs, rowNum) ->
            new UserSchedule(
                rs.getString("username"),
                rs.getString("userSchedule")
            )
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