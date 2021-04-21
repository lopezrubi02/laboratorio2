package com.example.laboratorio2.controller;

import com.example.laboratorio2.entity.AreaEntity;
import com.example.laboratorio2.repository.AreaRepository;
import com.example.laboratorio2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class AreaController {

    @Autowired
    AreaRepository areaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;



    @GetMapping("/area/listar")
    public String areaList(Model model) {
        model.addAttribute("listaArea",areaRepository.findAll());
        return "area/listar";
    }

    @GetMapping("/area/agregar")
    public String areaNew(){
        return "area/crear";
    }
//comentario prueba
    @PostMapping("/area/guardar")
    public String areaSave(AreaEntity area, RedirectAttributes attr){
        System.out.println("nombrearea" + area.getNombrearea());
        areaRepository.save(area);
        Optional<AreaEntity> areaOpt = areaRepository.findById(area.getIdarea());
        if(areaOpt.isPresent()){
            attr.addFlashAttribute("msg","Area editada exitosamente");
        }else{
            attr.addFlashAttribute("msg","Area creada exitosamente");
        }
        return "redirect:/area/listar";
    }

    @GetMapping("/area/editar")
    public String editArea(@RequestParam("id") int id, Model model){
        Optional<AreaEntity> areaOpt = areaRepository.findById(id);
        if(areaOpt.isPresent()){
            AreaEntity areaEntity = areaOpt.get();
            model.addAttribute("listaUsuarios", usuarioRepository.listarUsuariosporArea(id));
            model.addAttribute("area",areaEntity);
            return "area/edit";
        }else{
            return "redirect:/area/listar";
        }
    }

    @GetMapping("/area/borrar")
    public String deleteArea(@RequestParam("id") int id, RedirectAttributes attr){
        Optional<AreaEntity> areaOpt = areaRepository.findById(id);
        if(areaOpt.isPresent()) {
            areaRepository.deleteById(id);
            attr.addFlashAttribute("msg","Area borrada exitosamente");
        }
        return "redirect:/area/listar";

    }


}
