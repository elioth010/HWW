package com.umes.jeb.hww.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.eis.bo.MedioPago;
import com.umes.jeb.hww.util.persistence.ListaParametrosDTO;
import com.umes.jeb.hww.util.persistence.Parametro;
import com.umes.jeb.hww.view.bean.CobranzaCampoBean;
import com.umes.jeb.hww.view.bean.ItemBean;
import com.umes.jeb.hww.view.bean.ServicioBean;
import com.umes.jeb.hww.view.fragment.ListModulesFragment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by elioth010 on 5/25/15.
 */
public class MedioPagoAsociadosActivity extends AbstractActivity {

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private Toolbar mToolbar;

    private String[] osArray= null;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    private Integer medioPagoSeleccionado;
    private MedioPago medioPago = new MedioPago();
    private String correlativo;
    private boolean clave = false;
    private String cajero;
    private String monto;

    private ServicioBean servicioBean;
    private ArrayList<CobranzaCampoBean> cobranzaCampoBean;

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_medio_pago_asociado);

       // mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        mToolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if (mToolbar != null) {
//            mToolbar.setLogo(R.mipmap.ic_launcher);
            setSupportActionBar(mToolbar);
            mToolbar.setTitle("Medios de Pago Afiliado");
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clave)
                    MedioPagoAsociadosActivity.this.startActivity(new Intent(MedioPagoAsociadosActivity.this, MedioPagoClaveActivity.class).putExtra("cajero", cajero));
                else
                    MedioPagoAsociadosActivity.this.startActivity(new Intent(MedioPagoAsociadosActivity.this, MedioPagoActivity.class).putExtra("monto", monto));
                MedioPagoAsociadosActivity.this.finish();
            }
        });

        if (getIntent().getExtras()!=null) {
            cobranzaCampoBean = (ArrayList<CobranzaCampoBean>) getIntent().getExtras().getSerializable("listaCobranzaCampoBean");
            servicioBean = (ServicioBean) getIntent().getExtras().getSerializable("servicioBean");
            medioPagoSeleccionado = getIntent().getExtras().getInt("medioPagoSeleccionado");
            medioPago = (MedioPago) getIntent().getExtras().getSerializable("medioSeleccionado");
            correlativo = getIntent().getExtras().getString("correlativo");
            clave = getIntent().getExtras().getBoolean("clave");
            cajero = getIntent().getExtras().getString("cajero");
            monto = getIntent().getExtras().getString("monto");
            setMontoTotal((BigDecimal) getIntent().getExtras().getSerializable("total"));
            setMontoSinImpuestos((BigDecimal) getIntent().getExtras().getSerializable("totalSinImpuestos"));
            setImpuestos((BigDecimal) getIntent().getExtras().getSerializable("impuestos"));
        }



        ListModulesFragment fragment = new ListModulesFragment();
        ListaParametrosDTO parametros = new ListaParametrosDTO();
        parametros.getParametros().add(new Parametro("MEDIO_SELECCIONADO = ?", medioPagoSeleccionado));
        List<MedioPago> listaMediosAsociados = super.findByClassWithParameters(MedioPago.class,parametros);
        List<ItemBean> beans = new ArrayList<>();

        for (MedioPago medio:listaMediosAsociados){

            String valor = ""+medio.getNoTarjeta();
            String contador = valor.substring(12,16);

            String mascara = "XXXX-XXXX-XXXX-";

//            medioAfiliado.setText(mascara + contador);

            if(medio.getMedioSeleccionado() == 1){
                beans.add(new ItemBean(R.mipmap.sistemaclavei900,mascara+contador,medio.getNoTarjeta().toString(), false,null));
            }else if (medio.getMedioSeleccionado() == 2){
                beans.add(new ItemBean(R.mipmap.visai900,mascara+contador,medio.getNoTarjeta().toString(), false,null));
            }else if (medio.getMedioSeleccionado() == 3){
                if(medio.getNoTarjeta() != null){
                    beans.add(new ItemBean(R.mipmap.mastercardi900,mascara+contador,medio.getNoTarjeta().toString(), false,null));
                }
            }else if (medio.getMedioSeleccionado() == 4){
                if(medio.getNoTarjeta() != null){
                    beans.add(new ItemBean(R.mipmap.monederoi900,mascara+contador,medio.getNoTarjeta().toString(), false,null));
                }
            }
        }

        fragment.setItemBeans(beans);
        fragment.setMedioDePagoSeleccionado(medioPagoSeleccionado);
        fragment.setCobranzaCampoBean(cobranzaCampoBean);
        fragment.setServicioBean(servicioBean);
        fragment.setClave(clave);
        fragment.setCajero(cajero);
        fragment.setMonto(monto);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_container, fragment);
        ft.commit();


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

    public void irDatosFacturacionActivity(View v) {
        Intent intent = new Intent(this, DatosFacturacionActivity.class);
        intent.putExtra("correlativo", correlativo);
        intent.putExtra("clave", true);
        this.startActivity(intent);
        this.finish();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK){
            if (clave)
                this.startActivity(new Intent(this, MedioPagoClaveActivity.class).putExtra("cajero", cajero));
            else
                this.startActivity(new Intent(this, MedioPagoActivity.class).putExtra("monto", monto));
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void show(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MedioPagoAsociadosActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * verifica el hilo y maneja la exception y la muestra en el gui
     */
    @Override
    public void handleException(final Exception e) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                show(MedioPagoAsociadosActivity.this.getResources().getString(R.string.app_toast_error_login_message));
            }
        });
    }
}
