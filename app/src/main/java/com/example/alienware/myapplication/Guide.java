package com.example.alienware.myapplication;

/**
 * Created by alienware on 2017/11/20.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.alienware.util.Myadapter;

import java.util.ArrayList;
import java.util.List;


public class Guide extends Activity implements ViewPager.OnPageChangeListener{
    private Myadapter viewPagerAdapter;
    private ViewPager viewPager;
    private List<View> views;

    private Button btn;

    private ImageView[] dots;
    private int[] ids = {R.id.iv1, R.id.iv2, R.id.iv3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);
        SharedPreferences sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        boolean isFirst = sharedPreferences.getBoolean("isFirst", true);
        if(isFirst){
            initViews();
            initdots();
            btn = (Button) views.get(2).findViewById(R.id.btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor=getSharedPreferences("config",MODE_PRIVATE).edit();
                    editor.putBoolean("isFirst",false);
                    editor.commit();
                    Intent i = new Intent(Guide.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            });
        }else{
            Intent i = new Intent(Guide.this,MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    private void initdots(){
        dots = new ImageView[views.size()];
        for(int i=0;i<views.size();i++){
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    private void initViews(){
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.page1, null));
        views.add(inflater.inflate(R.layout.page2, null));
        views.add(inflater.inflate(R.layout.page3, null));
        viewPagerAdapter = new Myadapter(views,this);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for(int a=0;a<ids.length;a++){
            if(a==position){
                dots[a].setImageResource(R.drawable.page_indicator_focused);
            }else{
                dots[a].setImageResource(R.drawable.page_indicator_unfocused);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}