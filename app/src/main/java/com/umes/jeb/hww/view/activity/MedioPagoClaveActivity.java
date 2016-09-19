package com.umes.jeb.hww.view.activity;

import android.content.Intent;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.umes.jeb.hww.R;

/**
 * Created by elioth010 on 5/25/15.
 */
public class MedioPagoClaveActivity extends ActionBarActivity {

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private Toolbar mToolbar;

    private String[] osArray= null;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    private String correlativo;
    private String nameActivity;
    private String cajero;

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tarjeta_clave);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
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
                MedioPagoClaveActivity.this.startActivity(new Intent(MedioPagoClaveActivity.this, PagoActivity.class).putExtra("cajero", cajero));
                MedioPagoClaveActivity.this.finish();
            }
        });
        if (getIntent().getExtras()!=null) {
            correlativo = getIntent().getExtras().getString("correlativo");
            nameActivity = getIntent().getExtras().getString("nameActivity");
            cajero = getIntent().getExtras().getString("cajero");
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
        /*if (id == R.id.action_settings) {
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }

    public void irMedioPagoClave(View v) {

        String id = v.getResources().getResourceName(v.getId()).split("/")[1];
        Intent intent = new Intent(this, MedioPagoAsociadosActivity.class);
        intent.putExtra("clave", true);
        intent.putExtra("cajero", cajero);
        intent.putExtra("medioPagoSeleccionado",1);
        this.startActivity(intent);
        this.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK){
            this.startActivity(new Intent(this, PagoActivity.class));
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
