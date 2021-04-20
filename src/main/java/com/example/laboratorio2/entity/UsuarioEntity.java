package com.example.laboratorio2.entity;

import javax.persistence.*;

import javax.persistence.Entity;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String correo;
    @Column
    private Integer idarea;
    @Column
    private String nombres;
    @Column
    private String apellidos;

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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
