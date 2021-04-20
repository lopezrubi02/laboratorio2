package com.example.laboratorio2.repository;

import com.example.laboratorio2.entity.ProyectoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoRepository extends JpaRepository<ProyectoEntity,Integer> {
}
