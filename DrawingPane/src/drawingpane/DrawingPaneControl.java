package drawingpane;

import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Class for the drawingPane and all the components needed to use it 
 * @author G43353
 */
public class DrawingPaneControl extends Parent implements IDrawing {

    private DrawingPane board;
    private ColorPicker color;
    private Button clear;
    private Slider width;

    /**
     * Constructor for DrawingPaneControl
     */
    DrawingPaneControl() {
        VBox panelBoard = new VBox();
        HBox panel = new HBox();
        board = new DrawingPane();
        color = new ColorPicker();
        clear = new Button("Clear");
        width = new Slider();
        setWidthParam();
        color.setOnAction(e -> {
            board.setColor(color.getValue());
        });
        clear.setOnAction(e -> {
            board.clear();
        });
        panel.getChildren().addAll(color, clear,width);
        panelBoard.getChildren().addAll(panel, board);
        getChildren().add(panelBoard);
    }

    /**
     * Set all the param need for the slider
     */
    private void setWidthParam(){
        width.setMin(5);
        width.setMax(100);
        width.setValue(6);
        width.setShowTickLabels(true);
        width.setShowTickMarks(true);
        width.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                board.setWidthLine(width.getValue());
            }
        });
    }

    @Override
    public void initialize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clearPane() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setColor(Color color) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Color getColor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObjectProperty<Integer> thicknessProperty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setThickness(int thickness) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getThickness() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
