
package esi.atl.message;

import esi.atl.deTurck.drawingpane.DrawingInfo;
import esi.atl.deTurck.users.User;

/**
 *
 * @author 43353
 */
public class MessageDrawing implements Message{
    private final Type type;
    private final User author;
    private final User recipient;
    private final DrawingInfo draw;

    /**
     * Constructs a general text message between users.
     *
     * @param type the type of the message.
     * @param author the author of the message
     * @param recipient the recipient of the message.
     * @param text the text of the message.
     */
    public MessageDrawing(Type type, User author, User recipient, DrawingInfo draw) {
        this.type = Type.DRAW;
        this.author = author;
        this.recipient = recipient;
        this.draw = draw;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public User getAuthor() {
        return author;
    }

    @Override
    public User getRecipient() {
        return recipient;
    }

    /**
     * Return the drawingInfo.
     *
     * @return The drawingInfo.
     */
    @Override
    public Object getContent() {
        return draw;
    }
}
