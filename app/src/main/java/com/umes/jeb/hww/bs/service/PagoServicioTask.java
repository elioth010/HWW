package com.umes.jeb.hww.bs.service;

import android.content.Intent;
import android.util.Log;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.eis.dto.CamposCobranzaDTO;
import com.umes.jeb.hww.eis.dto.PayBillRequestDTO;
import com.umes.jeb.hww.eis.dto.PayBillResponseDTO;
import com.umes.jeb.hww.eis.dto.PayBillResponseDTO;
import com.umes.jeb.hww.view.activity.AbstractActivity;
import com.umes.jeb.hww.view.activity.AutenticacionActivity;
import com.umes.jeb.hww.view.activity.ConfirmaPagoActivity;
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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.ConnectException;
import java.net.Inet4Address;
import java.util.ArrayList;


public class PagoServicioTask extends AbstractGetTask<String, Void, PayBillResponseDTO> {
	private static final String TAG = "PagoServicioTask";
	protected AbstractActivity parentActivity;

	private String token;
	private String tokenType;
	private String user;
	private String colector;
	private String idioma;
	private String canal;
	private PayBillResponseDTO payBillResponseDTO;
	private ArrayList<CobranzaCampoBean> listaCampos;
	private String idPago;

	public PagoServicioTask(AbstractActivity parentActivity, String token, String user, String tokenType, ArrayList<CobranzaCampoBean> listaCampos) {
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
	protected PayBillResponseDTO doInBackground(String... params) {
		try {
			final String url = super.BASE_URL+"/convenios/payBill";
			System.out.println(url);
			RestTemplate restTemplate = new RestTemplate(super.clientHttpRequestFactory());
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			ParameterizedTypeReference<PayBillResponseDTO> paramTypeReference = new ParameterizedTypeReference<PayBillResponseDTO>() {};
			PayBillRequestDTO request = new PayBillRequestDTO();
			request.setColector(new Integer(colector));
			request.setWebSession(this.tokenType);
			request.setIp(Inet4Address.getLocalHost().getHostAddress());

			//request.setAutorizacionDebitoColector();
			request.setCamposCobranza(getCamposCobranza(listaCampos));
			request.setIdPago(idPago);
			request.setCodigoIdioma(idioma);
			request.setCobranza(new Integer(params[0].toString()));
			request.setFacturador(new Integer(params[1].toString()));
			request.setServicio(new Integer(params[2].toString()));
			request.setMoneda(params[3].toString());
			request.setNumeroCuenta(new BigInteger(params[4].toString()));
			request.setProducto(new Integer(params[5].toString()));

			request.setSubTotal(new BigDecimal(params[6].toString()));
			request.setImpuestos(new BigDecimal(params[7].toString()));
			request.setTotal(new BigDecimal(params[8].toString()));


			ResponseEntity<PayBillResponseDTO> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<PayBillRequestDTO>(request,getBearerHeaders(tokenType, token)), paramTypeReference);

			payBillResponseDTO = response.getBody();
			if(payBillResponseDTO != null && payBillResponseDTO.getError() != null){
				if(payBillResponseDTO.getError().getCodigo()!= null && payBillResponseDTO.getError().getCodigo()!=0){
					System.out.println("Codigo de error: >>>>>> " +payBillResponseDTO.getError().getCodigo() + "-" +payBillResponseDTO.getError().getDescripcion() );
					parentActivity.show(payBillResponseDTO.getError().getDescripcion());
				}
			}
			return payBillResponseDTO;
		} catch (Exception ex) {
			if (ex instanceof ResourceAccessException) {
				if (ex.getCause() instanceof ConnectException) {
					onError(this.parentActivity.getResources().getString(R.string.app_toast_error_server_connection_message),ex);
					payBillResponseDTO = new PayBillResponseDTO();
					return payBillResponseDTO;
				}else if(ex.getCause() instanceof ConnectTimeoutException){
					onError(this.parentActivity.getResources().getString(R.string.app_toast_error_server_timeout_message), ex);
					return null;
				}
			}
			if (ex instanceof HttpClientErrorException) {
				if (((HttpClientErrorException) ex).getStatusCode() == HttpStatus.UNAUTHORIZED) {
					System.out.println(((HttpClientErrorException) ex).getResponseBodyAsString());
					onError(this.parentActivity.getResources().getString(R.string.app_toast_error_session_expired), ex);
					payBillResponseDTO = new PayBillResponseDTO();
					Intent intent = new Intent(this.parentActivity, AutenticacionActivity.class);
					this.parentActivity.startActivity(intent);
					this.parentActivity.finish();
					return payBillResponseDTO;
				}
			} else {
				onError(this.parentActivity.getResources().getString(R.string.app_toast_error_server_unknow_message), ex);
				payBillResponseDTO = new PayBillResponseDTO();
				return payBillResponseDTO;
			}
		}

		return payBillResponseDTO;
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
	protected void onPostExecute(PayBillResponseDTO result) {
		super.onPostExecute(result);
	/*	if(super.getDialog() != null){
			super.getDialog().dismiss();
		}
		((ConfirmaPagoActivity)this.parentActivity).afterOnPagoServicioTask(result);*/
	}

	private CamposCobranzaDTO getCamposCobranza(ArrayList<CobranzaCampoBean> listaCampos){
		CamposCobranzaDTO campos = new CamposCobranzaDTO();
		if(listaCampos != null && listaCampos.size()>0){
			for(CobranzaCampoBean cam : listaCampos){
				if(null != cam.getPosicionEnviaPago()){
					if(cam.getEsIdPago()){
						idPago = cam.getValorIngreso();
					}
					switch (cam.getPosicionEnviaPago()){
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
