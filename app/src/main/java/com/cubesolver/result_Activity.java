package com.cubesolver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class result_Activity extends AppCompatActivity {
    private TextView headline;
    private String solution;
    private String normal_solution;
    private Button nextButtom;
    private Button prevtButtom;
    private Button robotButtom;
    private int stepShowing = 0;
    String[] arraySolution = new String[24];
    int sizeOfSolution = 0;

    String side = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        headline = (TextView) findViewById(R.id.result_text);
        //solution = getStringFromFile();
        solution = (String) getIntent().getStringExtra("EXTRA_TIMERDATA");
        normal_solution = buildNormalSolution(solution);
        nextButtom = (Button) findViewById(R.id.nextButton);
        prevtButtom = (Button) findViewById(R.id.prevButtom);
        robotButtom = (Button) findViewById(R.id.robotButton);
        //headline = (TextView) findViewById(R.id.result_text);
        //headline.setText(getStringFromFile());
    }
    private String buildNormalSolution(String solution) {
        String res = "";
        int i = 0;

        String direction = "";
        char ch;

        for(i = 0; i < solution.length(); i++)
        {
            ch = solution.charAt(i);
            //if (ch == 'U' || ch == 'D' || ch == 'R' || ch == 'L' || ch == 'F' || ch == 'B'){
            side=addColorFromChar(ch);
            i++;
            if (i < solution.length()){ // if last move was ' ' not go over solution.length()
                ch = solution.charAt(i);
                //}
                //else if(ch == '\'' || ch == '2' || ch == ' ' ){
                direction=addDirectionFromChar(ch);
                i++;
                if (i < solution.length()) { // if last move was ''' or '2' not go over solution.length()
                    ch = solution.charAt(i);
                    //}
                    if (ch != ' ') {
                        i--;
                    }
                }
            }
            else{
                direction="clockwise";
            }

            res+="* Turn " + side + " side " + direction + "\n";
            arraySolution[sizeOfSolution] = "* Turn " + side + " side " + direction;
            sizeOfSolution++;
        }

        return res;
    }

    private String addDirectionFromChar(char ch) {

        return enum_direction.symbleToDirection(ch);
    }

    private String addColorFromChar(char ch) {
        return enum_color.symbleToSide(ch);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_normal:
                if (checked) {
                    nextButtom.setVisibility(Button.VISIBLE);
                    prevtButtom.setVisibility(Button.VISIBLE);
                    robotButtom.setVisibility(Button.INVISIBLE);
                    //headline.setText(normal_solution);
                    stepShowing = 0;
                    showNormalSolution();
                    break;
                }
            case R.id.radio_expert:
                if (checked){
                    nextButtom.setVisibility(Button.INVISIBLE);
                    prevtButtom.setVisibility(Button.INVISIBLE);
                    robotButtom.setVisibility(Button.INVISIBLE);
                    headline.setText("Hold the cube that the white side is facing up and green side is facing to the front:\n" + solution);
                    break;
                }
            case R.id.radio_robot:
                if (checked){
                    nextButtom.setVisibility(Button.INVISIBLE);
                    prevtButtom.setVisibility(Button.INVISIBLE);
                    robotButtom.setVisibility(Button.VISIBLE);
                    headline.setText("Let the robot solve it ! ! !");
                    break;
                }
        }
    }

    private void showNormalSolution() {
        headline.setText("step " + Integer.toString(stepShowing+1) + " of " + sizeOfSolution +":\n" + arraySolution[stepShowing]);
    }

    public void nextPress(View v) {
        if (stepShowing < sizeOfSolution-1) {
            stepShowing++;
            showNormalSolution();
        }
        else if (stepShowing == sizeOfSolution-1){
            stepShowing++;
            headline.setText("The cube is now solved ! ! !\nGood for you :)");

        }
    }

    public void prevPress(View v) {
        if (stepShowing > 0) {
            stepShowing--;
            showNormalSolution();
        }
    }

    public void robotPress(View v) {
        Intent intent = new Intent(v.getContext(), robot_solver.class);
        intent.putExtra("EXTRA_TIMERDATA",solution);
        startActivityForResult(intent, 0);
    }


    private String getStringFromFile() {
/*
        StringBuffer datax = new StringBuffer("");
        try {
            FileInputStream fIn = openFileInput ( "settings.dat" ) ;
            InputStreamReader isr = new InputStreamReader( fIn ) ;
            BufferedReader buffreader = new BufferedReader ( isr ) ;

            String readString = buffreader.readLine ( ) ;
            while ( readString != null ) {
                datax.append(readString);
                readString = buffreader.readLine ( ) ;
            }

            isr.close ( ) ;
        } catch ( IOException ioe ) {
            ioe.printStackTrace ( ) ;
        }
        return datax.toString() ;
        */
        return (String) getIntent().getStringExtra("EXTRA_TIMERDATA");

    }
}
