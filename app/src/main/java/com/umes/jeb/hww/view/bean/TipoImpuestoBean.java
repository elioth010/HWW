package com.umes.jeb.hww.view.bean;

import com.umes.jeb.hww.eis.dto.TipoImpuesto;

import java.io.Serializable;


public class TipoImpuestoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4314518481814294176L;
	
	 private Integer codigo;
	 private String descripcion;
	 private Integer id;
	 private Double porcentaje;
	 private Short version;
	
	public TipoImpuestoBean() {
		super();
	}
	
	public TipoImpuestoBean(TipoImpuesto dto) {
        super();
        if(dto != null){
            this.codigo = dto.getCodigo();
            this.descripcion = dto.getDescripcion();
            this.id=dto.getId();
            this.porcentaje=dto.getPorcentaje();
            this.version=dto.getVersion();
        }
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Short getVersion() {
        return version;
    }

    public void setVersion(Short version) {
        this.version = version;
    }

	
}
