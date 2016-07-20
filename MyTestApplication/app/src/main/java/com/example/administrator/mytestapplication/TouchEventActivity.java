package com.example.administrator.mytestapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class TouchEventActivity extends AppCompatActivity {

    private TouchTestView touchview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_touch_event);



        initView();

    }

    private void initView() {
        touchview = (TouchTestView) findViewById(R.id.touchview);

        touchview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("Touch", "Listener==============");
                return true;
            }
        });
    }
}
