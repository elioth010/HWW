package com.umes.jeb.hww.eis.dto;

/**
 * Created by elioth010 on 10/12/16.
 */

public class RolDTO {
    private Integer id;
    private String nombre;
    private Integer estado;

    public RolDTO(Integer id, String nombre, Integer estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    public RolDTO() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
