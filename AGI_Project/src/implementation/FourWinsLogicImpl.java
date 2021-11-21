package implementation;

public class FourWinsLogicImpl {

	private int amountOfThrows;
	
	public Object throwChip(Chip chip, int i) {
		
		amountOfThrows++;
		if(amountOfThrows == 4) {
			return Results.WIN;
		}
		return Results.UNDECIDED;
	}
}
