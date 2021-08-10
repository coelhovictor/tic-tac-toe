package com.coelhovictor.tictactoe.objs;

/**
 *
 * @author VictorCoelho
 */
public class Session {
    
    /**
     * Current player.
     */
    private final Player player;
    
    /**
     * Current scoreboard.
     */
    private final Scoreboard score;
    
    /**
     * Created time.
     */
    private final long timing;
    
    /**
     * Class constructor.
     * 
     * @param player the current player 
     */
    public Session(Player player) {
        this.player = player;
        this.score = new Scoreboard();
        this.timing = System.currentTimeMillis();
    }
    
    /**
     * Returns the current player.
     * 
     * @return <code>Player</code> the current player
     */
    public Player getPlayer() { return this.player; }
    
    /**
     * Returns the current scoreboard.
     * 
     * @return <code>Scoreboard</code> the current scoreboard
     */
    public Scoreboard getScoreboard() { return this.score; }
    
}
