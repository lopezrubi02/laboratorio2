package com.example.laboratorio2.controller;


import com.example.laboratorio2.entity.UsuarioEntity;
import com.example.laboratorio2.repository.AreaRepository;
import com.example.laboratorio2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AreaRepository areaRepository;

    @GetMapping("/usuario/listar")
    public String usuarioList(Model model) {
        System.out.println("Llegamos aqui");
        model.addAttribute("usuarioList", usuarioRepository.findAll());
        return "usuario/lista";
    }

    @GetMapping("/usuario/agregar")
    public String usuarioNew(Model model) {
        model.addAttribute("listaAreas",areaRepository.findAll());
        return "usuario/crear";
    }
    
    @PostMapping("/usuario/guardar")
    public String usuarioSave(UsuarioEntity usuario){
        System.out.println("El idarea es " + usuario.getIdarea());
        usuarioRepository.save(usuario);
        return "redirect:/usuario/listar";
        
    }
    
    @GetMapping("/usuario/editar")
    public String usuarioEdit(Model model,
                              @RequestParam("correo") String correo){
        Optional<UsuarioEntity> optUsuario = usuarioRepository.findById(correo);
        if(optUsuario.isPresent()){
            UsuarioEntity usuario = optUsuario.get();
            model.addAttribute("usuario",usuario);
            model.addAttribute("listaAreas",areaRepository.findAll());
            return "usuario/editar";
        }else{
            return "redirect:/usuario/lista";
        }
    }



}
