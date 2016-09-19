
package com.umes.jeb.hww.eis.dto;


public enum TipoCobranza {

    L,
    O;

    public String value() {
        return name();
    }

    public static TipoCobranza fromValue(String v) {
        return valueOf(v);
    }

}
