package org.scrum.psd.battleship.controller.dto;

public class HitStatus {

    private boolean isHit;

    private boolean turnEnd;

    private String desc;

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isTurnEnd() {
        return turnEnd;
    }

    public void setTurnEnd(boolean turnEnd) {
        this.turnEnd = turnEnd;
    }
}
