
package esi.atl.message;

import esi.atl.deTurck.users.User;
import esi.atl.table.AllTables;

/**
 * The <code> Message </code> represents a message with 
 * the id of the table and user who want to join it
 * @author G43353
 */
public class MessageJoinTable implements Message{
    
    private final User author;
    private final int idTable;
    
    /**
     * Constructor for messageJoinTable
     * @param author The author of the message
     * @param idTable The id of the table
     */
    public MessageJoinTable(User author, int idTable) {
        this.author = author;
        this.idTable = idTable;
    }

    /**
     * Return the administrator. The author of a message with all the connected
     * users is the administrator.
     *
     * @return the author
     */
    @Override
    public User getAuthor() {
        return author;
    }

    /**
     * Return the recipient of the message.
     *
     * @return the recipient of the message.
     */
    @Override
    public User getRecipient() {
        return User.EVERYBODY;
    }

    /**
     * Return the type of the message, in this case Type.JOINTABLE.
     *
     * @return the type of the message, in this case Type.JOINTABLE.
     */
    @Override
    public Type getType() {
        return Type.JOINTABLE;
    }

    /**
     * Return the content of the message : the id of table who need add the user on it.
     *
     * @return the content of the message : the id of table who need add the user on it.
     */
    @Override
    public Object getContent() {
        return idTable;
    }

}
 
