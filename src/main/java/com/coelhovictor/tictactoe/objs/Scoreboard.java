package com.coelhovictor.tictactoe.objs;

/**
 *
 * @author VictorCoelho
 */
public class Scoreboard {
    
    /**
     * Player score.
     */
    private int playerScore;
    
    /**
     * CPU score.
     */
    private int cpuScore;
    
    /**
     * Class constructor.
     */
    public Scoreboard() {
        this.playerScore = 0;
        this.cpuScore = 0;
    }
    
    /**
     * Returns the player score.
     * 
     * @return <code>int</code> player score 
     */
    public int getPlayerScore() { return this.playerScore; }
    
    /**
     * Returns the CPU score.
     * 
     * @return <code>int</code> CPU score 
     */
    public int getCPUScore() { return this.cpuScore; }
    
    /**
     * Add player score.
     */
    public void addPlayer() { this.playerScore++; }
    
    /**
     * Add CPU score.
     */
    public void addCPU() { this.cpuScore++; }
    
}
