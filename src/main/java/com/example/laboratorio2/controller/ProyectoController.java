package com.example.laboratorio2.controller;

import com.example.laboratorio2.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProyectoController {

    @Autowired
    ProyectoRepository proyectoRepository;

    @GetMapping("/proyecto")
    public String proyectoLista(Model model){

        model.addAttribute("listaProyectos",proyectoRepository.findAll());

        return "listaProyectos";
    }
}
