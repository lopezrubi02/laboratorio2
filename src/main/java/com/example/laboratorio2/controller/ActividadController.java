package com.example.laboratorio2.controller;

import com.example.laboratorio2.repository.ActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ActividadController {

    @Autowired
    ActividadRepository actividadRepository;


}
