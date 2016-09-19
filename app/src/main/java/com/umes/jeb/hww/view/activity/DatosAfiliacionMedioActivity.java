package com.umes.jeb.hww.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.umes.jeb.hww.R;
import com.umes.jeb.hww.eis.bo.MedioPago;
import com.umes.jeb.hww.view.fragment.FechaVencimientoDialogFragment;

/**
 * Created by DISEÑO on 04-09-15.
 */
public class DatosAfiliacionMedioActivity extends AbstractActivity implements FechaVencimientoDialogFragment.FechaVencimientoOnclickListener{

    private Toolbar mToolbar;
    private AlertDialog alertDialog = null;
    private DialogFragment dialog = null;
    private EditText fechaExpiracion = null;
    private EditText noTarjeta = null;
    private EditText cvv2 = null;

    private Integer afiliarPago;
    private String correlativo;

    @Override
    public void show(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(DatosAfiliacionMedioActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_datos_afiliacion_medio);

        mToolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if (mToolbar != null) {
//            mToolbar.setLogo(R.mipmap.ic_launcher);
//            setSupportActionBar(mToolbar);
            mToolbar.setTitle("Datos de Afiliación");
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatosAfiliacionMedioActivity.this.startActivity(new Intent(DatosAfiliacionMedioActivity.this, MedioPagoAfiliarActivity.class).putExtra("correlativo", correlativo));
                DatosAfiliacionMedioActivity.this.finish();
            }
        });

        fechaExpiracion = (EditText) this.findViewById(R.id.montoPago);
        noTarjeta = (EditText) this.findViewById(R.id.noTelefonoRecarga);
        cvv2 = (EditText) this.findViewById(R.id.cvv2);

        if (getIntent().getExtras()!=null) {
            afiliarPago = getIntent().getExtras().getInt("medioPagoSeleccionado");
            noTarjeta.setText(getIntent().getExtras().getString("noTarjeta"));
            fechaExpiracion.setText(getIntent().getExtras().getString("fechaExpiracion"));
            cvv2.setText(getIntent().getExtras().getString("cvv"));
            correlativo = getIntent().getExtras().getString("correlativo");
            //show(getIntent().getExtras().getString("holderName"));
        }

        //dialog = new FechaVencimientoDialogFragment();

/*      DATE PICKER ------------------------------------------------------------
        EditText fechaExpiracion = (EditText) this.findViewById(R.id.fechaExpiracion);
        fechaExpiracion.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(DatosAfiliacionMedioActivity.this, "click", Toast.LENGTH_SHORT).show();
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
                return true;
            }
        });*/
//        DATE PICKER ------------------------------------------------------------
//        EditText fechaExpiracion = (EditText) this.findViewById(R.id.fechaExpiracion);
        fechaExpiracion.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    dialog = new FechaVencimientoDialogFragment();
                    dialog.show(getSupportFragmentManager(), "FechaVencimientoDialogFramgent");
                }
                return false;
            }
        });

//        ListModulesFragment fragment = new ListModulesFragment();
//        List<MedioPago> listaMediosAsociados = super.findAll(MedioPago.class);
//        List<ItemBean> beans = new ArrayList<>();
////        for (MedioPago medio:listaMediosAsociados){
////            beans.add(new ItemBean(R.mipmap.visai900,medio.getNoTarjeta(),null, false,null));
////        }
//        fragment.setItemBeans(beans);
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.main_container, fragment);
//        ft.commit();
    }

    @Override
    public void handleException(Exception e) {

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
                    Intent intent = new Intent(DatosAfiliacionMedioActivity.this, MedioPagoAfiliarActivity.class);
                    intent.putExtra("correlativo", correlativo);
                    DatosAfiliacionMedioActivity.this.startActivity(intent);
                    DatosAfiliacionMedioActivity.this.finish();
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

    public void irAddTokenAfiliar(View v) {
        String noTarjeta = this.noTarjeta.getText().toString();
        String fechaExpiracion = this.fechaExpiracion.getText().toString();
        String cvv2= this.cvv2.getText().toString();

        if (noTarjeta.equals("") && fechaExpiracion.equals("") && cvv2.equals("")) {
            Toast.makeText(getApplicationContext(),"Es necesario completar todos los campos",Toast.LENGTH_SHORT).show();
        }else{
            MedioPago medio = new MedioPago();
            medio.setId(Long.parseLong(String.valueOf(super.findAll(MedioPago.class).size() + 1)));
            medio.setMedioSeleccionado(afiliarPago);
            medio.setFechaExpiracion(fechaExpiracion);
            medio.setCvv2(Integer.parseInt(cvv2));
            medio.setNoTarjeta(Long.parseLong(noTarjeta));
            super.save(medio);
            super.findAll(MedioPago.class);
            Intent intent = new Intent(this, AddTokenAfiliarActivity.class);
            intent.putExtra("correlativo", correlativo);
            this.startActivity(intent);
            this.finish();
        }
    }

    public void showDatePickerDialog(View v) {
        //DialogFragment newFragment = new DatePickerFragment();
        //newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onPositiveButtonClicked(int month, int year) {
        if(fechaExpiracion!=null){
            fechaExpiracion.setText(String.format("%02d",month)+"/"+year);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            Intent intent = new Intent(this, MedioPagoAfiliarActivity.class);
            intent.putExtra("correlativo", correlativo);
            this.startActivity(intent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}

