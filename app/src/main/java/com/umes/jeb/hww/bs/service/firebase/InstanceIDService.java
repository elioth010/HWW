package com.umes.jeb.hww.bs.service.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.umes.jeb.hww.eis.bo.TokenFCM;
import com.umes.jeb.hww.security.SessionManager;

import java.util.List;

/**
 * Created by elioth010 on 9/27/16.
 */

public class InstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "InstanceIDService";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        //Send token to server
        sendRegistrationToServer(refreshedToken);
    }


    /**
     * Persist token to third-party servers.
     *
     *
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // TODO: Call to php page to update token per user.
        TokenFCM tokenFCM = new TokenFCM(token);
        List<TokenFCM> tokens = ((SessionManager)getApplication()).getSugarDAO().findAll(TokenFCM.class);
        if(tokens!=null && tokens.size()>0){
            TokenFCM fcm = tokens.get(0);
            fcm.setToken(token);
            ((SessionManager)getApplication()).getSugarDAO().update(tokenFCM);
        }else{
            ((SessionManager)getApplication()).getSugarDAO().save(tokenFCM);
        }
    }
}
