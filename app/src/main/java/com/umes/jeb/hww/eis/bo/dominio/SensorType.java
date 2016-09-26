package com.umes.jeb.hww.eis.bo.dominio;

/**
 * Created by elioth010 on 9/26/16.
 */

public enum SensorType {
    BS("Breath Sensor"), PO("Pulse And Oxygen Sensor"), BP("Blood Pressure Sensor"), TP("Temperature Sensor");

    public String value;

    SensorType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
