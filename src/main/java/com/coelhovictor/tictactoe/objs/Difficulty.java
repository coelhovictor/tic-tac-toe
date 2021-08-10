package com.coelhovictor.tictactoe.objs;

/**
 *
 * @author VictorCoelho
 */
public enum Difficulty {
    
    /**
     * Easy difficulty
     */
    EASY("EASY", 0),
    
    /**
     * Medium difficulty
     */
    MEDIUM("MEDIUM", 1),
    
    /**
     * Hard difficulty
     */
    HARD("HARD", 2);

    /**
     * Class constructor.
     */
    private Difficulty() {}

    /**
     * Class constructor.
     * 
     * @param s
     * @param n
     */
    private Difficulty(final String s, final int n) {}

}
