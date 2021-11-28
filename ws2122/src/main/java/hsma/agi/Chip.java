package hsma.agi;

/**
 * This class represents game chips
 * @author Praveen Yogananthan, Tom Rößler
 */
public class Chip {

    private Players player;
    private int column;
    private int row;

    public Chip (final Players player) {
        this.player = player;
    }

    public Players getPlayer() {
        return this.player;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return this.column;
    }

    public int getRow() {
        return this.row;
    }
}
