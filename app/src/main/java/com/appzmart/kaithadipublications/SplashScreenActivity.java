package com.appzmart.kaithadipublications;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Random;

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_splashscreen);
        ImageView image = (ImageView)findViewById(R.id.SplashImage);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.splash_blink);
        image.startAnimation(animation1);
//        Intent i = new Intent(SplashScreenActivity.this,MainActivity.class);
//        startActivity(i);
    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

}
