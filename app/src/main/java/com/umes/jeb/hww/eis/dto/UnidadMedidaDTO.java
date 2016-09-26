package com.umes.jeb.hww.eis.dto;

import java.io.Serializable;

/**
 * Created by elioth010 on 9/26/16.
 */

public class UnidadMedidaDTO implements Serializable {

    private Integer id;
    private String titulo;
    private Integer estado;

    public UnidadMedidaDTO() {
        super();
    }

    public UnidadMedidaDTO(Integer id, String titulo, Integer estado) {
        this.id = id;
        this.titulo = titulo;
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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
