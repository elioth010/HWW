package com.umes.jeb.hww.view.bean;

import com.umes.jeb.hww.eis.dto.CobranzaDTO;
import com.umes.jeb.hww.eis.dto.ProductoColectorDTO;
import com.umes.jeb.hww.eis.dto.ServicioDTO;
import com.umes.jeb.hww.eis.dto.TipoCobranza;
import com.umes.jeb.hww.eis.dto.TipoPago;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CobranzaBean implements Serializable{

	private static final long serialVersionUID = 3269273420026978012L;
	
	private String nombre;
	private Integer codigo;
	private Integer codigoCategoria;
	private String nombreCategoria;
	private String logoCategoria;
	private Integer codigoFacturador;
	private Boolean estado;
	private String logo;
	private String moneda;
	private String nombreFacturador;
	private String palabraClave;
	private List<ServicioBean> servicios;
	private TipoCobranza tipo;
	private TipoPago tipoPago;
	private String simboloMoneda;

	public CobranzaBean() {
		super();
	}
	
	public CobranzaBean(String nombre, Integer codigo,
			Integer codigoCategoria, Integer codigoFacturador, Boolean estado,
			String logo, String moneda, String nombreFacturador,
			String palabraClave, List<ServicioBean> servicios,
			TipoCobranza tipo, TipoPago tipoPago, String simboloMoneda) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.codigoCategoria = codigoCategoria;
		this.codigoFacturador = codigoFacturador;
		this.estado = estado;
		this.logo = logo;
		this.moneda = moneda;
		this.nombreFacturador = nombreFacturador;
		this.palabraClave = palabraClave;
		this.servicios = servicios;
		this.tipo = tipo;
		this.tipoPago = tipoPago;
		this.simboloMoneda = simboloMoneda;
	}
	
	public CobranzaBean(CobranzaDTO dto,List<ProductoColectorDTO> productos){
		this.nombre = dto.getNombre();
		this.codigo = dto.getCodigo();
		this.codigoCategoria = dto.getCodigoCategoria();
		this.nombreCategoria=dto.getNombreCategoria();
		this.logoCategoria=dto.getLogoCategoria();
		this.codigoFacturador = dto.getCodigoFacturador();
		this.estado = dto.isEstado();
		this.logo = dto.getLogo();
		this.moneda = dto.getMoneda();
		this.nombreFacturador = dto.getNombreFacturador();
		this.palabraClave = dto.getPalabraClave();
		this.servicios = new ArrayList<ServicioBean>();
		for(ServicioDTO servicio : dto.getServicios()){
			servicios.add(new ServicioBean(servicio,this.codigo,this.codigoFacturador,this.moneda,productos,this.nombreCategoria,this.logoCategoria));
		}
		if(servicios != null && servicios.size()>0){
		    Collections.sort(servicios, new Comparator<ServicioBean>() {
                @Override
                public int compare(ServicioBean o1, ServicioBean o2) {
                    return o1.getDescripcion().compareTo(o2.getDescripcion());
                }
            });
		}
		this.tipo = dto.getTipo();
		this.tipoPago = dto.getTipoPago();
		this.simboloMoneda = dto.getSimboloMoneda();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigoCategoria() {
		return codigoCategoria;
	}

	public void setCodigoCategoria(Integer codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	public Integer getCodigoFacturador() {
		return codigoFacturador;
	}

	public void setCodigoFacturador(Integer codigoFacturador) {
		this.codigoFacturador = codigoFacturador;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getNombreFacturador() {
		return nombreFacturador;
	}

	public void setNombreFacturador(String nombreFacturador) {
		this.nombreFacturador = nombreFacturador;
	}

	public String getPalabraClave() {
		return palabraClave;
	}

	public void setPalabraClave(String palabraClave) {
		this.palabraClave = palabraClave;
	}

	public List<ServicioBean> getServicios() {
		return servicios;
	}

	public void setServicios(List<ServicioBean> servicios) {
		this.servicios = servicios;
	}

	public TipoCobranza getTipo() {
		return tipo;
	}

	public void setTipo(TipoCobranza tipo) {
		this.tipo = tipo;
	}

	public TipoPago getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}

	public String getSimboloMoneda() {
		return simboloMoneda;
	}

	public void setSimboloMoneda(String simboloMoneda) {
		this.simboloMoneda = simboloMoneda;
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
