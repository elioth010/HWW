
package com.umes.jeb.hww.eis.dto;


import java.util.Date;

public class CobranzaUsoRecienteDTO {

    protected String aliasUsuario;
    protected Integer cobranza;
    protected Integer colector;
    protected Date horaFecha;

    /**
     * Gets the value of the aliasUsuario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAliasUsuario() {
        return aliasUsuario;
    }

    /**
     * Sets the value of the aliasUsuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAliasUsuario(String value) {
        this.aliasUsuario = value;
    }

    /**
     * Gets the value of the cobranza property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCobranza() {
        return cobranza;
    }

    /**
     * Sets the value of the cobranza property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCobranza(Integer value) {
        this.cobranza = value;
    }

    /**
     * Gets the value of the colector property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getColector() {
        return colector;
    }

    /**
     * Sets the value of the colector property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setColector(Integer value) {
        this.colector = value;
    }

    /**
     * Gets the value of the horaFecha property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getHoraFecha() {
        return horaFecha;
    }

    /**
     * Sets the value of the horaFecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setHoraFecha(Date value) {
        this.horaFecha = value;
    }

}
