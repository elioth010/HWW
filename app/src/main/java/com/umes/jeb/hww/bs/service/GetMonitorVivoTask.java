package com.umes.jeb.hww.bs.service;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.eis.dto.BitacoraDTO;
import com.umes.jeb.hww.view.activity.AbstractActivity;
import com.umes.jeb.hww.view.activity.AutenticacionActivity;

import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by elioth010 on 10/10/16.
 */

public class GetMonitorVivoTask extends AbstractGetTask<Void, Void, List<List<BitacoraDTO>>> {

    protected AbstractActivity parentActivity;
    protected List<List<BitacoraDTO>> bitacora;
    public GetMonitorVivoTask(AbstractActivity parentActivity) {
        super(parentActivity);
        this.parentActivity = parentActivity;
    }

    @Override
    protected List<List<BitacoraDTO>> doInBackground(Void... params) {
        bitacora = new ArrayList<>();
        try {
            String url = super.BASE_URL + "/bitacora/vivo";
            System.out.println(url);
            RestTemplate restTemplate = new RestTemplate(super.clientHttpRequestFactory());
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ParameterizedTypeReference<List<BitacoraDTO>> paramTypeReference = new ParameterizedTypeReference<List<BitacoraDTO>>() {
            };
            ResponseEntity<List<BitacoraDTO>> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<List<BitacoraDTO>>(new HttpHeaders()), paramTypeReference);

            bitacora.add(response.getBody());

            url = super.BASE_URL + "/bitacora/resumen";
            System.out.println(url);
            restTemplate = new RestTemplate(super.clientHttpRequestFactory());
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<List<BitacoraDTO>>(new HttpHeaders()), paramTypeReference);
            bitacora.add(response.getBody());

            url = super.BASE_URL + "/bitacora/historico";
            System.out.println(url);
            restTemplate = new RestTemplate(super.clientHttpRequestFactory());
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<List<BitacoraDTO>>(new HttpHeaders()), paramTypeReference);
            bitacora.add(response.getBody());

            return bitacora;
        } catch (Exception ex) {
            if (ex instanceof ResourceAccessException) {
                if (ex.getCause() instanceof ConnectException) {
                    onError(this.parentActivity.getResources().getString(R.string.app_toast_error_server_connection_message), ex);
                    bitacora = new ArrayList<>();
                    return bitacora;
                } else if (ex.getCause() instanceof ConnectTimeoutException) {
                    onError(this.parentActivity.getResources().getString(R.string.app_toast_error_server_timeout_message), ex);
                    return null;
                }
            }
            if (ex instanceof HttpClientErrorException) {
                if (((HttpClientErrorException) ex).getStatusCode() == HttpStatus.UNAUTHORIZED) {
                    System.out.println(((HttpClientErrorException) ex).getResponseBodyAsString());
                    onError(this.parentActivity.getResources().getString(R.string.app_toast_error_session_expired), ex);
                    bitacora = new ArrayList<>();
                    Intent intent = new Intent(this.parentActivity, AutenticacionActivity.class);
                    this.parentActivity.startActivity(intent);
                    this.parentActivity.finish();
                    return bitacora;
                }
            } else {
                onError(this.parentActivity.getResources().getString(R.string.app_toast_error_server_unknow_message), ex);
                bitacora = new ArrayList<>();
                return bitacora;
            }
        }
        return new ArrayList<>();
    }

    @Override
    protected void onPostExecute(List<List<BitacoraDTO>> bitacoraDTOs) {
        super.onPostExecute(bitacoraDTOs);
        parentActivity.onPostExecuteTask(bitacoraDTOs);
    }

    public AbstractActivity getParentActivity() {
        return parentActivity;
    }

    public void setParentActivity(AbstractActivity parentActivity) {
        this.parentActivity = parentActivity;
    }
}
