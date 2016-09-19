package com.umes.jeb.hww.bs.service.sugar;



import com.umes.jeb.hww.eis.bo.BaseBO;
import com.umes.jeb.hww.util.persistence.ListaParametrosDTO;

import java.util.List;

public interface SugarService {
	public <T> BaseBO save(BaseBO baseBO);

	public void update(BaseBO baseBO);

	public void delete(BaseBO baseBO);

	public <T> BaseBO findById(Class<? extends BaseBO> clazz, Integer id);

	public <T> List<T> findAll(Class<? extends BaseBO> clazz);

	public <T> List<T> findBySQLQuery(Class<? extends BaseBO> clazz, String query);

	public <T> List<T> findByClassWithParameters(Class<? extends BaseBO> clazz, ListaParametrosDTO parametros);
}
