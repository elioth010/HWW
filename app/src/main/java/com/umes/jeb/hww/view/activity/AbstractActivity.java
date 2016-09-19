package com.umes.jeb.hww.view.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;


import com.umes.jeb.hww.R;
import com.umes.jeb.hww.bs.service.GetImageFromURLMenuTask;
import com.umes.jeb.hww.bs.service.GetImageFromURLTask;
import com.umes.jeb.hww.bs.service.LogOutTask;
import com.umes.jeb.hww.bs.service.sugar.SugarService;
import com.umes.jeb.hww.bs.service.sugar.impl.SugarServiceImpl;
import com.umes.jeb.hww.eis.bo.BaseBO;
import com.umes.jeb.hww.eis.dto.TipoDato;
import com.umes.jeb.hww.eis.dto.TipoPago;
import com.umes.jeb.hww.security.SessionManager;
import com.umes.jeb.hww.util.persistence.ListaParametrosDTO;
import com.umes.jeb.hww.view.bean.CobranzaBean;
import com.umes.jeb.hww.view.bean.CobranzaCampoBean;

import java.math.BigDecimal;
import java.util.List;

public abstract class AbstractActivity extends ActionBarActivity {


    public static final String SCOPE = "trust";
    public static final String  GRANT_TYPE = "client_credentials";
    public static final String COLECTOR ="00666";
    public static final String CANAL="IB";
    public static final String IDIOMA="es";
    private SugarService service;
    public DisplayMetrics metrics = new DisplayMetrics();
    private boolean attached = false;

    private BigDecimal montoSinImpuestos;
    private BigDecimal impuestos;
    private BigDecimal montoTotal;
    private LruCache<String, Bitmap> mMemoryCache;

    /**
     * muestra la informacion en tiempo de ejeccucion del error en la Interfaz
     */
    public abstract void show(final String message);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = new SugarServiceImpl(getSession().getSugarDAO());
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
        attached = true;
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        attached = false;
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        attached = false;
    }

    /**
     * verifica el hilo y maneja la exception y la muestra en el gui
     */
    public abstract void handleException(final Exception e);

    public void closeSession() {
        SessionManager sessionManager = (SessionManager) getApplicationContext();
        logOutTask(this, sessionManager.getToken(), sessionManager.getTokenType()).execute();
    }

    private LogOutTask logOutTask(AbstractActivity parentActivity, String token, String tokenType) {
        return new LogOutTask(parentActivity, token, tokenType);
    }

    public SessionManager getSession() {
        return (SessionManager) getApplication();
    }

    /**
     * verifica si el dispositivo tiene coneccion a internet
     */
    public boolean isDeviceOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public int getSizeOfScreenInInches() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        float cardViewWidth = getResources().getDimension(R.dimen.cardview_minimum_width);
        System.out.println(cardViewWidth);
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        int widthPixels = displaymetrics.widthPixels;
        int heightPixels = displaymetrics.heightPixels;

        float widthDpi = displaymetrics.xdpi;
        float heightDpi = displaymetrics.ydpi;

        float widthInches = widthPixels / widthDpi;
        float heightInches = heightPixels / heightDpi;

        double sizeScreenInch = Math.hypot(widthInches, heightInches);
        return ((Long) Math.round(sizeScreenInch)).intValue();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout_task) {
            this.closeSession();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private SugarService getService() {
        return service;
    }

    public <T> BaseBO save(BaseBO baseBO) {
        return getService().save(baseBO);
    }

    public void update(BaseBO baseBO) {
        getService().update(baseBO);
    }

    public void delete(BaseBO baseBO) {
        getService().delete(baseBO);
    }

    public <T> BaseBO findById(Class<? extends BaseBO> clazz, Integer id) {
        return getService().findById(clazz, id);
    }

    public <T> List<T> findAll(Class<? extends BaseBO> clazz) {
        return getService().findAll(clazz);
    }

    public <T> List<T> findByClassWithParameters(Class<? extends BaseBO> clazz, ListaParametrosDTO parametros) {
        return getService().findByClassWithParameters(clazz, parametros);
    }

    public <T> List<T> findBySQLQuery(Class<? extends BaseBO> clazz, String query) {
        return getService().findBySQLQuery(clazz, query);
    }

    public boolean isAttached() {
        return attached;
    }

    public void setAttached(boolean attached) {
        this.attached = attached;
    }

    public void onPostExecuteTask(Object response){

    }

    public BigDecimal getMontoSinImpuestos() {
        return montoSinImpuestos;
    }

    public void setMontoSinImpuestos(BigDecimal montoSinImpuestos) {
        this.montoSinImpuestos = montoSinImpuestos;
    }

    public BigDecimal getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(BigDecimal impuestos) {
        this.impuestos = impuestos;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    protected CobranzaCampoBean getCobranzaTotal(CobranzaBean cobranzaSeleccionada) {
        CobranzaCampoBean bean = new CobranzaCampoBean();
        if (cobranzaSeleccionada.getTipoPago() != TipoPago.STO) {
            bean.setVisiblePago(true);
            bean.setPermiteIngresoPago(true);
            bean.setIngresoObligatorioPago(true);
            bean.setVisibleConsulta(false);
            bean.setDescripcion(getString(R.string.app_textView_pago_valor_a_pagar));
            bean.setTipoDato(TipoDato.D);
            bean.setLongitud(15);
            bean.setCantidadDecimales(2);
            bean.setVisibleResultado(true);
            bean.setEsTotal(false);
            bean.setEsIdPago(false);
            bean.setEsFavorito(false);
            bean.setEsImpuesto(false);
            bean.setEsCampoPagoTotal(true);
            return bean;
        }
        return null;
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public void loadBitmapMenu(String url, MenuItem imageView) {
        new GetImageFromURLMenuTask(this, url, imageView).execute();
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }
}
