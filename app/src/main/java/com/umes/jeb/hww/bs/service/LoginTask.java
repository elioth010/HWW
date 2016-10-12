package com.umes.jeb.hww.bs.service;

import java.io.IOException;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.eis.bo.BaseBO;
import com.umes.jeb.hww.eis.bo.UserToken;
import com.umes.jeb.hww.eis.dto.ProfileDTO;
import com.umes.jeb.hww.eis.dto.RolDTO;
import com.umes.jeb.hww.eis.dto.UsuarioDTO;
import com.umes.jeb.hww.security.SessionManager;
import com.umes.jeb.hww.view.activity.AbstractActivity;
import com.umes.jeb.hww.view.activity.HomeTabActivity;
import com.fasterxml.jackson.databind.ObjectMapper;


public class LoginTask extends AbstractGetTask<Void, Void, Boolean> {

	private static final String TAG = "LoginTask";
	protected AbstractActivity parentActivity;
	private ProgressDialog dialog;

	private String scope;
	private String user;
	private String password;
	private String grantType;

	public LoginTask(AbstractActivity parentActivity, String user, String password, String scope, String grantType) {
		super(parentActivity);
		this.parentActivity = parentActivity;
		this.scope = scope;
		this.user = user;
		this.password = password;
		this.grantType = grantType;
	}

	@Override
	protected void onPreExecute() {
		dialog = ProgressDialog.show(parentActivity, parentActivity.getResources().getString(R.string.app_progress_dialog_login_title), parentActivity.getResources().getString(R.string.app_progress_dialog_wait_message), true);
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		try {
			final String url = super.BASE_URL+"/login/entrar";
			RestTemplate restTemplate = new RestTemplate(super.clientHttpRequestFactory());
			HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
			HttpMessageConverter stringHttpMessageConverternew = new StringHttpMessageConverter();
			List<HttpMessageConverter<?>> converters = new ArrayList<>();
			converters.add(formHttpMessageConverter);
			converters.add(stringHttpMessageConverternew);
			restTemplate.setMessageConverters(converters);
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add("login", this.user);
			map.add("password", this.password);
			String response =restTemplate.postForObject(url, map, String.class);
			UsuarioDTO profileDTO = fetchUser(response, this.password);
			System.out.println(response);
			SessionManager manager = this.parentActivity.getSession();
			manager.setProfile(profileDTO);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			if(parentActivity.findAll(UserToken.class).size()>0){
				parentActivity.delete((BaseBO) parentActivity.findAll(UserToken.class).get(0));
			}
			UserToken userLogged = new UserToken(1, user, manager.getProfile().getPassword(), manager.getProfile().getLogin(), dateFormat.format(new GregorianCalendar().getTime()));
			this.parentActivity.save(userLogged);
			return true;
		} catch (Exception ex) {
			if (ex instanceof ResourceAccessException) {
				if (ex.getCause() instanceof ConnectException) {
					onError(this.parentActivity.getResources().getString(R.string.app_toast_error_server_connection_message), ex, true);
					return false;
				}else if(ex.getCause() instanceof ConnectTimeoutException){
					onError(this.parentActivity.getResources().getString(R.string.app_toast_error_server_timeout_message), ex, true);
					return false;
				}
			}
			if (ex instanceof HttpClientErrorException) {
				if (((HttpClientErrorException) ex).getStatusCode() == HttpStatus.UNAUTHORIZED) {
					System.out.println(((HttpClientErrorException) ex).getResponseBodyAsString());
					onError(this.parentActivity.getResources().getString(R.string.app_toast_error_login_message), ex, true);
					return false;
				}
			} else {
				onError(this.parentActivity.getResources().getString(R.string.app_toast_error_server_unknow_message), ex, false);
				return false;
			}
		}

		return false;
	}

	private UsuarioDTO fetchUser(String response, String password) throws IOException {
		HashMap<?, ?> myMap;
		ObjectMapper objectMapper = new ObjectMapper();
		UsuarioDTO usuario = null;
		try {
			myMap = objectMapper.readValue(response,HashMap.class);
			System.out.println("Map: " + myMap);
			HashMap<?,?> mapUser = (HashMap<?, ?>) ((ArrayList) myMap.get("data")).get(0);
			usuario = new UsuarioDTO();
			usuario.setId(Integer.parseInt(String.valueOf(mapUser.get("id"))));
			usuario.setRol(Integer.parseInt(String.valueOf(mapUser.get("rol"))));
			usuario.setDireccion(String.valueOf(mapUser.get("direccion")));
			usuario.setLogin(String.valueOf(mapUser.get("login")));
			usuario.setNombre(String.valueOf(mapUser.get("nombre")));
			usuario.setPassword(password);
			usuario.setTelefono(Integer.parseInt(String.valueOf(mapUser.get("telefono"))));
			usuario.setEstado(Integer.parseInt(String.valueOf(mapUser.get("estado"))));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return usuario;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		dialog.cancel();
		if(result){
			try {
				Intent intent = new Intent(parentActivity, HomeTabActivity.class);
				parentActivity.startActivity(intent);
				this.finalize();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void onError(String msg, Exception e, boolean handled) {
		if (e != null) {
			Log.e(TAG, "Exception: ", e);
		}
		if(handled){
			parentActivity.show(msg);
		}else{
			parentActivity.handleException(e);
		}
		return;// muestra mensaje de error
	}

}
