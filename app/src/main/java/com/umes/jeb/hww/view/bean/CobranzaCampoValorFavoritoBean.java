package com.umes.jeb.hww.view.bean;

import com.umes.jeb.hww.eis.dto.CobranzaCampoValorFavoritoDTO;

import java.io.Serializable;
import java.math.BigInteger;


public class CobranzaCampoValorFavoritoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3870258164158842652L;
	
	private String aliasUsuario;
    private Integer cobranza;
    private Integer colector;
    private BigInteger id;
    private String nombreFavorito;
    private Integer servicio;
    private String valorFavorito;
    private Short version;
    private Boolean selected; 
	
	public CobranzaCampoValorFavoritoBean() {
		super();
	}
	
	public CobranzaCampoValorFavoritoBean(String aliasUsuario,
			Integer cobranza, Integer colector, BigInteger id,
			String nombreFavorito, Integer servicio, String valorFavorito,
			Short version) {
		super();
		this.aliasUsuario = aliasUsuario;
		this.cobranza = cobranza;
		this.colector = colector;
		this.id = id;
		this.nombreFavorito = nombreFavorito;
		this.servicio = servicio;
		this.valorFavorito = valorFavorito;
		this.version = version;
	}

	public CobranzaCampoValorFavoritoBean(CobranzaCampoValorFavoritoDTO dto) {
		super();
		this.aliasUsuario = dto.getAliasUsuario();
		this.cobranza = dto.getCobranza();
		this.colector = dto.getColector();
		this.id = dto.getId();
		this.nombreFavorito = dto.getNombreFavorito();
		this.servicio = dto.getServicio();
		this.valorFavorito = dto.getValorFavorito();
		this.version = dto.getVersion();
	}

	public CobranzaCampoValorFavoritoBean(String aliasUsuario,
			Integer cobranza, Integer colector, BigInteger id,
			String nombreFavorito, Integer servicio, String valorFavorito,
			Short version, Boolean selected) {
		super();
		this.aliasUsuario = aliasUsuario;
		this.cobranza = cobranza;
		this.colector = colector;
		this.id = id;
		this.nombreFavorito = nombreFavorito;
		this.servicio = servicio;
		this.valorFavorito = valorFavorito;
		this.version = version;
		this.selected = selected;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public String getAliasUsuario() {
		return aliasUsuario;
	}

	public void setAliasUsuario(String aliasUsuario) {
		this.aliasUsuario = aliasUsuario;
	}

	public Integer getCobranza() {
		return cobranza;
	}

	public void setCobranza(Integer cobranza) {
		this.cobranza = cobranza;
	}

	public Integer getColector() {
		return colector;
	}

	public void setColector(Integer colector) {
		this.colector = colector;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getNombreFavorito() {
		return nombreFavorito;
	}

	public void setNombreFavorito(String nombreFavorito) {
		this.nombreFavorito = nombreFavorito;
	}

	public Integer getServicio() {
		return servicio;
	}

	public void setServicio(Integer servicio) {
		this.servicio = servicio;
	}

	public String getValorFavorito() {
		return valorFavorito;
	}

	public void setValorFavorito(String valorFavorito) {
		this.valorFavorito = valorFavorito;
	}

	public Short getVersion() {
		return version;
	}

	public void setVersion(Short version) {
		this.version = version;
	}

}
