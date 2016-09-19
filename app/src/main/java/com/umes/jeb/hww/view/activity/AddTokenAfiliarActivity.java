package com.umes.jeb.hww.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.umes.jeb.hww.R;

/**
 * Created by DISEÑO on 07-09-15.
 */
public class AddTokenAfiliarActivity extends ActionBarActivity {

    private Toolbar mToolbar;
    private String correlativo;

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_token_afiliar);

        mToolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if (mToolbar != null) {
//            mToolbar.setLogo(R.mipmap.ic_launcher);
//            setSupportActionBar(mToolbar);
            mToolbar.setTitle("Confirmar Afiliación");
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTokenAfiliarActivity.this.startActivity(new Intent(AddTokenAfiliarActivity.this, DatosAfiliacionMedioActivity.class).putExtra("correlativo", correlativo));
                AddTokenAfiliarActivity.this.finish();
            }
        });

        if (getIntent().getExtras() != null) {
            correlativo = getIntent().getExtras().getString("correlativo");
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
                    Intent intent = new Intent(AddTokenAfiliarActivity.this, MedioPagoAfiliarActivity.class);
                    intent.putExtra("correlativo", correlativo);
                    AddTokenAfiliarActivity.this.startActivity(intent);
                    AddTokenAfiliarActivity.this.finish();
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
    public void irFinalizarAfiliacion(View v) {
        Intent intent = new Intent(this, FinalizarAfiliacionActivity.class);
        intent.putExtra("correlativo", correlativo);
        this.startActivity(intent);
        this.finish();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            Intent intent = new Intent(this, DatosAfiliacionMedioActivity.class);
            intent.putExtra("correlativo", correlativo);
            this.startActivity(intent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
