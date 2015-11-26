package ca.ubc.games.sudoku.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.roastlechon.games.sudoku.model.Square;

public class SquareTest {

    /**
     * Tests isInputSquare for an input square
     */
    @Test
    public void testIsInputSquare() {
	Square square = new Square(0, 0);
	assertTrue(square.isInputSquare());
    }

    /**
     * Test isInputSquare for an non-input square
     */
    @Test
    public void testNonInputSquare() {
	Square square = new Square(0, 5);
	assertFalse(square.isInputSquare());
    }
}
