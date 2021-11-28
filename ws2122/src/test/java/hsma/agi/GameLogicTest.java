package hsma.agi;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests all aspects of ConnectFourLogic and TicTacToeLogic using the black box testing paradigm.
 * @author Praveen Yogananthan, Tom Rößler
 */
class GameLogicTest {

	/*========================Connect Four Tests=================================*/  

	/*========================general tests==============================*/  

	@Test
	public void testThrowChipLeftOnce_ReturnsUndecided() {
		ConnectFourLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
	}

	@Test
	public void testThrowChipIntoIllegalColumn_ReturnsError() {
		ConnectFourLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		assertEquals(Results.ERROR, game.throwChip(chip, -1));
		assertEquals(Results.ERROR, game.throwChip(chip, 7));
	}

	@Test
	public void testThrowChipWithNoChip_ReturnsError() {
		ConnectFourLogic game = new GameLogic();
		assertEquals(Results.ERROR, game.throwChip(null, 0));
	}

	@Test
	public void testThrowChipWithFullBoardOnConnectFour_ReturnsDraw() {
		ConnectFourLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		Chip chip2 = new Chip(Players.PLAYER2);
		for(int column = 0; column < 7; column++) {
			if(column % 2 != 0) {
				assertEquals(Results.UNDECIDED, game.throwChip(chip, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip2, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip2, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip, column));
			}else {
				assertEquals(Results.UNDECIDED, game.throwChip(chip2, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip2, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip2, column));
				if(column == 6) {
					assertEquals(Results.DRAW, game.throwChip(chip2, column));			
				}else {
					assertEquals(Results.UNDECIDED, game.throwChip(chip2, column));					
				}
			}
		}
	}

	/*========================vertical tests==============================*/  

	@Test
	public void testFourLowestChipsOfSamePlayerInFirstColumn_ReturnsWin() {
		ConnectFourLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
		assertEquals(Results.WIN, game.throwChip(chip, 0));
	}

	@Test
	public void testFourLowestChipsOfSamePlayerInLastColumn_ReturnsWin() {
		ConnectFourLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 6));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 6));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 6));
		assertEquals(Results.WIN, game.throwChip(chip, 6));
	}

	@Test
	public void testFourChipsOfSamePlayerInSeveralColumns_ReturnsUndecided() {
		ConnectFourLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 1));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 1));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
	}

	@Test
	public void testFourLeftUpmostChipsOfSamePlayerInOneColumn_ReturnsWin() {
		ConnectFourLogic game = new GameLogic();
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
	public void testFourRightUpmostChipsOfSamePlayerInOneColumn_ReturnsWin() {
		ConnectFourLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		Chip chip2 = new Chip(Players.PLAYER2);
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 6));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 6));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 6));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 6));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 6));
		assertEquals(Results.WIN, game.throwChip(chip, 6));
	}

	@Test
	public void testThrowChipsWithTwoAlternatingPlayersInOneColumn_ReturnsUndecided() {
		ConnectFourLogic game = new GameLogic();
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
		ConnectFourLogic game = new GameLogic();
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

	/*========================horizontal tests==============================*/

	@Test
	public void testFourLeftmostColumnsOfSamePlayer_ReturnsWin() {
		ConnectFourLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 1));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 2));
		assertEquals(Results.WIN, game.throwChip(chip, 3));
	}

	@Test
	public void testFourRightmostColumnsOfSamePlayer_ReturnsWin() {
		ConnectFourLogic game = new GameLogic();
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
	public void testFourLeftUpmostColumnsOfSamePlayer_ReturnsWin() {
		ConnectFourLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		Chip chip2 = new Chip(Players.PLAYER2);
		for(int column = 0; column < 4; column++) {
			if(column == 0 || column == 1) {
				assertEquals(Results.UNDECIDED, game.throwChip(chip, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip2, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip2, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip2, column));
			} else {
				assertEquals(Results.UNDECIDED, game.throwChip(chip2, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip2, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip2, column));
				if(column == 3) {
					assertEquals(Results.WIN, game.throwChip(chip2, column));
				} else {
					assertEquals(Results.UNDECIDED, game.throwChip(chip2, column));
				}
			}
		}
	}

	@Test
	public void testFourRightUpmostColumnsOfSamePlayer_ReturnsWin() {
		ConnectFourLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		Chip chip2 = new Chip(Players.PLAYER2);
		for(int column = 3; column < 7; column++) {
			if(column == 3 || column == 4) {
				assertEquals(Results.UNDECIDED, game.throwChip(chip, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip2, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip2, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip2, column));
			} else {
				assertEquals(Results.UNDECIDED, game.throwChip(chip2, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip2, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip, column));
				assertEquals(Results.UNDECIDED, game.throwChip(chip2, column));
				if(column == 6) {
					assertEquals(Results.WIN, game.throwChip(chip2, column));
				} else {
					assertEquals(Results.UNDECIDED, game.throwChip(chip2, column));
				}
			}
		}
	}

	@Test
	public void testHorizontallyAlternatingPlayersThrow_ReturnsUndecided() {
		ConnectFourLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		Chip chip2 = new Chip(Players.PLAYER2);
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 1));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 2));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 3));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 4));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 5));
	}

	@Test
	public void testHorizontallyIrregularlyAlternatingPlayersThrow_ReturnsUndecided() {
		ConnectFourLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		Chip chip2 = new Chip(Players.PLAYER2);
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 1));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 2));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 3));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 4));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 5));
	}

	/*========================diagonal tests==============================*/

	@Test
	public void testDiagonallyFromBottomLeftToTopRightInBottomLeft_ReturnsWin() {
		ConnectFourLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		Chip chip2 = new Chip(Players.PLAYER2);
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 1));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 1));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 2));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 2));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 2));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 3));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 3));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 3));
		assertEquals(Results.WIN, game.throwChip(chip, 3));
	}

	@Test
	public void testDiagonallyFromTopLeftToBottomRightBottomLeft_ReturnsWin() {
		ConnectFourLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		Chip chip2 = new Chip(Players.PLAYER2);
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 0));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 0));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 0));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 1));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 1));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 1));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 2));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 2));
		assertEquals(Results.WIN, game.throwChip(chip, 3));
	}

	@Test
	public void testDiagonallyTopLeftToBottomRightInTopLeft_ReturnsWin() {
		ConnectFourLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		Chip chip2 = new Chip(Players.PLAYER2);
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 0));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 0));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 0));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));

		assertEquals(Results.UNDECIDED, game.throwChip(chip, 1));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 1));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 1));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 1));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 1));

		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 2));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 2));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 2));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 2));

		assertEquals(Results.UNDECIDED, game.throwChip(chip, 3));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 3));
		assertEquals(Results.WIN, game.throwChip(chip, 3));
	}

	@Test
	public void testDiagonallyBottomLeftToTopRightInTopRight_ReturnsWin() {
		ConnectFourLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		Chip chip2 = new Chip(Players.PLAYER2);
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 3));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 3));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 3));

		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 4));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 4));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 4));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 4));

		assertEquals(Results.UNDECIDED, game.throwChip(chip, 5));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 5));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 5));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 5));

		assertEquals(Results.UNDECIDED, game.throwChip(chip, 6));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 6));		
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 6));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 6));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 6));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 6));

		assertEquals(Results.WIN, game.throwChip(chip, 5));	
	}

	@Test
	public void testDiagonallyFromTopLeftToBottomRightInBottomRight_ReturnsWin() {
		ConnectFourLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		Chip chip2 = new Chip(Players.PLAYER2);
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 3));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 3));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 3));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 3));

		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 4));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 4));

		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 5));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 5));

		assertEquals(Results.UNDECIDED, game.throwChip(chip, 6));
		assertEquals(Results.WIN, game.throwChip(chip, 4));
	}

	/*========================Tic Tac Toe Tests=================================*/  


/*========================general tests=================================*/
	@Test
	public void testSomeChip_ReturnsUndecided() {
		TicTacToeLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		assertEquals(Results.UNDECIDED, game.setChip(chip, 0, 1));
	}
	
	@Test
	public void testPlacingChipOnIllegalField_ReturnsError() {
		TicTacToeLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		assertEquals(Results.ERROR, game.setChip(chip, -1, -1));
		assertEquals(Results.ERROR, game.setChip(chip, -1, 4));
		assertEquals(Results.ERROR, game.setChip(chip, 4, -1));
		assertEquals(Results.ERROR, game.setChip(chip, 4, 4));
		assertEquals(Results.ERROR, game.setChip(chip, 4, 0));
		assertEquals(Results.ERROR, game.setChip(chip, 3, -1));
	}

	@Test
	public void testTwoChipsOnSameFieldSamePlayer_ReturnsError() {
		TicTacToeLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		assertEquals(Results.UNDECIDED, game.setChip(chip, 1, 1));
		assertEquals(Results.ERROR, game.setChip(chip, 1, 1));
	}

	@Test
	public void testTwoChipsOnSameFieldTwoPlayers_ReturnsError() {
		TicTacToeLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		Chip chip2 = new Chip(Players.PLAYER2);
		assertEquals(Results.UNDECIDED, game.setChip(chip, 1, 1));
		assertEquals(Results.ERROR, game.setChip(chip2, 1, 1));
	}	

	@Test
	public void testThrowChipWithFullBoardOnTicTacToe_ReturnsDraw() {
		TicTacToeLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		Chip chip2 = new Chip(Players.PLAYER2);
		assertEquals(Results.UNDECIDED, game.setChip(chip, 0, 0));
		assertEquals(Results.UNDECIDED, game.setChip(chip2, 0, 1));
		assertEquals(Results.UNDECIDED, game.setChip(chip, 0, 2));
		assertEquals(Results.UNDECIDED, game.setChip(chip2, 1, 0));
		assertEquals(Results.UNDECIDED, game.setChip(chip, 1, 1));
		assertEquals(Results.UNDECIDED, game.setChip(chip2, 1, 2));
		assertEquals(Results.UNDECIDED, game.setChip(chip2, 2, 0));
		assertEquals(Results.UNDECIDED, game.setChip(chip, 2, 1));
		assertEquals(Results.DRAW, game.setChip(chip2, 2, 2));
	}

/*========================vertical tests=================================*/	
	@Test
	public void testLeftColumn_ReturnsWin() {
		TicTacToeLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		assertEquals(Results.UNDECIDED, game.setChip(chip, 0, 0));
		assertEquals(Results.UNDECIDED, game.setChip(chip, 1, 0));
		assertEquals(Results.WIN, game.setChip(chip, 2, 0));
	}
	
	@Test
	public void testRightColumn_ReturnsWin() {
		TicTacToeLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		assertEquals(Results.UNDECIDED, game.setChip(chip, 0, 2));
		assertEquals(Results.UNDECIDED, game.setChip(chip, 1, 2));
		assertEquals(Results.WIN, game.setChip(chip, 2, 2));
	}
	
	
/*========================horizontal tests=================================*/
	@Test
	public void testBottomRow_ReturnsWin() {
		TicTacToeLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		assertEquals(Results.UNDECIDED, game.setChip(chip, 0, 0));
		assertEquals(Results.UNDECIDED, game.setChip(chip, 0, 1));
		assertEquals(Results.WIN, game.setChip(chip, 0, 2));
	}
	
	@Test
	public void testTopRow_ReturnsWin() {
		TicTacToeLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		assertEquals(Results.UNDECIDED, game.setChip(chip, 2, 0));
		assertEquals(Results.UNDECIDED, game.setChip(chip, 2, 1));
		assertEquals(Results.WIN, game.setChip(chip, 2, 2));
	}
	
/*========================diagonal tests=================================*/
	@Test
	public void testTopLeftToBottomRight_ReturnsWin() {
		TicTacToeLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		assertEquals(Results.UNDECIDED, game.setChip(chip, 2, 0));
		assertEquals(Results.UNDECIDED, game.setChip(chip, 1, 1));
		assertEquals(Results.WIN, game.setChip(chip, 0, 2));
	}
	
	@Test
	public void testBottomLeftToTopRight_ReturnsWin() {
		TicTacToeLogic game = new GameLogic();
		Chip chip = new Chip(Players.PLAYER1);
		assertEquals(Results.UNDECIDED, game.setChip(chip, 0, 0));
		assertEquals(Results.UNDECIDED, game.setChip(chip, 1, 1));
		assertEquals(Results.WIN, game.setChip(chip, 2, 2));
	}
}
