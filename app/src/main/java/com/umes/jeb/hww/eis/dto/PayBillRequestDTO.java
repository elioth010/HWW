package com.umes.jeb.hww.eis.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class PayBillRequestDTO {

    protected String sesionId;
    protected int facturador;
    protected int cobranza;
    protected int servicio;
    protected long idTrxColector;
    protected CamposCobranzaDTO camposCobranza;
    protected BigDecimal subTotal;
    protected BigDecimal impuestos;
    protected BigDecimal total;
    protected long autorizacionDebitoColector;
    protected String idPago;
    protected String moneda;
    protected BigInteger numeroCuenta;
    protected Integer producto;
    protected String codigoIdioma;
    protected Integer colector;
    protected String webSession;
    protected String ip;

    public String getSesionId() {
        return sesionId;
    }

    public void setSesionId(String sesionId) {
        this.sesionId = sesionId;
    }

    public int getFacturador() {
        return facturador;
    }

    public void setFacturador(int facturador) {
        this.facturador = facturador;
    }

    public int getCobranza() {
        return cobranza;
    }

    public void setCobranza(int cobranza) {
        this.cobranza = cobranza;
    }

    public int getServicio() {
        return servicio;
    }

    public void setServicio(int servicio) {
        this.servicio = servicio;
    }

    public long getIdTrxColector() {
        return idTrxColector;
    }

    public void setIdTrxColector(long idTrxColector) {
        this.idTrxColector = idTrxColector;
    }

    public CamposCobranzaDTO getCamposCobranza() {
        return camposCobranza;
    }

    public void setCamposCobranza(CamposCobranzaDTO camposCobranza) {
        this.camposCobranza = camposCobranza;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(BigDecimal impuestos) {
        this.impuestos = impuestos;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public long getAutorizacionDebitoColector() {
        return autorizacionDebitoColector;
    }

    public void setAutorizacionDebitoColector(long autorizacionDebitoColector) {
        this.autorizacionDebitoColector = autorizacionDebitoColector;
    }

    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public BigInteger getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(BigInteger numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Integer getProducto() {
        return producto;
    }

    public void setProducto(Integer producto) {
        this.producto = producto;
    }

    public String getCodigoIdioma() {
        return codigoIdioma;
    }

    public void setCodigoIdioma(String codigoIdioma) {
        this.codigoIdioma = codigoIdioma;
    }

    public Integer getColector() {
        return colector;
    }

    public void setColector(Integer colector) {
        this.colector = colector;
    }

    public String getWebSession() {
        return webSession;
    }

    public void setWebSession(String webSession) {
        this.webSession = webSession;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
