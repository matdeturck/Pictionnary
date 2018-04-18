package be.he2b.atl.view.graphic;

import be.he2b.atl.chat.pictionnary.console.ChatClientConsole;
import be.he2b.atl.pictionnary.model.PitctionnaryClient;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author G43353
 */
public class ConnectionClientViewController implements Initializable {

    private PitctionnaryClient client;
    private ClientGraphicsController tableGame;

    @FXML
    private TextField namePlayer;

    @FXML
    private Button connection;
    
    @FXML
    private Label error;

    @FXML
    void GoToServer(ActionEvent event) {
        if (namePlayer.getText().equals("")) {
            error.setText("Entrez un nom de joueur!");
        } else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/be/he2b/atl/view/graphic/ClientGraphics.fxml"));
                Parent root1 = fxmlLoader.load();
                tableGame = (ClientGraphicsController) fxmlLoader.getController();
                fxmlLoader.getController();

                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ((Node) (event.getSource())).getScene().getWindow().hide();
            setClient();
            tableGame.setClient(client);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableGame = null;
    }

    public void setClient() {
        client = null;
        try {
            String host = "localhost";
            int port = 12_345;
            String name = namePlayer.getText();
            String password = "";
            client = new PitctionnaryClient(host, port, name, password);
        } catch (IOException ex) {
            Logger.getLogger(ChatClientConsole.class.getName()).log(Level.SEVERE, "Main error", ex);
            try {
                client.quit();
            } catch (NullPointerException | IOException clientEx) {
                Logger.getLogger(ChatClientConsole.class.getName()).log(Level.SEVERE, "Quit client error", clientEx);
            }
            System.exit(0);
        }
    }
}
