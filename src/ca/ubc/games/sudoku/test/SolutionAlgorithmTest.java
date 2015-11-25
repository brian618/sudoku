package ca.ubc.games.sudoku.test;

import org.junit.Test;

import com.roastlechon.games.sudoku.business.GenerationAlgorithm;
import com.roastlechon.games.sudoku.view.Board;

public class SolutionAlgorithmTest {

    /**
     * Tests areValidZones function
     */
    @Test
    public void testAreValidZones(){
	Board board = new Board(new GenerationAlgorithm().puzzle);
    }
}