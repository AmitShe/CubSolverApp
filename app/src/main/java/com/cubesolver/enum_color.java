package com.cubesolver;

import android.graphics.Color;


public enum enum_color {
    ORANGE, RED, WHITE, YELLOW, BLUE, GREEN, GRAY;

    public static int colorToRGB(enum_color color_to_change) {
        int red_value, green_value, blue_value;
        switch (color_to_change)
        {
            case ORANGE:
                red_value = 255;
                green_value = 111;
                blue_value = 0;
                break;
            case RED:
                red_value = 255;
                green_value = 0;
                blue_value = 0;
                break;
            case WHITE:
                red_value = 248;
                green_value = 248;
                blue_value = 255;
                break;
            case YELLOW:
                red_value = 255;
                green_value = 255;
                blue_value = 0;
                break;
            case BLUE:
                red_value = 0;
                green_value = 85;
                blue_value = 255;
                break;
            case GREEN:
                red_value = 0;
                green_value = 255;
                blue_value = 0;
                break;
            case GRAY:
                red_value = 192;
                green_value = 192;
                blue_value = 192;
                break;
            default: // shouldn't be hare :)
                red_value = 0;
                green_value = 0;
                blue_value = 0;
                break;
        }
        return Color.rgb(red_value,green_value,blue_value);
    }

    public static String colorToDirection(enum_color sideColor) {
        String res = "A";
        switch (sideColor) {
            case ORANGE:
                res = "L";
                break;
            case RED:
                res = "R";
                break;
            case WHITE:
                res = "U";
                break;
            case YELLOW:
                res = "D";
                break;
            case BLUE:
                res = "B";
                break;
            case GREEN:
                res = "F";
                break;
            case GRAY:
                res = "X";
                break;

        }
        return res;
    }

    public static enum_color nextColor (enum_color prevColor) {
        enum_color res = enum_color.GRAY;
        switch (prevColor) {
            case ORANGE:
                res = RED;
                break;
            case RED:
                res = WHITE;
                break;
            case WHITE:
                res = YELLOW;
                break;
            case YELLOW:
                res = BLUE;
                break;
            case BLUE:
                res = GREEN;
                break;
            case GREEN:
                res = WHITE;
                break;
            case GRAY:
                res = ORANGE;
                break;
        }
        return res;
    }

    public static String symbleToSide(char side_to_change) {
        enum_color return_value = enum_color.GRAY;
        switch (side_to_change)
        {
            case 'U':
                return_value = enum_color.WHITE;
                break;
            case 'D':
                return_value = enum_color.YELLOW;
                break;
            case 'F':
                return_value = enum_color.GREEN;
                break;
            case 'B':
                return_value = enum_color.BLUE;
                break;
            case 'R':
                return_value = enum_color.RED;
                break;
            case 'L':
                return_value = enum_color.ORANGE;
                break;
        }
        return SideToSring(return_value);
    }

    public static String SideToSring(enum_color sideColor) {
        String res = "A";
        switch (sideColor) {
            case ORANGE:
                res = "orange";
                break;
            case RED:
                res = "red";
                break;
            case WHITE:
                res = "white";
                break;
            case YELLOW:
                res = "yellow";
                break;
            case BLUE:
                res = "blue";
                break;
            case GREEN:
                res = "green";
                break;
        }
        return res;
    }

    public static com.cubesolver.solving_algorithm.Color enumColorToAlgorithmColor (enum_color prevColor) {
        com.cubesolver.solving_algorithm.Color res = com.cubesolver.solving_algorithm.Color.U;
        switch (prevColor) {
            case ORANGE:
                res = com.cubesolver.solving_algorithm.Color.L;
                break;
            case RED:
                res = com.cubesolver.solving_algorithm.Color.R;
                break;
            case WHITE:
                res = com.cubesolver.solving_algorithm.Color.U;
                break;
            case YELLOW:
                res = com.cubesolver.solving_algorithm.Color.D;
                break;
            case BLUE:
                res = com.cubesolver.solving_algorithm.Color.B;
                break;
            case GREEN:
                res = com.cubesolver.solving_algorithm.Color.F;
                break;
            case GRAY:
                res = com.cubesolver.solving_algorithm.Color.F;
                break;
        }
        return res;
    }
}
