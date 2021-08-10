package com.coelhovictor.tictactoe.objs;

/**
 *
 * @author VictorCoelho
 */
public class Scoreleader {
    
    /**
     * Scoreleader nick.
     */
    private String nick;
    
    /**
     * Scoreleader num of wins.
     */
    private int wins;
    
    /**
     * Scoreleader num of defeats.
     */
    private int defeats;
    
    /**
     * Class constructor.
     */
    public Scoreleader() {}
    
    /**
     * Class constructor.
     * 
     * @param nick scoreleader nick
     */
    public Scoreleader(String nick) {
        this.nick = nick;
        this.wins = 0;
        this.defeats = 0;
    }
    
    /**
     * Returns the scoreleader nick.
     * 
     * @return <code>String</code> scoreleader nick
     */
    public String getPlayerNick() { return this.nick; }
    
    /**
     * Returns the scoreleader wins.
     * 
     * @return <code>int</code> scoreleader wins
     */
    public int getWins() { return this.wins; }
    
    /**
     * Returns the scoreleader defeats.
     * 
     * @return <code>int</code> scoreleader defeats
     */
    public int getDefeats() { return this.defeats; }
    
    /**
     * Increase the scoreleader num of wins.
     */
    public void addWin() { this.wins++; }
    
    /**
     * Increase the scoreleader num of defeats.
     */
    public void addDefeat() { this.defeats++; }
    
}
