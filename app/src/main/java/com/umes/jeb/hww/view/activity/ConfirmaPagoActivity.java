package com.umes.jeb.hww.view.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.bs.service.PagoServicioTask;
import com.umes.jeb.hww.eis.dto.AccionCampo;
import com.umes.jeb.hww.eis.dto.PayBillResponseDTO;
import com.umes.jeb.hww.eis.dto.TipoDato;
import com.umes.jeb.hww.eis.dto.TipoPago;
import com.umes.jeb.hww.view.adapter.ConfirmaPagoAdapter;
import com.umes.jeb.hww.view.adapter.PagoAdapter;
import com.umes.jeb.hww.view.bean.CobranzaBean;
import com.umes.jeb.hww.view.bean.CobranzaCampoBean;
import com.umes.jeb.hww.view.bean.ServicioBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by DISEÑO on 03-09-15.
 */
public class ConfirmaPagoActivity extends AbstractActivity {

    private RecyclerView mRecyclerView;
    private ConfirmaPagoAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout mDrawer;
    private int originalOrientation;

    private Toolbar mToolbar;

    private String[] osArray = null;

    private String mActivityTitle;

    private ServicioBean servicioBean;

    private CobranzaBean cobranzaBean;

    private String activityName;

    private ArrayList<CobranzaCampoBean> camposCobranza;


    @Override
    public void show(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ConfirmaPagoActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_campo_servicio);

        mActivityTitle = getTitle().toString();

        mToolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if (mToolbar != null) {
//            mToolbar.setLogo(R.mipmap.ic_launcher);
            setSupportActionBar(mToolbar);
            mToolbar.setTitle("Confirmar Pago");
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ConfirmaPagoActivity.this, PagoActivity.class);
                    ConfirmaPagoActivity.this.finish();
                }
            });
        }


        getSupportActionBar().setHomeButtonEnabled(true);

        if (getIntent().getExtras() != null) {
            camposCobranza = (ArrayList<CobranzaCampoBean>) getIntent().getExtras().getSerializable("listaBeans");
            servicioBean = (ServicioBean) getIntent().getExtras().getSerializable("servicioBean");
            //setMontoSinImpuestos(getIntent().getExtras().getS);
            setMontoTotal((BigDecimal) getIntent().getExtras().getSerializable("total"));
            setMontoSinImpuestos((BigDecimal) getIntent().getExtras().getSerializable("totalSinImpuestos"));
            setMedioSeleccionado(getIntent().getExtras().getString("tarjetaSeleccionada"));
            setImpuestos((BigDecimal) getIntent().getExtras().getSerializable("impuestos"));
            afterOnCreate(camposCobranza);
        }
        Button boton = (Button) this.findViewById(R.id.button_action_service);
        boton.setText("confirmar pago");
    }

    private void setMedioSeleccionado(String tarjetaSeleccionada) {
        CobranzaCampoBean bean = new CobranzaCampoBean();
        bean.setVisiblePago(true);
        bean.setPermiteIngresoPago(false);
        bean.setIngresoObligatorioPago(false);
        bean.setVisibleConsulta(false);
        bean.setDescripcion(getString(R.string.app_textView_cuenta_seleccionada));
        bean.setTipoDato(TipoDato.A);
        bean.setLongitud(18);
        bean.setValorIngreso(tarjetaSeleccionada);
        bean.setCantidadDecimales(0);
        bean.setVisibleResultado(true);
        bean.setEsTotal(false);
        bean.setEsIdPago(false);
        bean.setEsFavorito(false);
        bean.setEsImpuesto(false);
        bean.setEsCampoPagoTotal(false);
        camposCobranza.add(bean);
    }


    @Override
    public void handleException(Exception e) {
        show(e.getMessage());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.colectores_menu, menu);
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem cobranzaIcono = menu.findItem(R.id.iconos_cobranzas);
        if(cobranzaIcono!=null){
            loadBitmapMenu(servicioBean.getUrlLogo(), cobranzaIcono);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    public void ejecutaAccionOnClickActionService(View v) {

        String producto ="1";
       /* for(ProductoColectorBean prod: servicioBean.getProductosColector()){
            if(prod.getEsTC()){
                producto = prod.getCodigo();
            }
        }*/
        new PagoServicioTask(this, getSession().getToken(), getSession().getUser(), getSession().getTokenType(), camposCobranza).execute(servicioBean.getCodigoCobranza().toString(),
                servicioBean.getCodigoFacturador().toString(), servicioBean.getCodigo().toString(), servicioBean.getMoneda(), "12345678", producto, getMontoSinImpuestos().toString(), getImpuestos().toString(), getMontoTotal().toString());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            Intent intent = new Intent(ConfirmaPagoActivity.this, PagoActivity.class);
            ConfirmaPagoActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPostExecuteTask(Object response) {
        super.onPostExecuteTask(response);
        PayBillResponseDTO respo = (PayBillResponseDTO) response;
        List<CobranzaCampoBean> beans = getCobranzaCampoBean(respo);
       // afterOnCreate(beans);
    }

    private void afterOnCreate(List<CobranzaCampoBean> beans) {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view_campo_servicio);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ConfirmaPagoAdapter(beans, this);
        mLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<CobranzaCampoBean> getCobranzaCampoBean(PayBillResponseDTO response) {
        List<CobranzaCampoBean> lista = new ArrayList<CobranzaCampoBean>();
        if (response != null) {
            if (response.getCamposCobranza() != null) {
                for (CobranzaCampoBean bean : camposCobranza) {
                    if (bean.getPosicionRecibePago() != null && bean.getPosicionRecibePago() > 0) {
                        switch (bean.getPosicionRecibePago()) {
                            case 1:
                                bean.setValorIngreso(response.getCamposCobranza().getCampo1());
                                break;
                            case 2:
                                bean.setValorIngreso(response.getCamposCobranza().getCampo2());
                                break;
                            case 3:
                                bean.setValorIngreso(response.getCamposCobranza().getCampo3());
                                break;
                            case 4:
                                bean.setValorIngreso(response.getCamposCobranza().getCampo4());
                                break;
                            case 5:
                                bean.setValorIngreso(response.getCamposCobranza().getCampo5());
                                break;
                            case 6:
                                bean.setValorIngreso(response.getCamposCobranza().getCampo6());
                                break;
                            case 7:
                                bean.setValorIngreso(response.getCamposCobranza().getCampo7());
                                break;
                            case 8:
                                bean.setValorIngreso(response.getCamposCobranza().getCampo8());
                                break;
                            case 9:
                                bean.setValorIngreso(response.getCamposCobranza().getCampo9());
                                break;
                            case 10:
                                bean.setValorIngreso(response.getCamposCobranza().getCampo10());
                                break;
                        }
                    }
                }
                if (response != null) {
                    if (response.getError() == null || response.getError().getCodigo() == null || response.getError().getCodigo() == 0) {
                        Intent intent = new Intent(ConfirmaPagoActivity.this, ResultadoActivity.class);
                        intent.putExtra("listBeans", camposCobranza);
                        intent.putExtra("servicioBean", servicioBean);
                        ConfirmaPagoActivity.this.startActivity(intent);
                    }
                }
            }
        }
        return lista;
    }

}
