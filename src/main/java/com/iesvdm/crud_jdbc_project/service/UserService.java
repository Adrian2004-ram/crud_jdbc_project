package com.iesvdm.crud_jdbc_project.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.iesvdm.crud_jdbc_project.dao.UserDAO;
import com.iesvdm.crud_jdbc_project.model.User;
import com.iesvdm.crud_jdbc_project.util.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private HashUtil hashUtil;

    public boolean find(User user) throws NoSuchAlgorithmException {


        // Buscamos el usuario en la base de datos
        User found = userDAO.findByUsername(user.getUsername());

        // Hasheamos la contraseña proporcionada
        String pass = hashUtil.hashPassword(user.getPassword());

        // Comparamos la contraseña hasheada con la almacenada
        if (found == null) {
            return false;
        } else if (found.getPassword().equals(pass)) {
            return true;
        }
        return false;
    }

    public List<User> listAllUsers() {

    List<User> users = userDAO.findAllUsers();

    return users;

    }





}
