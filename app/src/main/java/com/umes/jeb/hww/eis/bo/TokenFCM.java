package com.umes.jeb.hww.eis.bo;

/**
 * Created by elioth010 on 9/27/16.
 */

public class TokenFCM extends BaseBO<TokenFCM> {
    private String token;

    public TokenFCM() {
        super();
    }

    public TokenFCM(String token) {
        this.token = token;
    }

    public TokenFCM(String token, Long id) {
        this.token = token;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
