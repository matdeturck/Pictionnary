package be.he2b.atl.view.graphic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * View of the client for get ina table or chat with other client
 * @author G43353
 */
public class ClientGraphic extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientGraphics.fxml"));
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
