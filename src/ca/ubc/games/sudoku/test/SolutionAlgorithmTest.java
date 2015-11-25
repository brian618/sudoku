package ca.ubc.games.sudoku.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.roastlechon.games.sudoku.business.GenerationAlgorithm;
import com.roastlechon.games.sudoku.business.SolutionAlgorithm;
import com.roastlechon.games.sudoku.model.Square;
import com.roastlechon.games.sudoku.view.Board;

public class SolutionAlgorithmTest {

    /**
     * Test isComplete() for complete board
     */
    @Test
    public void testBoardIsComplete() {
	// complete board
	Board completeBoard = new Board(new GenerationAlgorithm().puzzle);
	assertTrue(completeBoard.isComplete());
    }

    /**
     * Test isComplete() for incomplete board
     */
    @Test
    public void testBoardIsNotComplete() {
	// incomplete board
	Board incompleteBoard = new Board(new GenerationAlgorithm(1).puzzle);
	assertFalse(incompleteBoard.isComplete());
    }

    /**
     * Test areValidZones() with valid zones
     */
    @Test
    public void testValidZones() {
	Board validBoard = new Board(new GenerationAlgorithm().puzzle);
	SolutionAlgorithm slnAlg = new SolutionAlgorithm(validBoard);
	assertTrue(slnAlg.areValidZones(slnAlg.getAllSquares()));
    }

    /**
     * Test areValidZones() with invalid zones
     */
    @Test
    public void testInvalidZones() {
	Board invalidBoard = new Board(new GenerationAlgorithm().puzzle);
	SolutionAlgorithm slnAlg = new SolutionAlgorithm(invalidBoard);
	for (Square s : slnAlg.getAllSquares()) {
	    if (s.zone == 1) {
		s.value = 9;
	    }
	}
	assertFalse(slnAlg.areValidZones(slnAlg.getAllSquares()));
    }
}