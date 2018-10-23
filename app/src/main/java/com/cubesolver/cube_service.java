package com.cubesolver;

public class cube_service {

    //private full_cube gameCube;


    public void turnSide(full_cube gameCube, enum_color color) {
        // example of rotate blue

        enum_color sideToRotate[][] = {{enum_color.WHITE, enum_color.BLUE, enum_color.YELLOW, enum_color.GREEN}, {enum_color.WHITE, enum_color.GREEN, enum_color.YELLOW, enum_color.BLUE}, {enum_color.BLUE, enum_color.ORANGE, enum_color.GREEN, enum_color.RED}, {enum_color.BLUE, enum_color.RED, enum_color.GREEN, enum_color.ORANGE}, {enum_color.WHITE, enum_color.RED, enum_color.YELLOW, enum_color.ORANGE}, {enum_color.ORANGE, enum_color.YELLOW, enum_color.RED, enum_color.WHITE}};
//    ORANGE, RED, WHITE, YELLOW, BLUE, GREEN;

        int XofTile[][] = {{0, 0, 0, 2, 2, 2, 2, 2, 2, 0, 0, 0}, {2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0}, {2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0}, {0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2}, {2, 1, 0, 2, 2, 2, 2, 1, 0, 0, 0, 0}, {2, 2, 2, 0, 1, 2, 0, 0, 0, 0, 1, 2}};
        int YofTile[][] = {{0, 1, 2, 2, 1, 0, 2, 1, 0, 0, 1, 2}, {2, 1, 0, 2, 1, 0, 0, 1, 2, 0, 1, 2}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, {0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 1, 2}, {2, 1, 0, 2, 2, 2, 0, 1, 2, 2, 2, 2}};

        int sidePieceXofTile[] = {1, 0, 1, 2};
        int sidePieceYofTile[] = {0, 1, 2, 1};

        int cornerPieceXofTile[] = {0, 0, 2, 2};
        int cornerPieceYofTile[] = {0, 2, 2, 0};


        enum_color tempTiles[] = {enum_color.GRAY, enum_color.GRAY, enum_color.GRAY};
        enum_color tempSideTile = enum_color.GRAY;
        enum_color tempCornerTile = enum_color.GRAY;

        //twist Surrounding
        tempTiles[0] = gameCube.getSide(sideToRotate[color.ordinal()][0]).getSideTiles(XofTile[color.ordinal()][0], YofTile[color.ordinal()][0]);
        tempTiles[1] = gameCube.getSide(sideToRotate[color.ordinal()][0]).getSideTiles(XofTile[color.ordinal()][1], YofTile[color.ordinal()][1]);
        tempTiles[2] = gameCube.getSide(sideToRotate[color.ordinal()][0]).getSideTiles(XofTile[color.ordinal()][2], YofTile[color.ordinal()][2]);

        // red to white
        gameCube.getSide(sideToRotate[color.ordinal()][0]).setSideTiles(XofTile[color.ordinal()][0], YofTile[color.ordinal()][0], gameCube.getSide(sideToRotate[color.ordinal()][1]).getSideTiles(XofTile[color.ordinal()][3], YofTile[color.ordinal()][3]));
        gameCube.getSide(sideToRotate[color.ordinal()][0]).setSideTiles(XofTile[color.ordinal()][1], YofTile[color.ordinal()][1], gameCube.getSide(sideToRotate[color.ordinal()][1]).getSideTiles(XofTile[color.ordinal()][4], YofTile[color.ordinal()][4]));
        gameCube.getSide(sideToRotate[color.ordinal()][0]).setSideTiles(XofTile[color.ordinal()][2], YofTile[color.ordinal()][2], gameCube.getSide(sideToRotate[color.ordinal()][1]).getSideTiles(XofTile[color.ordinal()][5], YofTile[color.ordinal()][5]));

        // yellow to red
        gameCube.getSide(sideToRotate[color.ordinal()][1]).setSideTiles(XofTile[color.ordinal()][3], YofTile[color.ordinal()][3], gameCube.getSide(sideToRotate[color.ordinal()][2]).getSideTiles(XofTile[color.ordinal()][6], YofTile[color.ordinal()][6]));
        gameCube.getSide(sideToRotate[color.ordinal()][1]).setSideTiles(XofTile[color.ordinal()][4], YofTile[color.ordinal()][4], gameCube.getSide(sideToRotate[color.ordinal()][2]).getSideTiles(XofTile[color.ordinal()][7], YofTile[color.ordinal()][7]));
        gameCube.getSide(sideToRotate[color.ordinal()][1]).setSideTiles(XofTile[color.ordinal()][5], YofTile[color.ordinal()][5], gameCube.getSide(sideToRotate[color.ordinal()][2]).getSideTiles(XofTile[color.ordinal()][8], YofTile[color.ordinal()][8]));

        // orange to yellow
        gameCube.getSide(sideToRotate[color.ordinal()][2]).setSideTiles(XofTile[color.ordinal()][6], YofTile[color.ordinal()][6], gameCube.getSide(sideToRotate[color.ordinal()][3]).getSideTiles(XofTile[color.ordinal()][9], YofTile[color.ordinal()][9]));
        gameCube.getSide(sideToRotate[color.ordinal()][2]).setSideTiles(XofTile[color.ordinal()][7], YofTile[color.ordinal()][7], gameCube.getSide(sideToRotate[color.ordinal()][3]).getSideTiles(XofTile[color.ordinal()][10], YofTile[color.ordinal()][10]));
        gameCube.getSide(sideToRotate[color.ordinal()][2]).setSideTiles(XofTile[color.ordinal()][8], YofTile[color.ordinal()][8], gameCube.getSide(sideToRotate[color.ordinal()][3]).getSideTiles(XofTile[color.ordinal()][11], YofTile[color.ordinal()][11]));

        // temp to orange
        gameCube.getSide(sideToRotate[color.ordinal()][3]).setSideTiles(XofTile[color.ordinal()][9], YofTile[color.ordinal()][9], tempTiles[0]);
        gameCube.getSide(sideToRotate[color.ordinal()][3]).setSideTiles(XofTile[color.ordinal()][10], YofTile[color.ordinal()][10], tempTiles[1]);
        gameCube.getSide(sideToRotate[color.ordinal()][3]).setSideTiles(XofTile[color.ordinal()][11], YofTile[color.ordinal()][11], tempTiles[2]);


        // twist side piece
        tempSideTile = gameCube.getSide(color).getSideTiles(sidePieceXofTile[0], sidePieceYofTile[0]);
        gameCube.getSide(color).setSideTiles(sidePieceXofTile[0], sidePieceYofTile[0], gameCube.getSide(color).getSideTiles(sidePieceXofTile[1], sidePieceYofTile[1]));
        gameCube.getSide(color).setSideTiles(sidePieceXofTile[1], sidePieceYofTile[1], gameCube.getSide(color).getSideTiles(sidePieceXofTile[2], sidePieceYofTile[2]));
        gameCube.getSide(color).setSideTiles(sidePieceXofTile[2], sidePieceYofTile[2], gameCube.getSide(color).getSideTiles(sidePieceXofTile[3], sidePieceYofTile[3]));
        gameCube.getSide(color).setSideTiles(sidePieceXofTile[3], sidePieceYofTile[3], tempSideTile);


        // twist corner piece
        tempCornerTile = gameCube.getSide(color).getSideTiles(cornerPieceXofTile[0], cornerPieceYofTile[0]);
        gameCube.getSide(color).setSideTiles(cornerPieceXofTile[0], cornerPieceYofTile[0], gameCube.getSide(color).getSideTiles(cornerPieceXofTile[1], cornerPieceYofTile[1]));
        gameCube.getSide(color).setSideTiles(cornerPieceXofTile[1], cornerPieceYofTile[1], gameCube.getSide(color).getSideTiles(cornerPieceXofTile[2], cornerPieceYofTile[2]));
        gameCube.getSide(color).setSideTiles(cornerPieceXofTile[2], cornerPieceYofTile[2], gameCube.getSide(color).getSideTiles(cornerPieceXofTile[3], cornerPieceYofTile[3]));
        gameCube.getSide(color).setSideTiles(cornerPieceXofTile[3], cornerPieceYofTile[3], tempCornerTile);

    /*
    // white to temp
    tempTiles[0] = gameCube.getSide(enum_color.WHITE).getSideTiles(2,0);
    tempTiles[1] = gameCube.getSide(enum_color.WHITE).getSideTiles(1,0);
    tempTiles[2] = gameCube.getSide(enum_color.WHITE).getSideTiles(0,0);

    // red to white
    gameCube.getSide(enum_color.WHITE).setSideTiles(2, 0, gameCube.getSide(enum_color.RED).getSideTiles(2,2));
    gameCube.getSide(enum_color.WHITE).setSideTiles(1, 0, gameCube.getSide(enum_color.RED).getSideTiles(2,1));
    gameCube.getSide(enum_color.WHITE).setSideTiles(0, 0, gameCube.getSide(enum_color.RED).getSideTiles(2,0));

    // yellow to red
    gameCube.getSide(enum_color.RED).setSideTiles(2, 2, gameCube.getSide(enum_color.YELLOW).getSideTiles(2,0));
    gameCube.getSide(enum_color.RED).setSideTiles(2, 1, gameCube.getSide(enum_color.YELLOW).getSideTiles(1,0));
    gameCube.getSide(enum_color.RED).setSideTiles(2, 0, gameCube.getSide(enum_color.YELLOW).getSideTiles(0,0));

    // orange to yellow
    gameCube.getSide(enum_color.YELLOW).setSideTiles(2, 0, gameCube.getSide(enum_color.ORANGE).getSideTiles(0,0));
    gameCube.getSide(enum_color.YELLOW).setSideTiles(1, 0, gameCube.getSide(enum_color.ORANGE).getSideTiles(0,1));
    gameCube.getSide(enum_color.YELLOW).setSideTiles(0, 0, gameCube.getSide(enum_color.ORANGE).getSideTiles(0,2));

    // temp to orange
    gameCube.getSide(enum_color.ORANGE).setSideTiles(0, 0, tempTiles[0]);
    gameCube.getSide(enum_color.ORANGE).setSideTiles(0, 1, tempTiles[1]);
    gameCube.getSide(enum_color.ORANGE).setSideTiles(0, 2, tempTiles[2]);
     */


    }


    //public void turnSide(full_cube gameCube, enum_color color){
    public String solveTheCube(full_cube gameCube) {
        int numOfTurns = 0;
        String lastTurn = "X";
        String secondToLastTurn = "X";
        String SolveAlgorithm;

        SolveAlgorithm = doingASolve(numOfTurns, secondToLastTurn, lastTurn, gameCube);
        return SolveAlgorithm;
    }


    //solving white = U, green = F
    private String doingASolve(int numOfTurns, String secondToLastTurn, String lastTurn, full_cube gameCube) {
        String res = "";
        if (numOfTurns > 5) {
            return "";
        } else if (cubeIsSolved(gameCube)) {
            return "VVV"; // it a mark the function to return the twist
        } else {
            /*
            if (res.equals("")) {
                res = checkSide(gameCube, enum_color.WHITE, numOfTurns, res);
            }
            if (res.equals("")) {
                res = checkSide(gameCube, enum_color.YELLOW, numOfTurns, res);
            }
            if (res.equals("")) {
                res = checkSide(gameCube, enum_color.ORANGE, numOfTurns, res);
            }
            if (res.equals("")) {
                res = checkSide(gameCube, enum_color.RED, numOfTurns, res);
            }
            if (res.equals("")) {
                res = checkSide(gameCube, enum_color.GREEN, numOfTurns, res);
            }
            if (res.equals("")) {
                res = checkSide(gameCube, enum_color.BLUE, numOfTurns, res);
            }


            if (lastTurn != "U" && res.equals("")) {
                res = checkSide(gameCube, enum_color.WHITE, numOfTurns, res);
            }
            if (lastTurn != "D" && res.equals("")) {
                res = checkSide(gameCube, enum_color.YELLOW, numOfTurns, res);
            }
            if (lastTurn != "L" && res.equals("")) {
                res = checkSide(gameCube, enum_color.ORANGE, numOfTurns, res);
            }
            if (lastTurn != "R" && res.equals("")) {
                res = checkSide(gameCube, enum_color.RED, numOfTurns, res);
            }
            if (lastTurn != "F" && res.equals("")) {
                res = checkSide(gameCube, enum_color.GREEN, numOfTurns, res);
            }
            if (lastTurn != "B" && res.equals("")) {
                res = checkSide(gameCube, enum_color.BLUE, numOfTurns, res);
            }
                        */


            if (secondToLastTurn != "U" && secondToLastTurn != "D" && lastTurn != "U" && res.equals("")) {
                res = checkSide(gameCube, lastTurn, enum_color.WHITE, numOfTurns, res);
            }
            if (secondToLastTurn != "L" && secondToLastTurn != "R" && lastTurn != "L" && res.equals("")) {
                res = checkSide(gameCube, lastTurn, enum_color.ORANGE, numOfTurns, res);
            }
            if (secondToLastTurn != "B" && secondToLastTurn != "F" && lastTurn != "B" && res.equals("")) {
                res = checkSide(gameCube, lastTurn, enum_color.BLUE, numOfTurns, res);
            }
            if (secondToLastTurn != "D" && secondToLastTurn != "U" && lastTurn != "D" && res.equals("")) {
                res = checkSide(gameCube, lastTurn, enum_color.YELLOW, numOfTurns, res);
            }
            if (secondToLastTurn != "R" && secondToLastTurn != "L" && lastTurn != "R" && res.equals("")) {
                res = checkSide(gameCube, lastTurn, enum_color.RED, numOfTurns, res);
            }
            if (secondToLastTurn != "F" && secondToLastTurn != "B" && lastTurn != "F" && res.equals("")) {
                res = checkSide(gameCube, lastTurn, enum_color.GREEN, numOfTurns, res);
            }
        }
        return res;
    }

    private String checkSide(full_cube gameCube,String lastTurn, enum_color color, int numOfTurns, String res) {

        // 90 deg turn
        turnSide(gameCube, color);
        if (cubeIsSolved(gameCube)){
            //if (res.equals("VVV")) {
            res = enum_color.colorToDirection(color) + enum_direction.directionToSymble(enum_direction.CLOCKWISE);
            //} else {
            //    res = enum_color.colorToDirection(color) + enum_direction.directionToSymble(enum_direction.CLOCKWISE) + " " + res;
            //}
        }
        else{
            // 180 deg turn
            turnSide(gameCube, color);
            if (cubeIsSolved(gameCube)) {
                //if (res.equals("VVV")) {
                res = enum_color.colorToDirection(color) + enum_direction.directionToSymble(enum_direction.HALF_TURN);
                //} else {
                //    res = enum_color.colorToDirection(color) + enum_direction.directionToSymble(enum_direction.HALF_TURN) + res;
                //}
            }
            else{
                // 270 deg turn
                turnSide(gameCube, color);
                if (cubeIsSolved(gameCube)) {
                    //if (res.equals("VVV")) {
                    res = enum_color.colorToDirection(color) + enum_direction.directionToSymble(enum_direction.COUNTERCLOCKWISE);
                    //} else {
                    //    res = enum_color.colorToDirection(color) + enum_direction.directionToSymble(enum_direction.COUNTERCLOCKWISE) + res;
                    //}
                }
                //recursive call
                else{
                    //return cube to normal
                    turnSide(gameCube, color);
                    // check after 90 turn
                    turnSide(gameCube, color);
                    res = res + doingASolve(numOfTurns + 1, lastTurn, enum_color.colorToDirection(color), gameCube);
                    if (!res.equals("")){
                        if (res.equals("VVV")) {
                            res = enum_color.colorToDirection(color) + enum_direction.directionToSymble(enum_direction.CLOCKWISE);
                        } else {
                            res = enum_color.colorToDirection(color) + enum_direction.directionToSymble(enum_direction.CLOCKWISE) + res;
                        }
                    }
                    else{
                        // check after 180 turn
                        turnSide(gameCube, color);
                        res = res + doingASolve(numOfTurns + 1, lastTurn, enum_color.colorToDirection(color), gameCube);
                        if (!res.equals("")){
                            if (res.equals("VVV")) {
                                res = enum_color.colorToDirection(color) + enum_direction.directionToSymble(enum_direction.HALF_TURN);
                            } else {
                                res = enum_color.colorToDirection(color) + enum_direction.directionToSymble(enum_direction.HALF_TURN) + res;
                            }
                        }
                        else{
                            // check after 270 turn
                            turnSide(gameCube, color);

                            res = res + doingASolve(numOfTurns + 1, lastTurn, enum_color.colorToDirection(color), gameCube);
                            if (!res.equals("")){
                                if (res.equals("VVV")) {
                                    res = enum_color.colorToDirection(color) + enum_direction.directionToSymble(enum_direction.COUNTERCLOCKWISE);
                                } else {
                                    res = enum_color.colorToDirection(color) + enum_direction.directionToSymble(enum_direction.COUNTERCLOCKWISE) + res;
                                }
                            }
                            //return cube to normal
                            turnSide(gameCube, color);
                        }
                    }



                }
            }

        }
        return res;
    }

    private boolean cubeIsSolved(full_cube gameCube) {
        return cubeReady(gameCube);
    }

    /*
    private String checkSide(full_cube gameCube, enum_color color, int numOfTurns, String res) {

        // 90 deg turn
        turnSide(gameCube, color);
        res = res + doingASolve(numOfTurns + 1, enum_color.colorToDirection(color), gameCube);
        if (!res.equals("")) {
            if (res.equals("VVV")) {
                res = enum_color.colorToDirection(color) + enum_direction.directionToSymble(enum_direction.CLOCKWISE);
            } else {
                res = enum_color.colorToDirection(color) + enum_direction.directionToSymble(enum_direction.CLOCKWISE) + " " + res;
            }
        } else {
            // 180 deg turn
            turnSide(gameCube, color);
            res = res + doingASolve(numOfTurns + 1, enum_color.colorToDirection(color), gameCube);

            if (!res.equals("")) {
                if (res.equals("VVV")) {
                    res = enum_color.colorToDirection(color) + enum_direction.directionToSymble(enum_direction.HALF_TURN);
                } else {
                    res = enum_color.colorToDirection(color) + enum_direction.directionToSymble(enum_direction.HALF_TURN) + res;
                }
            } else {
                // 270 deg turn
                turnSide(gameCube, color);
                res = res + doingASolve(numOfTurns + 1, enum_color.colorToDirection(color), gameCube);
                if (!res.equals("")) {
                    if (res.equals("VVV")) {
                        res = enum_color.colorToDirection(color) + enum_direction.directionToSymble(enum_direction.COUNTERCLOCKWISE);
                    } else {
                        res = enum_color.colorToDirection(color) + enum_direction.directionToSymble(enum_direction.COUNTERCLOCKWISE) + res;
                    }
                }
                //return cube to normal
                turnSide(gameCube, color);

            }
        }
        return res;
    }

    private boolean cubeIsSolved(full_cube gameCube) {
        return cubeReady(gameCube);


    }
    */

    private boolean cubeReady(full_cube gameCube) {
        if (sideIsReady(enum_color.BLUE, gameCube) == true &&
                sideIsReady(enum_color.GREEN, gameCube) == true &&
                sideIsReady(enum_color.WHITE, gameCube) == true &&
                sideIsReady(enum_color.YELLOW, gameCube) == true &&
                sideIsReady(enum_color.RED, gameCube) == true &&
                sideIsReady(enum_color.ORANGE, gameCube) == true) {
            return true;
        } else {
            return false;
        }
    }


    private boolean sideIsReady(enum_color sideSelect, full_cube gameCube) {
        boolean res = true;
        int x, y;
        for (x = 0; x < 3; x++) {
            for (y = 0; y < 3; y++) {
                //enum_color test = gameCube.getSide(sideSelect).getSideTiles(x,y);
                if (gameCube.getSide(sideSelect).getSideTiles(x, y) != sideSelect)
                    res = false;
            }
        }
        return res;
    }






/*
TO DO ! ! !
get/set tile (set side in full cube(side, tile x, tile y, color) -> setSideTiles/getSideTiles  )
*/
/*
public void setGameCubeTile(enum_color side, int tileX, int tileY, enum_color color){
    (gameCube.getSide(side)).setSideTiles(tileX, tileY, color);
}


    public enum_color getGameCubeTile(enum_color side, int tileX, int tileY){
      return   (gameCube.getSide(side)).getSideTiles(tileX, tileY);
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"service started...", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"service destroyed...", Toast.LENGTH_LONG).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public void onCreate() {
        super.onCreate();
    }

    */

}
