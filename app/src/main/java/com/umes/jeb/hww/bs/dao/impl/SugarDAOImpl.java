package com.umes.jeb.hww.bs.dao.impl;

import com.umes.jeb.hww.bs.dao.SugarDAO;
import com.umes.jeb.hww.eis.bo.BaseBO;
import com.umes.jeb.hww.util.persistence.ListaParametrosDTO;
import com.umes.jeb.hww.util.persistence.Parametro;
import com.orm.query.Select;

import java.util.List;



public class SugarDAOImpl implements SugarDAO {

    public SugarDAOImpl() {
        super();
    }

    @Override
    public <T> BaseBO save(BaseBO baseBO) {
        baseBO.save();
        return baseBO;
    }

    @Override
    public void update(BaseBO baseBO) {
        baseBO.save();
    }

    @Override
    public void delete(BaseBO baseBO) {
        baseBO.delete();
    }

    @Override
    public <T> BaseBO findById(Class<? extends BaseBO> clazz, Integer id) {
        return BaseBO.findById(clazz, id.longValue());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> findAll(Class<? extends BaseBO> clazz) {
        return (List<T>) Select.from(clazz).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> findBySQLQuery(Class<? extends BaseBO> clazz, String query) {
        return (List<T>) BaseBO.findWithQuery(clazz, query);
    }

    @Override
    public <T> List<T> findByClassWithParameters(Class<? extends BaseBO> clazz, ListaParametrosDTO parametros) {
        String whereClause = "";
        String values[] = new String [parametros.getParametros().size()];
        int count = 0;
        for(Parametro parametro: parametros.getParametros()){
            whereClause += parametro.getName()+" ";
            values[count] = parametro.getValue().toString();
            count++;
        }
        return (List<T>) BaseBO.find(clazz, whereClause,values);
    }

}
