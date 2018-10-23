package com.cubesolver.solving_algorithm;

/**
 * Created by amit_sh on 01/06/2016.
 */

import com.cubesolver.enum_color;
import com.cubesolver.full_cube;

import java.util.Random;


public class Tools {


    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Check if the cube string s represents a solvable cube.
    // 0: Cube is solvable
    // -1: There is not exactly one facelet of each colour
    // -2: Not all 12 edges exist exactly once
    // -3: Flip error: One edge has to be flipped
    // -4: Not all corners exist exactly once
    // -5: Twist error: One corner has to be twisted
    // -6: Parity error: Two corners or two edges have to be exchanged
    //
    /**
     * Check if the cube definition string s represents a solvable cube.
     *
     * @param s is the cube definition string , see {@link Facelet}
     * @return 0: Cube is solvable<br>
     *         -1: There is not exactly one facelet of each colour<br>
     *         -2: Not all 12 edges exist exactly once<br>
     *         -3: Flip error: One edge has to be flipped<br>
     *         -4: Not all 8 corners exist exactly once<br>
     *         -5: Twist error: One corner has to be twisted<br>
     *         -6: Parity error: Two corners or two edges have to be exchanged
     */
    public static int verify(String s) {
        int[] count = new int[6];
        try {
            for (int i = 0; i < 54; i++)
                count[Color.valueOf(s.substring(i, i + 1)).ordinal()]++;
        } catch (Exception e) {
            return -1;
        }

        for (int i = 0; i < 6; i++)
            if (count[i] != 9)
                return -1;

        FaceCube fc = new FaceCube(s);
        CubieCube cc = fc.toCubieCube();

        return cc.verify();
    }

    /**
     * Generates a random cube.
     * @return A random cube in the string representation. Each cube of the cube space has the same probability.
     */
    public static String randomCube() {
        CubieCube cc = new CubieCube();
        Random gen = new Random();
        cc.setFlip((short) gen.nextInt(CoordCube.N_FLIP));
        cc.setTwist((short) gen.nextInt(CoordCube.N_TWIST));
        do {
            cc.setURFtoDLB(gen.nextInt(CoordCube.N_URFtoDLB));
            cc.setURtoBR(gen.nextInt(CoordCube.N_URtoBR));
        } while ((cc.edgeParity() ^ cc.cornerParity()) != 0);
        FaceCube fc = cc.toFaceCube();
        return fc.to_String();
    }

    public static String cubeToString(full_cube gameCube) {
        int i,j;
        String cubeString = "";
        /*
        U1, U2, U3, U4, U5, U6, U7, U8, U9,
        R1, R2, R3, R4, R5, R6, R7, R8, R9,
        F1, F2, F3, F4, F5, F6, F7, F8, F9,
        D1, D2, D3, D4, D5, D6, D7, D8, D9,
        L1, L2, L3, L4, L5, L6, L7, L8, L9,
        B1, B2, B3, B4, B5, B6, B7, B8, B9
        */

        // add white side;
        for (i=0 ; i<3 ; i++){
            for (j=0 ; j<3 ; j++) {
                cubeString += enum_color.colorToDirection(gameCube.getSide(enum_color.WHITE).getSideTiles(j,i));
            }
        }

        // add red side;
        for (i=0 ; i<3 ; i++){
            for (j=0 ; j<3 ; j++) {
                cubeString += enum_color.colorToDirection(gameCube.getSide(enum_color.RED).getSideTiles(j,i));
            }
        }

        // add green side;
        for (i=0 ; i<3 ; i++){
            for (j=0 ; j<3 ; j++) {
                cubeString += enum_color.colorToDirection(gameCube.getSide(enum_color.GREEN).getSideTiles(j,i));
            }
        }

        // add yellow side;
        for (i=2 ; i>=0 ; i--){
            for (j=2 ; j>=0 ; j--) {
                cubeString += enum_color.colorToDirection(gameCube.getSide(enum_color.YELLOW).getSideTiles(j,i));
            }
        }

        // add orange side;
        for (i=0 ; i<3 ; i++){
            for (j=0 ; j<3 ; j++) {
                cubeString += enum_color.colorToDirection(gameCube.getSide(enum_color.ORANGE).getSideTiles(j,i));
            }
        }

        // add blue side;
        for (i=0 ; i<3 ; i++){
            for (j=0 ; j<3 ; j++) {
                cubeString += enum_color.colorToDirection(gameCube.getSide(enum_color.BLUE).getSideTiles(j,i));
            }
        }
        return cubeString;
    }
}
