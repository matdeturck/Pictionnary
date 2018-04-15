package be.he2b.atl.pictionnary.model;

import be.he2b.atl.client.AbstractClient;
import esi.atl.deTurck.users.Members;
import esi.atl.deTurck.users.User;
import esi.atl.message.Message;
import esi.atl.message.MessageProfile;
import esi.atl.message.MessageCreateTable;
import esi.atl.message.MessageJoinTable;
import esi.atl.message.MessageToRecipient;
import esi.atl.message.Type;
import esi.atl.table.AllTables;
import esi.atl.table.Table;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The <code> PitctionnaryClient </code> contains all the methods necessary to
 * set up a Instant messaging client.
 */
public class PitctionnaryClient extends AbstractClient {

    private final Members members;
    private final AllTables tables;
    private User mySelf;

    /**
     * Constructs the client. Opens the connection with the server. Sends the
     * user name inside a <code> MessageProfile </code> to the server. Builds an
     * empty list of users.
     *
     * @param host the server's host name.
     * @param port the port number.
     * @param name the name of the user.
     * @param password the password needed to connect.
     * @throws IOException if an I/O error occurs when opening.
     */
    public PitctionnaryClient(String host, int port, String name, String password) throws IOException {
        super(host, port);
        openConnection();

        updateName(name);
        members = new Members();
        tables = new AllTables();
    }

    @Override
    protected void handleMessageFromServer(Object msg) {
        Message message = (Message) msg;
        Type type = message.getType();
        switch (type) {
            case PROFILE:
                setMySelf(message.getAuthor());
                break;
            case MAIL_TO:
                showMessage(message);
                break;
            case MEMBERS:
                Members members = (Members) message.getContent();
                updateMembers(members);
                break;
            case CREATETABLE: {
                updateTables(tables);
                break;
            }
            case JOINTABLE: {
                updateTables(tables);
                break;
            }
            case ALLTABLES: {
                AllTables tables = (AllTables) message.getContent();
                updateTables(tables);
                break;
            }case STATUS :{
                setMySelf(message.getAuthor());
                break;
            }

            default:
                throw new IllegalArgumentException("Message type unknown " + type);
        }
    }

    /**
     * Quits the client and closes all aspects of the connection to the server.
     *
     * @throws IOException if an I/O error occurs when closing.
     */
    public void quit() throws IOException {
        closeConnection();
    }

    /**
     * Return all the connected users.
     *
     * @return all the connected users.
     */
    public Members getMembers() {
        return members;
    }

    /**
     * Return the user with the given id.
     *
     * @param id of the user.
     * @return the user with the given id.
     */
    public User getUsers(int id) {
        return members.getUser(id);
    }

    /**
     * Send the text message to the given user.
     *
     * @param recipient recipient of the message.
     * @param text message send.
     * @throws IOException if an I/O error occurs when closing.
     */
    public void sendMessage(User recipient, String text) throws IOException {
        if (recipient == null) {
            throw new IllegalArgumentException("Pas de destinataire selectionne");
        }
        MessageToRecipient message = new MessageToRecipient(Type.MAIL_TO, getMySelf(), recipient, text);
        sendToServer(message);
    }

    /**
     * Return the user.
     *
     * @return the user.
     */
    public User getMySelf() {
        return mySelf;
    }

    void setMySelf(User user) {
        this.mySelf = user;
    }

    void updateMembers(Members members) {
        this.members.clear();
        for (User member : members) {
            this.members.add(member);
        }
        notifyChange();
    }

    void updateTables(AllTables alltable) {
        this.tables.clear();
        for (Table table : alltable) {
            this.tables.add(table);
        }
        notifyChange();
    }

    void showMessage(Message message) {
        notifyChange(message);
    }

    private void updateName(String name) throws IOException {
        sendToServer(new MessageProfile(0, name));
    }

    private void notifyChange() {
        setChanged();
        notifyObservers();
    }

    private void notifyChange(Message message) {
        setChanged();
        notifyObservers(message);
    }

    /**
     * Return the numbers of connected users.
     *
     * @return the numbers of connected users.
     */
    public int getNbConnected() {
        return members.size();
    }

    /**
     * Return the numbers of connected users.
     *
     * @return the numbers of connected users.
     */
    public int getNbTables() {
        return tables.size();
    }

    public AllTables getTables() {
        return tables;
    }

    public void createTable(String nameTable) throws IOException {
        MessageCreateTable message = new MessageCreateTable(mySelf, nameTable);
        setChanged();
        notifyObservers(message);
        sendToServer(message);

    }

    public void joinTable(int tableID) throws IOException {
        MessageJoinTable message = new MessageJoinTable(mySelf, tableID);
        setChanged();
        notifyObservers(message);
        sendToServer(message);
    }
}
