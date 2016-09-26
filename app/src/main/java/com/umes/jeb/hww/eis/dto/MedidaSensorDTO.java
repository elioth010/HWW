package com.umes.jeb.hww.eis.dto;

import java.io.Serializable;

/**
 * Created by elioth010 on 9/26/16.
 */

public class MedidaSensorDTO implements Serializable {
    private Integer id;
    private SensorDTO sensor;
    private UnidadMedidaDTO unidadMedida;
    private Integer estado;

    public MedidaSensorDTO(Integer id, SensorDTO sensor, UnidadMedidaDTO unidadMedida, Integer estado) {
        this.id = id;
        this.sensor = sensor;
        this.unidadMedida = unidadMedida;
        this.estado = estado;
    }

    public MedidaSensorDTO() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    public UnidadMedidaDTO getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedidaDTO unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
