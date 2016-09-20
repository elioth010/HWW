package com.umes.jeb.hww.security;


import com.umes.jeb.hww.bs.dao.SugarDAO;
import com.umes.jeb.hww.bs.dao.impl.SugarDAOImpl;
import com.orm.SugarApp;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

public class SessionManager extends SugarApp {

	private String token;
	private String tokenType;
	private String user;
	
	private SugarDAO sugarDAO;

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
        MultiDex.install(this);
    }

	@Override
	public void onCreate() {
		super.onCreate();
		sugarDAO = new SugarDAOImpl();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public SugarDAO getSugarDAO(){
		return this.sugarDAO;
	}

}
