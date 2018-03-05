package drawingpane;

/**
 * Class for coordinates 
 *
 * @author g43353
 */
public class Coordinates {
    private final int row;
    private final int column;

    /**
     * Constructor
     *
     * @param row Place on the row
     * @param column Place on the column
     */
    public Coordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Display coordinates
     *
     * @return The row and the column
     */
    @Override
    public String toString() {
        return "Coordinates{" + "row=" + row + ", column=" + column + '}';
    }

    /**
     * Get the value of row
     *
     * @return the value of row
     */
    public int getRow() {
        return row;
    }

    /**
     * Get the value of column
     *
     * @return the value of column
     */
    public int getColumn() {
        return column;
    }
    
    /**
     * Get the hashCode of a Coordinates
     * @return the hashCode
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.row;
        hash = 37 * hash + this.column;
        return hash;
    }
    
    /**
     * Test if a object is the same than another
     * @param obj the object to test
     * @return True if the two objects are the same
     */
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
        final Coordinates other = (Coordinates) obj;
        if (this.row != other.row) {
            return false;
        }
        return this.column == other.column;
    }

}
