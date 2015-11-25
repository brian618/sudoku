package ca.ubc.games.sudoku.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.roastlechon.games.sudoku.business.GenerationAlgorithm;
import com.roastlechon.games.sudoku.view.Board;

public class SolutionAlgorithmTest {

    /**
     * Test isComplete() for complete board
     */
    @Test
    public void testBoardIsComplete() {
	// complete board
	Board board = new Board(new GenerationAlgorithm().puzzle);
	assertTrue(board.isComplete());
    }

    /**
     * Test isComplete() for incomplete board
     */
    @Test
    public void testBoardIsNotComplete() {
	// incomplete board
	Board board2 = new Board(new GenerationAlgorithm(1).puzzle);
	assertFalse(board2.isComplete());
    }
}