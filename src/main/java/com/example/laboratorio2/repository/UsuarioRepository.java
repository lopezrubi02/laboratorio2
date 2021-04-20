package com.example.laboratorio2.repository;

import com.example.laboratorio2.controller.UsuarioController;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioController,String> {

}
