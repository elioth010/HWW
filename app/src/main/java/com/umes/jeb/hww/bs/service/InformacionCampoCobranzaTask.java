package com.umes.jeb.hww.bs.service;

import android.content.Intent;
import android.util.Log;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.eis.dto.InfoServicioCobranzaResponseDTO;
import com.umes.jeb.hww.eis.dto.InfoServicioCobranzaResponseDTO;
import com.umes.jeb.hww.view.activity.AbstractActivity;
import com.umes.jeb.hww.view.activity.AutenticacionActivity;

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

import java.net.ConnectException;


public class InformacionCampoCobranzaTask extends AbstractGetTask<String, Void, InfoServicioCobranzaResponseDTO> {
	private static final String TAG = "InformacionCobranzaTask";
	protected AbstractActivity parentActivity;

	private String token;
	private String tokenType;
	private String user;
	private String colector;
	private String idioma;
	private String canal;
	private InfoServicioCobranzaResponseDTO infoServicioCobranzaResponseDTO;

	public InformacionCampoCobranzaTask(AbstractActivity parentActivity, String token, String user, String tokenType) {
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
	protected InfoServicioCobranzaResponseDTO doInBackground(String... params) {
		try {
			final String url = super.BASE_URL+"/convenios/cobranzas/servicios?colector="+colector+"&cobranza="+params[0]+"&idServicio="+params[1]+"&servicio="+params[2]+
					"&versionCampos="+params[3]+"&idioma="+idioma+"&aliasUsuario="+user;
			System.out.println(url);
			RestTemplate restTemplate = new RestTemplate(super.clientHttpRequestFactory());
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			ParameterizedTypeReference<InfoServicioCobranzaResponseDTO> paramTypeReference = new ParameterizedTypeReference<InfoServicioCobranzaResponseDTO>() {};

			ResponseEntity<InfoServicioCobranzaResponseDTO> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<InfoServicioCobranzaResponseDTO>(getBearerHeaders(tokenType, token)), paramTypeReference);
			//ObjectMapper mapper = new ObjectMapper();
			//String responseString= response.getBody();
			//infoServicioCobranzaResponseDTO = mapper.readValue(responseString, InfoServicioCobranzaResponseDTO.class);
			infoServicioCobranzaResponseDTO = response.getBody();
			return infoServicioCobranzaResponseDTO;
		} catch (Exception ex) {
			if (ex instanceof ResourceAccessException) {
				if (ex.getCause() instanceof ConnectException) {
					onError(this.parentActivity.getResources().getString(R.string.app_toast_error_server_connection_message),ex);
					infoServicioCobranzaResponseDTO = new InfoServicioCobranzaResponseDTO();
					return infoServicioCobranzaResponseDTO;
				}else if(ex.getCause() instanceof ConnectTimeoutException){
					onError(this.parentActivity.getResources().getString(R.string.app_toast_error_server_timeout_message), ex);
					return null;
				}
			}
			if (ex instanceof HttpClientErrorException) {
				if (((HttpClientErrorException) ex).getStatusCode() == HttpStatus.UNAUTHORIZED) {
					System.out.println(((HttpClientErrorException) ex).getResponseBodyAsString());
					onError(this.parentActivity.getResources().getString(R.string.app_toast_error_session_expired), ex);
					infoServicioCobranzaResponseDTO = new InfoServicioCobranzaResponseDTO();
					Intent intent = new Intent(this.parentActivity, AutenticacionActivity.class);
					this.parentActivity.startActivity(intent);
					this.parentActivity.finish();
					return infoServicioCobranzaResponseDTO;
				}
			} else {
				onError(this.parentActivity.getResources().getString(R.string.app_toast_error_server_unknow_message), ex);
				infoServicioCobranzaResponseDTO = new InfoServicioCobranzaResponseDTO();
				return infoServicioCobranzaResponseDTO;
			}
		}

		return infoServicioCobranzaResponseDTO;
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
	protected void onPostExecute(InfoServicioCobranzaResponseDTO result) {
		super.onPostExecute(result);
	}

}
