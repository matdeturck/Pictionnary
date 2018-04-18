package esi.atl.table;

import esi.atl.deTurck.users.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * List of all tables who are on the server 
 * @author G4353
 */
public class AllTables implements Iterable<Table>, Serializable {

    private final List<Table> tables;

    @Override
    public Iterator<Table> iterator() {
        return tables.iterator();
    }

    /**
     * Constructs an empty list of tables.
     *
     */
    public AllTables() {
        tables = new ArrayList<>();
    }

    /**
     * Creates a new instance of a table and add this table to the list.
     *
     * @param id tableID of the new table.
     * @param user the first player who had created the table
     * @return the tableID of the new user.
     */
    public int add(int id, User user, String name) {
        Table table = new Table(id, user, name);
        add(table);
        return table.getId();
    }

    /**
     * Add a user to the list of connected tables.
     *
     * @param table The new table who have to be connected.
     */
    public void add(Table table) {
        tables.add(table);
    }

    /**
     * Add a partenaire to a table
     * @param idTable The id of the table who need to add the user
     * @param user The user who need to be add to the table
     */
    public void addPartenaire(int idTable, User user) {
        for(Table table:tables){
            if(table.getId()==idTable){
                if(table.isOpen()){
                    table.addListplayer(user);
                }
                
            }
        }
    }
    
    /**
     * Remove the user with the current userID from the list of connected
     * tables.
     *
     * @param id userID of the user disconnected.
     */
    public void remove(int id) {
        Iterator<Table> it = tables.iterator();
        boolean find = false;
        Table current = null;
        while (it.hasNext() && !find) {
            current = it.next();
            find = current.isId(id);
        }
        if (find) {
            tables.remove(current);
        }
    }

    /**
     * Return the number of tables connected.
     *
     * @return the number of tables connected.
     */
    public int size() {
        return tables.size();
    }

    /**
     * Clear the list of connected tables.
     */
    public void clear() {
        tables.clear();
    }

    /**
     * Return the table of the given id.
     *
     * @param id tableId of the table to return.
     * @return the table of the given id.
     */
    public Table getTable(int id) {
        Iterator<Table> it = tables.iterator();
        boolean find = false;
        Table current = null;
        while (it.hasNext() && !find) {
            current = it.next();
            find = current.isId(id);
        }
        return find ? current : null;
    }
    
    /**
     * Get the last id used for a table
     * @return int The last id used to create a table.
     */
    public int lastIDTable(){
        int i =0;
        for(Table table : tables){
            if(table.getId()>i){
                i=table.getId();
            }
        }
        return i;
    }
    
    public Table getIdTableWithPlayer(User user){
        for(Table table:tables){
            if(table.isPlayerOnTable(user)){
                return table;
            }
        }
        return null;
    }
    public boolean isPlayerOnATable(User user){
        for(Table table:tables){
            if(table.isPlayerOnTable(user)){
                return true;
            }
        }
        return false;
    }
}
