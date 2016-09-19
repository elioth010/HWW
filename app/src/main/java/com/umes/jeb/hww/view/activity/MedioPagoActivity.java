package com.umes.jeb.hww.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.umes.jeb.hww.R;
import com.umes.jeb.hww.view.bean.CobranzaCampoBean;
import com.umes.jeb.hww.view.bean.ServicioBean;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by elioth010 on 5/25/15.
 */
public class MedioPagoActivity extends AbstractActivity {

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private Toolbar mToolbar;

    private String[] osArray = null;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    private String correlativo;
    private String monto;
    private boolean pago = false;
    private boolean perfil = false;

    private ServicioBean servicioBean;
    private ArrayList<CobranzaCampoBean> cobranzaCampoBean;

    @Override
    public void show(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MedioPagoActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_medio_pago);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        mToolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if (mToolbar != null) {
//            mToolbar.setLogo(R.mipmap.ic_launcher);
            setSupportActionBar(mToolbar);
            mToolbar.setTitle("Medios de Pago");
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedioPagoActivity.this, PagoActivity.class);
                MedioPagoActivity.this.finish();
            }
        });
        if (getIntent().getExtras() != null) {
            cobranzaCampoBean = (ArrayList<CobranzaCampoBean>) getIntent().getExtras().getSerializable("listaCobranzaCampoBean");
            servicioBean = (ServicioBean) getIntent().getExtras().getSerializable("servicioBean");
            correlativo = getIntent().getExtras().getString("correlativo");
            monto = getIntent().getExtras().getString("monto");
            pago = getIntent().getExtras().getBoolean("pago");
            perfil = getIntent().getExtras().getBoolean("perfil");
            setMontoTotal((BigDecimal) getIntent().getExtras().getSerializable("total"));
            setMontoSinImpuestos((BigDecimal) getIntent().getExtras().getSerializable("totalSinImpuestos"));
            setImpuestos((BigDecimal) getIntent().getExtras().getSerializable("impuestos"));
        }

    }

    @Override
    public void handleException(Exception e) {
        if(e!=null){
            show(e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.colectores_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }

    public void irMedioPagoAsociados(View v) {

        String id = v.getResources().getResourceName(v.getId()).split("/")[1];
        Intent intent = new Intent(this, MedioPagoAsociadosActivity.class);
        intent.putExtra("monto", monto);
        intent.putExtra("listaCobranzaCampoBean", cobranzaCampoBean);
        intent.putExtra("servicioBean",servicioBean);
        intent.putExtra("total", getMontoTotal());
        intent.putExtra("totalSinImpuestos", getMontoSinImpuestos());
        intent.putExtra("impuestos", getImpuestos());
        if (id.equalsIgnoreCase("sistemaClave")) {
            intent.putExtra("medioPagoSeleccionado", 1);
        } else if (id.equalsIgnoreCase("visa")) {
            intent.putExtra("medioPagoSeleccionado", 2);
        } else if (id.equalsIgnoreCase("masterCard")) {
            intent.putExtra("medioPagoSeleccionado", 3);
        } else if (id.equalsIgnoreCase("monedero")) {
            intent.putExtra("medioPagoSeleccionado", 4);
        }
        this.startActivity(intent);
        this.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            Intent intent = new Intent(MedioPagoActivity.this, PagoActivity.class);
            MedioPagoActivity.this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
