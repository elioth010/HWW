/**
 * 
 */
package com.umes.jeb.hww.view.bean;

import com.umes.jeb.hww.eis.dto.CobranzaCampoDescripcionDTO;

import java.io.Serializable;

/**
 * @author developer
 * 
 */
public class CobranzaCampoDescripcionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7545715394727040490L;

	private String descripcion;
	private Integer id;
	private String valor;

	public CobranzaCampoDescripcionBean() {
		super();
	}
	
	public CobranzaCampoDescripcionBean(String descripcion, Integer id, String valor) {
		super();
		this.descripcion = descripcion;
		this.id = id;
		this.valor = valor;
	}

	public CobranzaCampoDescripcionBean(String descripcion, Integer id) {
		super();
		this.descripcion = descripcion;
		this.id = id;
	}
	
	public CobranzaCampoDescripcionBean(CobranzaCampoDescripcionDTO dto){
		this.descripcion = dto.getDescripcion();
		this.id = dto.getId();
		this.valor = dto.getValor();
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
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

	@Override
	public String toString() {
		return descripcion;
	}
}
