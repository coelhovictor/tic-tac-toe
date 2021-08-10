package com.coelhovictor.tictactoe.objs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VictorCoelho
 */
public class ChoosePrepare {
    
    /**
     * Spots list.
     */
    private final List<Spot> spots;
    
    /**
     * Class constructor.
     */
    public ChoosePrepare() {
        this.spots = new ArrayList<>();
    }
    
    /**
     * Returns all spots list.
     * 
     * @return <code>Spot</code> all spots list 
     */
    public List<Spot> getList() { return this.spots; }
    
    /**
     * Returns current score separated.
     * 
     * @return <code>int[]</code> current score separated
     */
    public int[] getScoreSeparated() {
        int scoreX = 0;
        int scoreO = 0;
        
        int none = 0;
        
        for(Spot s : this.spots) {
            if(s.getSpot() != SpotType.NONE) {
                for(Spot ss : this.spots) {
                    if(ss != s) {
                        if(ss.getSpot() == s.getSpot()) {
                            if(s.getSpot() == SpotType.X) {
                                scoreX++;
                            } else {
                                scoreO++;
                            }
                        }
                    }
                }
            } else {
                none++;
            }
        }
        
        if(none == 0) {
            return new int[]{0, 0};
        }
        
        return new int[]{scoreX, scoreO};
    }
    
    /**
     * Returns current score.
     * 
     * @return <code>int</code> current score
     */
    public int getScore() {
        
        int[] score = getScoreSeparated();
        
        int scoreX = score[0];
        int scoreO = score[1];
        
        if(scoreX > scoreO && scoreO == 0) {
            return scoreX;
        }
        
        if(scoreO > scoreX && scoreX == 0) {
            return scoreO;
        }
        
        return scoreX;
    }
    
    /**
     * Returns current choose.
     * 
     * @return <code>Spot</code> current choose.
     */
    public Spot choose() {
        Spot choose = null;
        
        for(Spot s : this.spots) {
            if(s.getSpot() == SpotType.NONE) {
                choose = s;
            }
        }
        
        return choose;
    }
    
    /**
     * Add a new spot.
     * 
     * @param spot new spot
     */
    public void add(Spot spot) { this.spots.add(spot); }
    
}
