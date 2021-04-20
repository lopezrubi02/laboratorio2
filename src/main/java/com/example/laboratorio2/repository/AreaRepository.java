package com.example.laboratorio2.repository;

import com.example.laboratorio2.entity.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AreaRepository extends JpaRepository<AreaEntity,Integer> {

    @Query(value = "SELECT correo,nombres,apellidos FROM pm_db.usuarios where idarea=%?1;",nativeQuery = true)
    List<AreaEntity> listarUsuariosporArea(int idarea);



}
