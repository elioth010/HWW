package com.umes.jeb.hww.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
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
import com.umes.jeb.hww.bs.service.InformacionCobranzaTask;
import com.umes.jeb.hww.eis.dto.CobranzaDTO;
import com.umes.jeb.hww.eis.dto.InfoCobranzasResponseDTO;
import com.umes.jeb.hww.view.adapter.HomeAdapter;
import com.umes.jeb.hww.view.adapter.NavigationItemsAdapter;
import com.umes.jeb.hww.view.bean.CategoriaBean;
import com.umes.jeb.hww.view.bean.CobranzaBean;
import com.umes.jeb.hww.view.bean.HomeBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by DISEÑO on 03-09-15.
 */
public class HomeActivity extends AbstractActivity {

    private RecyclerView mRecyclerView;
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


    @Override
    public void show(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(HomeActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        mActivityTitle = getTitle().toString();

        mToolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if (mToolbar != null) {
//            mToolbar.setLogo(R.mipmap.ic_launcher);
            setSupportActionBar(mToolbar);
            mToolbar.setTitle("Boton de Pagos");
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        drawerRecyclerView = (RecyclerView) findViewById(R.id.drawer_home_recycler_view);
        drawerRecyclerView.setHasFixedSize(true);

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
        new InformacionCobranzaTask(this, getSession().getToken(), getSession().getUser(), getSession().getTokenType()).execute();
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
        }
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
        getMenuInflater().inflate(R.menu.menu, menu);
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
                    Intent intent = new Intent(HomeActivity.this, AutenticacionActivity.class);
                    HomeActivity.this.startActivity(intent);
                    HomeActivity.this.finish();
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
        List<HomeBean> beans = null;
        InfoCobranzasResponseDTO resp = (InfoCobranzasResponseDTO) response;
        beans = getHomeBean(resp);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view_home);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new HomeAdapter(beans, this);
        mAdapter.setCallBack(new HomeAdapter.CallBack() {
            @Override
            public void onClick(View v, HomeBean bean) {
                Intent intent = null;
                if (bean.getIsCategoria()) {
                    intent = new Intent(HomeActivity.this, CategoriaActivity.class);
                } else {
                    intent = new Intent(HomeActivity.this, CobranzaActivity.class);
                    intent.putExtra("activity", "homeActivity");
                }
                intent.putExtra("homeBean", bean);
                HomeActivity.this.startActivity(intent);
                //HomeActivity.this.finish();
            }
        });
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public List<HomeBean> getHomeBean(InfoCobranzasResponseDTO resp) {
        List<HomeBean> beans = new ArrayList<>();
        if (resp != null) {
            Map<Integer, CategoriaBean> mapCategoria = new HashMap<Integer, CategoriaBean>();
            HomeBean beanCategoria = new HomeBean();
            beanCategoria.setNombre("Categorias");
            beanCategoria.setImagen("categoria.png");
            beanCategoria.setIsCategoria(true);
            HomeBean beanCobranza = new HomeBean();
            beanCobranza.setIsListaCobranza(true);
            beanCobranza.setNombre("Todas");
            beanCobranza.setImagen("todas_cobranzas.png");
            for (CobranzaDTO dto : resp.getListaCobranzas()) {
                CategoriaBean cat = mapCategoria.get(dto.getCodigoCategoria());
                CobranzaBean cob = new CobranzaBean(dto,resp.getProductosColector());
                if (cat == null) {
                    cat = new CategoriaBean(dto.getCodigoCategoria(), dto.getNombreCategoria(), dto.getLogoCategoria(), cob);
                    mapCategoria.put(cat.getCodigo(), cat);
                    beanCategoria.getListaCategorias().add(cat);
                } else {
                    cat.getCobranzas().add(cob);
                    mapCategoria.put(cat.getCodigo(), cat);
                }
                beanCobranza.getListaCobranzas().add(cob);
            }
            if (beanCobranza.getListaCobranzas() != null && beanCobranza.getListaCobranzas().size() > 0) {
                beans.add(beanCategoria);
                beans.add(beanCobranza);
            }
            if (resp.getCobranzasFavoritas() != null && resp.getCobranzasFavoritas().size() > 0) {
                HomeBean beanFavorita = new HomeBean();
                beanFavorita.setNombre("Favoritas");
                beanFavorita.setImagen("cobranzas_favorito.png");
                beanFavorita.setIsFavorita(true);
                for (CobranzaDTO dto : resp.getCobranzasFavoritas()) {
                    beanFavorita.getListaCobranzas().add(new CobranzaBean(dto,resp.getProductosColector()));
                }
                beans.add(beanFavorita);
            }
            if (resp.getCobranzasRecientes() != null && resp.getCobranzasRecientes().size() > 0) {
                HomeBean beanReciente = new HomeBean();
                beanReciente.setNombre("Recientes");
                beanReciente.setImagen("cobranzas_recientes.png");
                beanReciente.setIsUsoReciente(true);
                for(CobranzaDTO dto : resp.getCobranzasRecientes()){
                    beanReciente.getListaCobranzas().add(new CobranzaBean(dto,resp.getProductosColector()));
                }
                beans.add(beanReciente);
            }
        }
        return beans;
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
