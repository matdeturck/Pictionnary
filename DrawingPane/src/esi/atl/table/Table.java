package esi.atl.table;

import esi.atl.deTurck.users.User;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Geekette Force
 */
public class Table implements Serializable{
    private final int id;
    private boolean open;
    private List<User> listplayer;
    private final String wordToGuess;
    private List<String> listWordProposed;

    public Table(int id) {
        this.id = ;
        
    }
}
