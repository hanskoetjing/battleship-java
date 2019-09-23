package org.scrum.psd.battleship.controller.dto;

public enum Letter {
    A, B, C, D, E, F, G, H;

    public static boolean isOnPlayingBoard(String val) {
        for(Letter letter : Letter.values()){
            if (val.equalsIgnoreCase(letter.toString())) {
                return true;
            }
        }

        return false;
    }
}
