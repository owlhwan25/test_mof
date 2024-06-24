package com.cookandroid.selfpractice8_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Button btnPrev, btnNext;
    TextView showText;
    myPictureView myPicture;
    int curNum = 0;
    File[] imageFiles = new File[0];
    String imageFname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 이미지 뷰어");
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        showText = findViewById(R.id.showNumber);
        myPicture = findViewById(R.id.myPictureView1);

        File[] allFiles = new File("/data/data/com.cookandroid.selfpractice8_2/files").listFiles();
        for (int i=0; i<allFiles.length; i++) {
            if (allFiles[i].isFile()) {
                imageFiles = Arrays.copyOf(imageFiles, imageFiles.length + 1);
                imageFiles[imageFiles.length - 1] = allFiles[i];
            }
        }
        showText.setText(Integer.toString(curNum + 1) + "/" + Integer.toString(imageFiles.length));
        imageFname = imageFiles[curNum].toString();
        myPicture.imagePath = imageFname;


        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(curNum <= 0) {
                    curNum = imageFiles.length - 1;
                }
                else {
                    curNum--;
                }
                showText.setText(Integer.toString(curNum + 1) + "/" + Integer.toString(imageFiles.length));
                imageFname = imageFiles[curNum].toString();
                myPicture.imagePath = imageFname;
                myPicture.invalidate();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(curNum >= imageFiles.length - 1) {
                    curNum = 0;

                }
                else {
                    curNum++;
                }
                showText.setText(Integer.toString(curNum + 1) + "/" + Integer.toString(imageFiles.length));
                imageFname = imageFiles[curNum].toString();
                myPicture.imagePath = imageFname;
                myPicture.invalidate();
            }
        });
    }
}