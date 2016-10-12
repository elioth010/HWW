package com.umes.jeb.hww.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.Toast;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.bs.service.GetHistoricoSensorTask;
import com.umes.jeb.hww.bs.service.GetMonitorVivoTask;
import com.umes.jeb.hww.bs.service.GetResumenSensorTask;
import com.umes.jeb.hww.bs.service.code.ResponseCodesHelper;
import com.umes.jeb.hww.eis.dto.BitacoraDTO;
import com.umes.jeb.hww.view.adapter.HomeAdapter;
import com.umes.jeb.hww.view.adapter.NavigationItemsAdapter;
import com.umes.jeb.hww.view.bean.HomeBean;
import com.umes.jeb.hww.view.bean.SensorBean;
import com.umes.jeb.hww.view.fragment.HomeTabsColorsFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.umes.jeb.hww.eis.bo.dominio.SensorType.BP;
import static com.umes.jeb.hww.eis.bo.dominio.SensorType.BS;
import static com.umes.jeb.hww.eis.bo.dominio.SensorType.PO;
import static com.umes.jeb.hww.eis.bo.dominio.SensorType.TP;

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
    private Handler handler = new Handler();
    private Runnable runnable;
    private String[] osArray = null;

    private String mActivityTitle;
    private List<HomeBean> beans = new ArrayList<>();

    private List<BitacoraDTO> bitacoraPulso = new ArrayList<>();
    private List<BitacoraDTO> bitacoraPulsoResumen = new ArrayList<>();

    private List<BitacoraDTO> bitacoraBreath = new ArrayList<>();
    private List<BitacoraDTO> bitacoraBreathResumen = new ArrayList<>();

    private List<BitacoraDTO> bitacoraTemp = new ArrayList<>();
    private List<BitacoraDTO> bitacoraTempResumen = new ArrayList<>();

    private List<BitacoraDTO> bitacoraBlood = new ArrayList<>();
    private List<BitacoraDTO> bitacoraBloodResumen = new ArrayList<>();

    private List<BitacoraDTO> vivo = new ArrayList<>();
    private HomeTabsColorsFragment fragment;


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
        drawerAdapter = new NavigationItemsAdapter(getResources().getStringArray(R.array.nav_options), getResources().obtainTypedArray(R.array.nav_icons), getSession().getProfile().getNombre(), getSession().getProfile().getLogin());
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

        final List<com.umes.jeb.hww.eis.bo.Configuration> config = findAll(com.umes.jeb.hww.eis.bo.Configuration.class);
        if(config!=null && config.size()>0){
            runnable = new Runnable() {
                public void run() {
                    new GetMonitorVivoTask(HomeTabActivity.this).execute();
                    new GetHistoricoSensorTask(HomeTabActivity.this).execute();
                    new GetResumenSensorTask(HomeTabActivity.this).execute();
                    handler.postDelayed(this, config.get(0).getTimeToRefresh());
                }
            };
        }else{
            runnable = new Runnable() {
                public void run() {
                    new GetMonitorVivoTask(HomeTabActivity.this).execute();
                    new GetHistoricoSensorTask(HomeTabActivity.this).execute();
                    new GetResumenSensorTask(HomeTabActivity.this).execute();
                    handler.postDelayed(this, 120000);
                }
            };
        }
        runnable.run();

        beans.add(new HomeBean("Dashboard", "", vivo, new ArrayList<BitacoraDTO>(), new SensorBean()));
        beans.add(new HomeBean("Pulse Monitor", "", bitacoraPulso, bitacoraPulsoResumen, new SensorBean(PO)));
        beans.add(new HomeBean("Breath Monitor", "", bitacoraBreath, bitacoraBreathResumen, new SensorBean(BS)));
        beans.add(new HomeBean("Temperature Monitor", "", bitacoraTemp, bitacoraTempResumen, new SensorBean(TP)));
        beans.add(new HomeBean("Bload Pressure", "", bitacoraBlood, bitacoraBloodResumen, new SensorBean(BP)));

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment = new HomeTabsColorsFragment();
        fragment.setListHomeBean(beans);
        transaction.replace(R.id.content_fragment, fragment);
        transaction.commit();

        //onPostExecuteTask(null);
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
    public void onPostExecuteTask(Object response, Integer code) {
        super.onPostExecuteTask(response, code);
        //beans.clear();
        //
        if (response == null || ((List<BitacoraDTO>) response).size() == 0) {
            return;
        }

        List<BitacoraDTO> bitacora = (List<BitacoraDTO>) response;

        if (code == ResponseCodesHelper.RESULT_LIVE_MONITOR_CODE) {
            vivo.clear();
            vivo.addAll(bitacora);
            Collections.sort(vivo, new Comparator<BitacoraDTO>() {
                @Override
                public int compare(BitacoraDTO lhs, BitacoraDTO rhs) {
                    return lhs.getMedidaSensor().getSensor().getId().compareTo(rhs.getMedidaSensor().getSensor().getId());
                }
            });
        } else if (code == ResponseCodesHelper.RESULT_HISTORICAL_MONITOR_CODE) {
            bitacoraPulso.clear();
            bitacoraBreath.clear();
            bitacoraTemp.clear();
            bitacoraBlood.clear();
            for (BitacoraDTO hist : bitacora) {
                switch (hist.getMedidaSensor().getSensor().getSensorType()) {
                    case PO:
                        bitacoraPulso.add(hist);
                        break;
                    case BS:
                        bitacoraBreath.add(hist);
                        break;
                    case TP:
                        bitacoraTemp.add(hist);
                        break;
                    case BP:
                        bitacoraBlood.add(hist);
                }
            }

            for (HomeBean bean : beans) {
                if (bean.getSensorBean().getType() == null)
                    continue;
                switch (bean.getSensorBean().getType()) {
                    case PO:
                        bean.setHistorialBeanList(bitacoraPulso);
                        break;
                    case BS:
                        bean.setHistorialBeanList(bitacoraBreath);
                        break;
                    case TP:
                        bean.setHistorialBeanList(bitacoraTemp);
                        break;
                    case BP:
                        bean.setHistorialBeanList(bitacoraBlood);
                }
            }
        } else if (code == ResponseCodesHelper.RESULT_SUMMARY_MONITOR_CODE) {
            bitacoraPulsoResumen.clear();
            bitacoraBreathResumen.clear();
            bitacoraTempResumen.clear();
            bitacoraBloodResumen.clear();
            for (BitacoraDTO resum : bitacora) {
                switch (resum.getMedidaSensor().getSensor().getSensorType()) {
                    case PO:
                        bitacoraPulsoResumen.add(resum);
                        break;
                    case BS:
                        bitacoraBreathResumen.add(resum);
                        break;
                    case TP:
                        bitacoraTempResumen.add(resum);
                        break;
                    case BP:
                        bitacoraBloodResumen.add(resum);
                }
            }

            for (HomeBean bean : beans) {
                if (bean.getSensorBean().getType() == null)
                    continue;
                switch (bean.getSensorBean().getType()) {
                    case PO:
                        bean.setHistorialResumenBeanList(bitacoraPulsoResumen);
                        break;
                    case BS:
                        bean.setHistorialResumenBeanList(bitacoraBreathResumen);
                        break;
                    case TP:
                        bean.setHistorialResumenBeanList(bitacoraTempResumen);
                        break;
                    case BP:
                        bean.setHistorialResumenBeanList(bitacoraBloodResumen);
                }
            }
        }
        fragment.notifyDataChange();
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null && runnable != null)
            handler.removeCallbacks(runnable);
    }
}