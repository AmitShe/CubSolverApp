package com.cubesolver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class instructions_Manual extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions__manual);
        setTitle("Cube Solver - Manual instructions");
    }
    public void closeWindow(View v){
        finish();
    }

}
