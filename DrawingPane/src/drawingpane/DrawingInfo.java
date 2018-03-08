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
    
    public void addPoint(Color color , int thickness, double row, double column){
        listPoints.add(new DrawingPoint(color, thickness,row,column));
    }
    
    public void clear (){
        listPoints.clear();
    }
    public void release(){
        listPoints.get(listPoints.lastIndexOf(this)).setIsFinished(true);
    }

    public ArrayList<DrawingPoint> getListPoints() {
        return listPoints;
    }
}
