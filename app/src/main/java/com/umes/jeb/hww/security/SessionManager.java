package com.umes.jeb.hww.security;


import com.umes.jeb.hww.bs.dao.SugarDAO;
import com.umes.jeb.hww.bs.dao.impl.SugarDAOImpl;
import com.orm.SugarApp;
import com.umes.jeb.hww.eis.dto.ProfileDTO;
import com.umes.jeb.hww.eis.dto.UsuarioDTO;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

public class SessionManager extends SugarApp {

	private UsuarioDTO profile;
	
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

	public UsuarioDTO getProfile() {
		return profile;
	}

	public void setProfile(UsuarioDTO profile) {
		this.profile = profile;
	}

	public SugarDAO getSugarDAO(){
		return this.sugarDAO;
	}

}
