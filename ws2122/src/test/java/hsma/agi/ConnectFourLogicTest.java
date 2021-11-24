package hsma.agi;

import org.junit.jupiter.api.Test;
import hsma.agi.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConnectFourLogicTest {

 /*========================general tests==============================*/  

    @Test
    public void testThrowChipLeftOnce_ReturnsUndecided() {
        ConnectFourLogic game = new ConnectFourLogicImpl();
        Chip chip = new Chip(Players.PLAYER1);
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
    }

    @Test
    public void testThrowChipIntoIllegalColumn_ReturnsError() {
        ConnectFourLogic game = new ConnectFourLogicImpl();
        Chip chip = new Chip(Players.PLAYER1);
        assertEquals(Results.ERROR, game.throwChip(chip, -1));
        assertEquals(Results.ERROR, game.throwChip(chip, 7));
    }

    //Test for draw: board full but no winner 
    //pass null into throwChip

 /*========================vertical tests==============================*/  
   
    @Test
    public void testFourLowestChipsOfSamePlayerInOneColumn_ReturnsWIN() {
        ConnectFourLogic game = new ConnectFourLogicImpl();
        Chip chip = new Chip(Players.PLAYER1);
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
        assertEquals(Results.WIN, game.throwChip(chip, 0));
    }

    @Test
    public void testFourChipsOfSamePlayerInSeveralColumns_ReturnsUndecided() {
        ConnectFourLogic game = new ConnectFourLogicImpl();
        Chip chip = new Chip(Players.PLAYER1);
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 1));
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 1));
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
    }

    @Test
    public void testFourUpmostChipsOfSamePlayerInOneColumn_ReturnsWin() {
        ConnectFourLogic game = new ConnectFourLogicImpl();
        Chip chip = new Chip(Players.PLAYER1);
        Chip chip2 = new Chip(Players.PLAYER2);
        assertEquals(Results.UNDECIDED, game.throwChip(chip2, 0));
        assertEquals(Results.UNDECIDED, game.throwChip(chip2, 0));
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
        assertEquals(Results.WIN, game.throwChip(chip, 0));
    }

    @Test
    public void testThrowChipsWithTwoAlternatingPlayersInOneColumn_ReturnsUndecided() {
        ConnectFourLogic game = new ConnectFourLogicImpl();
        Chip chip = new Chip(Players.PLAYER1);
        Chip chip2 = new Chip(Players.PLAYER2);
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
        assertEquals(Results.UNDECIDED, game.throwChip(chip2, 0));
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
        assertEquals(Results.UNDECIDED, game.throwChip(chip2, 0));
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
        assertEquals(Results.UNDECIDED, game.throwChip(chip2, 0));   
    }

    @Test
    public void testColumnOverflow_ReturnsError() {
        ConnectFourLogic game = new ConnectFourLogicImpl();
        Chip chip = new Chip(Players.PLAYER1);
        Chip chip2 = new Chip(Players.PLAYER2);
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
        assertEquals(Results.UNDECIDED, game.throwChip(chip2, 0));
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
        assertEquals(Results.UNDECIDED, game.throwChip(chip2, 0));
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
        assertEquals(Results.UNDECIDED, game.throwChip(chip2, 0));
        assertEquals(Results.ERROR, game.throwChip(chip2, 0));
    }

    //missed cases: differentiate between different columns
    

/*========================horizontal tests==============================*/

    @Test
    public void testFourLeftmostColumnsOfSamePlayer_ReturnsWin() {
        ConnectFourLogic game = new ConnectFourLogicImpl();
        Chip chip = new Chip(Players.PLAYER1);
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 1));
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 2));
        assertEquals(Results.WIN, game.throwChip(chip, 3));
    }

    @Test
    public void testFourRightmostColumnsOfSamePlayer_ReturnsWin() {
        ConnectFourLogic game = new ConnectFourLogicImpl();
        Chip chip = new Chip(Players.PLAYER1);
        Chip chip2 = new Chip(Players.PLAYER2);
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 1));
        assertEquals(Results.UNDECIDED, game.throwChip(chip2, 2));
        assertEquals(Results.UNDECIDED, game.throwChip(chip2, 3));
        assertEquals(Results.UNDECIDED, game.throwChip(chip2, 4));
        assertEquals(Results.WIN, game.throwChip(chip2, 5));
    }

    @Test
    public void testHorizontallyAlternatingPlayersThrow_ReturnsUndecided() {
        ConnectFourLogic game = new ConnectFourLogicImpl();
        Chip chip = new Chip(Players.PLAYER1);
        Chip chip2 = new Chip(Players.PLAYER2);
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
        assertEquals(Results.UNDECIDED, game.throwChip(chip2, 1));
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 2));
        assertEquals(Results.UNDECIDED, game.throwChip(chip2, 3));
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 4));
        assertEquals(Results.UNDECIDED, game.throwChip(chip2, 5));
    }

    //next case
    @Test
    public void testHorizontallyIrregularlyAlternatingPlayersThrow_ReturnsUndecided() {
        ConnectFourLogic game = new ConnectFourLogicImpl();
        Chip chip = new Chip(Players.PLAYER1);
        Chip chip2 = new Chip(Players.PLAYER2);
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
        assertEquals(Results.UNDECIDED, game.throwChip(chip2, 1));
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 2));
        assertEquals(Results.UNDECIDED, game.throwChip(chip2, 3));
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 4));
        assertEquals(Results.UNDECIDED, game.throwChip(chip, 5));
    }

    //missed cases: differentiate between different rows

/*========================diagonal tests==============================*/

    
}
