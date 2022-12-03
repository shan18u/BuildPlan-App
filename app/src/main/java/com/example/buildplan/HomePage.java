package com.example.buildplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.buildplan.databinding.ActivityMainBinding;
import com.example.buildplan.MainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ParseAdapter adapter;
    private ArrayList<ParseItem> parseItems = new ArrayList<>();
    private ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);




        Bundle bundle = getIntent().getExtras();
        if (bundle!= null){
            int title2 = bundle.getInt("textid");
            int imgid2 = bundle.getInt("imgid");

            parseItems.add(new ParseItem(imgid2, title2));
        }

        ConstraintLayout constraintLayout = findViewById(R.id.activity_home_page);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(500);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch(item.getItemId()){
                case R.id.back:
                    break;

                case R.id.home:
                    break;

                case R.id.logout:
                    Intent intent3 =  new Intent(this, LogOut.class);
                    startActivity(intent3);
                    break;


            }
            return true;

        });

        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ParseAdapter(parseItems, this);
        recyclerView.setAdapter(adapter);
        Content content = new Content();
        content.execute();


    }

    public void OnClick(View v){
        Intent intent = new Intent(HomePage.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private class Content extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(HomePage.this, android.R.anim.fade_in));
        }


        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(HomePage.this, android.R.anim.fade_in));
            adapter.notifyDataSetChanged();
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
                int size= data.size();
                for (int i =0; i<size; i++){
                    String imgUrl = data.select("picture").select("img").eq(i).attr("src");
                    String title = data.select("div._61961").select("p.df867").eq(i).text();
                    parseItems.add(new ParseItem(imgUrl, title));
                    Log.d("items", "img:" +imgUrl + ". title" + title);

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


    }
}