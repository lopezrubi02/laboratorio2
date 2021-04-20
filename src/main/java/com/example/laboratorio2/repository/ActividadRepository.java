package com.example.laboratorio2.repository;

import com.example.laboratorio2.entity.ActividadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActividadRepository extends JpaRepository<ActividadEntity,Integer> {
}
