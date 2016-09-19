package com.umes.jeb.hww.eis.bo;

import java.math.BigInteger;

/**
 * @author elioth010
 */
public class MedioPago extends BaseBO<MedioPago> {

    /**
     *
     */
    private static final long serialVersionUID = -2657522461715497809L;
    private Long id;
    private Long noTarjeta;
    private String fechaExpiracion;
    private Integer cvv2;
    private Integer medioSeleccionado;

    public MedioPago() {
        super();
    }

    public MedioPago(Integer id, Long noTarjeta, String fechaExpiracion, Integer cvv2, Integer medioSeleccionado) {
        this.id = id.longValue();
        this.noTarjeta = noTarjeta;
        this.fechaExpiracion = fechaExpiracion;
        this.cvv2 = cvv2;
        this.medioSeleccionado = medioSeleccionado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNoTarjeta() {
        return noTarjeta;
    }

    public void setNoTarjeta(Long noTarjeta) {
        this.noTarjeta = noTarjeta;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {this.fechaExpiracion = fechaExpiracion;}

    public Integer getCvv2() {
        return cvv2;
    }

    public void setCvv2(Integer cvv2) {this.cvv2 = cvv2;}

    public Integer getMedioSeleccionado() { return medioSeleccionado;}

    public void setMedioSeleccionado(Integer medioSeleccionado){this.medioSeleccionado = medioSeleccionado;}

    @Override
    public String toString() {
        return "mediospago [id=" + super.getId() + ", noTarjeta=" + noTarjeta + ", fechaExpiracion=" + fechaExpiracion
                + ", cvv2=" + cvv2 + ", medioseleccionado="+medioSeleccionado+"]";
    }

}