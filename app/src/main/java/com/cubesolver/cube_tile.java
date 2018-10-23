package com.cubesolver;

public class cube_tile {
    private enum_color tileColor = enum_color.GRAY;

    public cube_tile(enum_color tileColor){
        setTileColor(tileColor = enum_color.GRAY);
    }

    public void setTileColor (enum_color tile_color){
        this.tileColor = tile_color;
    }

    public enum_color getTileColor (){
        return this.tileColor;
    }
}
