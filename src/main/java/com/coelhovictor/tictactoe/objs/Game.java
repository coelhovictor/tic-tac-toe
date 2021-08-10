package com.coelhovictor.tictactoe.objs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
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
     * Player spot type.
     */
    private final SpotType playerSpotType;
    
    /**
     * Game difficulty.
     */
    private final Difficulty difficulty;
    
    /**
     * Current <code>TurnType</code>.
     */
    private TurnType turnType;
    
    /**
     * Starting playing;
     */
    private final boolean startPlaying;
    
    /**
     * Game created time.
     */
    private final long timing;
    
    /** 
     * Class contructor.
     * 
     * @param playerSpotType the player spot type
     * @param startPlaying if player start playing
     * @param difficulty game difficulty
     */
    public Game(SpotType playerSpotType, boolean startPlaying, Difficulty difficulty) {
        this.id = UUID.randomUUID().toString();
        this.spots = new HashMap<>();
        this.live = true;
        this.startPlaying = startPlaying;
        if(startPlaying) {
            this.turnType = TurnType.PLAYER;
        } else {
            this.turnType = TurnType.CPU;
        }
        this.difficulty = difficulty;
        this.winner = null;
        this.winnerSpots = new ArrayList<>();
        this.playerSpotType = playerSpotType;
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
     * Returns the game difficulty
     * 
     * @return <code>Difficulty</code> of the game
     */
    public Difficulty getDifficulty() { return this.difficulty; }
    
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
     * Returns the player spot type.
     * 
     * @return <code>SpotType</code> player spot type
     */
    public SpotType getPlayerSpotType() { return this.playerSpotType; }
    
    /**
     * Returns the CPU spot type.
     * 
     * @return <code>SpotType</code> CPU spot type
     */
    public SpotType getCPUSpotType() { return this.playerSpotType == SpotType.X ? SpotType.O : SpotType.X; }
    
    /**
     * Returns if player start playing.
     * 
     * @return <code>boolean</code> if player start playing
     */
    public boolean startPlaying() { return this.startPlaying; }
    
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
            
            if(this.playerSpotType == SpotType.X) {
                spot.setSpot(SpotType.O);
            } else {
                spot.setSpot(SpotType.X);
            }
            
        } else {
            
            /**
             * Player action
             */
            
            if(this.playerSpotType == SpotType.X) {
                spot.setSpot(SpotType.X);
            } else {
                spot.setSpot(SpotType.O);
            }
            
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

        SpotType winnerBrute = null;
        List<Spot> winnerSpotsBrute = null;
        
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
                    
                    winnerBrute = count.get(0).getSpot();
                    winnerSpotsBrute = count;
                    
                    break;
                }

            }
            
        }

        /**
         * Second verification
         */
        
        if(winnerBrute == null) {
            
            Spot targetA = this.spots.get(keySpot(0, 0));
            Spot targetB = this.spots.get(keySpot(1, 1));
            Spot targetC = this.spots.get(keySpot(2, 2));
            
            if(targetA.getSpot() != SpotType.NONE
                    && (targetA.getSpot() == targetB.getSpot()
                    && targetB.getSpot() == targetC.getSpot())) {
                
                winnerBrute = targetA.getSpot();
                winnerSpotsBrute = Arrays.asList(targetA, targetB, targetC);
                
            }
            
        }
        
        /**
         * Third verification
         */
        
        if(winnerBrute == null) {
            
            Spot targetA = this.spots.get(keySpot(0, 2));
            Spot targetB = this.spots.get(keySpot(1, 1));
            Spot targetC = this.spots.get(keySpot(2, 0));
            
            if(targetA.getSpot() != SpotType.NONE
                    && (targetA.getSpot() == targetB.getSpot()
                    && targetB.getSpot() == targetC.getSpot())) {
                
                winnerBrute = targetA.getSpot();
                winnerSpotsBrute = Arrays.asList(targetA, targetB, targetC);
                
            }
            
        }
       
        if(winnerBrute != null) {
            
            /**
             * Setting winner
             */

            this.winner = winnerBrute;
            this.winnerSpots = winnerSpotsBrute;
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
     * Returns CPU spot choose.
     * 
     * @return <code>Spot</code>
     */
    public Spot chooseCPUSpot() {
     
        List<Spot> ls = avaliableSpots();
        if(!ls.isEmpty()) {
            
            Spot choose = null;
            SpotType preference = getCPUSpotType();
            
            /**
             * Easy difficulty
             */
            
            List<ChoosePrepare> chooseLs = new ArrayList<>();

            for(int ways = 0; ways < 2; ways++) {
                
                int separator = 0;
                ChoosePrepare choosePrepare = new ChoosePrepare();
                
                for(int rows = 0; rows < 3; rows++) {

                    for(int columns = 0; columns < 3; columns++) {
                        
                        if(separator == 2) {
                            chooseLs.add(choosePrepare);
                        }
                        
                        if(separator == 3) {
                            separator = 0;
                            choosePrepare = new ChoosePrepare();
                        }
                        
                        String keySpot;
                        
                        if(ways == 0) {
                            keySpot = keySpot(rows, columns);
                        } else {
                            keySpot = keySpot(columns, rows);
                        }
                        
                        choosePrepare.add(this.spots.get(keySpot));
                        
                        separator++;

                    }

                }
                
            }
            
            /**
            * Hard difficulty
            */
            
            if(getDifficulty().ordinal() >= 2) {
            
                for(int ways = 0; ways < 2; ways++) {

                    ChoosePrepare choosePrepare = new ChoosePrepare();

                    Spot targetA;
                    Spot targetB;
                    Spot targetC;

                    if(ways == 0) {
                        targetA = this.spots.get(keySpot(0, 0));
                        targetB = this.spots.get(keySpot(1, 1));
                        targetC = this.spots.get(keySpot(2, 2));
                    } else {
                        targetA = this.spots.get(keySpot(0, 2));
                        targetB = this.spots.get(keySpot(1, 1));
                        targetC = this.spots.get(keySpot(2, 0));
                    }

                    choosePrepare.add(targetA);
                    choosePrepare.add(targetB);
                    choosePrepare.add(targetC);
                    chooseLs.add(choosePrepare);

                }
                
            }
            
            Collections.sort (chooseLs, new Comparator<ChoosePrepare>() {
                public int compare(ChoosePrepare o1, ChoosePrepare o2) {
                    return o1.getScore() < o2.getScore() ? +1 
                            : (o1.getScore() > o2.getScore() ? -1 : 0);
                }
            });

            if(!ls.isEmpty()) {

                ChoosePrepare second = null;

                /**
                 * Medium difficulty
                 */
                
                if(getDifficulty().ordinal() >= 1) {
                
                    if(chooseLs.size() > 1) {

                        ChoosePrepare secondBrute = chooseLs.get(1);
                        if(secondBrute.getScore() > 1) {
                            second = secondBrute;
                        }

                    }
                    
                }

                ChoosePrepare first = chooseLs.get(0);
                if(first.getScore() > 1) {

                    if(second != null) {

                        int[] firstSeparated = first.getScoreSeparated();
                        int[] secondSeparated = second.getScoreSeparated();

                        if(preference == SpotType.X) {
                            if(secondSeparated[0] >= firstSeparated[0]) {
                                first = second;
                            }
                        }
                        if(preference == SpotType.O) {
                            if(secondSeparated[1] >= firstSeparated[1]) {
                                first = second;
                            }
                        }

                    }

                    Spot spot = first.choose();
                    if(spot != null) {
                        choose = spot;
                    }

                }

            }
            
            if(choose != null) {
                return choose;
            }
            
            Random random = new Random();
            return ls.get(random.nextInt(ls.size()));
        }
        
        return null;
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
