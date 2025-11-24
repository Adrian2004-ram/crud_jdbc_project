package com.iesvdm.crud_jdbc_project.controller;

import com.iesvdm.crud_jdbc_project.dto.PiramideDTO;
import com.iesvdm.crud_jdbc_project.model.User;
import com.iesvdm.crud_jdbc_project.service.UserService;
import jakarta.servlet.http.HttpSession;
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

        return "/inicio"; // nombre de la vista (archivo HTML)

    }

    // Procesar el formulario de inicio de sesión
    @PostMapping("/inicio")
    public String acedeUsuario(Model model, HttpSession session, @ModelAttribute("user") User user) throws NoSuchAlgorithmException {

        // Guardar el usuario en sesión
        session.setAttribute("usuarioLogueado", user);

        // Aquí puedes agregar la lógica

        String txt = "";

        try {

            boolean esta = userService.find(user);

            model.addAttribute("user", user.getUsername());

            if (user.getUsername().equals("admin") && esta) {
                return "redirect:/accesoadmin";
            } else if(esta){
                return "redirect:/acceso";
            }

            } catch(DataAccessException _) {

            txt = "Usuario o contraseña incorrectos";

            model.addAttribute("error", txt);

            }
        return "inicio";
    }

    // Mostrar la página de acceso para usuarios normales
    @GetMapping("/acceso")
    public String acceso(Model model, HttpSession session) {

        // Traigo al user que inicio sesion
        User usuario = (User) session.getAttribute("usuarioLogueado");
        model.addAttribute("user", usuario);

        return "acceso";

    }

    @PostMapping("/acceso")
    public String accesoAPiramide(Model model) {

        return "redirect:/piramide";

    }


    // Mostrar la página de acceso para admin
    @GetMapping("/accesoadmin")
    public String gestionAdminGEt(Model model) {

        return "accesoadmin";

    }

    @PostMapping("/accesoadmin")
    public String gestionAdminPOST(Model model) {

        return "redirect:/gestion";

    }

    @GetMapping("/gestion")
    public String gestionAdmin(Model model) {

        List<User> users = userService.listAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());

        return "gestion";

    }

    // Mostrar la página de gestión para admin
    @PostMapping("/gestion")
    public String altaGestion(Model model, @ModelAttribute("user") User user, @ModelAttribute("users") List<User> users) throws NoSuchAlgorithmException {

        userService.createUser(user);

        return "redirect:/gestion";

    }
}
