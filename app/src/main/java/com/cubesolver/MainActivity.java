package com.cubesolver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button manual_input = (Button) findViewById(R.id.manual_button);
        manual_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), manual_inputMainActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        Button camera_input = (Button) findViewById(R.id.camera_button);
        camera_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), camera_input.class);
                startActivityForResult(intent, 0);
            }
        });


        Button General_instructions = (Button) findViewById(R.id.General_instructions);
        General_instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), instructions_General.class);
                startActivityForResult(intent, 0);
            }
        });


        Button robot = (Button) findViewById(R.id.robot_test);
        robot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), robot_solver.class);
                startActivityForResult(intent, 0);
            }
        });
    }
    }
