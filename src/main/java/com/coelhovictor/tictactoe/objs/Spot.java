package com.coelhovictor.tictactoe.objs;

/**
 *
 * @author VictorCoelho
 */
public class Spot {
    
    /**
     * Current row.
     */
    private final int row;
    
    /**
     * Current column.
     */
    private final int column;
    
    /**
     * Current spot type.
     */
    private SpotType spot;
    
    /**
     * Class constructor.
     * 
     * @param row the current row
     * @param column the current column
     */
    public Spot(int row, int column) {
        this.row = row;
        this.column = column;
        this.spot = SpotType.NONE;
    }
    
    /**
     * Returns the current row.
     * 
     * @return <code>int</code> the current row
     */
    public int getRow() { return this.row; }
    
    /**
     * Returns the current column.
     * 
     * @return <code>int</code> the current column
     */
    public int getColumn() { return this.column; }
    
    /**
     * Returns the current spot type.
     * 
     * @return <code>SpotType</code> the current spot type
     */
    public SpotType getSpot() { return this.spot; }
    
    /**
     * Set the spot type.
     * 
     * @param spot the current spot type
     */
    public void setSpot(SpotType spot) { this.spot = spot; }
    
}
