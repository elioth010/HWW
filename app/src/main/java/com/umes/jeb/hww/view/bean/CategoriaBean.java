/**
 * 
 */
package com.umes.jeb.hww.view.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author developer
 * 
 */
public class CategoriaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6121339106369639784L;

	private String nombre;
	private String image;
	private Integer codigo;

	private List<CobranzaBean> cobranzas = new ArrayList<CobranzaBean>();

	/**
	 * 
	 */
	public CategoriaBean() {
		super();
	}

	public CategoriaBean(Integer codigo, String nombre, String image, List<CobranzaBean> cobranzas) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.image = image;
		this.cobranzas = cobranzas;
	}
	
	public CategoriaBean(Integer codigo, String nombre, String image, CobranzaBean cobranza) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.image = image;
		this.cobranzas.add(cobranza);
	}
	
	public CategoriaBean(Integer codigo, String nombre, String image) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.image = image;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<CobranzaBean> getCobranzas() {
		return cobranzas;
	}

	public void setCobranzas(List<CobranzaBean> cobranzas) {
		this.cobranzas = cobranzas;
	}

}
