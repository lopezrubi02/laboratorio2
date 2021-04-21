package com.example.laboratorio2.controller;

import com.example.laboratorio2.entity.ActividadEntity;
import com.example.laboratorio2.entity.AreaEntity;
import com.example.laboratorio2.entity.ProyectoEntity;
import com.example.laboratorio2.entity.UsuarioEntity;
import com.example.laboratorio2.repository.ActividadRepository;
import com.example.laboratorio2.repository.ProyectoRepository;
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
public class ActividadController {

    @Autowired
    ActividadRepository actividadRepository;

    @Autowired
    ProyectoRepository proyectoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/actividad/agregar")
    public String agregarActividad(Model model,@RequestParam("idproyectoactual") int idProyecto){

        System.out.printf(String.valueOf(idProyecto));
        Optional<ProyectoEntity> proyectoOpt = proyectoRepository.findById(idProyecto);
        if(proyectoOpt.isPresent()){
            ProyectoEntity proyecto = proyectoOpt.get();
            model.addAttribute("proyecto",proyecto);
            model.addAttribute("listaUsuarios",usuarioRepository.findAll());
            model.addAttribute("idproyectoactual",idProyecto);

            return "/actividad/crear";
        }else{
            return "redirect:/proyecto/listar";
        }

    }

    @PostMapping("actividad/guardar")
    public String guardarActividad(ActividadEntity actividad,
                                   RedirectAttributes attr){
        List<ActividadEntity> listaActividades =actividadRepository.findAll();

        Boolean existeActividad = false;

        for(ActividadEntity actividadX: listaActividades){
            if(actividadX.getIdactividad() == actividad.getIdactividad()) {
                existeActividad = true;
            }
        }
        if(existeActividad){
            attr.addFlashAttribute("Actividad editada exitosamente");
        }else{
            attr.addFlashAttribute("Actividad agregada exitosamente");
        }
        System.out.println(actividad.getIdproyecto());
        System.out.println(actividad.getDescripcion());
        System.out.println(actividad.getNombreactividad());
        System.out.println(actividad.getUsuario_owner());
        System.out.println(actividad.getEstado());
        System.out.println(actividad.getPeso());
        System.out.println(actividad.getIdactividad());
        actividadRepository.save(actividad);
        return "redirect:/proyecto/listar";
    }

    @GetMapping("/actividad/editar")
    public String editarActividad(@RequestParam("id") int id, Model model){

        Optional<ActividadEntity> actividadOpt = actividadRepository.findById(id);
        //Optional<ProyectoEntity> proyectoOpt = proyectoRepository.findById(id);

        if(actividadOpt.isPresent()){
            ActividadEntity actividad = actividadOpt.get();
           //ProyectoEntity proyecto = proyectoOpt.get();

            model.addAttribute("actividad",actividad);
            model.addAttribute("idProyectoactual",actividad.getIdproyecto());
            model.addAttribute("listaUsuarios",usuarioRepository.findAll());
            return "/actividad/editarActividad";
        }else{
            return "redirect:/proyecto/editarProyecto?id=" + id ;
        }
    }

    @GetMapping("/actividad/borrar")
    public String borrarActividad(@RequestParam("id") int id, Model model,
                                  RedirectAttributes attr){
        Optional<ActividadEntity> actividadOpt = actividadRepository.findById(id);
        if(actividadOpt.isPresent()){
            actividadRepository.deleteById(id);
            attr.addFlashAttribute("msg","Area borrada exitosamente");
        }
        return "redirect:/actividad/editar?id=" + id ;
    }

}
