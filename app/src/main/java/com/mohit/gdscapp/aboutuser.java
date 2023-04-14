package com.mohit.gdscapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class aboutuser extends AppCompatActivity {

    TextView welcome,name1,email1,about1;
    ImageView img;
    Uri selectedImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutuser);
        try {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e) {
        }
        name1=findViewById(R.id.nametext);
        email1=findViewById(R.id.emailtext);
        about1=findViewById(R.id.aboutusertext);
        welcome=findViewById(R.id.welcometext);
        img=findViewById(R.id.img);
        Intent intent = getIntent();
        String imageUriString = intent.getStringExtra("image_uri");
        Uri imageUri = Uri.parse(imageUriString);
        String text1 = intent.getStringExtra("name");
        String text2 = intent.getStringExtra("email");
        String text3 = intent.getStringExtra("about");
        Picasso.get().load(imageUri).into(img);
        name1.setText("Name: \n"+text1);
        email1.setText("Email: \n"+text2);
        about1.setText("About User: \n"+text3);
        welcome.setText("Hello "+text1);
    }

    }
