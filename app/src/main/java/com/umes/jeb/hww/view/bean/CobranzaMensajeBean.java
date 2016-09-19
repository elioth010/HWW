package com.umes.jeb.hww.view.bean;

import com.umes.jeb.hww.eis.dto.CobranzaMensajeDTO;

import java.io.Serializable;
import java.util.Date;


public class CobranzaMensajeBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1231130667892522290L;

    private String descripcion;
    private Date fechaFin;
    private Date fechaInicio;
    private TipoMensaje tipoMensaje;

    public CobranzaMensajeBean(String descripcion, Date fechaFin, Date fechaInicio, TipoMensaje tipoMensaje) {
        super();
        this.descripcion = descripcion;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.tipoMensaje = tipoMensaje;
    }

    public CobranzaMensajeBean() {
        super();
    }

    public CobranzaMensajeBean(CobranzaMensajeDTO dto) {
        this.descripcion = dto.getDescripcion();
        this.fechaFin = dto.getFechaFin();
        this.fechaInicio = dto.getFechaInicio();
        this.tipoMensaje = dto.getTipoMensaje();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public TipoMensaje getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(TipoMensaje tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

}
