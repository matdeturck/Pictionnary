
package be.he2b.atl.view.graphic;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main For all the game on the client
 * @author G43353
 */
public class MainView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ConnectionClientView.fxml"));
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
