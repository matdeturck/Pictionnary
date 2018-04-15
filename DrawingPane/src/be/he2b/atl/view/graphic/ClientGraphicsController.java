/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Geekette Force
 */
public class ClientGraphicsController implements Initializable, Observer {

    private PitctionnaryClient model;
    private DateTimeFormatter formatter;

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
        PitctionnaryClient client = null;
        this.printUsage();
        try {
            String host = "localhost";
            int port = 12_345;
            String name = "Lola";
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
        this.model = client;
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.model.addObserver(this);
        updateUser();
    }

    @FXML
    void askCommand(ActionEvent event) throws IOException {
        boolean end = false;
        String command = messagePlayer.getText();
        String[] splitCommand = command.split(" ");
        if (command.equals("quit")) {
            try {
                model.quit();
                messages.setText("Déconnexion \n");
            } catch (IOException ex) {
                Logger.getLogger(ClientGraphicsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            end = true;
        } else if (splitCommand[0].equals("send")) {
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
        } else if (splitCommand[0].equals("create")) {
            try {
                model.createTable(splitCommand[1]);
                messages.appendText("Bienvenue dans la table \n");
            } catch (IOException ex) {
                Logger.getLogger(ClientGraphicsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (splitCommand[0].equals("join")) {
            try {
                model.joinTable(Integer.parseInt(splitCommand[1]));
                messages.appendText("Bienvenue dans la table jointe \n");
            } catch (IOException ex) {
                Logger.getLogger(ClientGraphicsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            messages.appendText("Désolé vous êtes déjà dans une table");
        }
    }

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

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {
            Message message = (Message) arg;
            if (message.getType().equals(Type.MAIL_TO)) {
                messages.setText(messages.getText() + "Vous avez reçu le message : "
                        + message.getContent()
                        + " \n de : " + message.getAuthor().getName() + "\n");
            }
        }
        updateUser();
        updateTables();
    }

    private void updateTables() {
        connectedTables.clear();
        StringBuilder builder = new StringBuilder();
        builder.append("\n---- ---- Liste Tables ---- ----\n");
        builder.append("Nombre de Tables connectes : ")
                .append(model.getNbTables()).append("\n");
        for (Table table : model.getTables()) {
            builder.append("ID : ").append("\t"); 
            builder.append(table.getId()).append("\t");
            builder.append("Joueur :").append("\t\t\n");
            for (int i = 0; i < table.getListplayer().size(); i++) {
                builder.append(table.getListplayer().get(i).getName()).append("\t\n");
            }
        }
        connectedTables.appendText(builder.toString());
    }
}
