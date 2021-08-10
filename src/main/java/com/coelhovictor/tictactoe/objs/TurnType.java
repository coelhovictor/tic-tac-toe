package com.coelhovictor.tictactoe.objs;

/**
 *
 * @author VictorCoelho
 */
public enum TurnType {
    
    /**
     * PLAYER turn type.
     */
    PLAYER("PLAYER", 0),
    
    /**
     * CPU turn type.
     */
    CPU("CPU", 1);

    /**
     * Class constructor.
     */
    private TurnType() {}

    /**
     * Class constructor.
     * 
     * @param s
     * @param n 
     */
    private TurnType(final String s, final int n) {}

}
