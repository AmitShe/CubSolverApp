package com.cubesolver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class instructions_Camera extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions__camera);
    }
    public void close_camera_Window(View v){
        finish();
    }

}
