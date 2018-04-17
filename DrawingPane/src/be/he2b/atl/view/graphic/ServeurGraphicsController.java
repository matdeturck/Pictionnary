package be.he2b.atl.view.graphic;

import be.he2b.atl.chat.pictionnary.console.ChatServerConsole;
import be.he2b.atl.pictionnary.model.PictionnaryServer;
import esi.atl.deTurck.users.User;
import esi.atl.message.Message;
import esi.atl.table.Table;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author G43353
 */
public class ServeurGraphicsController implements Initializable, Observer {

    private PictionnaryServer model;

    @FXML
    private TextArea msgConnected;

    @FXML
    private TextArea msgField;

    @FXML
    private TextArea tables;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            model = new PictionnaryServer();
            model.addObserver(this);
            System.out.println("Server started");
            System.out.println("");
        } catch (IOException ex) {
            Logger.getLogger(ChatServerConsole.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
        msgConnected.setEditable(false);
        msgField.setEditable(false);
    }

    @Override
    public void update(Observable o, Object arg) {
        updateUser();
        updateTables();
        if (arg != null) {
            Message message = (Message) arg;
            updateMessage(message);
        }
    }

    /**
     * Update the connected users on the screen
     */
    private void updateUser() {
        msgConnected.clear();
        StringBuilder builder = new StringBuilder();
        builder.append("\n---- ---- Liste Users ---- ----\n");
        builder.append("Nombre d'utilisateurs connectes : ")
                .append(model.getNbConnected()).append("\n");
        builder.append("ID").append("\t");
        builder.append("IP").append("\t\t");
        builder.append("STAT").append("\t\t");
        builder.append("NAME").append("\n");
        for (User member : model.getMembers()) {
            builder.append(member.getId()).append("\t");
            builder.append(member.getAddress()).append("\t");
            builder.append(member.getStatus()).append("\t");
            builder.append(member.getName()).append("\n");
        }
        msgConnected.appendText(builder.toString());
    }

    /**
     * Update the message's client on the server
     * @param message 
     */
    private void updateMessage(Message message) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n---- ---- Message recu ---- ----\n");
        builder.append(LocalDateTime.now()).append(" \n");
        builder.append("Type : ").append(message.getType()).append("\n");
        builder.append("De : ").append(message.getAuthor()).append("\t");
        builder.append("Pour : ").append(message.getRecipient()).append("\n");
        builder.append("Contenu\t").append(message.getContent());
        builder.append("\n");

        msgField.appendText(builder.toString());
    }
    /**
     * Update the connected table on the screen
     */
    private void updateTables() {
        tables.clear();
        StringBuilder builder = new StringBuilder();
        builder.append("\n---- ---- Liste Tables ---- ----\n");
        builder.append("Nombre de Tables connectes : ")
                .append(model.getTables().size()).append("\n");
        builder.append("ID").append("\t");
        for (Table table : model.getTables()) {
            builder.append(table.getId()).append("\t\n");
            builder.append("Joueur").append("\t\t\n");
            for (int i = 0; i < table.getListplayer().size(); i++) {
                builder.append(table.getListplayer().get(i).getName()).append("\t\n");
            }
        }
        tables.appendText(builder.toString());
    }
}
