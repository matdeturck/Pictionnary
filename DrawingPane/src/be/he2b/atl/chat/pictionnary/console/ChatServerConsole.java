package be.he2b.atl.chat.pictionnary.console;

import be.he2b.atl.pictionnary.model.PictionnaryServer;
import esi.atl.deTurck.users.User;
import esi.atl.message.Message;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The <code> ChatServerConsole </code> contains all the methods necessary view
 * in console mode the instant messaging client side.
 */
public class ChatServerConsole implements Observer {

    /**
     * Entry points to the instant messaging server side.
     *
     * @param args no arguments needed.
     */
    public static void main(String[] args) {
        try {
            PictionnaryServer model = new PictionnaryServer();
            ChatServerConsole console = new ChatServerConsole(model);
            model.addObserver(console);
            System.out.println("Server started");
            System.out.println("");
        } catch (IOException ex) {
            Logger.getLogger(ChatServerConsole.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
    }

    private final PictionnaryServer model;

    /**
     * Constructs the console view. Subscribes to the instant messaging server.
     *
     * @param model instant messaging server.
     */
    public ChatServerConsole(PictionnaryServer model) {
        this.model = model;
    }

    @Override
    public void update(Observable o, Object arg) {
        updateUser();
        if (arg != null) {
            Message message = (Message) arg;
            updateMessage(message);
        }

    }

    /**
     * Update the list of the users who are connected on the server
     */
    private void updateUser() {
        System.out.println("");
        StringBuilder builder = new StringBuilder();
        builder.append("\n---- ---- Liste Users ---- ----\n");
        builder.append("Nombre d'utilisateurs connectes : ")
                .append(model.getNbConnected()).append("\n");
        builder.append("ID").append("\t");
        builder.append("IP").append("\t\t");
        builder.append("NAME").append("\n");
        for (User member : model.getMembers()) {
            builder.append(member.getId()).append("\t");
            builder.append(member.getAddress()).append("\t");
            builder.append(member.getName()).append("\n");
        }
        System.out.println(builder);
        System.out.println("");
    }
    
    /**
     * Get and show the message to the user
     * @param message The message who have been received
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
        System.out.println(builder);
    }
}
