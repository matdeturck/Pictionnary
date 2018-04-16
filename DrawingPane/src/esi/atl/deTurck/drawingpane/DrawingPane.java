package esi.atl.deTurck.drawingpane;

import java.util.ArrayList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.scene.Parent;
import javafx.scene.canvas.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * Class for the board where the player can draw
 *
 * @author G43353
 */
public class DrawingPane extends Parent implements IDrawing {

    private Canvas board;
    private GraphicsContext infoPaint;
    private ObjectProperty<Color> color;
    private ObjectProperty<Integer> thickness;
    private DrawingInfo infoDrawing;

    /**
     * Constructor for drawingPane
     */
    public DrawingPane() {
        initialize();
    }

    @Override
    public void initialize() {
        board = new Canvas(800, 500);
        infoPaint = board.getGraphicsContext2D();
        infoDrawing = new DrawingInfo();
        infoPaint.setLineWidth(6);
        isDrawing(true);
        color = new ObjectPropertyBase<Color>(Color.BLACK) {
            @Override
            public Object getBean() {
                return this;
            }

            @Override
            public String getName() {
                return "Color";
            }
        };
        thickness=new ObjectPropertyBase<Integer>(6) {
            @Override
            public Object getBean() {
               return this;
            }

            @Override
            public String getName() {
                return "Thickness";
            }
        };
        
        HBox hbox = new HBox();
        hbox.getChildren().add(board);
        hbox.setStyle("-fx-padding: 5;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-color: black;");
        getChildren().add(hbox);
    }

    /**
     * Set the infoPaint if the player can draw on it 
     * @param draw True if the player is a drawer and false if he isn't
     */
    public void isDrawing(boolean draw) {
        if (draw) {
            board.setOnMousePressed(e -> {
                infoPaint.beginPath();
                infoPaint.lineTo(e.getX(), e.getY());
                infoPaint.stroke();
                infoDrawing.addPoint((Color) infoPaint.getStroke(),
                        (int) infoPaint.getLineWidth(),e.getX(), e.getY());
            });
            board.setOnMouseDragged(e -> {
                infoPaint.lineTo(e.getX(), e.getY());
                infoPaint.stroke();
                infoDrawing.addPoint((Color) infoPaint.getStroke(),
                        (int) infoPaint.getLineWidth(),e.getX(), e.getY());
            });
            board.setOnMouseReleased(e -> {
                infoDrawing.release();
            });
        } else {
            board.setOnMousePressed(e -> {});
            board.setOnMouseDragReleased(e -> {});
            board.setOnMouseDragged(e -> {});
        }
    }

    @Override
    public void clearPane() {
        infoPaint.clearRect(0, 0, board.getWidth(), board.getHeight());
        infoDrawing = new DrawingInfo();
    }

    @Override
    public DrawingInfo getDrawingInfos() {
        return infoDrawing;
    }

    @Override
    public void setDrawingInfos(DrawingInfo dInfos) {
        infoDrawing = dInfos; 
        updateDrawing();
    }

    @Override
    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    @Override
    public Color getColor() {
        return (Color) infoPaint.getStroke();
    }

    @Override
    public ObjectProperty<Integer> thicknessProperty() {
        return thickness;
    }

    @Override
    public void setThickness(int newThickness) {
        infoPaint.setLineWidth(newThickness);
        thickness.set(newThickness);
    }

    @Override
    public int getThickness() {
        return (int) infoPaint.getLineWidth();
    }

    @Override
    public void setColor(Color newColor) {
        infoPaint.setStroke(newColor);
        color.set(newColor);
    }

    /**
     * Charge the image on the board from the infoDrawing list
     */
    private void updateDrawing() {
        ArrayList<DrawingPoint> list = infoDrawing.getListPoints();
        infoPaint.beginPath();
        for (DrawingPoint list1 : list) {
            setColor(list1.getColor());
            infoPaint.lineTo(list1.getRow(), list1.getColumn());
            if(list1.isIsFinished()){
                infoPaint.stroke();
                infoPaint.beginPath();
            }
                
        }
    }
}
