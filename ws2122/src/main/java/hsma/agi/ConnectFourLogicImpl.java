package hsma.agi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public final class ConnectFourLogicImpl implements ConnectFourLogic {

    private int totalThrows;

    private static final int NUM_OF_COLUMNS = 7;
    private static final int NUM_OF_ROWS = 6;

    private Chip[][] board = new Chip[NUM_OF_COLUMNS][NUM_OF_ROWS];

    @Override
    public Results throwChip(Chip chip, int column) {

        if(++this.totalThrows > 6 || (column < 0 || column > 6)) {
            return Results.ERROR;
        }

        chip.incrementCounter();//may be deleted

        for(int row = 0; row < NUM_OF_COLUMNS; row++) {
            if(this.board[column][row] == null) {
                this.board[column][row] = chip;
                chip.setColumn(column);
                chip.setRow(row);
                break;
            }
        }

        if(isForHorizontalWin(chip)) {
            return Results.WIN;
        } else { 
            return Results.UNDECIDED;
        }
    }

    private boolean isForHorizontalWin(final Chip chip) {

        int chipsInARow = 1;
        Chip nextChip;

        for(int column = chip.getColumn()+1; column+1 < NUM_OF_COLUMNS; column++) {
            nextChip = this.board[column][chip.getRow()];
            if(nextChip == null || !(chip.getPlayer().equals(nextChip.getPlayer()))) {
                break;
            }
            chipsInARow++;
            if(chipsInARow == 4) {
                return true;
            }
        }

        for(int column = chip.getColumn()-1; column-1 >= 0; column--) {
            nextChip = this.board[column][chip.getRow()];
            if(nextChip == null || !(chip.getPlayer().equals(nextChip.getPlayer()))) {
                break;
            }
            chipsInARow++;
            if(chipsInARow == 4) {
                return true;
            }
        }

        return false;
    }
}
