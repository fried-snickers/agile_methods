package hsma.agi;

public final class ConnectFourLogicImpl implements ConnectFourLogic {

    private int totalThrows;

    @Override
    public Results throwChip(Chip chip, int column) {

        chip.incrementCounter();
        
        if(++this.totalThrows > 6 || (column < 0 || column > 6)) {
            return Results.ERROR;
        }

        if(chip.getCounter() == 4) {
            return Results.WIN;
        } else { 
            return Results.UNDECIDED;
        }
    }
}
