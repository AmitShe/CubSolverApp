package com.cubesolver;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cubesolver.solving_algorithm.Search;
import com.cubesolver.solving_algorithm.Tools;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class manual_inputMainActivity extends AppCompatActivity {


    private full_cube gameCube = new full_cube();
    private enum_color colorSelect = enum_color.GRAY;
    private enum_color sideSelect = enum_color.GRAY;
    private boolean orangeReady = false, redReady = false, whiteReady = false, yellowReady = false, blueReady = false, greenReady = false;


    public void setColorSelect(enum_color selectedColor) {
        this.colorSelect = selectedColor;
    }

    public enum_color getColorSelect() {
        return this.colorSelect;
    }


    public void setSideSelect(enum_color selectedSide) {
        this.sideSelect = selectedSide;
    }

    public enum_color getSideSelect() {
        return this.sideSelect;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_input_main);

        TextView headline = (TextView) findViewById(R.id.headline);
        headline.setPaintFlags(headline.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        TextView side_headline = (TextView) findViewById(R.id.side_headline);
        side_headline.setPaintFlags(side_headline.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        setTitle("Cube Solver - Manual Input");


        TextView color_select_headline = (TextView) findViewById(R.id.color_select_headline);
        color_select_headline.setPaintFlags(color_select_headline.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }


    public void setTile(View v) {
        //        android:id="@+id/button10"
        //int selectedTile = v.getId();
        String selectedTile = v.getResources().getResourceName(v.getId());
        //selectedTile = selectedTile.substring(31);
        //String x = selectedTile.substring(6,7);
        //String y = selectedTile.substring(7);
        String x = selectedTile.substring(37, 38);
        String y = selectedTile.substring(38);
        int XValue = Integer.parseInt(x);
        int YValue = Integer.parseInt(y);
        //(((TextView) findViewById(R.id.color_select_headline))).setText("Tile select x=" + Integer.toString(XValue) + " y=" + Integer.toString(YValue)); // for testing  ! ! !
        if (colorSelect == enum_color.GRAY || sideSelect == enum_color.GRAY) {
            (((TextView) findViewById(R.id.color_select_headline))).setText("Error ! ! ! !"); // for testing  ! ! !
        } else {
            setGameCubeTile(sideSelect, XValue, YValue, colorSelect);
            updetePressTile(XValue, YValue, colorSelect, v.getId());
            if (sideIsReady(sideSelect)) {
                markSideReady(sideSelect);
                if (cubeReady()) {
                    final Button solve_button = (Button) findViewById(R.id.buttonSolve);
                    solve_button.setEnabled(true);
                }
            } else {
                makeSideNotReady(sideSelect);
            }
        }
    }

    private void makeSideNotReady(enum_color sideSelect) {
        switch (sideSelect) {
            case ORANGE:
                orangeReady = false;
                break;
            case RED:
                redReady = false;
                break;
            case WHITE:
                whiteReady = false;
                break;
            case YELLOW:
                yellowReady = false;
                break;
            case BLUE:
                blueReady = false;
                break;
            case GREEN:
                greenReady = false;
                break;
        }
    }


    private boolean cubeReady() {
        if (orangeReady == true && redReady == true && whiteReady == true && yellowReady == true && blueReady == true && greenReady == true)
            return true;
        else
            return false;
    }

    private void markSideReady(enum_color sideSelect) {
        switch (sideSelect) {
            case ORANGE:
                orangeReady = true;
                break;
            case RED:
                redReady = true;
                break;
            case WHITE:
                whiteReady = true;
                break;
            case YELLOW:
                yellowReady = true;
                break;
            case BLUE:
                blueReady = true;
                break;
            case GREEN:
                greenReady = true;
                break;
        }
    }

    private boolean sideIsReady(enum_color sideSelect) {
        boolean res = true;
        int x, y;
        for (x = 0; x < 3; x++) {
            for (y = 0; y < 3; y++) {
                if (gameCube.getSide(sideSelect).getSideTiles(x, y) == enum_color.GRAY)
                    res = false;
            }
        }
        return res;
    }

    public void colorSelect(View v) {
        // this function start on orange_color_select
        switch (v.getId()) {
            case R.id.orange_color_select:
                setColorSelect(enum_color.ORANGE);
                break;
            case R.id.red_color_select:
                setColorSelect(enum_color.RED);
                break;
            case R.id.white_color_select:
                setColorSelect(enum_color.WHITE);
                break;
            case R.id.yellow_color_select:
                setColorSelect(enum_color.YELLOW);
                break;
            case R.id.blue_color_select:
                setColorSelect(enum_color.BLUE);
                break;
            case R.id.green_color_select:
                setColorSelect(enum_color.GREEN);
                break;
        }
        (((TextView) findViewById(R.id.color_select_headline))).setText("color select " + colorSelect.name()); // for testing  ! ! !

    }

    public void sideSelect(View v) {
        // this function start on orange_color_select
        Button centerButton = (Button) findViewById(R.id.button11);
        switch (v.getId()) {
            case R.id.orange_side_select:
                setSideSelect(enum_color.ORANGE);
                // centerButton.getBackground().setColorFilter(enum_color.colorToRGB(enum_color.ORANGE), PorterDuff.Mode.MULTIPLY); // for testing  ! ! !
                centerButton.setText("ORANGE");
                break;
            case R.id.red_side_select:
                setSideSelect(enum_color.RED);
                // centerButton.getBackground().setColorFilter(enum_color.colorToRGB(enum_color.RED), PorterDuff.Mode.MULTIPLY); // for testing  ! ! !
                centerButton.setText("RED");
                break;
            case R.id.white_side_select:
                setSideSelect(enum_color.WHITE);
                // centerButton.getBackground().setColorFilter(enum_color.colorToRGB(enum_color.WHITE), PorterDuff.Mode.MULTIPLY); // for testing  ! ! !
                centerButton.setText("WHITE");
                break;
            case R.id.yellow_side_select:
                setSideSelect(enum_color.YELLOW);
                // centerButton.getBackground().setColorFilter(enum_color.colorToRGB(enum_color.YELLOW), PorterDuff.Mode.MULTIPLY); // for testing  ! ! !
                centerButton.setText("YELLOW");
                break;
            case R.id.blue_side_select:
                setSideSelect(enum_color.BLUE);
                // centerButton.getBackground().setColorFilter(enum_color.colorToRGB(enum_color.BLUE), PorterDuff.Mode.MULTIPLY); // for testing  ! ! !
                centerButton.setText("BLUE");
                break;
            case R.id.green_side_select:
                setSideSelect(enum_color.GREEN);
                // centerButton.getBackground().setColorFilter(enum_color.colorToRGB(enum_color.GREEN), PorterDuff.Mode.MULTIPLY); // for testing  ! ! !
                centerButton.setText("GREEN");
                break;
        }
        (((TextView) findViewById(R.id.side_headline))).setText("side select " + sideSelect.name()); // for testing  ! ! !

        // TO DO ! ! !
        // add updeteTileColorOnScreen (enum_color color){
        updeteTileColorOnScreen(sideSelect);

    }


    public void updetePressTile(int XValue, int YValue, enum_color color, int buttonID) {
        Button selectedButom = (Button) findViewById(buttonID);
        selectedButom.getBackground().setColorFilter(enum_color.colorToRGB(color), PorterDuff.Mode.MULTIPLY);

    }


    public void setGameCubeTile(enum_color side, int tileX, int tileY, enum_color color) {
        (gameCube.getSide(side)).setSideTiles(tileX, tileY, color);

        //Button selectedButom = (Button) findViewById(R.id.buttonSolve); // for testing  ! ! !
        //selectedButom.getBackground().setColorFilter(enum_color.colorToRGB(gameCube.getSide(side).getsideColor()), PorterDuff.Mode.MULTIPLY); // for testing  ! ! !

    }

    public void updeteTileColorOnScreen(enum_color color) {
        int x, y;
        Button upButtom = (Button) findViewById(R.id.up_side_color);
        upButtom.getBackground().setColorFilter(enum_color.colorToRGB(gameCube.getSide(color).getUpSide()), PorterDuff.Mode.MULTIPLY);

        for (x = 0; x < 3; x++) {
            for (y = 0; y < 3; y++) {
                String buttonID = "button".concat(Integer.toString(x)).concat(Integer.toString(y));
                int resID = getResources().getIdentifier(buttonID, "id", this.getPackageName());
                Button selectedButtom = (Button) findViewById(resID);
                // (((TextView) findViewById(R.id.side_headline))).setText(selectedButom.getText()); // for testing  ! ! !

                selectedButtom.getBackground().setColorFilter(enum_color.colorToRGB(gameCube.getSide(color).getSideTiles(x, y)), PorterDuff.Mode.MULTIPLY);

                /*
                 public enum_color getGameCubeTile(enum_color side, int tileX, int tileY){
      return   (gameCube.getSide(side)).getSideTiles(tileX, tileY);
    }
                 */


            }
        }

    }

    // for testing
    public void turnBlue(View v) {
        cube_service service = new cube_service();
        service.turnSide(gameCube, colorSelect);
        updeteTileColorOnScreen(sideSelect);
    }


    public void solveTheCube(View v) {
        cube_service service = new cube_service();
        String solution = service.solveTheCube(gameCube);

        TextView headline = (TextView) findViewById(R.id.headline);
        headline.setText(solution);


    }

    public void showSolusion(View v) {
        Intent intent = new Intent(v.getContext(), result_Activity.class);
        intent.putExtra("EXTRA_TIMERDATA", "R L2");
        startActivityForResult(intent, 0);
    }


    public void manualHelpWindow(View v) {
        Intent intent = new Intent(v.getContext(), instructions_Manual.class);
        startActivityForResult(intent, 0);
    }


    public void solveTheCube_test(View v) throws IOException {
        String solution;
        String cubeString =  Tools.cubeToString(gameCube);
        solution = Search.solution(cubeString, 24, 5, false);
        if (solution.contains("Error")) {
            switch (solution.charAt(solution.length() - 1)) {
                case '1':
                    solution = "There are not exactly nine facelets of each color!";
                    break;
                case '2':
                    solution = "Not all 12 edges exist exactly once!";
                    break;
                case '3':
                    solution = "Flip error: One edge has to be flipped!";
                    break;
                case '4':
                    solution = "Not all 8 corners exist exactly once!";
                    break;
                case '5':
                    solution = "Twist error: One corner has to be twisted!";
                    break;
                case '6':
                    solution = "Parity error: Two corners or two edges have to be exchanged!";
                    break;
                case '7':
                    solution = "No solution exists for the given maximum move number!";
                    break;
                case '8':
                    solution = "Timeout, no solution found within given maximum time!";
                    break;
            }
            TextView headline = (TextView) findViewById(R.id.headline);
            headline.setText(solution);
        } else {


            try {
                FileOutputStream fOut = openFileOutput("settings.dat", MODE_WORLD_READABLE);
                OutputStreamWriter osw = new OutputStreamWriter(fOut);
                osw.write(solution);
                osw.flush();
                osw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(v.getContext(), result_Activity.class);
            intent.putExtra("EXTRA_TIMERDATA", solution);
            startActivityForResult(intent, 0);
        }
    }
}
