package com.mobiloitte.androidm3retest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;


import com.mobiloitte.androidm3retest.Adapter.PagerAdapter;
import com.mobiloitte.androidm3retest.activity.ActivityEditProfile;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivback,iv_call,ivSetting;
    Activity activity=  MainActivity.this;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_details);
        ivback=findViewById(R.id.iv_back);
        iv_call=findViewById(R.id.iv_call);
        ivSetting=findViewById(R.id.iv_setting);

        ivback.setOnClickListener(this);
        iv_call.setOnClickListener(this);
        ivSetting.setOnClickListener(this);





        changeStatusBarColor();

        viewPager = findViewById(R.id.pager);

        PagerAdapter myPagerAdapter = new PagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(myPagerAdapter);
        // viewPager.setPageTransformer(true,new ZoomOutPageTransformer());
        //viewPager.setPageTransformer(true,new DepthPageTransformer());
        // viewPager.setPageTransformer(true,new ParallaxPageTransformer());
        TabLayout tabLayout =findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

    }

    @SuppressLint("NewApi")
    public void   changeStatusBarColor(){
        Window window =  activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.colorBlack));

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                startActivity(new Intent(MainActivity.this, ActivityEditProfile.class));
                break;
            case R.id.iv_call:
                Toast.makeText(this,"Coming soon.",Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_setting:
                Toast.makeText(this,"Coming soon.",Toast.LENGTH_SHORT).show();
                break;

        }
    }
}






