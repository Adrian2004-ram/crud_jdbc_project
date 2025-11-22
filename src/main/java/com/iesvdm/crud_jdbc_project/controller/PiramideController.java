package com.iesvdm.crud_jdbc_project.controller;

import com.iesvdm.crud_jdbc_project.dto.PiramideDTO;
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

        //    model.addAttribute("piramideDTO", piramideDTO);

        return "piramide"; // nombre de la vista (archivo HTML)

    }

    // Post info de altura
    @PostMapping("/piramide")
    public String piramideGatitos(Model model, @ModelAttribute("piramideDTO") PiramideDTO piramideDTO) {

        //   model.addAttribute("piramideDTO", piramideDTO);

        return "gatitos"; // nombre de la vista (archivo HTML)

    }

    // Obten info de altura
    @GetMapping("/gatitos")
    public String gatitos(Model model, @ModelAttribute("piramideDTO") PiramideDTO piramideDTO) {

        //   model.addAttribute("piramideDTO", piramideDTO);

        return "gatitos"; // nombre de la vista (archivo HTML)

    }

}
