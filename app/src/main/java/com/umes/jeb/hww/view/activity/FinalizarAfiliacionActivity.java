package com.umes.jeb.hww.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.umes.jeb.hww.R;


/**
 * Created by elioth010 on 5/27/15.
 */
public class FinalizarAfiliacionActivity extends ActionBarActivity {

    private Toolbar mToolbar;
    private String correlativo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_afiliacion);

        mToolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if (mToolbar != null) {
//            mToolbar.setLogo(R.drawable.ensa2);
//            setSupportActionBar(mToolbar);
            mToolbar.setTitle("Finalizar Afiliación");
//            setSupportActionBar(mToolbar);
        }
        ImageView checkImage = (ImageView) this.findViewById(R.id.finaliza_trx_check);
        AnimationSet animationSet = new AnimationSet(false);
        if(checkImage!=null && animationSet!=null){
/*            Animation fade = AnimationUtils.loadAnimation(this, R.anim.transparent);
            if(fade!=null){
                fade.reset();
                animationSet.addAnimation(fade);
            }

            Animation rotar = AnimationUtils.loadAnimation(this, R.anim.rotate);
            if(rotar!=null){
                rotar.reset();
                animationSet.addAnimation(rotar);
            }*/

            Animation scale = AnimationUtils.loadAnimation(this, R.anim.scale);
            if(scale!=null){
                scale.reset();
                animationSet.addAnimation(scale);
            }
            checkImage.startAnimation(animationSet);
        }

        ImageView checkImageCard = (ImageView) this.findViewById(R.id.finaliza_trx_card);
        animationSet = new AnimationSet(false);
        if(checkImageCard!=null && animationSet!=null){
            Animation fade = AnimationUtils.loadAnimation(this, R.anim.transparent);
            if(fade!=null){
                fade.reset();
                animationSet.addAnimation(fade);
            }

            checkImageCard.startAnimation(animationSet);
        }

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
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    public void finalizarAfiliacionirLogin(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        this.startActivity(intent);
        this.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            Toast toast = Toast.makeText(getApplicationContext(),"Presione Finalizar",Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
