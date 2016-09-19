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
import android.widget.Toast;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.view.adapter.ServicioAdapter;
import com.umes.jeb.hww.view.bean.CobranzaBean;
import com.umes.jeb.hww.view.bean.CobranzaCampoBean;
import com.umes.jeb.hww.view.bean.ServicioBean;

import java.util.List;


/**
 * Created by DISEÑO on 03-09-15.
 */
public class ServicioActivity extends AbstractActivity {

    private RecyclerView mRecyclerView;
    private ServicioAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout mDrawer;
    private int originalOrientation;

    private Toolbar mToolbar;

    private String[] osArray = null;

    private String mActivityTitle;

    private CobranzaBean cobranzaBean;
    private CobranzaCampoBean campoTotal;

    @Override
    public void show(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ServicioActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_cobranza);

        mActivityTitle = getTitle().toString();

        mToolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if (mToolbar != null) {
//            mToolbar.setLogo(R.mipmap.ic_launcher);
            setSupportActionBar(mToolbar);
            mToolbar.setTitle("Lista de Servicios");
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ServicioActivity.this.finish();
                }
            });
        }


        getSupportActionBar().setHomeButtonEnabled(true);

        if (getIntent().getExtras() != null) {
            cobranzaBean = (CobranzaBean) getIntent().getExtras().getSerializable("cobranzaBean");
            afterOnCreateMethod(cobranzaBean.getServicios());
        }
    }

    protected void afterOnCreateMethod(List<ServicioBean> beans) {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view_cobranza);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new ServicioAdapter(beans, this);
        mAdapter.setCallBack(new ServicioAdapter.CallBack() {
            @Override
            public void onClick(View v, ServicioBean dto) {
                Intent intent = null;
                if(dto.getUsaConsulta()){
                    intent =  new Intent(ServicioActivity.this, ConsultaActivity.class);
                    campoTotal = ServicioActivity.this.getCobranzaTotal(cobranzaBean);
                    intent.putExtra("campoTotal", campoTotal);
                }else{
                    intent = new Intent(ServicioActivity.this, PagoActivity.class);
                }
                intent.putExtra("activity", "servicioActivity");
                intent.putExtra("servicioBean", dto);
                intent.putExtra("cobranzaBean", cobranzaBean);
                ServicioActivity.this.startActivity(intent);
            }
        });
        mLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
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

    public void irMedioPagoAfiliarActivity(View v) {
       /* Intent intent = new Intent(this, MedioPagoActivity.class);
        this.startActivity(intent);
        this.finish();*/
    }

    public void irRecarga(View v) {
        /*Intent recarga = new Intent(this, RecargasActivity.class);
        this.startActivity(recarga);
        this.finish();*/
    }

    public void irPagos(View v) {
       /* Intent pago = new Intent(this, PagosActivity.class);
        this.startActivity(pago);
        this.finish();*/
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            Intent intent = new Intent(ServicioActivity.this, CobranzaActivity.class);
            // ServicioActivity.this.startActivity(intent);
            ServicioActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPostExecuteTask(Object response) {
        super.onPostExecuteTask(response);

    }
}
