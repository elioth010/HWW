package com.umes.jeb.hww.view.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.umes.jeb.hww.R;


public class SplashActivity extends ActionBarActivity {

    private String urlQR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        RelativeLayout img = (RelativeLayout) findViewById(R.id.splashTelered);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, AutenticacionActivity.class);
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
            }
        });
//        ImageView circularImage = (ImageView) this.findViewById(R.id.fondo);
//        AnimationSet animationSet = new AnimationSet(false);
//        if(circularImage!=null && animationSet!=null){
//            Animation fade = AnimationUtils.loadAnimation(this, R.anim.transparent);
//            if(fade!=null){
//                fade.reset();
//                animationSet.addAnimation(fade);
//            }
//
//            Animation rotar = AnimationUtils.loadAnimation(this, R.anim.rotate);
//            if(rotar!=null){
//                rotar.reset();
//                animationSet.addAnimation(rotar);
//            }
//
//            Animation scale = AnimationUtils.loadAnimation(this, R.anim.scale);
//            if(scale!=null){
//                scale.reset();
//                animationSet.addAnimation(scale);
//            }
//            circularImage.startAnimation(animationSet);
//        }

        ImageView nameImage = (ImageView) this.findViewById(R.id.nombre);
        AnimationSet animationSet1 = new AnimationSet(false);
        if(nameImage!=null && animationSet1!=null){
            Animation fade = AnimationUtils.loadAnimation(this, R.anim.transparent);
            if(fade!=null){
                fade.reset();
                animationSet1.addAnimation(fade);
            }

            Animation scale = AnimationUtils.loadAnimation(this, R.anim.scale);
            if(scale!=null){
                scale.reset();
                animationSet1.addAnimation(scale);
            }
            nameImage.startAnimation(animationSet1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }

    public void show(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SplashActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }
}