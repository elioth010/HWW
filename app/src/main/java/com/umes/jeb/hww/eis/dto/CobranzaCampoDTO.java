
package com.umes.jeb.hww.eis.dto;


import java.util.ArrayList;
import java.util.List;

public class CobranzaCampoDTO {

    protected AccionCampo accionPagoMinimo;
    protected AccionCampo accionTotal;
    protected String ayuda;
    protected Integer campoAfectoImpuesto;
    protected Integer cantidadDecimales;
    protected Integer codigo;
    protected String descripcion;
    protected Boolean esFavorito;
    protected Boolean esIdConsulta;
    protected Boolean esIdPago;
    protected Boolean esImpuesto;
    protected Boolean esSeleccionable;
    protected Boolean esTotal;
    protected FormatoFecha formatoFecha;
    protected Integer id;
    protected Boolean ingresoObligatorioConsulta;
    protected Boolean ingresoObligatorioPago;
    protected List<CobranzaCampoDescripcionDTO> listaDescripcion;
    protected Integer longitud;
    protected Integer longitudMinima;
    protected Integer ordenPantalla;
    protected Boolean permiteIngresoConsulta;
    protected Boolean permiteIngresoPago;
    protected Double porcentajeImpuesto;
    protected Integer posicionEnviaConsultaSaldo;
    protected Integer posicionEnviaPago;
    protected Integer posicionRecibeConsultaSaldo;
    protected Integer posicionRecibePago;
    protected String separadorFecha;
    protected TipoCampo tipoCampo;
    protected TipoDato tipoDato;
    protected TipoImpuesto tipoImpuesto;
    protected Boolean visibleConsulta;
    protected Boolean visiblePago;
    protected Boolean visibleResultado;

    /**
     * Gets the value of the accionPagoMinimo property.
     * 
     * @return
     *     possible object is
     *     {@link AccionCampo }
     *     
     */
    public AccionCampo getAccionPagoMinimo() {
        return accionPagoMinimo;
    }

    /**
     * Sets the value of the accionPagoMinimo property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccionCampo }
     *     
     */
    public void setAccionPagoMinimo(AccionCampo value) {
        this.accionPagoMinimo = value;
    }

    /**
     * Gets the value of the accionTotal property.
     * 
     * @return
     *     possible object is
     *     {@link AccionCampo }
     *     
     */
    public AccionCampo getAccionTotal() {
        return accionTotal;
    }

    /**
     * Sets the value of the accionTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccionCampo }
     *     
     */
    public void setAccionTotal(AccionCampo value) {
        this.accionTotal = value;
    }

    /**
     * Gets the value of the ayuda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAyuda() {
        return ayuda;
    }

    /**
     * Sets the value of the ayuda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAyuda(String value) {
        this.ayuda = value;
    }

    /**
     * Gets the value of the campoAfectoImpuesto property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCampoAfectoImpuesto() {
        return campoAfectoImpuesto;
    }

    /**
     * Sets the value of the campoAfectoImpuesto property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCampoAfectoImpuesto(Integer value) {
        this.campoAfectoImpuesto = value;
    }

    /**
     * Gets the value of the cantidadDecimales property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCantidadDecimales() {
        return cantidadDecimales;
    }

    /**
     * Sets the value of the cantidadDecimales property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCantidadDecimales(Integer value) {
        this.cantidadDecimales = value;
    }

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
     * Gets the value of the esFavorito property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsFavorito() {
        return esFavorito;
    }

    /**
     * Sets the value of the esFavorito property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsFavorito(Boolean value) {
        this.esFavorito = value;
    }

    /**
     * Gets the value of the esIdConsulta property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsIdConsulta() {
        return esIdConsulta;
    }

    /**
     * Sets the value of the esIdConsulta property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsIdConsulta(Boolean value) {
        this.esIdConsulta = value;
    }

    /**
     * Gets the value of the esIdPago property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsIdPago() {
        return esIdPago;
    }

    /**
     * Sets the value of the esIdPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsIdPago(Boolean value) {
        this.esIdPago = value;
    }

    /**
     * Gets the value of the esImpuesto property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsImpuesto() {
        return esImpuesto;
    }

    /**
     * Sets the value of the esImpuesto property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsImpuesto(Boolean value) {
        this.esImpuesto = value;
    }

    /**
     * Gets the value of the esSeleccionable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsSeleccionable() {
        return esSeleccionable;
    }

    /**
     * Sets the value of the esSeleccionable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsSeleccionable(Boolean value) {
        this.esSeleccionable = value;
    }

    /**
     * Gets the value of the esTotal property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsTotal() {
        return esTotal;
    }

    /**
     * Sets the value of the esTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsTotal(Boolean value) {
        this.esTotal = value;
    }

    /**
     * Gets the value of the formatoFecha property.
     * 
     * @return
     *     possible object is
     *     {@link FormatoFecha }
     *     
     */
    public FormatoFecha getFormatoFecha() {
        return formatoFecha;
    }

    /**
     * Sets the value of the formatoFecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormatoFecha }
     *     
     */
    public void setFormatoFecha(FormatoFecha value) {
        this.formatoFecha = value;
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
     * Gets the value of the ingresoObligatorioConsulta property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIngresoObligatorioConsulta() {
        return ingresoObligatorioConsulta;
    }

    /**
     * Sets the value of the ingresoObligatorioConsulta property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIngresoObligatorioConsulta(Boolean value) {
        this.ingresoObligatorioConsulta = value;
    }

    /**
     * Gets the value of the ingresoObligatorioPago property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIngresoObligatorioPago() {
        return ingresoObligatorioPago;
    }

    /**
     * Sets the value of the ingresoObligatorioPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIngresoObligatorioPago(Boolean value) {
        this.ingresoObligatorioPago = value;
    }

    /**
     * Gets the value of the listaDescripcion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaDescripcion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaDescripcion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CobranzaCampoDescripcionDTO }
     * 
     * 
     */
    public List<CobranzaCampoDescripcionDTO> getListaDescripcion() {
        if (listaDescripcion == null) {
            listaDescripcion = new ArrayList<CobranzaCampoDescripcionDTO>();
        }
        return this.listaDescripcion;
    }

    /**
     * Gets the value of the longitud property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLongitud() {
        return longitud;
    }

    /**
     * Sets the value of the longitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLongitud(Integer value) {
        this.longitud = value;
    }

    /**
     * Gets the value of the longitudMinima property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLongitudMinima() {
        return longitudMinima;
    }

    /**
     * Sets the value of the longitudMinima property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLongitudMinima(Integer value) {
        this.longitudMinima = value;
    }

    /**
     * Gets the value of the ordenPantalla property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOrdenPantalla() {
        return ordenPantalla;
    }

    /**
     * Sets the value of the ordenPantalla property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOrdenPantalla(Integer value) {
        this.ordenPantalla = value;
    }

    /**
     * Gets the value of the permiteIngresoConsulta property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPermiteIngresoConsulta() {
        return permiteIngresoConsulta;
    }

    /**
     * Sets the value of the permiteIngresoConsulta property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPermiteIngresoConsulta(Boolean value) {
        this.permiteIngresoConsulta = value;
    }

    /**
     * Gets the value of the permiteIngresoPago property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPermiteIngresoPago() {
        return permiteIngresoPago;
    }

    /**
     * Sets the value of the permiteIngresoPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPermiteIngresoPago(Boolean value) {
        this.permiteIngresoPago = value;
    }

    /**
     * Gets the value of the porcentajeImpuesto property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPorcentajeImpuesto() {
        return porcentajeImpuesto;
    }

    /**
     * Sets the value of the porcentajeImpuesto property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPorcentajeImpuesto(Double value) {
        this.porcentajeImpuesto = value;
    }

    /**
     * Gets the value of the posicionEnviaConsultaSaldo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPosicionEnviaConsultaSaldo() {
        return posicionEnviaConsultaSaldo;
    }

    /**
     * Sets the value of the posicionEnviaConsultaSaldo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPosicionEnviaConsultaSaldo(Integer value) {
        this.posicionEnviaConsultaSaldo = value;
    }

    /**
     * Gets the value of the posicionEnviaPago property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPosicionEnviaPago() {
        return posicionEnviaPago;
    }

    /**
     * Sets the value of the posicionEnviaPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPosicionEnviaPago(Integer value) {
        this.posicionEnviaPago = value;
    }

    /**
     * Gets the value of the posicionRecibeConsultaSaldo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPosicionRecibeConsultaSaldo() {
        return posicionRecibeConsultaSaldo;
    }

    /**
     * Sets the value of the posicionRecibeConsultaSaldo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPosicionRecibeConsultaSaldo(Integer value) {
        this.posicionRecibeConsultaSaldo = value;
    }

    /**
     * Gets the value of the posicionRecibePago property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPosicionRecibePago() {
        return posicionRecibePago;
    }

    /**
     * Sets the value of the posicionRecibePago property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPosicionRecibePago(Integer value) {
        this.posicionRecibePago = value;
    }

    /**
     * Gets the value of the separadorFecha property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeparadorFecha() {
        return separadorFecha;
    }

    /**
     * Sets the value of the separadorFecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeparadorFecha(String value) {
        this.separadorFecha = value;
    }

    /**
     * Gets the value of the tipoCampo property.
     * 
     * @return
     *     possible object is
     *     {@link TipoCampo }
     *     
     */
    public TipoCampo getTipoCampo() {
        return tipoCampo;
    }

    /**
     * Sets the value of the tipoCampo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoCampo }
     *     
     */
    public void setTipoCampo(TipoCampo value) {
        this.tipoCampo = value;
    }

    /**
     * Gets the value of the tipoDato property.
     * 
     * @return
     *     possible object is
     *     {@link TipoDato }
     *     
     */
    public TipoDato getTipoDato() {
        return tipoDato;
    }

    /**
     * Sets the value of the tipoDato property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoDato }
     *     
     */
    public void setTipoDato(TipoDato value) {
        this.tipoDato = value;
    }

    /**
     * Gets the value of the tipoImpuesto property.
     * 
     * @return
     *     possible object is
     *     {@link TipoImpuesto }
     *     
     */
    public TipoImpuesto getTipoImpuesto() {
        return tipoImpuesto;
    }

    /**
     * Sets the value of the tipoImpuesto property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoImpuesto }
     *     
     */
    public void setTipoImpuesto(TipoImpuesto value) {
        this.tipoImpuesto = value;
    }

    /**
     * Gets the value of the visibleConsulta property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVisibleConsulta() {
        return visibleConsulta;
    }

    /**
     * Sets the value of the visibleConsulta property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVisibleConsulta(Boolean value) {
        this.visibleConsulta = value;
    }

    /**
     * Gets the value of the visiblePago property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVisiblePago() {
        return visiblePago;
    }

    /**
     * Sets the value of the visiblePago property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVisiblePago(Boolean value) {
        this.visiblePago = value;
    }

    /**
     * Gets the value of the visibleResultado property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVisibleResultado() {
        return visibleResultado;
    }

    /**
     * Sets the value of the visibleResultado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVisibleResultado(Boolean value) {
        this.visibleResultado = value;
    }

}
