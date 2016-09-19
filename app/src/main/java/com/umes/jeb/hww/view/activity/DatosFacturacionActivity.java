package com.umes.jeb.hww.view.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.app.AlertDialog;
import android.widget.EditText;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.eis.bo.MedioPago;


/**
 * Created by DISEÑO on 04-09-15.
 */
public class DatosFacturacionActivity extends ActionBarActivity {

    private Toolbar mToolbar;
    private AlertDialog alertDialog = null;
    private android.support.v4.app.DialogFragment dialog = null;
    private Integer medioPagoMascara;
    private MedioPago medioPago = new MedioPago();
    private Integer medioPagoSeleccionado;
    private EditText nombre;
    private EditText nit;
    private Integer medioSeleccionado;
    private String correlativo;
    private boolean clave = false;
    private String cajero;
    private String monto;
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_datos_facturacion);

        mToolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if (mToolbar != null) {
//            mToolbar.setLogo(R.mipmap.ic_launcher);
//            setSupportActionBar(mToolbar);
            mToolbar.setTitle(" Datos de Facturación");
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        if (getIntent().getExtras()!=null) {
            medioPagoSeleccionado = getIntent().getExtras().getInt("medioPagoSeleccionado");
            medioPago = (MedioPago) getIntent().getExtras().getSerializable("medioSeleccionado");
            correlativo = getIntent().getExtras().getString("correlativo");
            clave = getIntent().getExtras().getBoolean("clave");
            cajero = getIntent().getExtras().getString("cajero");
            monto = getIntent().getExtras().getString("monto");
        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent =  new Intent(DatosFacturacionActivity.this, MedioPagoAsociadosActivity.class);
                intent.putExtra("medioPagoSeleccionado", medioPagoSeleccionado);
                intent.putExtra("medioSeleccionado", medioPago);
                intent.putExtra("correlativo", correlativo);
                intent.putExtra("clave", clave);
                intent.putExtra("cajero", cajero);
                intent.putExtra("monto", monto);
                DatosFacturacionActivity.this.startActivity(intent);
                DatosFacturacionActivity.this.finish();
            }
        });


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
                    Intent intent =  new Intent(DatosFacturacionActivity.this, HomeActivity.class);
                    intent.putExtra("usuario", correlativo);
                    DatosFacturacionActivity.this.startActivity(intent);
                    DatosFacturacionActivity.this.finish();
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

    public void irAddToken(View v) {
        Intent intent =  new Intent(DatosFacturacionActivity.this, AddTokenActivity.class);
        intent.putExtra("medioPagoSeleccionado", medioPagoSeleccionado);
        intent.putExtra("medioSeleccionado", medioPago);
        intent.putExtra("correlativo", correlativo);
        intent.putExtra("clave", clave);
        intent.putExtra("cajero", cajero);
        intent.putExtra("monto", monto);
        this.startActivity(intent);
        this.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK){
            Intent intent =  new Intent(DatosFacturacionActivity.this, MedioPagoAsociadosActivity.class);
            intent.putExtra("medioPagoSeleccionado", medioPagoSeleccionado);
            intent.putExtra("medioSeleccionado", medioPago);
            intent.putExtra("correlativo", correlativo);
            intent.putExtra("clave", clave);
            intent.putExtra("cajero", cajero);
            intent.putExtra("monto", monto);
            DatosFacturacionActivity.this.startActivity(intent);
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

