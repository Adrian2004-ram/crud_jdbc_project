package com.iesvdm.crud_jdbc_project;

import com.iesvdm.crud_jdbc_project.dao.UserDAO;
import com.iesvdm.crud_jdbc_project.model.User;
import com.iesvdm.crud_jdbc_project.util.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class CrudJdbcProjectApplicationTests {

    @Autowired
    UserDAO userDAO;

    @Autowired
    HashUtil hashUtil;

    @Test
    void contextLoads() {
    }

    @Test
    void createUserAdminTest() {

        String username = "jose";
        String password = "1234";

        String passawordHashed = null;

        try {

            passawordHashed =  hashUtil.hashPassword(password);

            log.info("Password hashed: " + passawordHashed);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        User user = User.builder().username(username).password(passawordHashed).build();

        userDAO.create(user);

        Assertions.assertTrue(user.getId()>0);

    }

}
