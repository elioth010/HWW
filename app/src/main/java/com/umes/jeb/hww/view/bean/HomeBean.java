package com.umes.jeb.hww.view.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ccalito on 07/04/2016.
 */
public class HomeBean implements Serializable {

    private String nombre;
    private String imagen;
    private Boolean isCategoria = false;
    private Boolean isFavorita = false;
    private Boolean isListaCobranza = false;
    private Boolean isUsoReciente = false;
    private List<CategoriaBean> listaCategorias = new ArrayList<CategoriaBean>();
    private List<CobranzaBean> listaCobranzas = new ArrayList<CobranzaBean>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Boolean getIsCategoria() {
        return isCategoria;
    }

    public void setIsCategoria(Boolean isCategoria) {
        this.isCategoria = isCategoria;
    }

    public Boolean getIsFavorita() {
        return isFavorita;
    }

    public void setIsFavorita(Boolean isFavorita) {
        this.isFavorita = isFavorita;
    }

    public Boolean getIsListaCobranza() {
        return isListaCobranza;
    }

    public void setIsListaCobranza(Boolean isListaCobranza) {
        this.isListaCobranza = isListaCobranza;
    }

    public Boolean getIsUsoReciente() {
        return isUsoReciente;
    }

    public void setIsUsoReciente(Boolean isUsoReciente) {
        this.isUsoReciente = isUsoReciente;
    }

    public List<CategoriaBean> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<CategoriaBean> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public List<CobranzaBean> getListaCobranzas() {
        return listaCobranzas;
    }

    public void setListaCobranzas(List<CobranzaBean> listaCobranzas) {
        this.listaCobranzas = listaCobranzas;
    }
}
