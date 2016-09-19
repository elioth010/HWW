
package com.umes.jeb.hww.eis.dto;


public enum TipoDato {

    A,
    N,
    D,
    F;

    public String value() {
        return name();
    }

    public static TipoDato fromValue(String v) {
        return valueOf(v);
    }

}
