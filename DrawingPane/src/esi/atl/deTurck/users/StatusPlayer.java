package esi.atl.deTurck.users;

/**
 * Status of the client 
 * @author G43353
 */
public enum StatusPlayer {
    /**
     * The client isn't on a table
     */
    ALONE,
    /**
     * The client create a table and is the drawer of the game
     */
    DRAWER,
    /**
     * The client join the game and is the guesser of the game
     */
    GUESSER;
}
