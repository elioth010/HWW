
package com.umes.jeb.hww.eis.dto;


import com.umes.jeb.hww.view.bean.TipoMensaje;

import java.util.Date;

public class CobranzaMensajeDTO {

    protected String descripcion;
    protected Date fechaFin;
    protected Date fechaInicio;
    protected TipoMensaje tipoMensaje;

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the fechaFin property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * Sets the value of the fechaFin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setFechaFin(Date value) {
        this.fechaFin = value;
    }

    /**
     * Gets the value of the fechaInicio property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Sets the value of the fechaInicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setFechaInicio(Date value) {
        this.fechaInicio = value;
    }

    /**
     * Gets the value of the tipoMensaje property.
     * 
     * @return
     *     possible object is
     *     {@link TipoMensaje }
     *     
     */
    public TipoMensaje getTipoMensaje() {
        return tipoMensaje;
    }

    /**
     * Sets the value of the tipoMensaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoMensaje }
     *     
     */
    public void setTipoMensaje(TipoMensaje value) {
        this.tipoMensaje = value;
    }

}
