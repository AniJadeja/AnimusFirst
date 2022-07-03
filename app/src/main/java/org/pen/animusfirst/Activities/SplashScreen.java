package org.pen.animusfirst.Activities;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.pen.animusfirst.R;
import org.pen.animusfirst.utilities.PreRender;

public class SplashScreen extends AppCompatActivity {

    ImageView logo;
    int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mode = PreRender.ifNight(getApplicationContext());
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        logo = findViewById(R.id.logo);
        if (mode != 0) {
            logo.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.brandColorDark)));
        }
    }


}