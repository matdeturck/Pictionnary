package esi.atl.message;

/**
 * The <code> Type </code> represents the type of a message send between a user
 * and the server.
 */
public enum Type {

    /**
     * Message with the profile of a specific user.
     */
    PROFILE,
    /**
     * Message with the list of all connected users.
     */
    MEMBERS,
    /**
     * General message send between two connected users.
     */
    MAIL_TO;
}

