
package com.umes.jeb.hww.eis.dto;

public enum AccionCampo {

    S,
    R,
    N;

    public String value() {
        return name();
    }

    public static AccionCampo fromValue(String v) {
        return valueOf(v);
    }

}
