package com.cubesolver;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.cubesolver.solving_algorithm.Search;
import com.cubesolver.solving_algorithm.Tools;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;

import java.io.IOException;


public class camera_input extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    private full_cube gameCube = new full_cube();
    private enum_color sideSelect = enum_color.GRAY;
    private static final String TAG = "OpenCV Gradle Demo";
    private boolean orangeReady = false, redReady = false, whiteReady = false, yellowReady = false, blueReady = false, greenReady = false;
    //extView test;
    private double x = -1;
    private double y = -1;
    private Mat testFrame;
    private double[] rgb = {0, 0, 0};

    private CameraBridgeViewBase mOpenCvCameraView;
    //private TextView test;
    private Button button00;
    private Button button01;
    private Button button02;
    private Button button10;
    private Button button11;
    private Button button12;
    private Button button20;
    private Button button21;
    private Button button22;
    private Button sideSelectedButton;
    private Button TimeToSolve;
    private Button capture;
    private Button clear;
    private TextView sideSelectText;
    private TextView sideStatusText;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "called onCreate");
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_camera_input);
        //test = (TextView) findViewById(R.id.test_text);
        error = (TextView) findViewById(R.id.errorText);
        TimeToSolve = (Button) findViewById(R.id.solveButton);
        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.HelloOpenCvView);
        //mOpenCvCameraView.setRotation(90f);
        //mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);
        //test = (TextView) findViewById(R.id.test_text);
        button00 = (Button) findViewById(R.id.button00);
        button01 = (Button) findViewById(R.id.button01);
        button02 = (Button) findViewById(R.id.button02);
        button10 = (Button) findViewById(R.id.button10);
        button11 = (Button) findViewById(R.id.button11);
        button12 = (Button) findViewById(R.id.button12);
        button20 = (Button) findViewById(R.id.button20);
        button21 = (Button) findViewById(R.id.button21);
        button22 = (Button) findViewById(R.id.button22);
        capture = (Button) findViewById(R.id.take_pictue_button);
        //clear = (Button) findViewById(R.id.clearSideButton);
        setTitle("Cube Solver - Camera Input");
        //addTouchListener();
    }

    public void cameraHelpWindow(View v) {
        Intent intent = new Intent(v.getContext(), instructions_Camera.class);
        startActivityForResult(intent, 0);
    }

    private void makeSideReady(enum_color sideSelect) {
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

    public void sideSelect(View v) {

        sideSelectedButton = (Button) findViewById(v.getId());
        sideSelectText = (TextView) findViewById(R.id.side_select_text);
        sideStatusText = (TextView) findViewById(R.id.side_status);
        clear = (Button) findViewById(R.id.clearSideButton);
        capture.setEnabled(true);
        clear.setEnabled(true);
        sideStatusText.setText("");
        switch (v.getId()) {
            case R.id.orange_select:
                setSideSelect(enum_color.ORANGE);
                button11.getBackground().setColorFilter(enum_color.colorToRGB(enum_color.ORANGE), PorterDuff.Mode.MULTIPLY);
                setGameCubeTile(sideSelect, 1, 1, enum_color.ORANGE);
                sideSelectText.setText("Side selected: orange\n(upper side = white)");
                break;
            case R.id.red_select:
                setSideSelect(enum_color.RED);
                button11.getBackground().setColorFilter(enum_color.colorToRGB(enum_color.RED), PorterDuff.Mode.MULTIPLY);
                setGameCubeTile(sideSelect, 1, 1, enum_color.RED);
                sideSelectText.setText("Side selected: red\n(upper side = white)");
                break;
            case R.id.white_select:
                setSideSelect(enum_color.WHITE);
                button11.getBackground().setColorFilter(enum_color.colorToRGB(enum_color.WHITE), PorterDuff.Mode.MULTIPLY);
                setGameCubeTile(sideSelect, 1, 1, enum_color.WHITE);
                sideSelectText.setText("Side selected: white\n(upper side = blue)");
                break;
            case R.id.yellow_select:
                setSideSelect(enum_color.YELLOW);
                button11.getBackground().setColorFilter(enum_color.colorToRGB(enum_color.YELLOW), PorterDuff.Mode.MULTIPLY);
                setGameCubeTile(sideSelect, 1, 1, enum_color.YELLOW);
                sideSelectText.setText("Side selected: yellow\n(upper side = blue)");
                break;
            case R.id.blue_select:
                setSideSelect(enum_color.BLUE);
                button11.getBackground().setColorFilter(enum_color.colorToRGB(enum_color.BLUE), PorterDuff.Mode.MULTIPLY);
                setGameCubeTile(sideSelect, 1, 1, enum_color.BLUE);
                sideSelectText.setText("Side selected: blue\n(upper side = white)");
                break;
            case R.id.green_select:
                setSideSelect(enum_color.GREEN);
                button11.getBackground().setColorFilter(enum_color.colorToRGB(enum_color.GREEN), PorterDuff.Mode.MULTIPLY);
                setGameCubeTile(sideSelect, 1, 1, enum_color.GREEN);
                sideSelectText.setText("Side selected: green\n(upper side = white)");
                break;
        }
        start();
    }

    public void setSideSelect(enum_color selectedSide) {
        this.sideSelect = selectedSide;
        updateScreen(sideSelect);
    }

    private void updateScreen(enum_color sideSelect) {
        button00.getBackground().setColorFilter(enum_color.colorToRGB(gameCube.getSide(sideSelect).getSideTiles(0, 0)), PorterDuff.Mode.MULTIPLY);
        button01.getBackground().setColorFilter(enum_color.colorToRGB(gameCube.getSide(sideSelect).getSideTiles(0, 1)), PorterDuff.Mode.MULTIPLY);
        button02.getBackground().setColorFilter(enum_color.colorToRGB(gameCube.getSide(sideSelect).getSideTiles(0, 2)), PorterDuff.Mode.MULTIPLY);
        button10.getBackground().setColorFilter(enum_color.colorToRGB(gameCube.getSide(sideSelect).getSideTiles(1, 0)), PorterDuff.Mode.MULTIPLY);
        //button11.getBackground().setColorFilter(enum_color.colorToRGB(enum_color.ORANGE), PorterDuff.Mode.MULTIPLY);
        button12.getBackground().setColorFilter(enum_color.colorToRGB(gameCube.getSide(sideSelect).getSideTiles(1, 2)), PorterDuff.Mode.MULTIPLY);
        button20.getBackground().setColorFilter(enum_color.colorToRGB(gameCube.getSide(sideSelect).getSideTiles(2, 0)), PorterDuff.Mode.MULTIPLY);
        button21.getBackground().setColorFilter(enum_color.colorToRGB(gameCube.getSide(sideSelect).getSideTiles(2, 1)), PorterDuff.Mode.MULTIPLY);
        button22.getBackground().setColorFilter(enum_color.colorToRGB(gameCube.getSide(sideSelect).getSideTiles(2, 2)), PorterDuff.Mode.MULTIPLY);
    }

    private void addTouchListener() {
        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.HelloOpenCvView);
        //final TextView test = (TextView) findViewById(R.id.test_text);

        mOpenCvCameraView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x = event.getX();
                y = event.getY();

                printCor(x, y);

                //final TextView test = (TextView) findViewById(R.id.test_text);

                //rgb = test2.get((int) x,(int) y);

                //test.setText("X= " + Double.valueOf(x) + " Y= " + Double.valueOf(y));

                return false;
            }
        });

    }

    private void printCor(double x, double y) {
        //final TextView test = (TextView) findViewById(R.id.test_text);

        rgb = testFrame.get((int) x, (int) y);
        error.setText("R: " + Double.valueOf(rgb[0])
                        + " G: " + Double.valueOf(rgb[1]) + " B: " + Double.valueOf(rgb[2])

                /*
                 error.setText("X= " + Double.valueOf(x) + "\nY= " + Double.valueOf(y)
                        + "\n" + "RGB values--> " + "\nRed: " + Double.valueOf(rgb[0])
                        + "\nGreen: " + Double.valueOf(rgb[1]) + "\nBlue: " + Double.valueOf(rgb[2])
                 */
        );
    }

    public void start() {
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, this, mLoaderCallback);
    }


    public void stop() {
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();

    }

    @Override
    public void onResume() {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, this, mLoaderCallback);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    public void onCameraViewStarted(int width, int height) {
    }

    public void onCameraViewStopped() {
    }

    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        //final TextView test = (TextView) findViewById(R.id.test_text);
        //double local_X = x;
        //double local_Y = y;
        Mat mRgba = inputFrame.rgba();

        /// TODO: need to continue fixing the mat view!!!!
        
//        resize(mRgba, mRgba, mRgba.size(), 0, 1, INTER_CUBIC);

//        Mat mRgbaT = mRgba.t();
//        Core.flip(mRgba.t(), mRgbaT, 1);
//        Imgproc.resize(mRgbaT, mRgbaT, mRgba.size());
//
//        //Imgproc.rectangle(mRgbaT, new Point(0, 0), new Point(100, 100), new Scalar(0, 255, 0, 255), 5);
//
//        Imgproc.circle(mRgbaT, new Point(200, 480), 5, new Scalar(0, 255, 0, 255));
//        Imgproc.circle(mRgbaT, new Point(200, 780), 5, new Scalar(0, 255, 0, 255));
//        Imgproc.circle(mRgbaT, new Point(500, 180), 5, new Scalar(0, 255, 0, 255));
//
//        Imgproc.circle(mRgbaT, new Point(500, 780), 5, new Scalar(0, 255, 0, 255));
//        Imgproc.circle(mRgbaT, new Point(790, 180), 5, new Scalar(0, 255, 0, 255));
//        Imgproc.circle(mRgbaT, new Point(790, 480), 5, new Scalar(0, 255, 0, 255));
//
//        Imgproc.circle(mRgbaT, new Point(200, 180), 5, new Scalar(0, 255, 0, 255));
//        Imgproc.circle(mRgbaT, new Point(500, 480), 5, new Scalar(0, 255, 0, 255));
//        Imgproc.circle(mRgbaT, new Point(790, 780), 5, new Scalar(0, 255, 0, 255));
//
//        testFrame = mRgbaT;
//        return mRgbaT;


        testFrame = mRgba;
        return mRgba;
    }

    public void testCenterColor(View v) {
        String Color = "";

        rgb = testFrame.get(200, 180);

        // if red
        if (rgb[0] >= 230 && rgb[0] <= 255 && rgb[1] >= 0 && rgb[1] <= 65 && rgb[2] >= 10 && rgb[2] <= 65) {
            Color = "red";
        }

        // if orange
        if (rgb[0] >= 225 && rgb[0] <= 255 && rgb[1] >= 95 && rgb[1] <= 180 && rgb[2] >= 0 && rgb[2] <= 20) {
            Color = "orange";
        }

        // if white
        if (rgb[0] >= 220 && rgb[0] <= 255 && rgb[1] >= 235 && rgb[1] <= 255 && rgb[2] >= 235 && rgb[2] <= 255) {
            Color = "white";
        }

        // if yellow
        if (rgb[0] >= 240 && rgb[0] <= 255 && rgb[1] >= 220 && rgb[1] <= 255 && rgb[2] >= 0 && rgb[2] <= 40) {
            Color = "yellow";
        }

        // if green
        if (rgb[0] >= 50 && rgb[0] <= 120 && rgb[1] >= 170 && rgb[1] <= 235 && rgb[2] >= 0 && rgb[2] <= 25) {
            Color = "green";
        }

        // if blue
        if (rgb[0] >= 0 && rgb[0] <= 50 && rgb[1] >= 70 && rgb[1] <= 160 && rgb[2] >= 175 && rgb[2] <= 255) {
            Color = "blue";
        }

        //test.setText(Color + "\nRed: " + Double.valueOf(rgb[0])
        //              + "\nGreen: " + Double.valueOf(rgb[1]) + "\nBlue: " + Double.valueOf(rgb[2])
        // );
    }


    public void clearResult(View v) {
        button00.getBackground().setColorFilter(enum_color.colorToRGB(enum_color.GRAY), PorterDuff.Mode.MULTIPLY);
        button01.getBackground().setColorFilter(enum_color.colorToRGB(enum_color.GRAY), PorterDuff.Mode.MULTIPLY);
        button02.getBackground().setColorFilter(enum_color.colorToRGB(enum_color.GRAY), PorterDuff.Mode.MULTIPLY);
        button10.getBackground().setColorFilter(enum_color.colorToRGB(enum_color.GRAY), PorterDuff.Mode.MULTIPLY);
        //button11.getBackground().setColorFilter(enum_color.colorToRGB(enum_color.GRAY), PorterDuff.Mode.MULTIPLY);
        button12.getBackground().setColorFilter(enum_color.colorToRGB(enum_color.GRAY), PorterDuff.Mode.MULTIPLY);
        button20.getBackground().setColorFilter(enum_color.colorToRGB(enum_color.GRAY), PorterDuff.Mode.MULTIPLY);
        button21.getBackground().setColorFilter(enum_color.colorToRGB(enum_color.GRAY), PorterDuff.Mode.MULTIPLY);
        button22.getBackground().setColorFilter(enum_color.colorToRGB(enum_color.GRAY), PorterDuff.Mode.MULTIPLY);
        setGameCubeTile(sideSelect, 0, 0, enum_color.GRAY);
        setGameCubeTile(sideSelect, 0, 1, enum_color.GRAY);
        setGameCubeTile(sideSelect, 0, 2, enum_color.GRAY);
        setGameCubeTile(sideSelect, 1, 0, enum_color.GRAY);
        //setGameCubeTile(sideSelect, 1, 1, enum_color.GRAY);
        setGameCubeTile(sideSelect, 1, 2, enum_color.GRAY);
        setGameCubeTile(sideSelect, 2, 0, enum_color.GRAY);
        setGameCubeTile(sideSelect, 2, 1, enum_color.GRAY);
        setGameCubeTile(sideSelect, 2, 2, enum_color.GRAY);
    }


    public void captureSide(View v) {
        int i;
        double[] rgbTile;
        int xValue[] = {200, 500, 790, 200, 500, 790, 200, 500, 790};
        int yValue[] = {180, 180, 180, 480, 480, 480, 780, 780, 780};
        enum_color sideColors[] = {enum_color.GRAY, enum_color.GRAY, enum_color.GRAY, enum_color.GRAY, enum_color.GRAY, enum_color.GRAY, enum_color.GRAY, enum_color.GRAY, enum_color.GRAY};

        for (i = 0; i < 9; i++) {
            rgbTile = testFrame.get(xValue[i], yValue[i]);
            if (i == 1) {
                //   test.setText("x= " + Integer.valueOf(xValue[i]) + "y= " + Integer.valueOf(yValue[i]) + "\nRed: " + Double.valueOf(rgbTile[0])
                //           + "\nGreen: " + Double.valueOf(rgbTile[1]) + "\nBlue: " + Double.valueOf(rgbTile[2]));
            }
            sideColors[i] = getTileColor(rgbTile);
            rgbTile[0] = 0;
            rgbTile[1] = 0;
            rgbTile[2] = 0;
        }

        if (sideColors[0] != enum_color.GRAY) {
            button00.getBackground().setColorFilter(enum_color.colorToRGB(sideColors[0]), PorterDuff.Mode.MULTIPLY);
            setGameCubeTile(sideSelect, 0, 0, sideColors[0]);
        }
        if (sideColors[1] != enum_color.GRAY) {
            button01.getBackground().setColorFilter(enum_color.colorToRGB(sideColors[1]), PorterDuff.Mode.MULTIPLY);
            setGameCubeTile(sideSelect, 0, 1, sideColors[1]);
        }
        if (sideColors[2] != enum_color.GRAY) {
            button02.getBackground().setColorFilter(enum_color.colorToRGB(sideColors[2]), PorterDuff.Mode.MULTIPLY);
            setGameCubeTile(sideSelect, 0, 2, sideColors[2]);
        }
        if (sideColors[3] != enum_color.GRAY) {
            button10.getBackground().setColorFilter(enum_color.colorToRGB(sideColors[3]), PorterDuff.Mode.MULTIPLY);
            setGameCubeTile(sideSelect, 1, 0, sideColors[3]);
        }
        //if (sideColors[4]!=enum_color.GRAY){
        //    button11.getBackground().setColorFilter(enum_color.colorToRGB(sideColors[4]), PorterDuff.Mode.MULTIPLY);
        //}
        if (sideColors[5] != enum_color.GRAY) {
            button12.getBackground().setColorFilter(enum_color.colorToRGB(sideColors[5]), PorterDuff.Mode.MULTIPLY);
            setGameCubeTile(sideSelect, 1, 2, sideColors[5]);
        }
        if (sideColors[6] != enum_color.GRAY) {
            button20.getBackground().setColorFilter(enum_color.colorToRGB(sideColors[6]), PorterDuff.Mode.MULTIPLY);
            setGameCubeTile(sideSelect, 2, 0, sideColors[6]);
        }
        if (sideColors[7] != enum_color.GRAY) {
            button21.getBackground().setColorFilter(enum_color.colorToRGB(sideColors[7]), PorterDuff.Mode.MULTIPLY);
            setGameCubeTile(sideSelect, 2, 1, sideColors[7]);
        }
        if (sideColors[8] != enum_color.GRAY) {
            button22.getBackground().setColorFilter(enum_color.colorToRGB(sideColors[8]), PorterDuff.Mode.MULTIPLY);
            setGameCubeTile(sideSelect, 2, 2, sideColors[8]);
        }
        if (sideIsFull(sideSelect)) {
            markSideIsFull(sideSelect);
        }
    }




    private void markSideIsFull(enum_color sideSelect) {
        sideStatusText = (TextView) findViewById(R.id.side_status);
        sideStatusText.setText(enum_color.SideToSring(sideSelect) + " is done ! ! !");
        sideSelectedButton.setText("DONE!!!");
        makeSideReady(sideSelect);
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
        if (cubeReady()) {
            TimeToSolve.setEnabled(true);
        }
    }

    private boolean cubeReady() {
        if (orangeReady == true && redReady == true && whiteReady == true && yellowReady == true && blueReady == true && greenReady == true)
            return true;
        else
            return false;
    }

    private boolean sideIsFull(enum_color sideSelect) {
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

    public void setGameCubeTile(enum_color side, int tileX, int tileY, enum_color color) {
        (gameCube.getSide(side)).setSideTiles(tileX, tileY, color);
    }


    private enum_color getTileColor(double[] rgbTile) {
        enum_color res = enum_color.GRAY;

        // if red
        if (rgbTile[0] >= 155 && rgbTile[0] <= 255 && rgbTile[1] >= 0 && rgbTile[1] <= 70 && rgbTile[2] >= 0 && rgbTile[2] <= 65) {
            res = enum_color.RED;
        }

        // if orange
        if (rgbTile[0] >= 225 && rgbTile[0] <= 255 && rgbTile[1] >= 71 && rgbTile[1] <= 180 && rgbTile[2] >= 0 && rgbTile[2] <= 20) {
//      if (rgbTile[0] >= 200 && rgbTile[0] <= 255 && rgbTile[1] >= 0 && rgbTile[1] <= 110 && rgbTile[2] >= 0 && rgbTile[2] <= 65) {

            res = enum_color.ORANGE;
        }

        // if white
        if (rgbTile[0] >= 220 && rgbTile[0] <= 255 && rgbTile[1] >= 235 && rgbTile[1] <= 255 && rgbTile[2] >= 195 && rgbTile[2] <= 255) {
            res = enum_color.WHITE;
        }

        // if yellow
        if (rgbTile[0] >= 210 && rgbTile[0] <= 255 && rgbTile[1] >= 200 && rgbTile[1] <= 255 && rgbTile[2] >= 0 && rgbTile[2] <= 170) {
            res = enum_color.YELLOW;
        }

        // if green
        if (rgbTile[0] >= 0 && rgbTile[0] <= 150 && rgbTile[1] >= 80 && rgbTile[1] <= 235 && rgbTile[2] >= 0 && rgbTile[2] <= 85) {
            res = enum_color.GREEN;
        }

        // if blue
        if (rgbTile[0] >= 0 && rgbTile[0] <= 75 && rgbTile[1] >= 20 && rgbTile[1] <= 160 && rgbTile[2] >= 148 && rgbTile[2] <= 255) {
            res = enum_color.BLUE;
        }

        return res;
    }

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                    Log.i(TAG, "OpenCV loaded successfully");
                    mOpenCvCameraView.enableView();

                    break;

                default:
                    super.onManagerConnected(status);
                    break;
            }
        }
    };


    public void solveTheCube(View v) throws IOException {
        String solution;
        String cubeString = Tools.cubeToString(gameCube);
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
            //TextView headline = (TextView) findViewById(R.id.headline);
            error.setText(solution);
        } else {
/*
            try {
                FileOutputStream fOut = openFileOutput("settings.dat", MODE_WORLD_READABLE);
                OutputStreamWriter osw = new OutputStreamWriter(fOut);
                osw.write(solution);
                osw.flush();
                osw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Intent intent1 = new Intent(v.getContext(), result_Activity.class);
            startActivityForResult(intent1, 0);
*/

            Intent intent = new Intent(this, result_Activity.class);
            intent.putExtra("EXTRA_TIMERDATA",solution);
            startActivity(intent);
        }


    }
}