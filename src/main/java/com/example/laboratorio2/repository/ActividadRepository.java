package com.example.laboratorio2.repository;

import com.example.laboratorio2.entity.ActividadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActividadRepository extends JpaRepository<ActividadEntity,Integer> {


    @Query(value="select * from actividades where idproyecto = ?1",nativeQuery = true)
    List<ActividadEntity> listaDeActividadesPorProyecto (int idproyecto);

    @Query(value="select SUM(peso) from actividades where idproyecto= ?1",nativeQuery = true)
    Double valorSumaTotal (int idproyecto);

    @Query(value="select SUM(peso) from actividades where idproyecto= ?1 and estado=1", nativeQuery = true)
    Double valorSumaFinalizado (int idproyecto);
}
