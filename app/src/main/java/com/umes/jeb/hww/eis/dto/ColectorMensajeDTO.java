
package com.umes.jeb.hww.eis.dto;

import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;


public class ColectorMensajeDTO {

    protected String descripcion;
    protected Date fechaFinVigencia;
    protected Date fechaInicioVigencia;

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
     * Gets the value of the fechaFinVigencia property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    /**
     * Sets the value of the fechaFinVigencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaFinVigencia(Date value) {
        this.fechaFinVigencia = value;
    }

    /**
     * Gets the value of the fechaInicioVigencia property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    /**
     * Sets the value of the fechaInicioVigencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInicioVigencia(Date value) {
        this.fechaInicioVigencia = value;
    }

}
