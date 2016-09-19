
package com.umes.jeb.hww.eis.dto;

public enum FormatoFecha {

    DDMMAAAA,
    AAAAMMDD,
    MMDDAAAA;

    public String value() {
        return name();
    }

    public static FormatoFecha fromValue(String v) {
        return valueOf(v);
    }

}
