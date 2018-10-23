package com.cubesolver;

public enum enum_sides {
    LEFT, RIGHT, UP, DOWN, BACK, FRONT;

    public static char sideToSymble(enum_sides side_to_change) {
        char return_value;
        switch (side_to_change)
        {
            case LEFT:
                return_value = 'L';
                break;
            case RIGHT:
                return_value = 'R';
                break;
            case UP:
                return_value = 'U';
                break;
            case DOWN:
                return_value = 'D';
                break;
            case BACK:
                return_value = 'B';
                break;
            case FRONT:
                return_value = 'F';
                break;
            default:
                return_value = 'X';
                break;
        }
        return return_value;
    }
}

