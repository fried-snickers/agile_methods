package test;
 
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import implementation.*;

class FourWinsLogicImplTest {

	@Test
	public void testThrowChipLeftOnce() {
		FourWinsLogicImpl game = new FourWinsLogicImpl();
		Chip chip = new Chip(Players.PLAYER1);
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
	}
	

	@Test
	public void testThrowChipLeftFourTimes() {
		FourWinsLogicImpl game = new FourWinsLogicImpl();
		Chip chip = new Chip(Players.PLAYER1);
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
		assertEquals(Results.WIN, game.throwChip(chip, 0));
	}
	
	@Test
	public void testThrowChipToFourSequentialColumns() {
		FourWinsLogicImpl game = new FourWinsLogicImpl();
		Chip chip = new Chip(Players.PLAYER1);
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 0));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 1));
		assertEquals(Results.UNDECIDED, game.throwChip(chip, 2));
		assertEquals(Results.WIN, game.throwChip(chip, 3));
	}
	
	@Test
	public void testThrowChipLeftFourTimesAlternating() {
		FourWinsLogicImpl game = new FourWinsLogicImpl();
		Chip chip1 = new Chip(Players.PLAYER1);
		Chip chip2 = new Chip(Players.PLAYER2);
		assertEquals(Results.UNDECIDED, game.throwChip(chip1, 0));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 0));
		assertEquals(Results.UNDECIDED, game.throwChip(chip1, 0));
		assertEquals(Results.UNDECIDED, game.throwChip(chip2, 0));
	}
	
	@Test
	public void testThrowChipLeftFourTimesAlternating() {
		
	}
}
