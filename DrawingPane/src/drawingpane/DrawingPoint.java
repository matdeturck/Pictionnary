
package drawingpane;

import javafx.scene.paint.Color;

/**
 *
 * @author G43353
 */
public class DrawingPoint {
    private final Color color;
    private final int thickness;
    private final double row;
    private final double column;
    private boolean isFinished;

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }
    
    DrawingPoint(Color colored, int thick, double newRow, double newColumn ){
        isFinished = false;
        color= colored;
        thickness = thick;
        row = newRow;
        column = newColumn;
    }

    public Color getColor() {
        return color;
    }

    public int getThickness() {
        return thickness;
    }

    public double getRow() {
        return row;
    }

    public double getColumn() {
        return column;
    }


    public boolean isIsFinished() {
        return isFinished;
    }
    
    
}
