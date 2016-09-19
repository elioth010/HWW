
package com.umes.jeb.hww.eis.dto;

import java.util.ArrayList;
import java.util.List;


public class InfoCobranzasResponseDTO {


    protected List<CobranzaDTO> cobranzasFavoritas;

    protected List<CobranzaDTO> cobranzasRecientes;
    protected Integer codigoError;
    protected String descripcionError;

    protected List<CobranzaDTO> listaCobranzas;
    protected String mensaje;

    protected List<ColectorMensajeDTO> mensajesColector;

    protected List<ProductoColectorDTO> productosColector;
    protected String token;

    public List<CobranzaDTO> getCobranzasRecientes() {
        return cobranzasRecientes;
    }

    public void setCobranzasRecientes(List<CobranzaDTO> cobranzasRecientes) {
        this.cobranzasRecientes = cobranzasRecientes;
    }

    public List<CobranzaDTO> getCobranzasFavoritas() {
        return cobranzasFavoritas;
    }

    public void setCobranzasFavoritas(List<CobranzaDTO> cobranzasFavoritas) {
        this.cobranzasFavoritas = cobranzasFavoritas;
    }

    public Integer getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(Integer codigoError) {
        this.codigoError = codigoError;
    }

    public String getDescripcionError() {
        return descripcionError;
    }

    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }

    public List<CobranzaDTO> getListaCobranzas() {
        return listaCobranzas;
    }

    public void setListaCobranzas(List<CobranzaDTO> listaCobranzas) {
        this.listaCobranzas = listaCobranzas;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<ColectorMensajeDTO> getMensajesColector() {
        return mensajesColector;
    }

    public void setMensajesColector(List<ColectorMensajeDTO> mensajesColector) {
        this.mensajesColector = mensajesColector;
    }

    public List<ProductoColectorDTO> getProductosColector() {
        return productosColector;
    }

    public void setProductosColector(List<ProductoColectorDTO> productosColector) {
        this.productosColector = productosColector;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
