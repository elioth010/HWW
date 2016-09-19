package com.umes.jeb.hww.view.bean;

import com.umes.jeb.hww.eis.dto.AccionCampo;
import com.umes.jeb.hww.eis.dto.CobranzaCampoDTO;
import com.umes.jeb.hww.eis.dto.CobranzaCampoDescripcionDTO;
import com.umes.jeb.hww.eis.dto.FormatoFecha;
import com.umes.jeb.hww.eis.dto.TipoDato;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CobranzaCampoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4314518481814294176L;
	
	private static final Integer EMPTY_VALUE = -1;
	private static final String EMPTY_DESC = "----- Seleccione un valor -----";

	private AccionCampo accionPagoMinimo;
	private AccionCampo accionTotal;
	private String ayuda;
	private Integer campoAfectoImpuesto;
	private Integer cantidadDecimales;
	private Integer codigo;
	private String descripcion;
	private Boolean esFavorito;
	private Boolean esIdPago;
	private Boolean esIdConsulta;
	private Boolean esImpuesto;
	private Boolean esSeleccionable;
	private Boolean esTotal;
	
	private String idComponent;
	/**
	 * Flag para manejo de pagos parciales
	 */
	private Boolean esCampoPagoTotal = false;
	
	private FormatoFecha formatoFecha;
	private String formatoFechaConSeparador;
	private Integer id;
	private Boolean ingresoObligatorioConsulta;
	private Boolean ingresoObligatorioPago;
	private List<CobranzaCampoDescripcionBean> listaDescripcion;
	private Integer longitud;
	private Integer ordenPantalla;
	private Boolean permiteIngresoConsulta;
	private Boolean permiteIngresoPago;
	private Integer posicionEnviaConsultaSaldo;
	private Integer posicionEnviaPago;
	private Integer posicionRecibeConsultaSaldo;
	private Integer posicionRecibePago;
	private String separadorFecha;
	private TipoCampoBean tipoCampoBean;
	private TipoDato tipoDato;
	private TipoImpuestoBean tipoImpuesto;
	private Boolean visibleConsulta;
	private Boolean visiblePago;
	private Boolean visibleResultado;
	private String valorIngreso;
	private String styleClass;
	private Double porcentajeImpuesto;
	private Integer longitudMinima;
	
	public CobranzaCampoBean() {
		super();
	}

	public CobranzaCampoBean(CobranzaCampoDTO dto){
		super();
		this.accionPagoMinimo = dto.getAccionPagoMinimo();
		this.accionTotal = dto.getAccionTotal();
		this.ayuda = dto.getAyuda();
		this.campoAfectoImpuesto = dto.getCampoAfectoImpuesto();
		this.cantidadDecimales = dto.getCantidadDecimales();
		this.codigo = dto.getCodigo();
		this.descripcion = dto.getDescripcion();
		this.esFavorito = dto.isEsFavorito();
		this.esIdPago = dto.isEsIdPago();
		this.esIdConsulta=dto.isEsIdConsulta();
		this.esImpuesto = dto.isEsImpuesto();
		this.esSeleccionable = dto.isEsSeleccionable();
		this.esTotal = dto.isEsTotal();
		this.formatoFecha = dto.getFormatoFecha();
		this.id = dto.getId();
		this.ingresoObligatorioConsulta = dto.isIngresoObligatorioConsulta();
		this.ingresoObligatorioPago = dto.isIngresoObligatorioPago();
		this.idComponent = "campoCobranza"+this.codigo+this.ordenPantalla;
		this.listaDescripcion = new ArrayList<CobranzaCampoDescripcionBean>();
		for(CobranzaCampoDescripcionDTO campoDTO : dto.getListaDescripcion()){
			this.listaDescripcion.add(new CobranzaCampoDescripcionBean(campoDTO));
		}
		this.longitud = dto.getLongitud();											
		this.ordenPantalla = dto.getOrdenPantalla();
		this.permiteIngresoConsulta = dto.isPermiteIngresoConsulta();
		this.permiteIngresoPago = dto.isPermiteIngresoPago();
		this.posicionEnviaConsultaSaldo = dto.getPosicionEnviaConsultaSaldo();
		this.posicionEnviaPago = dto.getPosicionEnviaPago();
		this.posicionRecibeConsultaSaldo = dto.getPosicionRecibeConsultaSaldo();
		
		this.posicionRecibePago = dto.getPosicionRecibePago();
		this.separadorFecha = dto.getSeparadorFecha();
		this.tipoCampoBean = new TipoCampoBean(dto.getTipoCampo());
		this.tipoDato = dto.getTipoDato();
		this.tipoImpuesto = new TipoImpuestoBean(dto.getTipoImpuesto());
		this.visibleConsulta = dto.isVisibleConsulta();
		this.visiblePago = dto.isVisiblePago();
		this.visibleResultado = dto.isVisibleResultado();
		this.porcentajeImpuesto = dto.getPorcentajeImpuesto();
		this.longitudMinima = dto.getLongitudMinima();
	}
	
	public AccionCampo getAccionPagoMinimo() {
		return accionPagoMinimo;
	}

	public void setAccionPagoMinimo(AccionCampo accionPagoMinimo) {
		this.accionPagoMinimo = accionPagoMinimo;
	}

	public AccionCampo getAccionTotal() {
		return accionTotal;
	}

	public void setAccionTotal(AccionCampo accionTotal) {
		this.accionTotal = accionTotal;
	}

	public String getAyuda() {
		return ayuda;
	}

	public void setAyuda(String ayuda) {
		this.ayuda = ayuda;
	}

	public Integer getCampoAfectoImpuesto() {
		return campoAfectoImpuesto;
	}

	public void setCampoAfectoImpuesto(Integer campoAfectoImpuesto) {
		this.campoAfectoImpuesto = campoAfectoImpuesto;
	}

	public Integer getCantidadDecimales() {
		return cantidadDecimales;
	}

	public void setCantidadDecimales(Integer cantidadDecimales) {
		this.cantidadDecimales = cantidadDecimales;
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

	public Boolean getEsFavorito() {
		return esFavorito;
	}

	public void setEsFavorito(Boolean esFavorito) {
		this.esFavorito = esFavorito;
	}

	public Boolean getEsIdPago() {
		return esIdPago;
	}

	public void setEsIdPago(Boolean esIdPago) {
		this.esIdPago = esIdPago;
	}

	public Boolean getEsImpuesto() {
		return esImpuesto;
	}

	public void setEsImpuesto(Boolean esImpuesto) {
		this.esImpuesto = esImpuesto;
	}

	public Boolean getEsSeleccionable() {
		return esSeleccionable;
	}

	public void setEsSeleccionable(Boolean esSeleccionable) {
		this.esSeleccionable = esSeleccionable;
	}

	public Boolean getEsTotal() {
		return esTotal;
	}

	public void setEsTotal(Boolean esTotal) {
		this.esTotal = esTotal;
	}

	public FormatoFecha getFormatoFecha() {
		return formatoFecha;
	}

	public void setFormatoFecha(FormatoFecha formatoFecha) {
		this.formatoFecha = formatoFecha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIngresoObligatorioConsulta() {
		return ingresoObligatorioConsulta;
	}

	public void setIngresoObligatorioConsulta(Boolean ingresoObligatorioConsulta) {
		this.ingresoObligatorioConsulta = ingresoObligatorioConsulta;
	}

	public Boolean getIngresoObligatorioPago() {
		return ingresoObligatorioPago;
	}

	public void setIngresoObligatorioPago(Boolean ingresoObligatorioPago) {
		this.ingresoObligatorioPago = ingresoObligatorioPago;
	}

	public List<CobranzaCampoDescripcionBean> getListaDescripcion() {
		return listaDescripcion;
	}

	public void setListaDescripcion(List<CobranzaCampoDescripcionBean> listaDescripcion) {
		this.listaDescripcion = listaDescripcion;
	}

	public Integer getLongitud() {
		if(tipoDato == TipoDato.F){
			return getFormatoFechaConSeparador().length();
		}
		if(tipoDato == TipoDato.D){
			int comas = 0;
			for(int i=1 ; i<longitud; i++){
				if(i%3==0){
					comas++;
				}
			}
			return longitud+comas+2;
		}
		return longitud;
	}

	public void setLongitud(Integer longitud) {
		this.longitud = longitud;
	}

	public Integer getOrdenPantalla() {
		return ordenPantalla;
	}

	public void setOrdenPantalla(Integer ordenPantalla) {
		this.ordenPantalla = ordenPantalla;
	}

	public Boolean getPermiteIngresoConsulta() {
		return permiteIngresoConsulta;
	}

	public void setPermiteIngresoConsulta(Boolean permiteIngresoConsulta) {
		this.permiteIngresoConsulta = permiteIngresoConsulta;
	}

	public Boolean getPermiteIngresoPago() {
		return permiteIngresoPago;
	}

	public void setPermiteIngresoPago(Boolean permiteIngresoPago) {
		this.permiteIngresoPago = permiteIngresoPago;
	}

	public Integer getPosicionEnviaConsultaSaldo() {
		return posicionEnviaConsultaSaldo;
	}

	public void setPosicionEnviaConsultaSaldo(Integer posicionEnviaConsultaSaldo) {
		this.posicionEnviaConsultaSaldo = posicionEnviaConsultaSaldo;
	}

	public Integer getPosicionEnviaPago() {
		return posicionEnviaPago;
	}

	public void setPosicionEnviaPago(Integer posicionEnviaPago) {
		this.posicionEnviaPago = posicionEnviaPago;
	}

	public Integer getPosicionRecibeConsultaSaldo() {
		return posicionRecibeConsultaSaldo;
	}

	public void setPosicionRecibeConsultaSaldo(Integer posicionRecibeConsultaSaldo) {
		this.posicionRecibeConsultaSaldo = posicionRecibeConsultaSaldo;
	}

	public Integer getPosicionRecibePago() {
		return posicionRecibePago;
	}

	public void setPosicionRecibePago(Integer posicionRecibePago) {
		this.posicionRecibePago = posicionRecibePago;
	}

	public String getSeparadorFecha() {
		return separadorFecha;
	}

	public void setSeparadorFecha(String separadorFecha) {
		this.separadorFecha = separadorFecha;
	}

	public TipoDato getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(TipoDato tipoDato) {
		this.tipoDato = tipoDato;
	}

	public TipoImpuestoBean getTipoImpuesto() {
		return tipoImpuesto;
	}

	public void setTipoImpuesto(TipoImpuestoBean tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}

	public Boolean getVisibleConsulta() {
		return visibleConsulta;
	}

	public void setVisibleConsulta(Boolean visibleConsulta) {
		this.visibleConsulta = visibleConsulta;
	}

	public Boolean getVisiblePago() {
		return visiblePago;
	}

	public void setVisiblePago(Boolean visiblePago) {
		this.visiblePago = visiblePago;
	}

	public Boolean getVisibleResultado() {
		return visibleResultado;
	}

	public void setVisibleResultado(Boolean visibleResultado) {
		this.visibleResultado = visibleResultado;
	}

	public String getValorIngreso() {
		return valorIngreso;
	}

	public void setValorIngreso(String valorIngreso) {
		this.valorIngreso = valorIngreso;
	}

	public String getFormatoFechaConSeparador() {
		if(formatoFechaConSeparador == null){
			if(formatoFecha == FormatoFecha.AAAAMMDD){
				formatoFechaConSeparador = "yyyy"+separadorFecha+"MM"+separadorFecha+"dd";
			}else if(formatoFecha == FormatoFecha.DDMMAAAA){
				formatoFechaConSeparador = "dd"+separadorFecha+"MM"+separadorFecha+"yyyy";
			}else if(formatoFecha == FormatoFecha.MMDDAAAA){
				formatoFechaConSeparador = "MM"+separadorFecha+"dd"+separadorFecha+"yyyy";
			}
		}
		return formatoFechaConSeparador;
	}

	public void setFormatoFechaConSeparador(String formatoFechaConSeparador) {
		this.formatoFechaConSeparador = formatoFechaConSeparador;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public Boolean getEsCampoPagoTotal() {
		return esCampoPagoTotal;
	}

	public void setEsCampoPagoTotal(Boolean esCampoPagoTotal) {
		this.esCampoPagoTotal = esCampoPagoTotal;
	}

	public String getIdComponent() {
		return idComponent;
	}

	public void setIdComponent(String idComponent) {
		this.idComponent = idComponent;
	}

	public Double getPorcentajeImpuesto() {
		return porcentajeImpuesto;
	}

	public void setPorcentajeImpuesto(Double porcentajeImpuesto) {
		this.porcentajeImpuesto = porcentajeImpuesto;
	}

	public Integer getLongitudMinima() {
		return longitudMinima;
	}

	public void setLongitudMinima(Integer longitudMinima) {
		this.longitudMinima = longitudMinima;
	}

    public TipoCampoBean getTipoCampoBean() {
        return tipoCampoBean;
    }

    public void setTipoCampoBean(TipoCampoBean tipoCampoBean) {
        this.tipoCampoBean = tipoCampoBean;
    }

    public Boolean getEsIdConsulta() {
        return esIdConsulta;
    }

    public void setEsIdConsulta(Boolean esIdConsulta) {
        this.esIdConsulta = esIdConsulta;
    }
}
