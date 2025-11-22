package com.iesvdm.crud_jdbc_project.dao;


import com.iesvdm.crud_jdbc_project.model.User;

import java.util.List;

public interface UserDAO {

    User create(User user);

    /*
    *
    * @param username único en bbdd
    * @return User con el username y password
     */
    User findByUsername(String username);

    List<User> findAllUsers();


}
