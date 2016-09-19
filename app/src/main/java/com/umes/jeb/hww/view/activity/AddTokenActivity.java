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
import com.umes.jeb.hww.eis.bo.MedioPago;

import java.net.ConnectException;

/**
 * Created by DISEÑO on 07-09-15.
 */
public class AddTokenActivity extends AbstractActivity {

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private Toolbar mToolbar;

    private String[] osArray = null;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    private Integer medioSeleccionado;
    private Integer medioPagoMascara;
    private MedioPago medioPago = new MedioPago();
    private Integer medioPagoSeleccionado;
    private String nameActivity;
    private String cajero;
    private String correlativo;
    private boolean clave;
    private String monto;

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_token);

        mToolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if (mToolbar != null) {
//            mToolbar.setLogo(R.drawable.ensa2);
//            setSupportActionBar(mToolbar);
            mToolbar.setTitle("Validación");
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clave) {
                    Intent intent = new Intent(AddTokenActivity.this, MedioPagoAsociadosActivity.class);
                    intent.putExtra("medioPagoSeleccionado", medioPagoSeleccionado);
                    intent.putExtra("medioSeleccionado", medioPago);
                    intent.putExtra("clave", clave);
                    intent.putExtra("cajero", cajero);
                    intent.putExtra("monto", monto);
                    AddTokenActivity.this.startActivity(intent);
                    AddTokenActivity.this.finish();
                } else {
                    Intent intent = new Intent(AddTokenActivity.this, DatosFacturacionActivity.class);
                    intent.putExtra("medioPagoSeleccionado", medioPagoSeleccionado);
                    intent.putExtra("medioSeleccionado", medioPago);
                    intent.putExtra("clave", clave);
                    intent.putExtra("cajero", cajero);
                    intent.putExtra("monto", monto);
                    AddTokenActivity.this.startActivity(intent);
                    AddTokenActivity.this.finish();
                }
            }
        });

        if (getIntent().getExtras() != null) {

            medioPagoSeleccionado = getIntent().getExtras().getInt("medioPagoSeleccionado");
            medioPago = (MedioPago) getIntent().getExtras().getSerializable("medioSeleccionado");
            nameActivity = getIntent().getExtras().getString("nameActivity");
            cajero = getIntent().getExtras().getString("cajero");
            correlativo = getIntent().getExtras().getString("correlativo");
            clave = getIntent().getExtras().getBoolean("clave");
            monto = getIntent().getExtras().getString("monto");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.colectores_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.cancelar) {

            final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Cancelar Proceso");
            alertDialog.setMessage("Realmente desea Cancelar?");
            alertDialog.setCancelable(false);
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "ACEPTAR", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(AddTokenActivity.this, HomeActivity.class);
                    intent.putExtra("usuario", correlativo);
                    AddTokenActivity.this.startActivity(intent);
                    AddTokenActivity.this.finish();
                }
            });
            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCELAR", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.setIcon(R.drawable.telered);
            alertDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void irDetalleFactura(View v) {
        Intent intent = new Intent(AddTokenActivity.this, DetalleFacturaActivity.class);
        intent.putExtra("correlativo", correlativo);
        intent.putExtra("medioPagoSeleccionado", medioPagoSeleccionado);
        intent.putExtra("medioSeleccionado", medioPago);
        this.startActivity(intent);
        this.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            if (clave) {
                Intent intent = new Intent(AddTokenActivity.this, MedioPagoAsociadosActivity.class);
                intent.putExtra("medioPagoSeleccionado", medioPagoSeleccionado);
                intent.putExtra("medioSeleccionado", medioPago);
                intent.putExtra("clave", clave);
                intent.putExtra("cajero", cajero);
                intent.putExtra("monto", monto);
                AddTokenActivity.this.startActivity(intent);
                AddTokenActivity.this.finish();
                return true;
            } else {
                Intent intent = new Intent(AddTokenActivity.this, DatosFacturacionActivity.class);
                intent.putExtra("medioPagoSeleccionado", medioPagoSeleccionado);
                intent.putExtra("medioSeleccionado", medioPago);
                intent.putExtra("correlativo", correlativo);
                intent.putExtra("clave", clave);
                intent.putExtra("cajero", cajero);
                intent.putExtra("monto", monto);
                AddTokenActivity.this.startActivity(intent);
                this.finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * muestra la informacion en tiempo de ejeccucion del error en la Interfaz
     */
    @Override
    public void show(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(AddTokenActivity.this, message, Toast.LENGTH_LONG).show();
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
                show(AddTokenActivity.this.getResources().getString(R.string.app_toast_error_login_message));
            }
        });
    }
}
