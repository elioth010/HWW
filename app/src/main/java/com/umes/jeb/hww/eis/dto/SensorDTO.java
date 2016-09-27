package com.umes.jeb.hww.eis.dto;

import com.umes.jeb.hww.eis.bo.dominio.SensorType;

import java.io.Serializable;

/**
 * Created by elioth010 on 9/26/16.
 */

public class SensorDTO implements Serializable {

    private Integer id;
    private String titulo;
    private String descripcion;
    private SensorType sensorType;
    private Integer estado;

    public SensorDTO() {
        super();
    }

    public SensorDTO(Integer id, String titulo, String descripcion, Integer estado, SensorType type) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.sensorType = type;
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

    public SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }
}
