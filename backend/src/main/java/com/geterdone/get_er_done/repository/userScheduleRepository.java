package com.geterdone.get_er_done.repository;

import com.geterdone.get_er_done.model.userSchedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserScheduleRepository {

    private final JdbcTemplate jdbc;

    public UserScheduleRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<userSchedule> getUserSchedulesByUserId(int userId) {
        String sql = "SELECT * FROM user_schedules WHERE user_id = ?";
        return jdbc.query(sql, new Object[]{userId}, (rs, rowNum) -> {
            userSchedule schedule = new userSchedule();
            schedule.setId(rs.getInt("id"));
            schedule.setUserId(rs.getInt("user_id"));
            schedule.setScheduleData(rs.getString("schedule_data"));
            // Map other fields as necessary
            return schedule;
        });
    }
}
