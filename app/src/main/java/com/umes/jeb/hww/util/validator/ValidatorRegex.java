package com.umes.jeb.hww.util.validator;

/**
 * Created by elioth010 on 4/15/16.
 */
public class ValidatorRegex {
    public static final String REGEX_CHARACTER_AND_SPECIALS = "([a-z]|[A-Z]|[<>%\\$])";
    public static final String REGEX_COMA = ",";
    public static final String REGEX_DOUBLE = "(^(\\d*[0-9]\\.\\d*)\\d+$|^(\\d*[0-9]+$))";
    public static final String REGEX_DATE = "((0[1-9]|[12][0-9]|3[01])(\\W)(0[1-9]|1[012])(\\W)\\d\\d\\d\\d)";
    public static final String REGEX_NUMERIC = "\\d+\\d$";


}
