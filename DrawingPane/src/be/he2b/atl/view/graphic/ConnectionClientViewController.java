
package be.he2b.atl.view.graphic;

import be.he2b.atl.chat.pictionnary.console.ChatClientConsole;
import be.he2b.atl.pictionnary.model.PitctionnaryClient;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author G43353
 */
public class ConnectionClientViewController implements Initializable {

    @FXML
    private TextField namePlayer;

    @FXML
    private Button connection;

    @FXML
    void GoToServer(ActionEvent event) {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientGraphics.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
        } catch(Exception e) {
           e.printStackTrace();
        }
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    /**
     * Get the name of the Client 
     * @return TextField The name of the client 
     */
    public TextField getNamePlayer() {
        return namePlayer;
    }


}
