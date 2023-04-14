package com.mohit.gdscapp;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Instrumentation;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    EditText name, email, aboutuser;
    Button button;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;
    private Button btnSelectImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        imageView = findViewById(R.id.imageview);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        aboutuser = findViewById(R.id.aboutuser);
        button = findViewById(R.id.submitbutton);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitData();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();
            try {
                imageView.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), mImageUri));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void submitData() {
        String text1 = name.getText().toString().trim();
        String text2 = email.getText().toString().trim();
        String text3 = aboutuser.getText().toString().trim();
        if (name.getText().toString().isEmpty() || email.getText().toString().isEmpty() || aboutuser.getText().toString().isEmpty()||mImageUri==null) {
            Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MainActivity.this, aboutuser.class);
            intent.putExtra("image_uri", mImageUri.toString());
            intent.putExtra("name", text1);
            intent.putExtra("email", text2);
            intent.putExtra("about", text3);
            Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_SHORT).show();
            startActivity(intent);

        }
    }
}

