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
import com.umes.jeb.hww.eis.dto.CobranzaCampoDTO;
import com.umes.jeb.hww.eis.dto.InfoServicioCobranzaResponseDTO;
import com.umes.jeb.hww.view.adapter.ResultadoAdapter;
import com.umes.jeb.hww.view.bean.CobranzaBean;
import com.umes.jeb.hww.view.bean.CobranzaCampoBean;
import com.umes.jeb.hww.view.bean.ServicioBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by DISEÑO on 03-09-15.
 */
public class ResultadoActivity extends AbstractActivity {

    private RecyclerView mRecyclerView;
    private ResultadoAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout mDrawer;
    private int originalOrientation;

    private Toolbar mToolbar;

    private String[] osArray = null;

    private String mActivityTitle;

    private ServicioBean servicioBean;

    private CobranzaBean cobranzaBean;

    private String activityName;

    private ArrayList<CobranzaCampoBean> beans;

    @Override
    public void show(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ResultadoActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_campo_servicio_fin);

        mActivityTitle = getTitle().toString();

        mToolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if (mToolbar != null) {
//            mToolbar.setLogo(R.mipmap.ic_launcher);
            setSupportActionBar(mToolbar);
            mToolbar.setTitle("Resultado");
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), HomeTabActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
        }


        getSupportActionBar().setHomeButtonEnabled(true);

        if (getIntent().getExtras() != null) {
            servicioBean = (ServicioBean) getIntent().getExtras().getSerializable("servicioBean");
            beans = (ArrayList<CobranzaCampoBean>)  getIntent().getExtras().getSerializable("listBeans");;
            afterOnCreate(beans);
        }

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

        //new CobranzaRecienteTask(this, getSession().getToken(), getSession().getUser(), getSession().getTokenType(),servicioBean.getCodigoCobranza(), new Date()).execute();
        Intent pago = new Intent(this, HomeTabActivity.class);
        pago.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(pago);
        this.finish();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            Intent intent = new Intent(ResultadoActivity.this, HomeTabActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            ResultadoActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPostExecuteTask(Object response) {
        super.onPostExecuteTask(response);
    }

    private void afterOnCreate(List<CobranzaCampoBean> beans) {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view_campo_servicio);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ResultadoAdapter(beans, this);
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



}
