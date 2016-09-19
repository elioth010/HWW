package com.umes.jeb.hww.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


import com.umes.jeb.hww.R;
import com.umes.jeb.hww.eis.bo.MedioPago;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DISEÑO on 07-09-15.
 */
public class DetalleFacturaActivity extends ActionBarActivity {

    private Toolbar mToolbar;
    private Integer afiliarPago;
    TextView medioSeleccionadodetalle;
    private MedioPago medioPago = new MedioPago();
    private TextView medioAfiliado;
    private Integer medioDePagoSeleccionado;
    private Integer medioSeleccionado;
    private TextView fechaDetalle;
    private String correlativo;

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalle_factura);

        mToolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if (mToolbar != null) {
//            mToolbar.setLogo(R.mipmap.ic_launcher);
//            setSupportActionBar(mToolbar);
            mToolbar.setTitle(" Detalle de Factura");
//            setSupportActionBar(mToolbar);
        }

        fechaDetalle = (TextView) this.findViewById(R.id.fecha_detalle);
        medioSeleccionadodetalle = (TextView)findViewById(R.id.medio_seleccionado_detalle);
        medioAfiliado = (TextView) this.findViewById(R.id.medio_afiliado_detalle);

        if (getIntent().getExtras()!=null) {
            medioDePagoSeleccionado = getIntent().getExtras().getInt("medioDePagoSeleccionado");
            medioPago = (MedioPago) getIntent().getExtras().getSerializable("medioSeleccionado");
            correlativo = getIntent().getExtras().getString("correlativo");
        }

        if(medioPago!=null) {
            medioAfiliado.setText(""+medioSeleccionado);
        }
        if (medioPago.getMedioSeleccionado() == 1){
            medioSeleccionadodetalle.setText("Sistema Clave");
        } else if (medioPago.getMedioSeleccionado() == 2) {
            medioSeleccionadodetalle.setText("Visa");
        } else if (medioPago.getMedioSeleccionado()== 3) {
            medioSeleccionadodetalle.setText("Master Card");
        } else if (medioPago.getMedioSeleccionado() == 4) {
            medioSeleccionadodetalle.setText("Monedero");
        }
        String valor = ""+medioPago.getNoTarjeta();
        String contador = valor.substring(12,16);

        String mascara = "XXXX-XXXX-XXXX-";
        medioAfiliado.setText(mascara+contador);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        fechaDetalle.setText(df.format(new Date()));

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
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    public void irFinalizarTransaccion(View v) {
        Intent intent = new Intent(this, FinalizaTransaccionActivity.class);
        intent.putExtra("correlativo", correlativo);
        this.startActivity(intent);
        this.finish();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            Toast toast = Toast.makeText(getApplicationContext(),"No puede realizar esta acción, debe Finalizar la operación en Curso.",Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}