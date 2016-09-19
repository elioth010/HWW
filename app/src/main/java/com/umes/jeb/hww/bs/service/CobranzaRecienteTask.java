package com.umes.jeb.hww.bs.service;

import android.content.Intent;
import android.util.Log;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.eis.dto.CamposCobranzaDTO;
import com.umes.jeb.hww.eis.dto.CobranzaUsoRecienteDTO;
import com.umes.jeb.hww.eis.dto.PayBillRequestDTO;
import com.umes.jeb.hww.view.activity.AbstractActivity;
import com.umes.jeb.hww.view.activity.AutenticacionActivity;
import com.umes.jeb.hww.view.bean.CobranzaCampoBean;

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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.ConnectException;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Date;


public class CobranzaRecienteTask extends AbstractGetTask<String, Void, Integer> {
	private static final String TAG = "CobranzaRecienteTask";
	protected AbstractActivity parentActivity;

	private String token;
	private String tokenType;
	private String user;
	private String colector;
	private String idioma;
	private String canal;
	private Integer response;
	private Integer cobranza;
	private Date horaFecha;

	public CobranzaRecienteTask(AbstractActivity parentActivity, String token, String user, String tokenType, Integer cobranza,Date horaFecha) {
		super(parentActivity);
		this.parentActivity = parentActivity;
		this.token=token;
		this.user = user;
		this.tokenType = tokenType;
		this.colector= parentActivity.COLECTOR;
		this.canal=parentActivity.CANAL;
		this.idioma=parentActivity.IDIOMA;
		this.cobranza=cobranza;
		this.horaFecha=horaFecha;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected Integer doInBackground(String... params) {
		try {
			final String url = super.BASE_URL+"/convenios/cobranzas/save/cobranzaReciente";
			System.out.println(url);
			RestTemplate restTemplate = new RestTemplate(super.clientHttpRequestFactory());
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			ParameterizedTypeReference<Integer> paramTypeReference = new ParameterizedTypeReference<Integer>() {};
			CobranzaUsoRecienteDTO request = new CobranzaUsoRecienteDTO();
			request.setColector(new Integer(colector));
			request.setAliasUsuario(user);
			request.setCobranza(cobranza);
			request.setHoraFecha(horaFecha);

			ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<CobranzaUsoRecienteDTO>(request,getBearerHeaders(tokenType, token)), paramTypeReference);

			Integer resp = response.getBody();
			return resp;
		} catch (Exception ex) {
			if (ex instanceof ResourceAccessException) {
				if (ex.getCause() instanceof ConnectException) {
					onError(this.parentActivity.getResources().getString(R.string.app_toast_error_server_connection_message),ex);
					response = new Integer("0");
					return response;
				}else if(ex.getCause() instanceof ConnectTimeoutException){
					onError(this.parentActivity.getResources().getString(R.string.app_toast_error_server_timeout_message), ex);
					return null;
				}
			}
			if (ex instanceof HttpClientErrorException) {
				if (((HttpClientErrorException) ex).getStatusCode() == HttpStatus.UNAUTHORIZED) {
					System.out.println(((HttpClientErrorException) ex).getResponseBodyAsString());
					onError(this.parentActivity.getResources().getString(R.string.app_toast_error_session_expired), ex);
					response = new Integer("0");
					Intent intent = new Intent(this.parentActivity, AutenticacionActivity.class);
					this.parentActivity.startActivity(intent);
					this.parentActivity.finish();
					return response;
				}
			} else {
				onError(this.parentActivity.getResources().getString(R.string.app_toast_error_server_unknow_message), ex);
				response = new Integer("0");
				return response;
			}
		}

		return response;
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
	protected void onPostExecute(Integer result) {
		super.onPostExecute(result);
	}

}
