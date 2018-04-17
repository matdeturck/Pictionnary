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
    MAIL_TO,
    /**
     * Message with the status of a specific user.
     */
    STATUS,
    /**
     * Message with the list of all connected Tables.
     */
    ALLTABLES,
    /**
     * Message send for creating a table.
     */
    CREATETABLE,
    /**
     * Message send for join table.
     */
    JOINTABLE,
    /**
     * Message send for share the draw with the other user
     */
    DRAW;
}

