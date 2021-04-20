package com.example.laboratorio2.entity;

import javax.persistence.*;

import javax.persistence.Entity;

@Entity
@Table(name ="usuarios")
public class UsuarioEntity {
    @Id
    private String correo;

    private Integer idarea;

    private String nombre;

    private String apellido;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getIdarea() {
        return idarea;
    }

    public void setIdarea(Integer idarea) {
        this.idarea = idarea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
