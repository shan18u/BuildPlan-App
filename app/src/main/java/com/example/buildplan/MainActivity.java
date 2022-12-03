package com.example.buildplan;


import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.content.PackageManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.buildplan.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    ImageView ivUser;
    Button btntakePictureLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        ConstraintLayout constraintLayout = findViewById(R.id.mainLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(3500);
        animationDrawable.start();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.back:
                    Intent intent = new Intent(MainActivity.this, HomePage.class);
                    startActivity(intent);
                    break;
                case R.id.home:
                    Intent intent1 = new Intent(MainActivity.this, HomePage.class);
                    startActivity(intent1);
                    break;
                case R.id.logout:
                    Intent intent2 = new Intent(MainActivity.this, LogOut.class);
                    startActivity(intent2);
                    break;


            }
            return true;

        });



        ivUser = findViewById(R.id.ivUser);




        btntakePictureLauncher = findViewById(R.id.btntakePictureLauncher);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }
        btntakePictureLauncher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });


    }
    public void OnAcceptClick (View v){
        Intent i = new Intent(MainActivity.this, HomePage.class);

        i.putExtra("imgid", R.id.accept2);
        i.putExtra("textid", R.id.textView3);
        startActivity(i);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==100){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ivUser.setImageBitmap(bitmap);
        }
    }
}
