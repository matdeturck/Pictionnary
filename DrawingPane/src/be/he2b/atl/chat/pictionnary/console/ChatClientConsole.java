package be.he2b.atl.chat.pictionnary.console;

import be.he2b.atl.pictionnary.model.PitctionnaryClient;
import esi.atl.deTurck.users.User;
import esi.atl.message.Message;
import esi.atl.message.Type;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The <code> ChatClientConsole </code> contains all the methods necessary view
 * in console mode the instant messaging client side.
 */
public class ChatClientConsole implements Observer {

    /**
     * Entry points to the instant messaging client side.
     *
     * @param args no arguments needed.
     */
    public static void main(String[] args) {
        PitctionnaryClient client = null;
        try {
            String host = "localhost";
            int port = 12_345;
            String name = "25555";
            String password = "";
            client = new PitctionnaryClient(host, port, name, password);
            ChatClientConsole console = new ChatClientConsole(client);
            console.printUsage();
            console.askCommand();
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

    private final PitctionnaryClient model;
    private final DateTimeFormatter formatter;

    /**
     * Constructs the console view. Subscribes to the instant messaging client.
     *
     * @param client instant messaging client.
     */
    public ChatClientConsole(PitctionnaryClient client) {
        this.model = client;
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.model.addObserver(this);
    }

    /**
     * Asks to the user a command in console. If the command is list, the
     * console print the list of all connected users. If the command is send,
     * the client sends a message to the given connected user. If the command is
     * quit, the client disconnect from the server.
     */
    private void askCommand() throws IOException {
        Scanner clavier = new Scanner(System.in);
        boolean end = false;
        while (!end) {

            String command = clavier.nextLine();
            String[] splitCommand = command.split(" ");
            if (command.equals("list")) {
                System.out.println("list");
                int number = model.getNbConnected();
                System.out.println("Nombre de client :" + number);
                for (User user : model.getMembers()) {
                    System.out.print(user.getId() + " id  ");
                    System.out.println(user.getName() + "  name ");
                }
            } else if (command.equals("quit")) {
                model.quit();

                end = true;
            } else if (splitCommand[0].equals("send")) {
                User dest = model.getUsers(Integer.parseInt(splitCommand[1]));
                String message = " ";
                for (int i=2;i<splitCommand.length;i++ ){
                    message+=splitCommand[i];
                    message+=" ";
                }
                model.sendMessage(dest, message);
            } else {
                System.out.println("Erreur dans votre requête");
            }
            printUsage();
        }

    }

    private void printUsage() {
        System.out.println("");
        System.out.println("Usage : ");
        System.out.println("\tAfficher la liste de utilisateurs connectés\t:\tlist");
        System.out.println("\tEnvoyer un message à un utilisateur connecté\t:\tsend <userID> <message>");
        System.out.println("\tSe deconnecter\t:\tquit");
        System.out.println("");
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {
            Message message = (Message) arg;
            if (message.getType().equals(Type.MAIL_TO)) {
                  System.out.println("");
                System.out.println("Vous avez reçu le message : " + message.getContent() + " de : " + message.getAuthor().getName());      
            }
        }
    }

}
