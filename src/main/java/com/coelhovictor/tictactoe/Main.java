package com.coelhovictor.tictactoe;


import com.coelhovictor.tictactoe.guis.Home;
import com.coelhovictor.tictactoe.objs.Difficulty;
import com.coelhovictor.tictactoe.objs.Session;
import com.coelhovictor.tictactoe.util.Util;


/**
 *
 * @author VictorCoelho
 */
public class Main {
   
    /**
     * Current game session.
     */
    private static Session currentSession;
    
    public static void main(String[] args) {
    
        /**
         * Util inicialize
         */
        
         Util util = new Util();
         util.setupFonts();
        
        /**
         * Open home screen
         */
        
        new Home(true, Difficulty.EASY);
        
    }
    
    /**
     * Set the current game <code>Session</code>.
     *
     * @param session the new session
     */
    public static void setSession(Session session) { currentSession = session; }
    
    /**
     * Returns the current game <code>Session</code>.
     *
     * @return <code>Session</code> the current session
     * @see #setSession
     */
    public static Session getSession() { return currentSession; }

}
