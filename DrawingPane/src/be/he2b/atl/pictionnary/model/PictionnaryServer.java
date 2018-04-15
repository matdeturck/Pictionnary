package be.he2b.atl.pictionnary.model;

import be.he2b.atl.server.AbstractServer;
import be.he2b.atl.server.ConnectionToClient;
import esi.atl.deTurck.users.Members;
import esi.atl.deTurck.users.StatusPlayer;
import esi.atl.deTurck.users.User;
import esi.atl.message.Message;
import esi.atl.message.MessageAllTables;
import esi.atl.message.MessageMembers;
import esi.atl.message.MessageProfile;
import esi.atl.message.MessageCreateTable;
import esi.atl.message.MessageStatus;
import esi.atl.message.Type;
import esi.atl.table.AllTables;
import esi.atl.table.Table;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The <code> PictionnaryServer </code> contains all the methods necessary to
 * set up a Instant messaging server.
 */
public class PictionnaryServer extends AbstractServer {

    private static final int PORT = 12_345;
    static final String ID_MAPINFO = "ID";

    private static InetAddress getLocalAddress() {
        try {
            Enumeration<NetworkInterface> b = NetworkInterface.getNetworkInterfaces();
            while (b.hasMoreElements()) {
                for (InterfaceAddress f : b.nextElement().getInterfaceAddresses()) {
                    if (f.getAddress().isSiteLocalAddress()) {
                        return f.getAddress();
                    }
                }
            }
        } catch (SocketException e) {
            Logger.getLogger(PictionnaryServer.class.getName()).log(Level.SEVERE, "NetworkInterface error", e);
        }
        return null;
    }

    private int clientId;

    private final Members members;

    private final AllTables tables;

    /**
     * Constructs the server. Build a thread to listen connection request.
     *
     * @throws IOException if an I/O error occurs when creating the server
     * socket.
     */
    public PictionnaryServer() throws IOException {
        super(PORT);
        members = new Members();
        tables = new AllTables();
        clientId = 0;
        this.listen();
    }

    public int nbTables() {
        return tables.size();
    }

    public AllTables getTables() {
        return tables;
    }

    /**
     * Return the list of connected users.
     *
     * @return the list of connected users.
     */
    public Members getMembers() {
        return members;
    }

    /**
     * Return the server IP address.
     *
     * @return the server IP address.
     */
    public String getIP() {
        if (getLocalAddress() == null) {
            return "Unknown";
        }
        return getLocalAddress().getHostAddress();
    }

    /**
     * Return the number of connected users.
     *
     * @return the number of connected users.
     */
    public int getNbConnected() {
        return getNumberOfClients();
    }

    /**
     * Quits the server and closes all aspects of the connection to clients.
     *
     * @throws IOException
     */
    public void quit() throws IOException {
        this.stopListening();
        this.close();
    }

    /**
     * Return the next client id.
     *
     * @return the next client id.
     */
    final synchronized int getNextId() {
        clientId++;
        return clientId;
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        Message message = (Message) msg;
        Type type = message.getType();
        switch (type) {
            case PROFILE:
                int memberId = (int) client.getInfo(ID_MAPINFO);
                User author = message.getAuthor();
                members.changeName(author.getName(), memberId);
                Message messageName = new MessageProfile(memberId, author.getName());
                sendToClient(messageName, memberId);
                sendToAllClients(new MessageMembers(members));
                break;
            case MAIL_TO:
                sendToClient(message, message.getRecipient());
                break;
            case MEMBERS:
                break;
            case CREATETABLE:
                int memberIdMsg = (int) client.getInfo(ID_MAPINFO);
                User authorMsg = message.getAuthor();
                if (authorMsg.getStatus() == StatusPlayer.ALONE) {
                    authorMsg.setStatus(StatusPlayer.DRAWER);
                    members.changeStatus(StatusPlayer.DRAWER,authorMsg.getId());
                    Table table = new Table(tables.lastIDTable() + 1, authorMsg, message.getContent().toString());
                    tables.add(table);
                }
                sendToAllClients(new MessageMembers(members));
                sendToClient(new MessageStatus(memberIdMsg,authorMsg.getName(),authorMsg.getStatus()), authorMsg);
                sendToAllClients(new MessageAllTables(tables));
                break;
            case JOINTABLE:
                int memberIdJoin = (int) client.getInfo(ID_MAPINFO);
                User authorJoin = message.getAuthor();
                int idTable = (int) message.getContent();
                if (authorJoin.getStatus() == StatusPlayer.ALONE && tables.getTable(idTable).isOpen()) {
                    authorJoin.setStatus(StatusPlayer.GUESSER);
                    members.changeStatus(StatusPlayer.GUESSER,authorJoin.getId());
                    tables.addPartenaire(idTable, authorJoin);
                }
                sendToClient(new MessageStatus(memberIdJoin,authorJoin.getName(),authorJoin.getStatus()), authorJoin);
                sendToAllClients(new MessageAllTables(tables));
                break;
            default:
                throw new IllegalArgumentException("Message type unknown " + type);
        }
        setChanged();
        notifyObservers(message);
    }

    @Override
    protected void clientConnected(ConnectionToClient client) {
        super.clientConnected(client);
        int memberId = members.add(getNextId(), client.getName(), client.getInetAddress());
        client.setInfo(ID_MAPINFO, memberId);
        sendToAllClients(new MessageMembers(members));
        setChanged();
        notifyObservers();
    }

    @Override
    protected synchronized void clientDisconnected(ConnectionToClient client) {
        super.clientDisconnected(client);
        try {
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(PictionnaryServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        members.remove((int) client.getInfo(ID_MAPINFO));
        sendToAllClients(new MessageMembers(members));
        setChanged();
        notifyObservers();
    }

    @Override
    protected synchronized void clientException(ConnectionToClient client, Throwable exception) {
        super.clientException(client, exception);
        try {
            if (client.isConnected()) {
                client.sendToClient(new IllegalArgumentException("Message illisible " + exception.getMessage()));
            }
        } catch (IOException ex) {
            Logger.getLogger(PictionnaryServer.class.getName()).log(Level.SEVERE, "Impossible d envoyer erreur au client", ex);
        }
    }

    void sendToClient(Message message, User recipient) {
        sendToClient(message, recipient.getId());
    }

    void sendToClient(Message message, int clientId) {
        List<Thread> clientConnections = super.getClientConnections();
        for (int i = 0; i < clientConnections.size(); i++) {
            ConnectionToClient clientConnection = (ConnectionToClient) clientConnections.get(i);
            int id = (Integer) clientConnection.getInfo(ID_MAPINFO);
            if (id == clientId) {
                try {
                    clientConnection.sendToClient(message);
                } catch (IOException ex) {
                    Logger.getLogger(PictionnaryServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
