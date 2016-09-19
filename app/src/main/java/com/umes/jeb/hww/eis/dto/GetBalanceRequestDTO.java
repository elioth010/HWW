package com.umes.jeb.hww.eis.dto;

public class GetBalanceRequestDTO {

    protected String sesionId;
    protected int facturador;
    protected int cobranza;
    protected int servicio;
    protected long idTrxColector;
    protected String idConsulta;
    protected CamposCobranzaDTO camposCobranza;
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

    public String getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(String idConsulta) {
        this.idConsulta = idConsulta;
    }

    public CamposCobranzaDTO getCamposCobranza() {
        return camposCobranza;
    }

    public void setCamposCobranza(CamposCobranzaDTO camposCobranza) {
        this.camposCobranza = camposCobranza;
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
