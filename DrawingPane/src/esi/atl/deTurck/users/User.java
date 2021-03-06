package esi.atl.deTurck.users;

import java.io.Serializable;
import java.net.InetAddress;

/**
 * The <code> User </code> represents a connected user.
 */
public class User implements Serializable {

    /**
     * Represents the server administrator.
     */
    public static final User ADMIN = new User(0, "ADMIN");

    /**
     * Represents a virtual user usefull for broadcast.
     */
    public static final User EVERYBODY = new User(0, "EVERYBODY");

    private final int id;
    private String name;
    private InetAddress address;
    private StatusPlayer status;
    /**
     * Constructs a connected user.
     *
     * @param id userID of the connected user.
     * @param name name of the connected user.
     * @param address IP address of the connected user.
     */
    public User(int id, String name, InetAddress address) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.status = StatusPlayer.ALONE;
    }

    /**
     * Constructs a connected user.
     *
     * @param id userID of the connected user.
     * @param name name of the connected user.
     */
    public User(int id, String name) {
        this(id, name,(InetAddress) null);
    }

    /**
     * Construct a connected user
     * @param id userID of the connected user.
     * @param name name of the connected user.
     * @param stat state of the player 
     */
    public User(int id, String name,StatusPlayer stat) {
        this(id,name,(InetAddress)null);
        this.setStatus(stat);
    }

    /**
     * Return the user name.
     *
     * @return the user name.
     */
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return this.id == other.id;
    }

    /**
     * Check if the id of the table is the id given in the parameter
     * @param id The id who need to be tested
     * @return True if the id is equals to the id of the table
     */
    boolean is(int id) {
        return this.id == id;
    }

    /**
     * Return the userID.
     *
     * @return the userID.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the name of the table
     * @param name The new name of the table
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * Get the status of the client 
     * @return StatusPlayer The current client status
     */
    public StatusPlayer getStatus() {
        return status;
    }

    /**
     * Set the status of the client 
     * @param status The new status of the client 
     */
    public void setStatus(StatusPlayer status) {
        this.status = status;
    }

    /**
     * Return the user IP address .
     *
     * @return the user IP address .
     */
    public String getAddress() {
        return address.getHostAddress();
    }

    @Override
    public String toString() {
        return name + "(" + id + ")";
    }

}
