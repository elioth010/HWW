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
import com.umes.jeb.hww.view.adapter.CobranzaAdapter;
import com.umes.jeb.hww.view.bean.CategoriaBean;
import com.umes.jeb.hww.view.bean.CobranzaBean;
import com.umes.jeb.hww.view.bean.CobranzaCampoBean;
import com.umes.jeb.hww.view.bean.HomeBean;

import java.util.List;


/**
 * Created by DISEÑO on 03-09-15.
 */
public class CobranzaActivity extends AbstractActivity {

    private RecyclerView mRecyclerView;
    private CobranzaAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout mDrawer;
    private int originalOrientation;

    private Toolbar mToolbar;

    private String[] osArray = null;

    private String mActivityTitle;

    private HomeBean homeBean;

    private CategoriaBean categoriaBean;

    private String activityName;


    @Override
    public void show(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(CobranzaActivity.this, message, Toast.LENGTH_SHORT).show();
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
            mToolbar.setTitle("Lista de Cobranzas");
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CobranzaActivity.this.finish();
                }
            });
        }


        getSupportActionBar().setHomeButtonEnabled(true);

        if (getIntent().getExtras() != null) {
            activityName = (String) getIntent().getExtras().getString("activity");
            if (activityName != null && activityName.equalsIgnoreCase("homeActivity")) {
                homeBean = (HomeBean) getIntent().getExtras().getSerializable("homeBean");
                afterOnCreateMethod(homeBean.getListaCobranzas());
            } else if (activityName != null && activityName.equalsIgnoreCase("categoriaActivity")) {
                categoriaBean = (CategoriaBean) getIntent().getExtras().getSerializable("categoriaBean");
                afterOnCreateMethod(categoriaBean.getCobranzas());
            }
        }
    }

    protected void afterOnCreateMethod(List<CobranzaBean> beans) {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view_cobranza);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new CobranzaAdapter(beans, this);
        mAdapter.setCallBack(new CobranzaAdapter.CallBack() {
            @Override
            public void onClick(View v, CobranzaBean dto) {
                if(dto!= null && dto.getServicios()!= null){
                    Intent intent =null;
                    if(dto.getServicios().size()>1){
                        intent = new Intent(CobranzaActivity.this, ServicioActivity.class);
                    }else{
                        if(dto.getServicios().get(0).getUsaConsulta()) {
                            CobranzaCampoBean campoTotal = CobranzaActivity.this.getCobranzaTotal(dto);
                            intent = new Intent(CobranzaActivity.this, ConsultaActivity.class);
                            intent.putExtra("activity", "cobranzaActivity");
                            intent.putExtra("campoTotal", campoTotal);
                        }else{
                            intent = new Intent(CobranzaActivity.this, PagoActivity.class);
                            intent.putExtra("activity", "cobranzaActivity");
                        }
                    }
                    intent.putExtra("cobranzaBean", dto);
                    CobranzaActivity.this.startActivity(intent);
                }
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
            Intent intent = null;
            if (activityName != null && activityName.equalsIgnoreCase("homeActivity")) {
                intent = new Intent(CobranzaActivity.this, HomeActivity.class);
            } else if (activityName != null && activityName.equalsIgnoreCase("categoriaActivity")) {
                intent = new Intent(CobranzaActivity.this, CategoriaActivity.class);
            }
           // CobranzaActivity.this.startActivity(intent);
            CobranzaActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPostExecuteTask(Object response) {
        super.onPostExecuteTask(response);

    }
}
