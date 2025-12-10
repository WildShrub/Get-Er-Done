package com.geterdone.get_er_done.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.geterdone.get_er_done.model.GroupSchedule;

@Repository
public class GroupScheduleRepository {
    
    private final JdbcTemplate jdbc;
    //Create the repository and connect it to a database
    public GroupScheduleRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public GroupSchedule findByGroupID(String groupID) {
        String sql = "SELECT groupID, groupSchedule FROM groupSchedules WHERE groupID = ?";


        return jdbc.queryForObject(
            sql,
            (rs,rowNum) -> new GroupSchedule(
                rs.getString("groupID"),
                rs.getString("groupSchedule")
            ),
            groupID
        );
    }

    public void save(GroupSchedule schedule) {
        String sql = """
            INSERT INTO groupSchedules (groupID, groupSchedule)
            VALUES (?,?)
            ON DUPLICATE KEY UPDATE groupSchedule = VALUES(groupSchedule)
        """;
        //send the query
        jdbc.update(sql, schedule.getGroupID(), schedule.getGroupScheduleJson());
    }
}
