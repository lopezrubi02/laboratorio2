package com.example.laboratorio2.controller;

import com.example.laboratorio2.entity.ActividadEntity;
import com.example.laboratorio2.entity.ProyectoEntity;
import com.example.laboratorio2.repository.ActividadRepository;
import com.example.laboratorio2.repository.ProyectoRepository;
import com.example.laboratorio2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ProyectoController {

    @Autowired
    ProyectoRepository proyectoRepository;

    @Autowired
    ActividadRepository actividadRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/proyecto/listar")
    public String listarProyecto(Model model){
        model.addAttribute("listaProyectos",proyectoRepository.findAll());
        return "/proyecto/listar";
    }

    @GetMapping("/proyecto/editar")
    public String editarProyecto(@RequestParam("id") int id, Model model){
        Optional<ProyectoEntity> proyectoOpt = proyectoRepository.findById(id);
        //Optional<ActividadEntity> actvidadOpt = actividadRepository.findById(id);

        if(proyectoOpt.isPresent()){
            ProyectoEntity proyecto = proyectoOpt.get();
            model.addAttribute("proyecto",proyecto);

            model.addAttribute("listaActividades",actividadRepository.listaDeActividadesPorProyecto(id));
            return "/actividad/editarActividad";
        }else{
            return "redirect:/proyecto/listar";
        }
    }

    @GetMapping("/proyecto/agregar")
    public String crearProyecto(Model model){
        model.addAttribute("listaUsuarios",usuarioRepository.findAll());
        return "proyecto/nuevoProyecto";
    }

    @PostMapping("/proyecto/guardar")
    public String guardarProyecto(ProyectoEntity proyecto){
        proyectoRepository.save(proyecto);
        return "redirect:/proyecto/listar";
    }


}
