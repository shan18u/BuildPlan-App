package com.example.buildplan;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;


import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Details extends AppCompatActivity {

    private ImageView imageView;
    private TextView titleTExtView, textView2, textView3;
    public static  int count = 10;
    private Button accept_btn, decline_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch(item.getItemId()){
                case R.id.back:
                case R.id.home:
                    Intent intent =  new Intent(this, HomePage.class);
                    startActivity(intent);
                    break;

                case R.id.logout:
                    Intent intent3 =  new Intent(this, LogOut.class);
                    startActivity(intent3);
                    break;


            }
            return true;

        });



        imageView = findViewById(R.id.imageView);
        titleTExtView = findViewById(R.id.textView);



        titleTExtView.setText(getIntent().getStringExtra("title"));
        try{
            Picasso.get().load(getIntent().getStringExtra("image")). into(imageView);
        }catch (Exception e){
            e.printStackTrace();

            Picasso.get().load(String.valueOf(imageView));
        }

        Content content = new Content();
        content.execute();
    }

    public  void OnAcceptClick (View v) {
        Intent intent = new Intent(Details.this, Vote.class);
        startActivity(intent);

    }

    public void OnDeclineCLick (View v){
        Intent intent = new Intent(Details.this, HomePage.class);
        startActivity(intent);

    }


    private class Content extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
             }


        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
          }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }


        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "https://www.land.com/San-Jose-CA/ranches/";
                Document doc = Jsoup.connect(url).get();
                Elements data = doc.select("div.afe46");

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}