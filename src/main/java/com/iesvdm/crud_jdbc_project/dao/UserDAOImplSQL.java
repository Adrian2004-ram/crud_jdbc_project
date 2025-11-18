package com.iesvdm.crud_jdbc_project.dao;

import com.iesvdm.crud_jdbc_project.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class UserDAOImplSQL implements UserDAO {

    private JdbcTemplate jdbcTemplate;

    //INYECT DE JdbcTemplate
    public UserDAOImplSQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User create(User user) {

        String sql = """
                INSERT INTO user (username, password)
                VALUES (?, ?)
                """;

        String[] ids = {"id"};

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {

            PreparedStatement ps = con.prepareStatement(sql, ids);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());

            return ps;

        }, keyHolder);

        user.setId(keyHolder
                .getKey()
                .longValue());

        return null;
    }

    @Override
    public User findByUsername(String username) {

        String sql = """
                SELECT *
                FROM user
                WHERE username = ?
                """;

        User user = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> User.builder()
                                                                        .id(rs.getLong("id"))
                                                                        .username(rs.getString("username"))
                                                                        .password(rs.getString("password"))
                                                                        .build(), username);



        return null;
    }


}
