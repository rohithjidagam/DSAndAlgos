package com.uh.blackjack;

public class Player {
    
    private int id;
    private int score;
    private boolean inactive;
    private boolean hasAce;
    private int altScore;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public boolean isInactive() {
        return inactive;
    }
    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }
    public boolean isHasAce() {
        return hasAce;
    }
    public void setHasAce(boolean hasAce) {
        this.hasAce = hasAce;
    }
    public int getAltScore() {
        return altScore;
    }
    public void setAltScore(int altScore) {
        this.altScore = altScore;
    }
    

}
