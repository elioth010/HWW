package com.umes.jeb.hww.eis.dto;

public class PayBillResponseDTO {

    protected ErrorDTO error;
    protected String autorizacionFacturador;
    protected CamposCobranzaDTO camposCobranza;

    public ErrorDTO getError() {
        return error;
    }

    public void setError(ErrorDTO error) {
        this.error = error;
    }

    public String getAutorizacionFacturador() {
        return autorizacionFacturador;
    }

    public void setAutorizacionFacturador(String autorizacionFacturador) {
        this.autorizacionFacturador = autorizacionFacturador;
    }

    public CamposCobranzaDTO getCamposCobranza() {
        return camposCobranza;
    }

    public void setCamposCobranza(CamposCobranzaDTO camposCobranza) {
        this.camposCobranza = camposCobranza;
    }

}
