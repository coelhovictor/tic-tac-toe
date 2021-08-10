package com.coelhovictor.tictactoe.objs;

/**
 *
 * @author VictorCoelho
 */
public enum SpotType {
    
    /**
     * Default type.
     */
    NONE("NONE", 0),
    
    /**
     * X spot type.
     */
    X("X", 1),
    
    /**
     * O spot type.
     */
    O("O", 2);

    /**
     * Class constructor.
     */
    private SpotType() {}

    /**
     * Class constructor.
     * 
     * @param s
     * @param n
     */
    private SpotType(final String s, final int n) {}

}
