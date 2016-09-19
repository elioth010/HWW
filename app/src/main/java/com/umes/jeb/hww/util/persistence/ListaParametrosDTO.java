package com.umes.jeb.hww.util.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaParametrosDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Parametro>	parametros = new ArrayList<Parametro>();
	
	/**
	 * @return the parametros
	 */
	public List<Parametro> getParametros() {
		return parametros;
	}
}
