package com.umes.jeb.hww.eis.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by elioth010 on 9/26/16.
 */

public class BitacoraDTO implements Serializable{
    private UsuarioDTO usuario;
    private MedidaSensorDTO medidaSensor;
    private Double dato;
    private Date fechaHora;

    public BitacoraDTO(UsuarioDTO usuario, MedidaSensorDTO medidaSensor, Double dato, Date fechaHora) {
        this.usuario = usuario;
        this.medidaSensor = medidaSensor;
        this.dato = dato;
        this.fechaHora = fechaHora;
    }

    public BitacoraDTO() {
        super();
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public MedidaSensorDTO getMedidaSensor() {
        return medidaSensor;
    }

    public void setMedidaSensor(MedidaSensorDTO medidaSensor) {
        this.medidaSensor = medidaSensor;
    }

    public Double getDato() {
        return dato;
    }

    public void setDato(Double dato) {
        this.dato = dato;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }
}
