package be.he2b.atl.view.graphic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Create the window for the server
 * @author G43353
 */
public class ServerGraphic extends Application {
    
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ServeurGraphics.fxml"));
        Parent root = loader.load();
                      
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
        launch(args);
    }

}
