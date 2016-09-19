package com.umes.jeb.hww.eis.dto;

public class GetBalanceResponseDTO {

    protected ErrorDTO error;
    protected CamposCobranzaDTO camposCobranza;

    public ErrorDTO getError() {
        return error;
    }

    public void setError(ErrorDTO error) {
        this.error = error;
    }

    public CamposCobranzaDTO getCamposCobranza() {
        return camposCobranza;
    }

    public void setCamposCobranza(CamposCobranzaDTO camposCobranza) {
        this.camposCobranza = camposCobranza;
    }

}
