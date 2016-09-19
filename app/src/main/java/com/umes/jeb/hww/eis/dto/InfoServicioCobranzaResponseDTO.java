
package com.umes.jeb.hww.eis.dto;

import java.util.ArrayList;
import java.util.List;

public class InfoServicioCobranzaResponseDTO {

    protected Integer codigoError;
    protected String descripcionError;
    protected List<CobranzaCampoDTO> listaCampos;
    protected String mensaje;
    protected List<CobranzaMensajeDTO> mensajesCobranzaConsultaSaldos;
    protected List<CobranzaMensajeDTO> mensajesCobranzaPantallaInicial;
    protected List<CobranzaMensajeDTO> mensajesCobranzaPantallaResultados;
    protected String token;
    protected List<CobranzaCampoValorFavoritoDTO> valoresFavoritos;

    /**
     * Gets the value of the codigoError property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodigoError() {
        return codigoError;
    }

    /**
     * Sets the value of the codigoError property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodigoError(Integer value) {
        this.codigoError = value;
    }

    /**
     * Gets the value of the descripcionError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionError() {
        return descripcionError;
    }

    /**
     * Sets the value of the descripcionError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionError(String value) {
        this.descripcionError = value;
    }

    /**
     * Gets the value of the listaCampos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaCampos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaCampos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CobranzaCampoDTO }
     * 
     * 
     */
    public List<CobranzaCampoDTO> getListaCampos() {
        if (listaCampos == null) {
            listaCampos = new ArrayList<CobranzaCampoDTO>();
        }
        return this.listaCampos;
    }

    /**
     * Gets the value of the mensaje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Sets the value of the mensaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensaje(String value) {
        this.mensaje = value;
    }

    /**
     * Gets the value of the mensajesCobranzaConsultaSaldos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mensajesCobranzaConsultaSaldos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMensajesCobranzaConsultaSaldos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CobranzaMensajeDTO }
     * 
     * 
     */
    public List<CobranzaMensajeDTO> getMensajesCobranzaConsultaSaldos() {
        if (mensajesCobranzaConsultaSaldos == null) {
            mensajesCobranzaConsultaSaldos = new ArrayList<CobranzaMensajeDTO>();
        }
        return this.mensajesCobranzaConsultaSaldos;
    }

    /**
     * Gets the value of the mensajesCobranzaPantallaInicial property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mensajesCobranzaPantallaInicial property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMensajesCobranzaPantallaInicial().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CobranzaMensajeDTO }
     * 
     * 
     */
    public List<CobranzaMensajeDTO> getMensajesCobranzaPantallaInicial() {
        if (mensajesCobranzaPantallaInicial == null) {
            mensajesCobranzaPantallaInicial = new ArrayList<CobranzaMensajeDTO>();
        }
        return this.mensajesCobranzaPantallaInicial;
    }

    /**
     * Gets the value of the mensajesCobranzaPantallaResultados property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mensajesCobranzaPantallaResultados property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMensajesCobranzaPantallaResultados().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CobranzaMensajeDTO }
     * 
     * 
     */
    public List<CobranzaMensajeDTO> getMensajesCobranzaPantallaResultados() {
        if (mensajesCobranzaPantallaResultados == null) {
            mensajesCobranzaPantallaResultados = new ArrayList<CobranzaMensajeDTO>();
        }
        return this.mensajesCobranzaPantallaResultados;
    }

    /**
     * Gets the value of the token property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the value of the token property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToken(String value) {
        this.token = value;
    }

    /**
     * Gets the value of the valoresFavoritos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valoresFavoritos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValoresFavoritos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CobranzaCampoValorFavoritoDTO }
     * 
     * 
     */
    public List<CobranzaCampoValorFavoritoDTO> getValoresFavoritos() {
        if (valoresFavoritos == null) {
            valoresFavoritos = new ArrayList<CobranzaCampoValorFavoritoDTO>();
        }
        return this.valoresFavoritos;
    }

}
