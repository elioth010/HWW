package com.umes.jeb.hww.view.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
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
import com.umes.jeb.hww.eis.dto.CobranzaCampoDTO;
import com.umes.jeb.hww.eis.dto.GetBalanceResponseDTO;
import com.umes.jeb.hww.eis.dto.InfoServicioCobranzaResponseDTO;
import com.umes.jeb.hww.util.validator.ValidatorRegex;
import com.umes.jeb.hww.view.adapter.ConsultaAdapter;
import com.umes.jeb.hww.view.bean.CobranzaBean;
import com.umes.jeb.hww.view.bean.CobranzaCampoBean;
import com.umes.jeb.hww.view.bean.ServicioBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by DISEÑO on 03-09-15.
 */
public class ConsultaActivity extends AbstractActivity {

    private RecyclerView mRecyclerView;
    private ConsultaAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Toolbar mToolbar;

    private String mActivityTitle;

    private ServicioBean servicioBean;

    private CobranzaBean cobranzaBean;

    private CobranzaCampoBean campoTotal;

    private String activityName;


    @Override
    public void show(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ConsultaActivity.this, message, Toast.LENGTH_SHORT).show();
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
            mToolbar.setTitle("Consulta");
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConsultaActivity.this.finish();
                }
            });
        }


        getSupportActionBar().setHomeButtonEnabled(true);

        if (getIntent().getExtras() != null) {
            activityName = (String) getIntent().getExtras().getString("activity");
            if (activityName != null && activityName.equalsIgnoreCase("cobranzaActivity")) {
                cobranzaBean = (CobranzaBean) getIntent().getExtras().getSerializable("cobranzaBean");
                servicioBean = cobranzaBean.getServicios().get(0);
                campoTotal = (CobranzaCampoBean) getIntent().getExtras().getSerializable("campoTotal");
            } else if (activityName != null && activityName.equalsIgnoreCase("servicioActivity")) {
                cobranzaBean = (CobranzaBean) getIntent().getExtras().getSerializable("cobranzaBean");
                servicioBean = (ServicioBean) getIntent().getExtras().getSerializable("servicioBean");
            }
        }

        /*new InformacionCampoCobranzaTask(this, getSession().getToken(), getSession().getUser(), getSession().getTokenType()).execute(servicioBean.getCodigoCobranza().toString(),
                servicioBean.getId().toString(), servicioBean.getCodigo().toString(), servicioBean.getVersionCampos().toString());*/

        Button boton = (Button) this.findViewById(R.id.button_action_service);
        boton.setText("Continuar");
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
            loadBitmapMenu(cobranzaBean.getLogo(), cobranzaIcono);
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

       /* Intent pago = new Intent(this, PagosActivity.class);
        this.startActivity(pago);
        this.finish();*/
        List<CobranzaCampoBean> campos = mAdapter.getCobranzaCampoBeans();
        for (CobranzaCampoBean bean : campos) {
            if(bean.getPermiteIngresoConsulta() && bean.getIngresoObligatorioConsulta()){
                String result = ValidatorRegex.validateField(bean);
                if(result!=null){
                    show(result);
                    return;
                }
            }
        }



    }

    public void afterOnConsultaServicioTask(GetBalanceResponseDTO response) {
        if (response != null && response.getCamposCobranza() != null) {
            for (CobranzaCampoBean bean : mAdapter.getCobranzaCampoAll()) {
                if (!bean.getVisibleConsulta() && bean.getPosicionRecibeConsultaSaldo() != null && bean.getPosicionRecibeConsultaSaldo() > 0) {
                    switch (bean.getPosicionRecibeConsultaSaldo()) {
                        case 1:
                            bean.setValorIngreso(response.getCamposCobranza().getCampo1());
                            //bean.setValorIngreso("67582592");
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
                    Intent intent = new Intent(ConsultaActivity.this, PagoActivity.class);
                    intent.putExtra("activity", "consultaActivity");
                    intent.putExtra("listBeans", mAdapter.getCobranzaCampoAll());
                    intent.putExtra("servicioBean",servicioBean);
                    intent.putExtra("cobranzaBean", cobranzaBean);
                    ConsultaActivity.this.startActivity(intent);
                }
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            Intent intent = null;
            if (activityName != null && activityName.equalsIgnoreCase("cobranzaActivity")) {
                intent = new Intent(ConsultaActivity.this, CobranzaActivity.class);
            } else if (activityName != null && activityName.equalsIgnoreCase("servicioActivity")) {
                intent = new Intent(ConsultaActivity.this, ServicioActivity.class);
            }
            // CobranzaActivity.this.startActivity(intent);
            ConsultaActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPostExecuteTask(Object response) {
        super.onPostExecuteTask(response);
        InfoServicioCobranzaResponseDTO respo = (InfoServicioCobranzaResponseDTO) response;
        final ArrayList<CobranzaCampoBean> beans = getCobranzaCampoBean(respo);
        final ArrayList<CobranzaCampoBean> beansConsulta = getBeansConsulta(beans);
        if (campoTotal != null) {
            beans.add(campoTotal);
            int count = 0;
            for (CobranzaCampoBean campo : beans) {
                if (campo.getEsTotal()) {
                    campo.setPermiteIngresoPago(false);
                    beans.set(count, campo);
                    break;
                }
                count++;
            }
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view_campo_servicio);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ConsultaAdapter(beansConsulta, this, beans);
        mLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    private ArrayList<CobranzaCampoBean> getCobranzaCampoBean(InfoServicioCobranzaResponseDTO response) {
        ArrayList<CobranzaCampoBean> lista = new ArrayList<CobranzaCampoBean>();
        if (response != null) {
            if (response.getListaCampos() != null && response.getListaCampos().size() > 0) {
                for (CobranzaCampoDTO campo : response.getListaCampos()) {
                    lista.add(new CobranzaCampoBean(campo));
                }
            }
        }
        return lista;
    }

    private ArrayList<CobranzaCampoBean> getBeansConsulta(ArrayList<CobranzaCampoBean> beans) {
        ArrayList<CobranzaCampoBean> lista = new ArrayList<CobranzaCampoBean>();
        if (beans != null) {
            if (beans != null && beans.size() > 0) {
                for (CobranzaCampoBean campo : beans) {
                    if (campo.getVisibleConsulta()) {
                        lista.add(campo);
                    }
                }
            }
        }
        return lista;
    }

}
