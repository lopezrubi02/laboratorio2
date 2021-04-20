package com.example.laboratorio2.controller;

import com.example.laboratorio2.entity.ActividadEntity;
import com.example.laboratorio2.entity.ProyectoEntity;
import com.example.laboratorio2.repository.ActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ActividadController {

    @Autowired
    ActividadRepository actividadRepository;


    @GetMapping("/actividad/editar")
    public String editarActividad(@RequestParam("id") int id, Model model){

        Optional<ActividadEntity> actividadOpt = actividadRepository.findById(id);

        if(actividadOpt.isPresent()){
            ActividadEntity actividad = actividadOpt.get();
            model.addAttribute("actividad",actividad);

            return "actividad/editarActividad";
        }else{
            return "redirect:/actividad/editar?id=" + id ;
        }
    }

    @GetMapping("/actividad/borrar")
    public String borrarActividad(@RequestParam("id") int id, Model model){
        Optional<ActividadEntity> actividadOpt = actividadRepository.findById(id);
        if(actividadOpt.isPresent()){
            ActividadEntity actividad = actividadOpt.get();
            actividadRepository.deleteById(id);
        }
        return "redirect:/actividad/editar?id=" + id ;


    }

}
