package com.umes.jeb.hww.view.bean;

import com.umes.jeb.hww.eis.dto.BitacoraDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ccalito on 07/04/2016.
 */
public class HomeBean implements Serializable {

    private String nombre;
    private String imagen;
    private List<BitacoraDTO> historialBeanList = new ArrayList<BitacoraDTO>();
    private SensorBean sensorBean;

    public HomeBean(String nombre, String imagen, List<BitacoraDTO> historialBeanList, SensorBean sensorBean) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.historialBeanList = historialBeanList;
        this.sensorBean = sensorBean;
    }

    public HomeBean() {
        super();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<BitacoraDTO> getHistorialBeanList() {
        return historialBeanList;
    }

    public void setHistorialBeanList(List<BitacoraDTO> historialBeanList) {
        this.historialBeanList = historialBeanList;
    }

    public SensorBean getSensorBean() {
        return sensorBean;
    }

    public void setSensorBean(SensorBean sensorBean) {
        this.sensorBean = sensorBean;
    }
}
