package com.umes.jeb.hww.view.activity;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
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
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.umes.jeb.hww.view.adapter.BusquedaAdapter;
import com.umes.jeb.hww.view.adapter.NavigationItemsAdapter;
import com.umes.jeb.hww.view.bean.CobranzaBean;
import com.umes.jeb.hww.view.bean.CobranzaCampoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elioth010 on 4/19/16.
 */
public class BusquedaActivity extends AbstractActivity {

    private RecyclerView mRecyclerView;
    private BusquedaAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Toolbar mToolbar;
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    private  ArrayList<CobranzaBean> beans;

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        mToolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if (mToolbar != null) {
//            mToolbar.setLogo(R.mipmap.ic_launcher);
            setSupportActionBar(mToolbar);
            mToolbar.setTitle("Boton de Pagos");
            setSupportActionBar(mToolbar);
            //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {finish();
                }
            });
        }

        beans = (ArrayList<CobranzaBean>) getIntent().getSerializableExtra("listaCobranzas");
        betweenOnPostExecuteTask(beans);

        //new InformacionCobranzaTask(this, getSession().getToken(), getSession().getUser(), getSession().getTokenType()).execute();
    }


    @Override
    public void show(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void handleException(Exception e) {
        if(e!=null){
            show(e.getMessage());
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
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

        switch (item.getItemId()) {
            case R.id.action_search:
                // Not implemented here
                return false;
            default:
                break;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.colectores_menu, menu);
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);
                        ArrayList<CobranzaBean> nuevaLista = new ArrayList<>();
                        if(beans != null){
                            for(CobranzaBean bean : beans){
                                if(bean.getNombre().toLowerCase().contains(newText.toLowerCase())){
                                    nuevaLista.add(bean);
                                }else if(bean.getNombreFacturador().toLowerCase().contains(newText.toLowerCase())){
                                    nuevaLista.add(bean);
                                }else if(bean.getPalabraClave() != null){
                                    if(bean.getPalabraClave().toLowerCase().contains(newText.toLowerCase())){
                                        nuevaLista.add(bean);
                                    }
                                }else if(bean.getNombreCategoria() != null){
                                    if(bean.getNombreCategoria().toLowerCase().contains(newText.toLowerCase())){
                                        nuevaLista.add(bean);
                                    }
                                }
                            }
                        }
                    betweenOnPostExecuteTask(nuevaLista);
                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);

                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPostExecuteTask(Object response) {
        super.onPostExecuteTask(response);
        beans = null;
        InfoCobranzasResponseDTO resp = (InfoCobranzasResponseDTO) response;
        beans = getSearchBean(resp);
        betweenOnPostExecuteTask(beans);
    }

    public void betweenOnPostExecuteTask(ArrayList<CobranzaBean> beans){
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view_search);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new BusquedaAdapter(beans, this);
        mAdapter.setCallBack(new BusquedaAdapter.CallBack() {
            @Override
            public void onClick(View v, CobranzaBean bean) {
                if(bean!= null && bean.getServicios()!= null){
                    Intent intent =null;
                    if(bean.getServicios().size()>1){
                        intent = new Intent(getApplicationContext(), ServicioActivity.class);
                    }else{
                        if(bean.getServicios().get(0).getUsaConsulta()) {
                            CobranzaCampoBean campoTotal = BusquedaActivity.this.getCobranzaTotal(bean);
                            intent = new Intent(getApplicationContext(), ConsultaActivity.class);
                            intent.putExtra("activity", "cobranzaActivity");
                            intent.putExtra("campoTotal", campoTotal);
                        }else{
                            intent = new Intent(getApplicationContext(), PagoActivity.class);
                            intent.putExtra("activity", "cobranzaActivity");
                        }
                    }
                    intent.putExtra("cobranzaBean", bean);
                    startActivity(intent);
                }
            }
        });
        mLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public ArrayList<CobranzaBean> getSearchBean(InfoCobranzasResponseDTO resp) {
        ArrayList<CobranzaBean> beans = new ArrayList<>();
        if (resp != null) {
            for (CobranzaDTO dto : resp.getListaCobranzas()) {
                CobranzaBean cob = new CobranzaBean(dto, resp.getProductosColector());
                beans.add(cob);
            }
        }
        return beans;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



}
