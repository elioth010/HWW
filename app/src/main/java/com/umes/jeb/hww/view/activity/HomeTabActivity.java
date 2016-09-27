package com.umes.jeb.hww.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.eis.bo.dominio.SensorType;
import com.umes.jeb.hww.eis.dto.BitacoraDTO;
import com.umes.jeb.hww.eis.dto.MedidaSensorDTO;
import com.umes.jeb.hww.eis.dto.SensorDTO;
import com.umes.jeb.hww.eis.dto.UnidadMedidaDTO;
import com.umes.jeb.hww.eis.dto.UsuarioDTO;
import com.umes.jeb.hww.view.adapter.HomeAdapter;
import com.umes.jeb.hww.view.adapter.NavigationItemsAdapter;
import com.umes.jeb.hww.view.bean.HomeBean;
import com.umes.jeb.hww.view.bean.SensorBean;
import com.umes.jeb.hww.view.fragment.HomeTabsColorsFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by elioth010 on 4/19/16.
 */
public class HomeTabActivity extends AbstractActivity {

    //private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int originalOrientation;

    private NavigationItemsAdapter drawerAdapter;
    private RecyclerView.LayoutManager drawerLayoutManager;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private RecyclerView drawerRecyclerView;


    private Toolbar mToolbar;

    private String[] osArray = null;

    private String mActivityTitle;
    private List<HomeBean> beans = new ArrayList<>();


    @Override
    public void show(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(HomeTabActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tab_home);

        mActivityTitle = getTitle().toString();

        mToolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if (mToolbar != null) {
//            mToolbar.setLogo(R.mipmap.ic_launcher);
            setSupportActionBar(mToolbar);
            mToolbar.setTitle(getString(R.string.app_name));
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        drawerRecyclerView = (RecyclerView) findViewById(R.id.drawer_home_recycler_view);
        drawerRecyclerView.setHasFixedSize(true);
        getSession().setUser("Ejemplo Usuario");
        drawerAdapter = new NavigationItemsAdapter(getResources().getStringArray(R.array.nav_options), getResources().obtainTypedArray(R.array.nav_icons), getResources().getString(R.string.resouce_name), getResources().getString(R.string.resouce_secondary_text));
        drawerLayoutManager = new LinearLayoutManager(this);
        drawerRecyclerView.setLayoutManager(drawerLayoutManager);
        drawerRecyclerView.setAdapter(drawerAdapter);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_home_layout);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
//                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
        onPostExecuteTask(null);
        //new InformacionCobranzaTask(this, getSession().getToken(), getSession().getUser(), getSession().getTokenType()).execute();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        this.mDrawerToggle.syncState();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        /*if(id == R.id.qr_code){
            scanBarCode();
        }*/
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        } /*else if (id == R.id.menu_busqueda) {
            *//*Intent intent = new Intent(HomeTabActivity.this, BusquedaActivity.class);
            for (HomeBean bean : beans) {
                if (bean.getIsListaCobranza()) {
                    intent.putExtra("listaCobranzas", (Serializable)bean.getListaCobranzas());
                }
            }
            HomeTabActivity.this.startActivity(intent);*//*
        }*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void handleException(Exception e) {
        show(e.getMessage());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.colectores_menu, menu);
        getMenuInflater().inflate(R.menu.tab_menu, menu);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {

            final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Cancelar Proceso");
            alertDialog.setMessage("Realmente desea Cancelar?");
            alertDialog.setCancelable(false);
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "ACEPTAR", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(HomeTabActivity.this, AutenticacionActivity.class);
                    HomeTabActivity.this.startActivity(intent);
                    HomeTabActivity.this.finish();
                }
            });
            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCELAR", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.setIcon(R.drawable.logo_hww);
            alertDialog.show();

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPostExecuteTask(Object response) {
        super.onPostExecuteTask(response);
        /*mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view_home);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new HomeAdapter(beans, this);
        mAdapter.setCallBack(new HomeAdapter.CallBack() {
            @Override
            public void onClick(View v, HomeBean bean) {
                Intent intent = null;
                if (bean.getIsCategoria()) {
                    intent = new Intent(HomeTabActivity.this, CategoriaActivity.class);
                } else {
                    intent = new Intent(HomeTabActivity.this, CobranzaActivity.class);
                    intent.putExtra("activity", "HomeTabActivity");
                }
                intent.putExtra("homeBean", bean);
                HomeTabActivity.this.startActivity(intent);
                //HomeTabActivity.this.finish();
            }
        });
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);*/
        /*FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        HomeTabsColorsFragment fragment = new HomeTabsColorsFragment();
        //fragment.setListHomeBean(beans);
        transaction.replace(R.id.content_fragment, fragment);
        transaction.commit();*/

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        HomeTabsColorsFragment fragment = new HomeTabsColorsFragment();

        List<BitacoraDTO> datos = new ArrayList<>();

        UsuarioDTO usuario = new UsuarioDTO(2,1,"Javier Morales", "mrlsjavi@gmail.com", "6540sd4f45640as5d4f504s0f", "Amatitlan", 42365855, 1);

        SensorDTO sensorPulso = new SensorDTO(1,"Pulse And Oxigen Sensor", "Monitor de pulso y oxigeno", 1, SensorType.PO);
        SensorDTO sensorRespiracion = new SensorDTO(2,"Airflow Sensor", "Contador de respiraciones y flujo de aire", 1, SensorType.BS);
        SensorDTO sensorTemperatura = new SensorDTO(2,"Temperature Sensor", "Contador de respiraciones y flujo de aire", 1, SensorType.TP);

        UnidadMedidaDTO unidadPulso = new UnidadMedidaDTO(1, "PRbpm", 1);
        UnidadMedidaDTO unidadOxigeno = new UnidadMedidaDTO(2, "%SPo2", 1);
        UnidadMedidaDTO unidadRespiracion = new UnidadMedidaDTO(2, "Bpm", 1);
        UnidadMedidaDTO unidadTemperatura = new UnidadMedidaDTO(2, "C", 1);

        MedidaSensorDTO medidaSensorPulso = new MedidaSensorDTO(1,sensorPulso,unidadPulso,1);
        MedidaSensorDTO medidaSensorOxigeno = new MedidaSensorDTO(1,sensorPulso,unidadOxigeno,1);
        MedidaSensorDTO medidaSensorRespiracion = new MedidaSensorDTO(1,sensorRespiracion,unidadRespiracion,1);
        MedidaSensorDTO medidaSensorTemperatura = new MedidaSensorDTO(1,sensorTemperatura,unidadTemperatura,1);


        BitacoraDTO bitacoraPulso = new BitacoraDTO(usuario, medidaSensorPulso, 85d, new Date());
        BitacoraDTO bitacoraOxigeno = new BitacoraDTO(usuario, medidaSensorOxigeno, 98d, new Date());
        BitacoraDTO bitacoraRespiracion = new BitacoraDTO(usuario, medidaSensorRespiracion, 98d, new Date());
        BitacoraDTO bitacoraTemperatura = new BitacoraDTO(usuario, medidaSensorTemperatura, 34d, new Date());

        datos.add(bitacoraPulso);
        datos.add(bitacoraOxigeno);
        datos.add(bitacoraRespiracion);
        datos.add(bitacoraTemperatura);
        beans.add(new HomeBean("Dashboard", "", datos, new SensorBean()));
        beans.add(new HomeBean("Pulse Monitor", "", new ArrayList<BitacoraDTO>(), new SensorBean(SensorType.PO)));
        beans.add(new HomeBean("Breath Monitor", "", new ArrayList<BitacoraDTO>(), new SensorBean(SensorType.BS)));
        beans.add(new HomeBean("Temperature Monitor", "", new ArrayList<BitacoraDTO>(), new SensorBean(SensorType.TP)));
        fragment.setListHomeBean(beans);
        transaction.replace(R.id.content_fragment, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

}