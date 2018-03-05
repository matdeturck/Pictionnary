package drawingpane;

import javafx.beans.property.ObjectProperty;
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

    Canvas board;
    GraphicsContext infoPaint;

    /**
     * Constructor for drawingPane
     */
    DrawingPane() {
        initialize();
    }

    @Override
    public void initialize() {
        board = new Canvas(800, 500);
        infoPaint = board.getGraphicsContext2D();

        infoPaint.setLineWidth(6);

        board.setOnMousePressed(e -> {
            infoPaint.beginPath();
            infoPaint.lineTo(e.getX(), e.getY());
            infoPaint.stroke();
        });

        board.setOnMouseDragged(e -> {
            infoPaint.lineTo(e.getX(), e.getY());
            infoPaint.stroke();
        });

        HBox hbox = new HBox();
        hbox.getChildren().add(board);
        hbox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-color: black;");
        getChildren().add(hbox);
    }

    @Override
    public void clearPane() {
        infoPaint.clearRect(0, 0, board.getWidth(), board.getHeight());
    }

    @Override
    public DrawingInfos getDrawingInfos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDrawingInfos(DrawingInfos dInfos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObjectProperty<Color> colorProperty() {
         throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Color getColor() {
        return (Color) infoPaint.getStroke();
    }

    @Override
    public ObjectProperty<Integer> thicknessProperty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setThickness(int thickness) {
        infoPaint.setLineWidth(thickness);
    }

    @Override
    public int getThickness() {
        return (int) infoPaint.getLineWidth();
    }

    @Override
    public void setColor(Color color) {
        infoPaint.setStroke(color);
    }
}
