package com.coelhovictor.tictactoe.objs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author VictorCoelho
 */
public class Game {
    
    /**
     * Game ID.
     */
    private final String id;
    
    /**
     * All game <code>Spot</code>.
     */
    private final HashMap<String, Spot> spots;
    
    /**
     * Game status.
     */
    private boolean live;
    
    /**
     * Game winner.
     */
    private SpotType winner;
    
    /**
     * All winner <code>Spot</code>.
     */
    private List<Spot> winnerSpots;
    
    /**
     * Current <code>TurnType</code>.
     */
    private TurnType turnType;
    
    /**
     * Game created time.
     */
    private final long timing;
    
    /** 
     * Class contructor.
     */
    public Game() {
        this.id = UUID.randomUUID().toString();
        this.spots = new HashMap<>();
        this.live = true;
        this.turnType = TurnType.PLAYER;
        this.winner = null;
        this.winnerSpots = new ArrayList<>();
        this.timing = System.currentTimeMillis();
    }
    
    /**
     * Returns the game Id.
     * 
     * @return <code>String</code> the game id
     */
    public String getId() { return this.id; }
    
    /**
     * Returns the game created time.
     * 
     * @return <code>long</code> the game created time
     */
    public long getTiming() { return this.timing; }
    
    /**
     * Returns is the game is live.
     * 
     * @return <code>boolean</code> if the game is live
     */
    public boolean isLive() { return this.live; }
    
    /**
     * Returns the game <code>TurnType</code>.
     * 
     * @return <code>TurnType</code> the game turn type
     */
    public TurnType getTurnType() { return this.turnType; }
    
    /**
     * Returns the game winner.
     * 
     * @return <code>SpotType</code> the game winner 
     */
    public SpotType getWinner() { return this.winner; }
    
    /**
     * Returns all the winner spots.
     * 
     * @return <code>Spot</code> all the winner spots
     */
    public List<Spot> getWinnerSpots() { return this.winnerSpots; }
    
    /**
     * Returns all the avaliable spots.
     * 
     * @return <code>Spot</code> all the avaliable spots
     */
    public List<Spot> avaliableSpots() {
        List<Spot> ls = new ArrayList<>();
        
        if(this.live) {
            for(Spot as : this.spots.values()) {
                if(as.getSpot() == SpotType.NONE) {
                    ls.add(as);
                }
            }
        }
        
        return ls;
    }
     
    /**
     * Execute a game action.
     * 
     * @param action the game action.
     * @return <code>SpotType</code> the spot type
     */
    public SpotType action(GameAction action) {
        
        /**
         * Process action
         */
        
        TurnType actionTurnType = action.getTurnType();
        
        if(!this.live) {
            
            /**
             * Game is not live
             */
            
            return null;
        }
        
        if(this.turnType != actionTurnType) {
            
            /**
             * Wrong turn
             */
            
            return null;
        }
        
        String keySpot = keySpot(action);
        Spot spot = this.spots.get(keySpot);
        
        if(spot.getSpot() != SpotType.NONE) {
            
            /**
             * Spot already selected
             */
            
            return null;
        }
        
        if(actionTurnType == TurnType.CPU) {
            
            /**
             * CPU action
             */
            
            spot.setSpot(SpotType.O);
            
        } else {
            
            /**
             * Player action
             */
            
            spot.setSpot(SpotType.X);
            
        }
        
        this.spots.put(keySpot, spot);
        
        if(actionTurnType == TurnType.CPU) {
            this.turnType = TurnType.PLAYER;
        } else {
            this.turnType = TurnType.CPU;
        }
        
        return spot.getSpot();
    }
    
    /**
     * Set a game spot.
     * 
     * @param key spot key.
     * @param spot the spot.
     */
    public void setSpot(String key, Spot spot) { this.spots.put(key, spot); }
    
    /**
     * Check if has a winner.
     * 
     * @param action current game action.
     * @return <code>SpotType</code> the spot type 
     */
    public SpotType checkWinner(GameAction action) {

        SpotType winner = null;
        List<Spot> winnerSpots = null;
        
        /**
         * First verification
         */
        
        for(int ways = 0; ways < 2; ways++) {
            
            for(int rows = 0; rows < 3; rows++) {
            
                List<Spot> count = new ArrayList<>();

                for(int columns = 0; columns < 3; columns++) {

                    String keySpot;
                    if(ways == 0) {
                        keySpot = keySpot(rows, columns);
                    } else {
                        keySpot = keySpot(columns, rows);
                    }
                    
                    Spot spot = this.spots.get(keySpot);
                    count.add(spot);

                }

                if(count.get(0).getSpot() != SpotType.NONE 
                        && (count.get(0).getSpot() == count.get(1).getSpot() 
                        && count.get(1).getSpot() == count.get(2).getSpot())) {

                    String explain = "";
                    for(int i = 0; i < 3; i++) {
                        explain = explain + keySpot(count.get(i).getRow(), count.get(i).getColumn()) + ", ";
                    }
                    
                    winner = count.get(0).getSpot();
                    winnerSpots = count;
                    
                    break;
                }

            }
            
        }

        /**
         * Second verification
         */
        
        if(winner == null) {
            
            Spot targetA = this.spots.get(keySpot(0, 0));
            Spot targetB = this.spots.get(keySpot(1, 1));
            Spot targetC = this.spots.get(keySpot(2, 2));
            
            if(targetA.getSpot() != SpotType.NONE
                    && (targetA.getSpot() == targetB.getSpot()
                    && targetB.getSpot() == targetC.getSpot())) {
                
                winner = targetA.getSpot();
                winnerSpots = Arrays.asList(targetA, targetB, targetC);
                
            }
            
        }
        
        /**
         * Third verification
         */
        
        if(winner == null) {
            
            Spot targetA = this.spots.get(keySpot(0, 2));
            Spot targetB = this.spots.get(keySpot(1, 1));
            Spot targetC = this.spots.get(keySpot(2, 0));
            
            if(targetA.getSpot() != SpotType.NONE
                    && (targetA.getSpot() == targetB.getSpot()
                    && targetB.getSpot() == targetC.getSpot())) {
                
                winner = targetA.getSpot();
                winnerSpots = Arrays.asList(targetA, targetB, targetC);
                
            }
            
        }
       
        if(winner != null) {
            
            /**
             * Setting winner
             */

            this.winner = winner;
            this.winnerSpots = winnerSpots;
            this.live = false;

            return this.winner;
        }

        return null;
    }
    
    /**
     * Returns if is a draw.
     * 
     * @return <code>boolean</code> if is a draw
     */
    public boolean checkDraw() {
        
        if(this.winner == null && avaliableSpots().isEmpty()) {
            
            this.live = false;
            
            return true;
        }
        
        return false;
    }
    
    /**
     * Returns the spot key.
     * 
     * @param row num of the row
     * @param column num of the column
     * @return <code>String</code> the spot key
     */
    private String keySpot(int row, int column) {
        return row + "@" + column;
    }
    
    /**
     * Returns the spot key.
     * 
     * @param action current game action
     * @return <code>String</code> the spot key
     */
    private String keySpot(GameAction action) {
        return keySpot(action.getRow(), action.getColumn());
    }
    
}
