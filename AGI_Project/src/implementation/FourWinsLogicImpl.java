package implementation;

public class FourWinsLogicImpl {

	private int amountOfThrowsPlayerOne = 0;

	private int amountOfThrowsPlayerTwo = 0;

	//	private boolean playersAlternate = false;

	//	private Players currentPlayer = null;

	public Object throwChip(Chip chip, int i) {
		//		if(amountOfThrows == 0) {
		//			currentPlayer = chip.getPlayer();
		//		}
		//		
		//		if(chip.getPlayer() == currentPlayer) {
		//			playersAlternate = false;
		//		}else {
		//			playersAlternate = true;
		//		}

		if(chip.getPlayer() == Players.PLAYER1) {
			amountOfThrowsPlayerOne++;
		}else if(chip.getPlayer() == Players.PLAYER2) {
			amountOfThrowsPlayerTwo++;
		}

		
		if(amountOfThrowsPlayerOne == 4 || amountOfThrowsPlayerTwo== 4) {
			return Results.WIN;
		}
		return Results.UNDECIDED;
	}
}
