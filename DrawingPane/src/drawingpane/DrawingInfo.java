package drawingpane;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * All the point saved by the DrawingPane
 * @author g43353
 */
public class DrawingInfo {
    private ArrayList<DrawingPoint> listPoints;
    
    /**
     * COnstructor for the Drawing Info class
     */
    DrawingInfo(){
        listPoints = new ArrayList();
    }
    
    /**
     * Add a new point on the list
     * @param color The color of the point 
     * @param thickness The thickness of the point 
     * @param row The row on the panel where the point is.
     * @param column The column on the panel where the point is.
     */
    public void addPoint(Color color , int thickness, double row, double column){
        listPoints.add(new DrawingPoint(color, thickness,row,column));
    }
    
    /**
     * Clear the list from all points
     */
    public void clear (){
        listPoints.clear();
    }
    
    /**
     * Set the last point written with the mouse 
     */
    public void release(){
        listPoints.get(listPoints.size()-1).setIsFinished(true);
        System.out.println(listPoints.lastIndexOf(this));
        
    }

    /**
     * Get the list 
     * @return listPoints the list with all the written points
     */
    public ArrayList<DrawingPoint> getListPoints() {
        return listPoints;
    }
}
