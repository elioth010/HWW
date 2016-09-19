package com.umes.jeb.hww.bs.service;

import android.content.Intent;
import android.text.format.Formatter;
import android.util.Log;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.eis.dto.CamposCobranzaDTO;
import com.umes.jeb.hww.eis.dto.GetBalanceRequestDTO;
import com.umes.jeb.hww.eis.dto.GetBalanceResponseDTO;
import com.umes.jeb.hww.eis.dto.GetBalanceResponseDTO;
import com.umes.jeb.hww.view.activity.AbstractActivity;
import com.umes.jeb.hww.view.activity.AutenticacionActivity;
import com.umes.jeb.hww.view.activity.ConsultaActivity;
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

import java.net.ConnectException;
import java.net.Inet4Address;
import java.util.ArrayList;


public class ConsultaServicioTask extends AbstractGetTask<String, Void, GetBalanceResponseDTO> {
	private static final String TAG = "ConsultaServicioTask";
	protected AbstractActivity parentActivity;

	private String token;
	private String tokenType;
	private String user;
	private String colector;
	private String idioma;
	private String canal;
	private GetBalanceResponseDTO getBalanceResponseDTO;
	private ArrayList<CobranzaCampoBean> listaCampos;
	private String idConsulta;

	public ConsultaServicioTask(AbstractActivity parentActivity, String token, String user, String tokenType,ArrayList<CobranzaCampoBean> listaCampos) {
		super(parentActivity);
		this.parentActivity = parentActivity;
		this.token=token;
		this.user = user;
		this.tokenType = tokenType;
		this.colector= parentActivity.COLECTOR;
		this.canal=parentActivity.CANAL;
		this.idioma=parentActivity.IDIOMA;
		this.listaCampos=listaCampos;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected GetBalanceResponseDTO doInBackground(String... params) {
		try {
			final String url = super.BASE_URL+"/convenios/balance";
			System.out.println(url);
			RestTemplate restTemplate = new RestTemplate(super.clientHttpRequestFactory());
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			ParameterizedTypeReference<GetBalanceResponseDTO> paramTypeReference = new ParameterizedTypeReference<GetBalanceResponseDTO>() {};
			GetBalanceRequestDTO request = new GetBalanceRequestDTO();
			request.setCobranza(new Integer(params[0].toString()));
			request.setCodigoIdioma(idioma);
			request.setColector(new Integer(colector));
			request.setWebSession(this.tokenType);
			request.setIp(Inet4Address.getLocalHost().getHostAddress());

			request.setCamposCobranza(getCamposCobranza(listaCampos));
			request.setFacturador(new Integer(params[1].toString()));
			request.setServicio(new Integer(params[2].toString()));
			request.setIdConsulta(idConsulta);

			ResponseEntity<GetBalanceResponseDTO> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<GetBalanceRequestDTO>(request,getBearerHeaders(tokenType, token)), paramTypeReference);

			getBalanceResponseDTO = response.getBody();
			if(getBalanceResponseDTO != null && getBalanceResponseDTO.getError() != null){
				if(getBalanceResponseDTO.getError().getCodigo()!= null && getBalanceResponseDTO.getError().getCodigo()!=0){
					System.out.println("Codigo de error: >>>>>> " +getBalanceResponseDTO.getError().getCodigo() + "-" +getBalanceResponseDTO.getError().getDescripcion() );
					parentActivity.show(getBalanceResponseDTO.getError().getDescripcion());
				}
			}
			return getBalanceResponseDTO;
		} catch (Exception ex) {
			if (ex instanceof ResourceAccessException) {
				if (ex.getCause() instanceof ConnectException) {
					onError(this.parentActivity.getResources().getString(R.string.app_toast_error_server_connection_message),ex);
					getBalanceResponseDTO = new GetBalanceResponseDTO();
					return getBalanceResponseDTO;
				}else if(ex.getCause() instanceof ConnectTimeoutException){
					onError(this.parentActivity.getResources().getString(R.string.app_toast_error_server_timeout_message), ex);
					return null;
				}
			}
			if (ex instanceof HttpClientErrorException) {
				if (((HttpClientErrorException) ex).getStatusCode() == HttpStatus.UNAUTHORIZED) {
					System.out.println(((HttpClientErrorException) ex).getResponseBodyAsString());
					onError(this.parentActivity.getResources().getString(R.string.app_toast_error_session_expired), ex);
					getBalanceResponseDTO = new GetBalanceResponseDTO();
					Intent intent = new Intent(this.parentActivity, AutenticacionActivity.class);
					this.parentActivity.startActivity(intent);
					this.parentActivity.finish();
					return getBalanceResponseDTO;
				}
			} else {
				onError(this.parentActivity.getResources().getString(R.string.app_toast_error_server_unknow_message), ex);
				getBalanceResponseDTO = new GetBalanceResponseDTO();
				return getBalanceResponseDTO;
			}
		}

		return getBalanceResponseDTO;
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
	protected void onPostExecute(GetBalanceResponseDTO result) {
		//super.onPostExecute(result);
		if(super.getDialog() != null){
			super.getDialog().dismiss();
		}
		((ConsultaActivity)this.parentActivity).afterOnConsultaServicioTask(result);
	}

	private CamposCobranzaDTO getCamposCobranza(ArrayList<CobranzaCampoBean> listaCampos){
		CamposCobranzaDTO campos = new CamposCobranzaDTO();
		if(listaCampos != null && listaCampos.size()>0){
			for(CobranzaCampoBean cam : listaCampos){
				if(null != cam.getPosicionEnviaConsultaSaldo()){
					if(cam.getEsIdConsulta()){
						idConsulta = cam.getValorIngreso();
					}
					switch (cam.getPosicionEnviaConsultaSaldo()){
						case 1:
							campos.setCampo1(cam.getValorIngreso());
							break;
						case 2:
							campos.setCampo2(cam.getValorIngreso());
							break;
						case 3:
							campos.setCampo3(cam.getValorIngreso());
							break;
						case 4:
							campos.setCampo4(cam.getValorIngreso());
							break;
						case 5:
							campos.setCampo5(cam.getValorIngreso());
							break;
						case 6:
							campos.setCampo6(cam.getValorIngreso());
							break;
						case 7:
							campos.setCampo7(cam.getValorIngreso());
							break;
						case 8:
							campos.setCampo8(cam.getValorIngreso());
							break;
						case 9:
							campos.setCampo9(cam.getValorIngreso());
							break;
						case 10:
							campos.setCampo10(cam.getValorIngreso());
							break;
					}
				}
			}
		}
		return campos;
	}
}
