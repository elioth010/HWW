
package com.umes.jeb.hww.view.bean;


public enum TipoMensaje {

    PI,
    CS,
    RE;

    public String value() {
        return name();
    }

    public static TipoMensaje fromValue(String v) {
        return valueOf(v);
    }

}
