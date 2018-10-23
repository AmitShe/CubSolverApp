package com.cubesolver;

public class full_cube {

    private cube_side[] cube_sides = new cube_side[6];

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// oriantation! ! !                                                                                                                             //
// blue/ogange/green/red -> white up                                                                                                            //
// white/yellow -> blue up                                                                                                            //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public full_cube(){
        cube_sides[0] = new cube_side(enum_color.ORANGE);
        cube_sides[1] = new cube_side(enum_color.RED);
        cube_sides[2] = new cube_side(enum_color.WHITE);
        cube_sides[3] = new cube_side(enum_color.YELLOW);
        cube_sides[4] = new cube_side(enum_color.BLUE);
        cube_sides[5] = new cube_side(enum_color.GREEN);
    }

    // set side(side, tile x, tile y, color)
    public cube_side getSide(enum_color color){
        return  cube_sides[color.ordinal()];
    }
}
