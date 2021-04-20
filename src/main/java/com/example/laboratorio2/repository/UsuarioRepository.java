package com.example.laboratorio2.repository;

import com.example.laboratorio2.controller.UsuarioController;
import com.example.laboratorio2.entity.AreaEntity;
import com.example.laboratorio2.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity,String> {

    @Query(value = "SELECT * FROM pm_db.usuarios where idarea=?1",nativeQuery = true)
    List<UsuarioEntity> listarUsuariosporArea(int idarea);

}
