/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atl.message;

import esi.atl.deTurck.users.User;
import esi.atl.table.AllTables;

/**
 *
 * @author Geekette Force
 */
public class MessageJoinTable implements Message{
    
    private final User author;
    private final int idTable;

    public MessageJoinTable(User author, int idTable) {
        this.author = author;
        this.idTable = idTable;
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
        return Type.JOINTABLE;
    }

    /**
     * Return the content of the message : the list of all connected users.
     *
     * @return the content of the message : the list of all connected users.
     */
    @Override
    public Object getContent() {
        return idTable;
    }

}
 
