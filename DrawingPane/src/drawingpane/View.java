package drawingpane;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A class for testing all the drawing components
 * /!\ This isn't the final main on the class, it's just a testing class
 * @author G43353
 */
public class View extends Application {

    private DrawingInfo dInfos = null;

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene s = new Scene(root, 900, 600);

        DrawingPaneControl canvas = new DrawingPaneControl();
        Button save = new Button ("save");
        save.setOnAction(e->{
            dInfos = canvas.getDrawingInfos();
            System.out.println(dInfos.getListPoints().size()+"set");
        });
        
        Button load = new Button ("load");
        load.setOnAction(e->{
            System.out.println(dInfos.getListPoints().size()+"load");
            canvas.setDrawingInfos(dInfos);
        });
        
        
        VBox affich = new VBox();
        affich.getChildren().addAll(canvas,save, load);
        root.getChildren().addAll(affich);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(s);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
