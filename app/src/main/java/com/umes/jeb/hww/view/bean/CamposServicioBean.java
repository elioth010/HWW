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
public class CamposServicioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -635321025353341267L;
	
	private ServicioBean servicioBean = new ServicioBean();
	private Integer codigoError;
	private String descripcionError;
	private Boolean tieneCamposTipoFecha = false;
	private List<CobranzaCampoBean> listaCampos;
	private String mensaje;
	private List<CobranzaMensajeBean> mensajesCobranzaConsultaSaldos;
	private List<CobranzaMensajeBean> mensajesCobranzaPantallaInicial;
	private List<CobranzaMensajeBean> mensajesCobranzaPantallaResultados;
	private String token;
	private List<CobranzaCampoValorFavoritoBean> valoresFavoritos;

	public CamposServicioBean() {
		super();
	}
	
	public CamposServicioBean(ServicioBean servicioBean, Integer codigoError,
			String descripcionError, List<CobranzaCampoBean> listaCampos,
			String mensaje,
			List<CobranzaMensajeBean> mensajesCobranzaConsultaSaldos,
			List<CobranzaMensajeBean> mensajesCobranzaPantallaInicial,
			List<CobranzaMensajeBean> mensajesCobranzaPantallaResultados,
			String token, List<CobranzaCampoValorFavoritoBean> valoresFavoritos) {
		super();
		this.tieneCamposTipoFecha = false;
		this.servicioBean = servicioBean;
		this.codigoError = codigoError;
		this.descripcionError = descripcionError;
		this.listaCampos = listaCampos;
		this.mensaje = mensaje;
		this.mensajesCobranzaConsultaSaldos = mensajesCobranzaConsultaSaldos;
		this.mensajesCobranzaPantallaInicial = mensajesCobranzaPantallaInicial;
		this.mensajesCobranzaPantallaResultados = mensajesCobranzaPantallaResultados;
		this.token = token;
		this.valoresFavoritos = valoresFavoritos;
	}

	public ServicioBean getServicioBean() {
		return servicioBean;
	}

	public void setServicioBean(ServicioBean servicioBean) {
		this.servicioBean = servicioBean;
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

	public List<CobranzaCampoBean> getListaCampos() {
		return listaCampos;
	}

	public void setListaCampos(List<CobranzaCampoBean> listaCampos) {
		this.listaCampos = listaCampos;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<CobranzaMensajeBean> getMensajesCobranzaConsultaSaldos() {
		return mensajesCobranzaConsultaSaldos;
	}

	public void setMensajesCobranzaConsultaSaldos(List<CobranzaMensajeBean> mensajesCobranzaConsultaSaldos) {
		this.mensajesCobranzaConsultaSaldos = mensajesCobranzaConsultaSaldos;
	}

	public List<CobranzaMensajeBean> getMensajesCobranzaPantallaInicial() {
		return mensajesCobranzaPantallaInicial;
	}

	public void setMensajesCobranzaPantallaInicial(List<CobranzaMensajeBean> mensajesCobranzaPantallaInicial) {
		this.mensajesCobranzaPantallaInicial = mensajesCobranzaPantallaInicial;
	}

	public List<CobranzaMensajeBean> getMensajesCobranzaPantallaResultados() {
		return mensajesCobranzaPantallaResultados;
	}

	public void setMensajesCobranzaPantallaResultados(List<CobranzaMensajeBean> mensajesCobranzaPantallaResultados) {
		this.mensajesCobranzaPantallaResultados = mensajesCobranzaPantallaResultados;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<CobranzaCampoValorFavoritoBean> getValoresFavoritos() {
		return valoresFavoritos;
	}

	public void setValoresFavoritos(List<CobranzaCampoValorFavoritoBean> valoresFavoritos) {
		this.valoresFavoritos = valoresFavoritos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


    public Boolean getTieneCamposTipoFecha() {
        return tieneCamposTipoFecha;
    }

    public void setTieneCamposTipoFecha(Boolean tieneCamposTipoFecha) {
        this.tieneCamposTipoFecha = tieneCamposTipoFecha;
    }

}
