package com.umes.jeb.hww.view.bean;

import android.content.Intent;

import com.umes.jeb.hww.eis.dto.ProductoColectorDTO;
import com.umes.jeb.hww.eis.dto.ServicioDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ServicioBean implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1857744365130586227L;

    private Integer id;
    private Integer codigo;
    private String descripcion;
    private Boolean usaConsulta;
    private Integer versionCampos;
    private String urlLogo;
    private Integer codigoCobranza;
    private Integer codigoFacturador;
    private String moneda;
    private String nombreCategoria;
    private String logoCategoria;

    public ServicioBean() {
        super();
    }

    public ServicioBean(Integer id, Integer codigo, String descripcion,
                        Boolean usaConsulta, Integer versionCampos, String urlLogo, Integer codigoCobranza, Integer codigoFacturador, String moneda) {
        super();
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.usaConsulta = usaConsulta;
        this.versionCampos = versionCampos;
        this.urlLogo = urlLogo;
        this.codigoCobranza = codigoCobranza;
        this.codigoFacturador = codigoFacturador;
    }

    public ServicioBean(ServicioDTO dto, Integer codigoCobranza, Integer codigoFacturador, String moneda,List<ProductoColectorDTO> productos,String nombreCategoria,String logoCategoria) {
        super();
        this.id = dto.getId();
        this.codigo = dto.getCodigo();
        this.descripcion = dto.getDescripcion();
        this.usaConsulta = dto.isUsaConsulta();
        this.versionCampos = dto.getVersionCampos();
        this.urlLogo = dto.getUrlLogo();
        this.codigoCobranza = codigoCobranza;
        this.codigoFacturador = codigoFacturador;
        this.moneda=moneda;
        this.nombreCategoria=nombreCategoria;
        this.logoCategoria=logoCategoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getUsaConsulta() {
        return usaConsulta;
    }

    public void setUsaConsulta(Boolean usaConsulta) {
        this.usaConsulta = usaConsulta;
    }

    public Integer getVersionCampos() {
        return versionCampos;
    }

    public void setVersionCampos(Integer versionCampos) {
        this.versionCampos = versionCampos;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public Integer getCodigoCobranza() {
        return codigoCobranza;
    }

    public void setCodigoCobranza(Integer codigoCobranza) {
        this.codigoCobranza = codigoCobranza;
    }

    public Integer getCodigoFacturador() {
        return codigoFacturador;
    }

    public void setCodigoFacturador(Integer codigoFacturador) {
        this.codigoFacturador = codigoFacturador;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getLogoCategoria() {
        return logoCategoria;
    }

    public void setLogoCategoria(String logoCategoria) {
        this.logoCategoria = logoCategoria;
    }
}
