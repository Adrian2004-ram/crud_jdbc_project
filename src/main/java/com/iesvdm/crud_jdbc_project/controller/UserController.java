package com.iesvdm.crud_jdbc_project.controller;

import com.iesvdm.crud_jdbc_project.dto.PiramideDTO;
import com.iesvdm.crud_jdbc_project.model.User;
import com.iesvdm.crud_jdbc_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    // Mostrar el formulario de inicio de sesión
    @GetMapping("/inicio")
    public String inicio(@ModelAttribute("user") User user) {

        return "inicio"; // nombre de la vista (archivo HTML)

    }

    // Procesar el formulario de inicio de sesión
    @PostMapping("/inicio")
    public String acedeUsuario(Model model, @ModelAttribute("user") User user) throws NoSuchAlgorithmException {

        // Aquí puedes agregar la lógica

        String txt = "";

        try {

            boolean esta = userService.find(user);

            model.addAttribute("user", user.getUsername());

            if (user.getUsername().equals("admin") && esta) {
                return "accesoadmin";
            } else if(esta){
                return "acceso";
            }

            } catch(DataAccessException _) {

            txt = "Usuario o contraseña incorrectos";

            model.addAttribute("error", txt);

            }
        return "inicio";
    }

    // Mostrar la página de acceso para usuarios normales
    @GetMapping("/acceso")
    public String acceso(Model model) {

        PiramideDTO piramideDTO = new PiramideDTO();
        model.addAttribute("piramideDTO", piramideDTO);

        return "piramide"; // nombre de la vista (archivo HTML)

    }

    // Mostrar la página de acceso para admin
    @GetMapping("/accesoadmin")
    public String gestion(Model model) {

        List<User> users = userService.listAllUsers();
        model.addAttribute("users", users);

        return "gestion";

    }

    @GetMapping("/gestion")
    public String gestion(Model model, @ModelAttribute List<User> users) {

        return "gestion";

    }

    // Mostrar la página de gestión para admin
    @PostMapping("/gestion")
    public String altaGestion(Model model) {

        return "gestion";

    }

}
