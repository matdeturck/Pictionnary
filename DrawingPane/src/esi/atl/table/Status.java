package esi.atl.table;

/**
 * All The status for the tables
 * @author G43353
 */
public enum Status {
    /**
     * The guesser have find the word 
     */
    WIN,
    /**
     * A player have leave the table
     */
    LOOSE,
    /**
     * The table have only one user on his list
     */
    SEARCHINGPARTNAIR,
    /**
     * The table have 2 users on it and the game is launch.
     */
    INPROGRESS;
}
