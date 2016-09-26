package com.umes.jeb.hww.eis.dto;

import java.io.Serializable;

/**
 * Created by elioth010 on 9/26/16.
 */

public class SensorDTO implements Serializable {

    private Integer id;
    private String titulo;
    private String descripcion;
    private Integer estado;

    public SensorDTO() {
        super();
    }

    public SensorDTO(Integer id, String titulo, String descripcion, Integer estado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
