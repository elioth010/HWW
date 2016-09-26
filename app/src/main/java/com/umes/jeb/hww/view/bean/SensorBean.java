package com.umes.jeb.hww.view.bean;

import com.umes.jeb.hww.eis.bo.dominio.SensorType;

import java.io.Serializable;

/**
 * Created by elioth010 on 9/26/16.
 */

public class SensorBean implements Serializable{
    private SensorType type;
    private String image;
    private String nombre;

    public SensorBean(SensorType type) {
        this.type = type;
    }

    public SensorBean() {
        super();
    }

    public SensorType getType() {
        return type;
    }

    public void setType(SensorType type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
