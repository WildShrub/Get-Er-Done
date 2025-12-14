package com.geterdone.get_er_done.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.geterdone.get_er_done.model.GroupProfile;

@Repository
public class GroupProfileRepository {
    private final JdbcTemplate jdbc;

    public GroupProfileRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public GroupProfile findByGroupID(String groupID) {         //not sure if this is right
        String sql = "SELECT groupID, groupName FROM groupProfile WHERE groupID = ?";

        return jdbc.queryForObject(
            sql,
            (rs, rowNum) -> new GroupProfile(
                rs.getString("groupID"),
                rs.getString("groupName")
            ),
            groupID
        );
    }
    
    //might need one to produce the next possible groupID
    public int createGroupProfile(String name) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO example (value) VALUES (?)",
                Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, name);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }



    public void save(GroupProfile groupProfile) {
        String sql = """
            INSERT INTO userTask (groupID, groupName)
            VALUES (?, ?)
            ON DUPLICATE KEY UPDATE groupName = VALUES(groupName)
        """;

        jdbc.update(sql, groupProfile.getGroupID(), groupProfile.getGroupName()); //not sure if this will work
    }
}
