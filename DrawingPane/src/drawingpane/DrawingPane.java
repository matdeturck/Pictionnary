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

    private Canvas board;
    private GraphicsContext infoPaint;
    private ObjectProperty<Color> color;
    private ObjectProperty<Integer> thickness;
    private DrawingInfo infoDrawing;

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
        isDrawing(true);
        

        HBox hbox = new HBox();
        hbox.getChildren().add(board);
        hbox.setStyle("-fx-padding: 5;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-color: black;");
        getChildren().add(hbox);
    }

    public void isDrawing(boolean draw){
        if (draw){
            board.setOnMousePressed(e -> {
            infoPaint.beginPath();
            infoPaint.lineTo(e.getX(), e.getY());
            infoPaint.stroke();

         });

        board.setOnMouseDragged(e -> {
            infoPaint.lineTo(e.getX(), e.getY());
            infoPaint.stroke();
            });
        }else {
            board.setOnMousePressed(e -> {});
            board.setOnMouseDragged(e -> {});
        }
    }
    @Override
    public void clearPane() {
        infoPaint.clearRect(0, 0, board.getWidth(), board.getHeight());
    }

    @Override
    public DrawingInfo getDrawingInfos() {
        return infoDrawing;
    }

    @Override
    public void setDrawingInfos(DrawingInfo dInfos) {
        infoDrawing= dInfos;
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
