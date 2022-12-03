package com.example.buildplan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Vote extends AppCompatActivity {


    SeekBar seekBar, seekBar2;
    TextView tvOption, tvOption2;
    TextView tvPercent, tvPercent2;
    double count=1, count2=1;
    boolean flag1=true, flag2=true;
    Button home10;


    @SuppressLint("CickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch(item.getItemId()){
                case R.id.back:
                    Intent intent =  new Intent(this, Details.class);
                    startActivity(intent);
                    break;
                case R.id.home:


                    Intent intent2 =  new Intent(this, HomePage.class);
                    startActivity(intent2);

                    break;
                case R.id.logout:
                    Intent intent3 =  new Intent(this, LogOut.class);
                    startActivity(intent3);
                    break;


            }
            return true;

        });

        seekBar = findViewById(R.id.seek_bar1);
        seekBar2 = findViewById(R.id.seek_bar2);
        tvOption = findViewById(R.id.tv_option1);
        tvOption2 = findViewById(R.id.tv_option2);
        tvPercent = findViewById(R.id.tv_percent1);
        tvPercent2 = findViewById(R.id.tv_percent2);
        home10 = findViewById(R.id.home10);



        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;

            }
        });

        tvOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag1){
                    count++;
                    count2 = 1;

                    flag1= false;
                    flag2= false;

                    calculatePercent();
                }
            }
        });

        home10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 =  new Intent(Vote.this, HomePage.class);
                startActivity(intent3);
            }
        });

        seekBar2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        tvOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag2){
                    count=1;
                    count2++;

                    flag1= false;
                    flag2= false;

                    calculatePercent();
                }
            }
        });



    }

    private void calculatePercent() {

        double total= count+count2;
        double percent=(count/total)*100;
        double percent2=(count2/total)*100;


        tvPercent.setText(String.format("%.0f%%", percent));
        seekBar.setProgress((int) percent);
        tvPercent2.setText(String.format("%.0f%%", percent2));
        seekBar2.setProgress((int) percent);

    }
}