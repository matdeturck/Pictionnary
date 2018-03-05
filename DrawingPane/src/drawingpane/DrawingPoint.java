
package drawingpane;

import javafx.scene.paint.Color;

/**
 *
 * @author G43353
 */
public class DrawingPoint {
    private final Color color;
    private final int thickness;
    private final Coordinates coord;
    
    DrawingPoint(Color colored, int thick, Coordinates newCoord ){
        color= colored;
        thickness = thick;
        coord = newCoord;
    }

    public Color getColor() {
        return color;
    }

    public int getThickness() {
        return thickness;
    }

    public Coordinates getCoord() {
        return coord;
    }
    
    
}
