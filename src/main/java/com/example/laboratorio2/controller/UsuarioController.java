package com.example.laboratorio2.controller;


import com.example.laboratorio2.entity.UsuarioEntity;
import com.example.laboratorio2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/usuario/listar")
    public String usuarioList(Model model) {
        System.out.println("Llegamos aqui");
        model.addAttribute("usuarioList", usuarioRepository.findAll());
        return "usuario/lista";
    }

    @GetMapping("/usuario/agregar")
    public String usuarioNew() {
        return "usuario/crear";
    }


}
