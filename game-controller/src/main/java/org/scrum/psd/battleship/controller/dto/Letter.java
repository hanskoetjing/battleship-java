package org.scrum.psd.battleship.controller.dto;

public enum Letter {
    A, B, C, D, E, F, G, H;
    //I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z;

    public static boolean isOnPlayingBoard(String val) {
        for(Letter letter : Letter.values()){
            if (val.equalsIgnoreCase(letter.toString())) {
                return true;
            }
        }

        return false;
    }
    public static int getNum(String targ) {
        return valueOf(targ).ordinal();
    }

    public static int getNum(char targ) {
        return valueOf(String.valueOf(targ)).ordinal();
    }    
}
