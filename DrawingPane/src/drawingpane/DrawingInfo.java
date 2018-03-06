package drawingpane;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author g43353
 */
public class DrawingInfo {
    private ArrayList<DrawingPoint> listPoints;
    
    DrawingInfo(){
        listPoints = new ArrayList();
    }
    
    public void addPoint(Color color , int thickness, Coordinates coord){
        listPoints.add(new DrawingPoint(color,thickness,coord));
    }
    
    public void clear (){
        listPoints.clear();
    }

    public ArrayList<DrawingPoint> getListPoints() {
        return listPoints;
    }
}
