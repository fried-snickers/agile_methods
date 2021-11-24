package hsma.agi;

public class Chip {

    private Players player;
    private int counter;
    private int column;
    private int row;

    public Chip (final Players player) {
        this.player = player;
    }

    public Players getPlayer() {
        return this.player;
    }

    public void incrementCounter() {
        this.counter++;
    }

    public int getCounter() {
        return this.counter;
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
