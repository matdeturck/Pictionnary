package drawingpane;


import javafx.scene.Parent;
import javafx.scene.canvas.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


/**
 * Class for the board where the player can draw
 * @author G43353
 */
public class DrawingPane extends Parent {
    Canvas board;
    GraphicsContext infoPaint;
    
    /**
     * Constructor for drawingPane
     */
    DrawingPane(){
        board= new Canvas(800,500);
        infoPaint = board.getGraphicsContext2D();

        infoPaint.setLineWidth(6);
        
        board.setOnMousePressed(e->{
            infoPaint.beginPath();
            infoPaint.lineTo(e.getX(), e.getY());
            infoPaint.stroke();
        });
        
        board.setOnMouseDragged(e->{
            infoPaint.lineTo(e.getX(), e.getY());
            infoPaint.stroke();
        });
        
        HBox hbox= new HBox();
        hbox.getChildren().add(board);
        hbox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
        + "-fx-border-width: 2;" + "-fx-border-color: black;");
        getChildren().add(hbox);
    }
    
    /**
     * Set the color of the drawing line
     * @param newColor The color of the line made by the cursor
     */
    public void setColor(Color newColor){
        infoPaint.setStroke(newColor);
    }
    
    /**
     * Set the Width of the drawing line 
     * @param width The new width of the line made by the cursor
     */
    public void setWidthLine(double width){
        infoPaint.setLineWidth(width);
    }
    
    /**
     * Clear all the board 
     */
    public void clear(){
        infoPaint.clearRect(0,0,board.getWidth(),board.getHeight());
    }
}
