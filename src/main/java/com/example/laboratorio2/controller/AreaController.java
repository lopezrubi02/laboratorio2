package com.example.laboratorio2.controller;

import com.example.laboratorio2.entity.AreaEntity;
import com.example.laboratorio2.repository.AreaRepository;
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

    @GetMapping("/area")
    public String shipperList(Model model) {
        model.addAttribute("listaArea",areaRepository.findAll());
        return "area/listar";
    }

    @GetMapping("/area/nuevo")
    public String shipperNew(){
        return "area/crear";
    }

    @PostMapping("/area/guardar")
    public String shipperSave(AreaEntity area, RedirectAttributes attr){
        System.out.println("nombrearea" + area.getNombrearea());
        areaRepository.save(area);
        attr.addFlashAttribute("msg","Area creada exitosamente");
        return "redirect:/area";
    }

    @GetMapping("/area/editar")
    public String editShipper(@RequestParam("id") int id, Model model){
        Optional<AreaEntity> areaOpt = areaRepository.findById(id);

        if(areaOpt.isPresent()){
            AreaEntity areaEntity = areaOpt.get();
            model.addAttribute("listaUsuarios",areaRepository.listarUsuariosporArea(id));
            model.addAttribute("area",areaEntity);
            return "area/editar";
        }else{
            return "redirect:/area";
        }
    }

    @GetMapping("/area/borrar")
    public String deleteShipper(@RequestParam("id") int id){
        Optional<AreaEntity> areaOpt = areaRepository.findById(id);

        if(areaOpt.isPresent()) {
            areaRepository.deleteById(id);
        }
        return "redirect:/shipper";

    }


}
