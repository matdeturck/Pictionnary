package be.he2b.atl.view.graphic;

import be.he2b.atl.chat.pictionnary.console.ChatClientConsole;
import be.he2b.atl.pictionnary.model.PitctionnaryClient;
import esi.atl.deTurck.users.User;
import esi.atl.message.Message;
import esi.atl.message.Type;
import esi.atl.table.Table;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author G43353
 */
public class ClientGraphicsController implements Initializable, Observer {

    private PitctionnaryClient model;
    private DateTimeFormatter formatter;
    private PictionnaryClientDrawerController tableGame;

    @FXML
    private TextArea connectedTables;

    @FXML
    private TextArea connectedPlayer;

    @FXML
    private TextArea messages;

    @FXML
    private TextField messagePlayer;

    @FXML
    private Button send;

    @FXML
    private TextArea info;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connectedTables.setEditable(false);
        connectedPlayer.setEditable(false);
        messages.setEditable(false);
        tableGame = null;
    }

    /**
     * Initialise the client on the model
     *
     * @param client the client who have to be in the model
     */
    public void setClient(PitctionnaryClient client) {
        this.model = client;
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.model.addObserver(this);
        updateUser();
        updateTables();
        printUsage();
    }

    /**
     * Get the command of a client and see if the command is correct and use it.
     *
     * @param event Action of the player for sending the command
     * @throws IOException If the message failed to reach the server
     */
    @FXML
    void askCommand(ActionEvent event) throws IOException {
        String command = messagePlayer.getText();
        String[] splitCommand = command.split(" ");
        if (command.equals("quit")) {
            quit(splitCommand);
        } else if (splitCommand[0].equals("send")) {
            sendMessage(splitCommand);
        } else if (splitCommand[0].equals("create")) {
            try {
                model.createTable(splitCommand[1]);
            } catch (IOException ex) {
                Logger.getLogger(ClientGraphicsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (splitCommand[0].equals("join")) {
            try {
                model.joinTable(Integer.parseInt(splitCommand[1]));
            } catch (IOException ex) {
                Logger.getLogger(ClientGraphicsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            messages.appendText("Désolé votre commande est incorrecte");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {
            Message message = (Message) arg;
            if (message.getType().equals(Type.MAIL_TO)) {
                messages.setText(messages.getText() + "Vous avez reçu le message : "
                        + message.getContent()
                        + " \n de : " + message.getAuthor().getName() + "\n");
            } else if (message.getType().equals(Type.CREATETABLE)) {
                messages.appendText("Bienvenue dans la table");
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/be/he2b/atl/view/graphic/1.fxml"));
                    Parent root1 = fxmlLoader.load();
                    tableGame = (PictionnaryClientDrawerController) fxmlLoader.getController();
                    fxmlLoader.getController();

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (message.getType().equals(Type.JOINTABLE)) {
                messages.appendText("Merci d'avoir rejoint la table");
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/be/he2b/atl/view/graphic/1.fxml"));
                    Parent root1 = fxmlLoader.load();
                    tableGame = (PictionnaryClientDrawerController) fxmlLoader.getController();
                    fxmlLoader.getController();

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        updateUser();
        updateTables();
    }

    /**
     * Method for quit the server
     *
     * @param splitCommand The command of the user
     */
    public void quit(String[] splitCommand) {
        try {
            model.quit();
            messages.setText("Déconnexion \n");
        } catch (IOException ex) {
            Logger.getLogger(ClientGraphicsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }

    /**
     * Method for sending message
     *
     * @param splitCommand The command of the user
     */
    public void sendMessage(String[] splitCommand) {
        try {
            User dest = model.getUsers(Integer.parseInt(splitCommand[1]));
            String message = " ";
            for (int i = 2; i < splitCommand.length; i++) {
                message += splitCommand[i];
                message += " ";
            }
            model.sendMessage(dest, message);
        } catch (IOException ex) {
            Logger.getLogger(ClientGraphicsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Update the connected users on the screen
     */
    private void updateUser() {
        connectedPlayer.clear();
        StringBuilder builder = new StringBuilder();
        builder.append("\n---- ---- Liste Users ---- ----\n");
        builder.append("Nombre d'utilisateurs connectes : ")
                .append(model.getNbConnected()).append("\n");
        builder.append("ID").append("\t");
        builder.append("IP").append("\t\t");
        builder.append("NAME").append("\t");
        builder.append("STATUS").append("\n");
        for (User member : model.getMembers()) {
            builder.append(member.getId()).append("\t");
            builder.append(member.getAddress()).append("\t");
            builder.append(member.getName()).append("\t");
            builder.append(member.getStatus()).append("\n");
        }
        connectedPlayer.appendText(builder.toString());
    }

    /**
     * Update the connected table on the screen
     */
    private void updateTables() {
        connectedTables.clear();
        StringBuilder builder = new StringBuilder();
        builder.append("\n---- ---- Liste Tables ---- ----\n");
        builder.append("Nombre de Tables connectes : ")
                .append(model.getNbTables()).append("\n");
        for (Table table : model.getTables()) {
            builder.append("ID : ").append("\t");
            builder.append(table.getId()).append("\t\n");
            builder.append("Joueur :").append("\t\t\n");
            for (int i = 0; i < table.getListplayer().size(); i++) {
                builder.append(table.getListplayer().get(i).getName()).append("\t, \t");
            }
        }
        connectedTables.appendText(builder.toString());
    }

    /**
     * Print all the command possible for te client
     */
    public void printUsage() {
        StringBuilder builder = new StringBuilder();
        builder.append("Usage : \n");
        builder.append("\tEnvoyer un message à un utilisateur connecté\t:"
                + "\tsend <userID> <message> \n");
        builder.append("\tCréer une table \t:\tcreate <tableName> \n");
        builder.append("\tJoindre une table \t:\tjoin <tableID> \n");
        builder.append("\tSe deconnecter\t:\tquit  \n");
        info.appendText(builder.toString());
        info.setEditable(false);
    }

}
