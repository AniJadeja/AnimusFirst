package org.pen.animusfirst.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;

import org.pen.animusfirst.R;
import org.pen.animusfirst.utilities.Features;
import org.pen.animusfirst.utilities.FeaturesAdapter;
import org.pen.animusfirst.utilities.PreRender;

import java.util.ArrayList;

public class ActivityFeatures extends AppCompatActivity {

    ViewPager2 pager2;
    ArrayList<Features> featuresArrayList;
    TextView swipeText;
    LottieAnimationView arrow;

    int mode;
    int pagePosition;

    ImageView tab1, tab2, tab3, tab4;

    MaterialButton btnAllRight;


    int[] images;
    String[] heading, desc;

    FeaturesAdapter featuresAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mode = PreRender.ifNight(getApplicationContext());
        setContentView(R.layout.activity_features);

        init();
    }


    @Override
    protected void onStart() {
        super.onStart();

        setup();
    }


    private void setup() {
        images = new int[]{R.drawable.aircraft, R.drawable.travellers, R.drawable.assured_journey, R.drawable.travel_together};
        heading = new String[]{getString(R.string.featureHeading1), getString(R.string.featureHeading2), getString(R.string.featureHeading3), getString(R.string.featureHeading4)};
        desc = new String[]{getString(R.string.featureDescription1), getString(R.string.featureDescription2), getString(R.string.featureDescription3), getString(R.string.featureDescription4)};


        for (int i = 0; i < images.length; i++) {
            Features features = new Features(images[i], heading[i], desc[i]);
            featuresArrayList.add(features);
        }

        featuresAdapter = new FeaturesAdapter(featuresArrayList);
        pager2.setAdapter(featuresAdapter);
        pager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                pagePosition = position;
                switch (position) {
                    case 0:
                        tab1.setImageResource(R.drawable.indicator_selected);
                        tab2.setImageResource(R.drawable.indicator_default);
                        tab3.setImageResource(R.drawable.indicator_default);
                        tab4.setImageResource(R.drawable.indicator_default);
                        break;
                    case 1:
                        tab1.setImageResource(R.drawable.indicator_default);
                        tab2.setImageResource(R.drawable.indicator_selected);
                        tab3.setImageResource(R.drawable.indicator_default);
                        tab4.setImageResource(R.drawable.indicator_default);
                        break;
                    case 2:
                        tab1.setImageResource(R.drawable.indicator_default);
                        tab2.setImageResource(R.drawable.indicator_default);
                        tab3.setImageResource(R.drawable.indicator_selected);
                        tab4.setImageResource(R.drawable.indicator_default);
                        break;
                    case 3:
                        tab1.setImageResource(R.drawable.indicator_default);
                        tab2.setImageResource(R.drawable.indicator_default);
                        tab3.setImageResource(R.drawable.indicator_default);
                        tab4.setImageResource(R.drawable.indicator_selected);
                        break;
                }


                Log.d("onPageScrollSelected", "position: " + position);
                if (position == 3)
                    onLastPageSelected();
                else
                    otherPageSelected();
            }
        });


    }

    private void init() {
        pager2 = findViewById(R.id.pagerFeatures);
        swipeText = findViewById(R.id.swipeText);
        tab1 = findViewById(R.id.indicatorPage0);
        tab2 = findViewById(R.id.indicatorPage1);
        tab3 = findViewById(R.id.indicatorPage2);
        tab4 = findViewById(R.id.indicatorPage3);
        arrow = findViewById(R.id.arrow);
        btnAllRight = findViewById(R.id.btnAllRight);


        featuresArrayList = new ArrayList<>();

    }


    private void otherPageSelected() {
        swipeText.setVisibility(View.VISIBLE);
        arrow.setVisibility(View.VISIBLE);
        btnAllRight.setVisibility(View.GONE);
    }

    private void onLastPageSelected() {
        swipeText.setVisibility(View.INVISIBLE);
        arrow.setVisibility(View.INVISIBLE);
        btnAllRight.setVisibility(View.VISIBLE);

    }
}