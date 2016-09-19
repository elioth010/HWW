
package com.umes.jeb.hww.eis.dto;


public enum TipoPago {

    STO,
    PMI,
    CVA,
    IMA,
    IME;

    public String value() {
        return name();
    }

    public static TipoPago fromValue(String v) {
        return valueOf(v);
    }

}
