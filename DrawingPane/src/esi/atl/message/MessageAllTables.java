
package esi.atl.message;

import esi.atl.deTurck.users.User;
import esi.atl.table.AllTables;

/**
 * The <code> Message </code> represents a message with the list of all
 * connected tables.
 * @author G43353
 */
public class MessageAllTables implements Message{
    
    private final AllTables tables;
    /**
     * Constructs message with the list of all connected tables.
     *
     * @param tables The list of all tables
     */
    public MessageAllTables(AllTables tables) {
        this.tables = tables;
    }

    /**
     * Return the administrator. The author of a message with all the connected
     * users is the administrator.
     *
     * @return User the author
     */
    @Override
    public User getAuthor() {
        return User.ADMIN;
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
     * Return the type of the message, in this case Type.ALLTABLES.
     *
     * @return the type of the message, in this case Type.ALLTABLES.
     */
    @Override
    public Type getType() {
        return Type.ALLTABLES;
    }

    /**
     * Return the content of the message : the list of all connected tables.
     *
     * @return the content of the message : the list of all connected tables.
     */
    @Override
    public Object getContent() {
        return tables;
    }
}
