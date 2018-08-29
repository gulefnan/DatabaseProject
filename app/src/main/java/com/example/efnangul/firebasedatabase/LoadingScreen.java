package com.example.efnangul.firebasedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LoadingScreen extends AppCompatActivity {
    Animation rotate;
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        //rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        imageview = (ImageView) findViewById(R.id.imageview);
    }
}
