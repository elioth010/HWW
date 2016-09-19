
package com.umes.jeb.hww.eis.dto;

import java.util.ArrayList;
import java.util.List;

public class CobranzaDTO {

    protected Integer codigo;
    protected Integer codigoCategoria;
    protected Integer codigoFacturador;
    protected Boolean estado;
    protected Integer id;
    protected String logo;
    protected String logoCategoria;
    protected String moneda;
    protected String nombre;
    protected String nombreCategoria;
    protected String nombreFacturador;
    protected String palabraClave;
    protected List<ServicioDTO> servicios;
    protected String simboloMoneda;
    protected Integer tiempoMaximoEspera;
    protected TipoCobranza tipo;
    protected TipoPago tipoPago;
    protected Integer umbralAceptado;

    /**
     * Gets the value of the codigo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * Sets the value of the codigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodigo(Integer value) {
        this.codigo = value;
    }

    /**
     * Gets the value of the codigoCategoria property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodigoCategoria() {
        return codigoCategoria;
    }

    /**
     * Sets the value of the codigoCategoria property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodigoCategoria(Integer value) {
        this.codigoCategoria = value;
    }

    /**
     * Gets the value of the codigoFacturador property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodigoFacturador() {
        return codigoFacturador;
    }

    /**
     * Sets the value of the codigoFacturador property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodigoFacturador(Integer value) {
        this.codigoFacturador = value;
    }

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEstado(Boolean value) {
        this.estado = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the logo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogo() {
        return logo;
    }

    /**
     * Sets the value of the logo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogo(String value) {
        this.logo = value;
    }

    /**
     * Gets the value of the logoCategoria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogoCategoria() {
        return logoCategoria;
    }

    /**
     * Sets the value of the logoCategoria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogoCategoria(String value) {
        this.logoCategoria = value;
    }

    /**
     * Gets the value of the moneda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * Sets the value of the moneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoneda(String value) {
        this.moneda = value;
    }

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the nombreCategoria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    /**
     * Sets the value of the nombreCategoria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreCategoria(String value) {
        this.nombreCategoria = value;
    }

    /**
     * Gets the value of the nombreFacturador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreFacturador() {
        return nombreFacturador;
    }

    /**
     * Sets the value of the nombreFacturador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreFacturador(String value) {
        this.nombreFacturador = value;
    }

    /**
     * Gets the value of the palabraClave property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPalabraClave() {
        return palabraClave;
    }

    /**
     * Sets the value of the palabraClave property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPalabraClave(String value) {
        this.palabraClave = value;
    }

    /**
     * Gets the value of the servicios property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the servicios property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServicios().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServicioDTO }
     * 
     * 
     */
    public List<ServicioDTO> getServicios() {
        if (servicios == null) {
            servicios = new ArrayList<ServicioDTO>();
        }
        return this.servicios;
    }

    /**
     * Gets the value of the simboloMoneda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSimboloMoneda() {
        return simboloMoneda;
    }

    /**
     * Sets the value of the simboloMoneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSimboloMoneda(String value) {
        this.simboloMoneda = value;
    }

    /**
     * Gets the value of the tiempoMaximoEspera property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTiempoMaximoEspera() {
        return tiempoMaximoEspera;
    }

    /**
     * Sets the value of the tiempoMaximoEspera property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTiempoMaximoEspera(Integer value) {
        this.tiempoMaximoEspera = value;
    }

    /**
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link TipoCobranza }
     *     
     */
    public TipoCobranza getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoCobranza }
     *     
     */
    public void setTipo(TipoCobranza value) {
        this.tipo = value;
    }

    /**
     * Gets the value of the tipoPago property.
     * 
     * @return
     *     possible object is
     *     {@link TipoPago }
     *     
     */
    public TipoPago getTipoPago() {
        return tipoPago;
    }

    /**
     * Sets the value of the tipoPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoPago }
     *     
     */
    public void setTipoPago(TipoPago value) {
        this.tipoPago = value;
    }

    /**
     * Gets the value of the umbralAceptado property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUmbralAceptado() {
        return umbralAceptado;
    }

    /**
     * Sets the value of the umbralAceptado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUmbralAceptado(Integer value) {
        this.umbralAceptado = value;
    }

}
