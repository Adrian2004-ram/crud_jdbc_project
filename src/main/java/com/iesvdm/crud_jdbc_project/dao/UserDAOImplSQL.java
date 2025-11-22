package com.iesvdm.crud_jdbc_project.dao;

import com.iesvdm.crud_jdbc_project.model.User;
import com.iesvdm.crud_jdbc_project.util.HashUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class UserDAOImplSQL implements UserDAO {

    private JdbcTemplate jdbcTemplate;

    private HashUtil hashUtil;

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

        return user;

    }

    @Override
    public List<User> findAllUsers() {

        String sql = """
                SELECT *
                FROM user
                """;

        List<User> users = jdbcTemplate.query(sql, (rs, rowNum) -> User.builder()
                                                                .id(rs.getLong("id"))
                                                                .username(rs.getString("username"))
                                                                .password(rs.getString("password"))
                                                                .build());

        return users;

    }
}
