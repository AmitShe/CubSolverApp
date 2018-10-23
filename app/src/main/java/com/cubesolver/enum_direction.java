package com.cubesolver;

public enum enum_direction {
    CLOCKWISE, COUNTERCLOCKWISE, HALF_TURN;

    public static char directionToSymble(enum_direction direction_to_change) {
        char return_value;
        switch (direction_to_change)
        {
            case CLOCKWISE:
                return_value = ' ';
                break;
            case COUNTERCLOCKWISE:
                return_value = '\'';
                break;
            case HALF_TURN:
                return_value = '2';
                break;
            default:
                return_value = 'X';
                break;
        }
        return return_value;
    }

    public static String symbleToDirection(char direction_to_change) {
        enum_direction return_value = CLOCKWISE;
        switch (direction_to_change)
        {
            case ' ':
                return_value = CLOCKWISE;
                break;
            case '\'':
                return_value = COUNTERCLOCKWISE;
                break;
            case '2':
                return_value = HALF_TURN;
                break;
        }
        return directionToString(return_value);
    }

    private static String directionToString(enum_direction direction) {
        String return_value = "";
        switch (direction)
        {
            case CLOCKWISE:
                return_value = "90° clockwise";
                break;
            case COUNTERCLOCKWISE:
                return_value = "90° counterclockwise";
                break;
            case HALF_TURN:
                return_value = "half a turn";
                break;
        }
        return return_value;

    }


}