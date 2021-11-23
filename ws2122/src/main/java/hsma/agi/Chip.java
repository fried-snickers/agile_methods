package hsma.agi;

public class Chip {

    private Players player;
    private int counter;

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
}
