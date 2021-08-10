package com.coelhovictor.tictactoe.objs;

/**
 *
 * @author VictorCoelho
 */
public class Player {
    
    /**
     * Current nick.
     */
    private final String nick;
    
    /**
     * Created time.
     */
    private final long timing;
    
    /**
     * Class constructor.
     * 
     * @param nick the target nick
     */
    public Player(String nick) {
        this.nick = nick;
        this.timing = System.currentTimeMillis();
    }
    
    /**
     * Returns the player nick.
     * 
     * @return <code>String</code> current nick
     */
    public String getNick() { return this.nick; }
    
    /**
     * Returns the created time.
     * 
     * @return <code>long</code> created time
     */
    public long getTiming() { return this.timing; }
    
}
