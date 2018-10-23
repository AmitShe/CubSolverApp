package com.cubesolver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class instructions_General extends AppCompatActivity {

    private Button closeButtom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions__general);
        closeButtom = (Button) findViewById(R.id.close);
    }
    public void closeWindow(View v){
        finish();
    }

}
