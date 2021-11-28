package hsma.agi;

/**
 * This class implements ConnectFourLogic and TicTacToeLogic interfaces.
 * @author Praveen Yogananthan, Tom Rößler
 */
public final class GameLogic implements ConnectFourLogic, TicTacToeLogic {

	/*----connect four constants----*/
	private static final int NUM_OF_COLUMNS_CONNECTFOUR = 7;
	private static final int NUM_OF_ROWS_CONNECTFOUR = 6;
	private static final int WIN_COUNTER_CONNECTFOUR = 4;

	/*----TicTacToe constants----*/
	private static final int NUM_OF_COLUMNS_AND_ROWS_TICTACTOE = 3;
	private static final int WIN_COUNTER_TICTACTOE = 3;

	private int totalThrows;

	//initialise game board using connect four game board's dimensions since they pose a supremum
	private Chip[][] board = new Chip[NUM_OF_COLUMNS_CONNECTFOUR][NUM_OF_ROWS_CONNECTFOUR];

	/**
	 * @param chip chip to be inserted into board
	 * @param column column into which chip is to be inserted
	 * @return Result denotes result (Win, Draw, Error, Undecided)
	 */
	@Override
	public Results throwChip(final Chip chip, final int column) {

		//check violation of game boundaries
		if(column < 0 || column >= NUM_OF_COLUMNS_CONNECTFOUR || chip == null) {
			return Results.ERROR;
		}

		boolean chipInsertedSuccessfully = false;

		//determine row and insert chip
		for(int row = 0; row < NUM_OF_ROWS_CONNECTFOUR; row++) {
			if(chipInsertedSuccessfully = insertChip(chip, column, row)) {
				totalThrows++;
				break;
			}
		}

		return determineOutcome(chip, chipInsertedSuccessfully, NUM_OF_COLUMNS_CONNECTFOUR, NUM_OF_ROWS_CONNECTFOUR, WIN_COUNTER_CONNECTFOUR);
	}

	@Override
	public Results setChip(final Chip chip, final int row, final int column) {

		//check violation of game boundaries (and null)
		if(	column < 0 || column >= NUM_OF_COLUMNS_AND_ROWS_TICTACTOE ||
			row < 0 || row >= NUM_OF_COLUMNS_AND_ROWS_TICTACTOE ||
			chip == null) {
			return Results.ERROR;
		}

		//insert chip
		boolean chipInsertedSuccessfully = insertChip(chip, column, row);

		totalThrows++;

		return determineOutcome(chip, chipInsertedSuccessfully, NUM_OF_COLUMNS_AND_ROWS_TICTACTOE, NUM_OF_COLUMNS_AND_ROWS_TICTACTOE, WIN_COUNTER_TICTACTOE);
	}

	/**
	 * 
	 * @param chip latest thrown chip
	 * @param chipInsertedSuccessfully denotes whether chip could be inserted into board successfully 
	 * @param column represents game board boundaries
	 * @param row represents game board boundaries
	 * @param winCounter denotes amount of chips in a row needed for a win
	 * @return Result denotes result (Win, Draw, Error, Undecided)
	 */
	private Results determineOutcome(final Chip chip, final boolean chipInsertedSuccessfully, final int column, final int row, final int winCounter) {
		if(!chipInsertedSuccessfully) {
			return Results.ERROR;
		} else if(totalThrows == (column * row)) {
			return Results.DRAW;
		}

		if(isForHorizontalWin(chip, winCounter) ||isForVerticalWin(chip, winCounter)|| isForDiagonalWin(chip, winCounter)) {
			return Results.WIN;
		} else { 
			return Results.UNDECIDED;
		}
	}

	/**
	 * 
	 * @param chip chip to be inserted
	 * @param column column into which chip should be inserted
	 * @param row row into which chip should be inserted
	 * @return boolean to indicate whether chip could be inserted successfully
	 */
	private boolean insertChip(final Chip chip, final int column, final int row) {
		if(this.board[column][row] == null) {
			this.board[column][row] = chip;
			chip.setColumn(column);
			chip.setRow(row);
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param chip chip to be inserted into game board
	 * @param winCounter denotes amount of chips needed for a win
	 * @return boolean to indicate whether a horizontal win (true) was achieved or not (false)
	 */
	private boolean isForHorizontalWin(final Chip chip, final int winCounter) {

		int chipsInARow = 1;
		Chip nextChip;

		for(int column = chip.getColumn()+1; column < NUM_OF_COLUMNS_CONNECTFOUR; column++) {
			nextChip = this.board[column][chip.getRow()];
			if(nextChip == null || !(chip.getPlayer().equals(nextChip.getPlayer()))) {
				break;
			}
			chipsInARow++;
			if(chipsInARow == winCounter) {
				return true;
			}
		}

		for(int column = chip.getColumn()-1; column >= 0; column--) {
			nextChip = this.board[column][chip.getRow()];
			if(nextChip == null || !(chip.getPlayer().equals(nextChip.getPlayer()))) {
				break;
			}
			chipsInARow++;
			if(chipsInARow == winCounter) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 
	 * @param chip chip to be inserted into game board
	 * @param winCounter denotes amount of chips in a row needed for a win
	 * @return boolean to indicate whether a vertical win (true) was achieved or not (false)
	 */
	private boolean isForVerticalWin(final Chip chip, final int winCounter) {

		int chipsInAColumn = 1;
		Chip nextChip;

		for(int row = chip.getRow()-1; row >= 0; row--) {
			nextChip = this.board[chip.getColumn()][row];
			if(nextChip == null || !(chip.getPlayer().equals(nextChip.getPlayer()))) {
				break;
			}
			chipsInAColumn++;
			if(chipsInAColumn == winCounter) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 
	 * @param chip chip to be inserted into game board
	 * @param winCounter denotes amount of chips needed for a win
	 * @return boolean to indicate whether a diagonal win (true) was achieved or not (false)
	 */
	private boolean isForDiagonalWin(final Chip chip, final int winCounter) {

		int chipsInADiagonalLine = 1;
		Chip nextChip;

		//To the top right
		for(int i = 1; i < 4; i++) {
			if(chip.getColumn()+i < NUM_OF_COLUMNS_CONNECTFOUR && chip.getRow()+i < NUM_OF_ROWS_CONNECTFOUR) {
				nextChip = this.board[chip.getColumn()+i][chip.getRow()+i];
				if(nextChip == null || !(chip.getPlayer().equals(nextChip.getPlayer()))) {
					break;
				}
				chipsInADiagonalLine++;
				if(chipsInADiagonalLine == winCounter) {
					return true;
				}
			}else {
				break;
			}
		}

		//To the bottom left
		for(int i = 1; i < 4; i++) {
			if(chip.getColumn()-i >= 0 && chip.getRow()-i >= 0) {
				nextChip = this.board[chip.getColumn()-i][chip.getRow()-i];
				if(nextChip == null || !(chip.getPlayer().equals(nextChip.getPlayer()))) {
					break;
				}
				chipsInADiagonalLine++;
				if(chipsInADiagonalLine == winCounter) {
					return true;
				}
			}else {
				break;
			}
		}

		chipsInADiagonalLine = 1;

		//To the top left
		for(int i = 1; i < 4; i++) {
			if(chip.getColumn()-i >= 0 && chip.getRow()+i < NUM_OF_ROWS_CONNECTFOUR) {
				nextChip = this.board[chip.getColumn()-i][chip.getRow()+i];
				if(nextChip == null || !(chip.getPlayer().equals(nextChip.getPlayer()))) {
					break;
				}
				chipsInADiagonalLine++;
				if(chipsInADiagonalLine == winCounter) {
					return true;
				}
			}else {
				break;
			}
		}

		//To the bottom right
		for(int i = 1; i < 4; i++) {
			if(chip.getColumn()+i < NUM_OF_COLUMNS_CONNECTFOUR && chip.getRow()-i >= 0) {
				nextChip = this.board[chip.getColumn()+i][chip.getRow()-i];
				if(nextChip == null || !(chip.getPlayer().equals(nextChip.getPlayer()))) {
					break;
				}
				chipsInADiagonalLine++;
				if(chipsInADiagonalLine == winCounter) {
					return true;
				}
			}else {
				break;
			}
		}

		return false;
	}

}
