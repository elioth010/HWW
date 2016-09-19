package com.umes.jeb.hww.bs.service;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.eis.bo.UserToken;
import com.umes.jeb.hww.eis.dto.InfoCobranzasResponseDTO;
import com.umes.jeb.hww.security.SessionManager;
import com.umes.jeb.hww.view.activity.AbstractActivity;
import com.umes.jeb.hww.view.activity.AutenticacionActivity;
import com.umes.jeb.hww.view.activity.CobranzaActivity;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InformacionCobranzaTask extends AbstractGetTask<Void, Void, InfoCobranzasResponseDTO> {
	private static final String TAG = "InformacionCobranzaTask";
	protected AbstractActivity parentActivity;

	private String token;
	private String tokenType;
	private String user;
	private String colector;
	private String idioma;
	private String canal;
	private InfoCobranzasResponseDTO infoCobranzasResponseDTO;

	public InformacionCobranzaTask(AbstractActivity parentActivity, String token, String user, String tokenType) {
		super(parentActivity);
		this.parentActivity = parentActivity;
		this.token=token;
		this.user = user;
		this.tokenType = tokenType;
		this.colector= parentActivity.COLECTOR;
		this.canal=parentActivity.CANAL;
		this.idioma=parentActivity.IDIOMA;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected InfoCobranzasResponseDTO doInBackground(Void... params) {
		try {
			final String url = super.BASE_URL+"/convenios/cobranzas?colector="+colector+"&canal="+canal+"&idioma="+idioma+"&aliasUsuario="+user;
			System.out.println(url);
			RestTemplate restTemplate = new RestTemplate(super.clientHttpRequestFactory());
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			ParameterizedTypeReference<InfoCobranzasResponseDTO> paramTypeReference = new ParameterizedTypeReference<InfoCobranzasResponseDTO>() {};

			ResponseEntity<InfoCobranzasResponseDTO> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<InfoCobranzasResponseDTO>(getBearerHeaders(tokenType, token)), paramTypeReference);
			//ObjectMapper mapper = new ObjectMapper();
			//String responseString= response.getBody();
			//infoCobranzasResponseDTO = mapper.readValue(responseString, InfoCobranzasResponseDTO.class);
			infoCobranzasResponseDTO = response.getBody();
			return infoCobranzasResponseDTO;
		} catch (Exception ex) {
			if (ex instanceof ResourceAccessException) {
				if (ex.getCause() instanceof ConnectException) {
					onError(this.parentActivity.getResources().getString(R.string.app_toast_error_server_connection_message),ex);
					infoCobranzasResponseDTO = new InfoCobranzasResponseDTO();
					return infoCobranzasResponseDTO;
				}else if(ex.getCause() instanceof ConnectTimeoutException){
					onError(this.parentActivity.getResources().getString(R.string.app_toast_error_server_timeout_message), ex);
					return null;
				}
			}
			if (ex instanceof HttpClientErrorException) {
				if (((HttpClientErrorException) ex).getStatusCode() == HttpStatus.UNAUTHORIZED) {
					System.out.println(((HttpClientErrorException) ex).getResponseBodyAsString());
					onError(this.parentActivity.getResources().getString(R.string.app_toast_error_session_expired), ex);
					infoCobranzasResponseDTO = new InfoCobranzasResponseDTO();
					Intent intent = new Intent(this.parentActivity, AutenticacionActivity.class);
					this.parentActivity.startActivity(intent);
					this.parentActivity.finish();
					return infoCobranzasResponseDTO;
				}
			} else {
				onError(this.parentActivity.getResources().getString(R.string.app_toast_error_server_unknow_message), ex);
				infoCobranzasResponseDTO = new InfoCobranzasResponseDTO();
				return infoCobranzasResponseDTO;
			}
		}

		return infoCobranzasResponseDTO;
	}

	@Override
	protected void onError(String msg, Exception e) {
		if (e != null) {
			Log.e(TAG, "Exception: ", e);
		}
		parentActivity.show(msg);
		parentActivity.handleException(e); // muestra mensaje de error
	}

	@Override
	protected void onPostExecute(InfoCobranzasResponseDTO result) {
		super.onPostExecute(result);
	}

}
