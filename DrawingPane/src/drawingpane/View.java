package drawingpane;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A class for testing all the drawing components
 * /!\ This isn't the final main on the class, it's just a testing class
 * @author G43353
 */
public class View extends Application {

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene s = new Scene(root, 900, 600);

        DrawingPaneControl canvas = new DrawingPaneControl();
        
        root.getChildren().addAll(canvas);
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
