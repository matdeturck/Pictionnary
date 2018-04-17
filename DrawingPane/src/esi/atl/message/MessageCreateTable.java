package esi.atl.message;

import esi.atl.deTurck.users.User;

/**
 *
 * @author Geekette Force
 */
public class MessageCreateTable implements Message {
    
    private final User author;
    private final String text;
    /**
     * Constructs message with the list of all connected users.
     *
     * @param table
     */
    public MessageCreateTable(User author,String text) {
        this.author = author;
        this.text = text;
    }

    /**
     * Return the administrator. The author of a message with all the connected
     * users is the administrator.
     *
     * @return
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
     * Return the type of the message, in this case Type.MEMBERS.
     *
     * @return the type of the message, in this case Type.MEMBERS.
     */
    @Override
    public Type getType() {
        return Type.CREATETABLE;
    }

    /**
     * Return the content of the message : the list of all connected users.
     *
     * @return the content of the message : the list of all connected users.
     */
    @Override
    public Object getContent() {
        return text;
    }

}
