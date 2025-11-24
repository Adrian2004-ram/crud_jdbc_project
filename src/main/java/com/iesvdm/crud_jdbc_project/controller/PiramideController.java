package com.iesvdm.crud_jdbc_project.controller;

import com.iesvdm.crud_jdbc_project.dto.PiramideDTO;
import com.iesvdm.crud_jdbc_project.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PiramideController {


    // Mostrar la página de crear piramides
    @GetMapping("/piramide")
    public String piramideGET(@ModelAttribute("piramideDTO") PiramideDTO piramideDTO, Model model) {

        return "/piramide"; // nombre de la vista (archivo HTML)

    }

    // Post info de altura
    @PostMapping("/piramide")
    public String piramideGatitos(Model model, HttpSession session, @ModelAttribute("piramideDTO") PiramideDTO piramideDTO) {

        session.setAttribute("piramideDTOget", piramideDTO);

        return "redirect:/gatitos";

    }

    // Obten info de altura
    @GetMapping("/gatitos")
    public String gatitos(Model model, HttpSession session) {

        PiramideDTO piramideDTO = (PiramideDTO) session.getAttribute("piramideDTOget");
        model.addAttribute("piramideDTO", piramideDTO);

        return "gatitos"; // nombre de la vista (archivo HTML)

    }

}
