
package esi.atl.message;

import esi.atl.deTurck.users.StatusPlayer;
import esi.atl.deTurck.users.User;

/**
 * The <code> Message </code> represents a message with the status of a user 
 * @author Geekette Force
 */
public class MessageStatus implements Message{
   
    private final User author;

    /**
     * Constructs message with the profile of a specific user. The author of the
     * message give his profile to be send.
     *
     * @param id userID of the author.
     * @param stat the new status of the user
     */
    public MessageStatus(int id, String name,StatusPlayer stat) {
        author = new User(id, name,stat);
    }

    /**
     * Return the author of the message. The author send his profile.
     *
     * @return the author of the message.
     */
    @Override
    public User getAuthor() {
        return author;
    }

    /**
     * Return the recipient of this message. A profile message is always send to
     * the administrator.
     *
     * @return the recipient of this message.
     */
    @Override
    public User getRecipient() {
        return User.ADMIN;
    }

    /**
     * Return the type of the message, in this case Type.STATUS.
     *
     * @return the type of the message, in this case Type.STATUS.
     */
    @Override
    public Type getType() {
        return Type.STATUS;
    }

    /**
     * Return the content of the message : the author profile.
     *
     * @return the content of the message : the author profile.
     */
    @Override
    public Object getContent() {
        return author;
    }

}
