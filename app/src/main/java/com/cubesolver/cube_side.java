package com.cubesolver;

public class cube_side {
    private enum_color side_color;
    private enum_color up_color;
    // private cube_tile[][] side_tiles = new cube_tile[3][3];
    private cube_tile[][] side_tiles = {
            {new cube_tile(enum_color.GRAY), new cube_tile(enum_color.GRAY), new cube_tile(enum_color.GRAY)},
            {new cube_tile(enum_color.GRAY), new cube_tile(enum_color.GRAY), new cube_tile(enum_color.GRAY)},
            {new cube_tile(enum_color.GRAY), new cube_tile(enum_color.GRAY), new cube_tile(enum_color.GRAY)}
    };
    private cube_tile[][] side_neighbours = new cube_tile[4][3]; // how to do at ?!?!?!?


    public cube_side(enum_color sideColor) {
        setSideColor(sideColor);
        switch (sideColor) {
            case ORANGE:
                setUpSide(enum_color.WHITE);
                break;
            case RED:
                setUpSide(enum_color.WHITE);
                break;
            case WHITE:
                setUpSide(enum_color.BLUE);
                break;
            case YELLOW:
                setUpSide(enum_color.BLUE);
                break;
            case BLUE:
                setUpSide(enum_color.WHITE);
                break;
            case GREEN:
                setUpSide(enum_color.WHITE);
                break;

        }
    }





    private void setSideColor (enum_color side_color){
        this.side_color = side_color;
        this.setSideTiles(1, 1, side_color);
    }

    public enum_color getsideColor (){
        return this.side_color;
    }

    public void setSideTiles(int row, int col, enum_color color) {
        if (row != 1 || col != 1){
            side_tiles[row][col].setTileColor(color);
        }
        else{
            /// shouldn't be hare ! ! !
        }
    }



    public enum_color getSideTiles(int row, int col) {
        if (row != 1 || col != 1){
            return side_tiles[row][col].getTileColor();
        }
        else{
            return getsideColor();
        }
    }


    public void setUpSide(enum_color upColor) {
        this.up_color = upColor ;
    }

    public enum_color getUpSide() {
        return up_color;
    }
}
