package esi.atl.deTurck.drawingpane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Class for the drawingPane and all the components needed to use it
 *
 * @author G43353
 */
public class DrawingPaneControl extends Region {

    private DrawingPane board;

    /**
     * Constructor for DrawingPaneControl
     */
    public DrawingPaneControl() {
        VBox panelBoard = new VBox();
        HBox panel = new HBox();
        board = new DrawingPane();
        ColorPicker color = new ColorPicker();
        Button clear = new Button("Clear");
        Slider width = new Slider();

        width = setWidthParam(width);
        color.setOnAction(e -> {
            board.setColor(color.getValue());
        });
        clear.setOnAction(e -> {
            board.clearPane();
        });
        panel.getChildren().addAll(color, clear, width);
        panelBoard.getChildren().addAll(panel, board);
        getChildren().add(panelBoard);
    }

  

    /**
     * Set all the parameter needed for the slider
     */
    private Slider setWidthParam(Slider width) {
        width.setMin(5);
        width.setMax(100);
        width.setValue(6);
        width.setShowTickLabels(true);
        width.setShowTickMarks(true);
        width.valueProperty().addListener((ObservableValue<? extends Number> observable,
                Number oldValue, Number newValue) -> {
            board.setThickness((int) width.getValue());
        });
        return width;
    }

    /**
     * Set the info of the draw on the drawng info
     * @param dInfos  All the point who have been draw
     */
    public void setDrawingInfos(DrawingInfo dInfos) {
        board.setDrawingInfos(dInfos);
    }

    /**
     * Get the drawing info for print the draw
     * @return 
     */
    public DrawingInfo getDrawingInfos() {
        return board.getDrawingInfos(); 
    }

    public DrawingPane getBoard() {
        return board;
    }

}
