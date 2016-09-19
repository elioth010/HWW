package com.umes.jeb.hww.util.validator;

import com.umes.jeb.hww.view.bean.CobranzaCampoBean;

/**
 * Created by elioth010 on 4/15/16.
 */
public class ValidatorRegex {
    public static final String REGEX_CHARACTER_AND_SPECIALS = "([a-z]|[A-Z]|[<>%\\$])";
    public static final String REGEX_COMA = ",";
    public static final String REGEX_DOUBLE = "(^(\\d*[0-9]\\.\\d*)\\d+$|^(\\d*[0-9]+$))";
    public static final String REGEX_DATE = "((0[1-9]|[12][0-9]|3[01])(\\W)(0[1-9]|1[012])(\\W)\\d\\d\\d\\d)";
    public static final String REGEX_NUMERIC = "\\d+\\d$";

    public static String validateField(CobranzaCampoBean bean) {
        if (!(bean.getValorIngreso() != null && !bean.getValorIngreso().isEmpty())) {
            return "El campo: " + bean.getDescripcion() + " es obligatorio su ingreso";
        }

        if(bean.getLongitudMinima()==null){
            bean.setLongitudMinima(0);
        }
        switch (bean.getTipoDato()) {
            case N:
                if (!bean.getValorIngreso().matches(ValidatorRegex.REGEX_NUMERIC)) {
                    return "El campo: " + bean.getDescripcion() + " debe tener unicamente valores numericos";
                }
                if (bean.getValorIngreso().length() < bean.getLongitudMinima()) {
                    return "El campo: " + bean.getDescripcion() + " debe tener " + bean.getLongitudMinima() + " digitos";
                }
                break;

            case D:
                if (!bean.getValorIngreso().matches(ValidatorRegex.REGEX_DOUBLE)) {
                    return "El campo: " + bean.getDescripcion() + " debe tener unicamente valores decimales";
                }
                if (bean.getValorIngreso().length() < bean.getLongitudMinima()) {
                    return "El campo: " + bean.getDescripcion() + " debe tener " + bean.getLongitudMinima() + " digitos";
                }
                break;

            case F:
                if (!bean.getValorIngreso().matches(ValidatorRegex.REGEX_DATE)) {
                    return "El campo: " + bean.getDescripcion() + " debe tener unicamente valores tipo fecha";
                }
                if (bean.getValorIngreso().length() < bean.getLongitudMinima()) {
                    return "El campo: " + bean.getDescripcion() + " debe tener " + bean.getLongitudMinima() + " caracteres";
                }

                break;

            default:
                if (bean.getValorIngreso().matches(ValidatorRegex.REGEX_CHARACTER_AND_SPECIALS)) {
                    return "El campo :" + bean.getDescripcion() + " no debe tener caracteres especiales";
                }
                if (bean.getValorIngreso().length() < bean.getLongitudMinima()) {
                    return "El campo: " + bean.getDescripcion() + " debe tener " + bean.getLongitudMinima() + " caracteres";
                }

                break;
        }

        return null;
    }
}
