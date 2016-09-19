package com.umes.jeb.hww.view.bean;

import com.umes.jeb.hww.eis.dto.TipoCampo;

import java.io.Serializable;


public class TipoCampoBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4314518481814294176L;

    private Integer codigo;
    private Integer id;
    private String nombre;
    private Short version;

    public TipoCampoBean() {
        super();
    }

    public TipoCampoBean(TipoCampo dto) {
        super();
        if (dto != null) {
            this.codigo = dto.getCodigo();
            this.id = dto.getId();
            this.nombre = dto.getNombre();
            this.version = dto.getVersion();
        }
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Short getVersion() {
        return version;
    }

    public void setVersion(Short version) {
        this.version = version;
    }

}
