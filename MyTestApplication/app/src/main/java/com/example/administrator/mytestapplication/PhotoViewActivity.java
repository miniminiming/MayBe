package com.example.administrator.mytestapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bm.library.Info;
import com.bm.library.PhotoView;

public class PhotoViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_photo_view);
        final PhotoView iv_photo= (PhotoView) findViewById(R.id.iv_photo);
        PhotoView iv_from1= (PhotoView) findViewById(R.id.iv_from1);
        PhotoView iv_from2= (PhotoView) findViewById(R.id.iv_from2);
        PhotoView iv_from3= (PhotoView) findViewById(R.id.iv_from3);
        PhotoView iv_from4= (PhotoView) findViewById(R.id.iv_from4);


        final Info info1=iv_from1.getInfo();
        final Info info2=iv_from2.getInfo();
        final Info info3=iv_from3.getInfo();
        final Info info4=iv_from4.getInfo();

        iv_photo.enable();
        iv_from1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                iv_photo.setAnimaDuring(1000);
                iv_photo.animaFrom(info1);

            }
        });
        iv_from2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                iv_photo.setAnimaDuring(1000);
                iv_photo.animaFrom(info2);

            }
        });
        iv_from4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                iv_photo.setAnimaDuring(1000);
                iv_photo.animaFrom(info4);

            }
        });
        iv_from3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                iv_photo.setAnimaDuring(1000);
                iv_photo.animaFrom(info3);

            }
        });


//        iv_photo.animaTo(info, new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(PhotoViewActivity.this,"缩小完成",Toast.LENGTH_SHORT).show();
//            }
//        });



    }

}
