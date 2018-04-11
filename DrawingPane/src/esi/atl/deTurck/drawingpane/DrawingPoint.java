package esi.atl.deTurck.drawingpane;

import javafx.scene.paint.Color;

/**
 * Class for a point on a Canevas
 *
 * @author G43353
 */
public class DrawingPoint {

    private final Color color;
    private final int thickness;
    private final double row;
    private final double column;
    private boolean isFinished;

    /**
     * Constructor for the drawing point
     * @param colored The color of the point 
     * @param thick The thickness of the point 
     * @param newRow The row on the panel where the point is.
     * @param newColumn The column on the panel where the point is.
     */
    DrawingPoint(Color colored, int thick, double newRow, double newColumn) {
        isFinished = false;
        color = colored;
        thickness = thick;
        row = newRow;
        column = newColumn;
    }

    /**
     * Set the point on finish 
     * @param isFinished true if the point is the last on his drawing line
     */
    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    /**
     * Get the color of the point
     * @return color , The color of the point 
     */
    public Color getColor() {
        return color;
    }

    /**
     * Get the thickness of the point 
     * @return thickness, The thickness of the point 
     */
    public int getThickness() {
        return thickness;
    }

    /**
     * Get the row of the point
     * @return row, The row where the point is.
     */
    public double getRow() {
        return row;
    }

    /**
     * Get the column of the point 
     * @return column, The column where the point is.
     */
    public double getColumn() {
        return column;
    }

    /**
     * Get if the point is the last on his drawing row
     * @return isFinished, True if the point is the last.
     */
    public boolean isIsFinished() {
        return isFinished;
    }

}
