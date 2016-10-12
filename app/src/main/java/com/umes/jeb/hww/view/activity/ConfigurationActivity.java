package com.umes.jeb.hww.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.eis.bo.Configuration;
import com.umes.jeb.hww.eis.dto.BitacoraDTO;
import com.umes.jeb.hww.view.adapter.HomeAdapter;
import com.umes.jeb.hww.view.adapter.NavigationItemsAdapter;
import com.umes.jeb.hww.view.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elioth010 on 10/12/16.
 */

public class ConfigurationActivity extends AbstractActivity {

    private RecyclerView.LayoutManager mLayoutManager;
    private int originalOrientation;

    private Toolbar mToolbar;
    private String[] osArray = null;

    private String mActivityTitle;
    private EditText timeToRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_config);
        mActivityTitle = getTitle().toString();

        mToolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if (mToolbar != null) {
//            mToolbar.setLogo(R.mipmap.ic_launcher);
            setSupportActionBar(mToolbar);
            mToolbar.setTitle(mActivityTitle);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConfigurationActivity.this.finish();
                }
            });
        }

        timeToRefresh = (EditText) findViewById(R.id.intervalo);

        final List<com.umes.jeb.hww.eis.bo.Configuration> config = findAll(com.umes.jeb.hww.eis.bo.Configuration.class);
        if(config!=null && config.size()>0){
            timeToRefresh.setText(config.get(0).getTimeToRefresh().toString());
        }
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public void updateConfig(View v){
        if (this.timeToRefresh.getText().toString().equalsIgnoreCase("")) {
            this.show("El tiempo de refresco es requerido");
        } else {
            List<Configuration> config = super.findAll(Configuration.class);
            if(config!=null && config.size()>0){
                Configuration configuration = config.get(0);
                configuration.setTimeToRefresh(Integer.parseInt(timeToRefresh.getText().toString()));
                try{
                    update(configuration);
                    this.show("Configuracion actualizada exitosamente");
                }catch(Exception e){
                    handleException(e);
                }
            }else{
                Configuration configuration = new Configuration(60,true,true,true,true);
                try{
                    save(configuration);
                    this.show("Configuracion actualizada exitosamente");
                }catch(Exception e){
                    handleException(e);
                }
            }
        }

    }

    @Override
    public void show(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ConfigurationActivity.this, message, Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public void handleException(Exception e) {
        show(e.getMessage());
    }
}
