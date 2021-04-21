package com.example.laboratorio2.controller;

import com.example.laboratorio2.entity.ActividadEntity;
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
    public String editarProyecto(@RequestParam("idproyecto") int id, Model model){
        Optional<ProyectoEntity> proyectoOpt = proyectoRepository.findById(id);
        //Optional<ActividadEntity> actvidadOpt = actividadRepository.findById(id);

        if(proyectoOpt.isPresent()){
            ProyectoEntity proyecto = proyectoOpt.get();
            model.addAttribute("proyecto",proyecto);

            model.addAttribute("listaActividades",actividadRepository.listaDeActividadesPorProyecto(id));
            model.addAttribute("listaUsuarios",usuarioRepository.findAll());
            model.addAttribute("pesoActividades",actividadRepository.valorSumaTotal(id));
            model.addAttribute("pesoActividadesFinalizadas",actividadRepository.valorSumaFinalizado(id));
            model.addAttribute("idproyectoactual",id);
            //System.out.println(actividadRepository.valorSumaTotal(id));
            //System.out.println(actividadRepository.valorSumaFinalizado(id));
            return "/proyecto/editarProyecto";
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
    public String guardarProyecto(ProyectoEntity proyecto,RedirectAttributes attr){

        List<ProyectoEntity> listaProyectos = proyectoRepository.findAll();
        Boolean existeProyecto = false;
        for(ProyectoEntity proyectoX : listaProyectos){
            if(proyectoX.getIdproyecto() == proyecto.getIdproyecto()){
                existeProyecto = true;
            }
        }

        if(existeProyecto){
            attr.addFlashAttribute("msg","Proyecto actualizado exitosamente");
        }else{
            attr.addFlashAttribute("msg", "Proyecto creado exitosamente");
        }
        proyectoRepository.save(proyecto);
        return "redirect:/proyecto/listar";
    }


    @GetMapping("/proyecto/borrar")
    public String borrarProyecto(Model model,
                                @RequestParam("idproyecto") int id,
                                RedirectAttributes attr) {

        Optional<ProyectoEntity> optShipper = proyectoRepository.findById(id);
        if (optShipper.isPresent()) {
            proyectoRepository.deleteById(id);
            attr.addFlashAttribute("msg","Proyecto borrado exitosamente");
        }
        return "redirect:/proyecto/listar";

    }


}
