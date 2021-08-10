package com.coelhovictor.tictactoe.objs;

/**
 *
 * @author VictorCoelho
 */
public class GameAction {
    
    /**
     * Current player.
     */
    private final Player player;
    
    /**
     * Target row.
     */
    private final int targetRow;
    
    /**
     * Target column.
     */
    private final int targetColumn;
    
    /**
     * Turn type.
     */
    private final TurnType turnType;
    
    /** 
     * Class contructor.
     * 
     * @param player the current player
     * @param turnType the turn type
     * @param row the target row
     * @param column the target column
     */
    public GameAction(Player player, TurnType turnType, int row, int column) {
        this.player = player;
        this.targetRow = row;
        this.targetColumn = column;
        this.turnType = turnType;
    }
    
    /** 
     * Returns the row.
     * 
     * @return <code>int</code> the row
     */
    public int getRow() { return this.targetRow; }
    
    /** 
     * Returns the column.
     * 
     * @return <code>int</code> the column
     */
    public int getColumn() { return this.targetColumn; }
    
    /** 
     * Returns the current player.
     * 
     * @return <code>Player</code> the current player
     */
    public Player getPlayer() { return this.player; }
    
    /** 
     * Returns the turn type.
     * 
     * @return <code>TurnType</code> the turn type
     */
    public TurnType getTurnType() { return this.turnType; }
    
}
