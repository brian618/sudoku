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
	Board validZoneBoard = new Board(new GenerationAlgorithm().puzzle);
	SolutionAlgorithm slnAlg = new SolutionAlgorithm(validZoneBoard);
	assertTrue(slnAlg.areValidZones());
    }

    /**
     * Test areValidZones() with invalid zones
     */
    @Test
    public void testInvalidZones() {
	Board invalidZoneBoard = new Board(new GenerationAlgorithm().puzzle);
	SolutionAlgorithm slnAlg = new SolutionAlgorithm(invalidZoneBoard);
	for (Square s : slnAlg.getAllSquares()) {
	    if (s.zone == 1) {
		s.value = 9;
	    }
	}
	assertFalse(slnAlg.areValidZones());
    }

    /**
     * Test areValidRows() with valid rows
     */
    @Test
    public void testValidRows() {
	Board validRowBoard = new Board(new GenerationAlgorithm().puzzle);
	SolutionAlgorithm slnAlg = new SolutionAlgorithm(validRowBoard);
	assertTrue(slnAlg.areValidRows());
    }

    /**
     * Test areValidRows() with invalid rows
     */
    @Test
    public void testInvalidRows() {
	Board invalidRowBoard = new Board(new GenerationAlgorithm().puzzle);
	SolutionAlgorithm slnAlg = new SolutionAlgorithm(invalidRowBoard);
	int valueColumn1Row1;
	int valueColum1Row2;
	for (Square s : slnAlg.getAllSquares()) {
	    if (s.col == 1 && s.row == 1) {
		valueColumn1Row1 = s.value;
	    }
	    if (s.col == 1 && s.row == 2) {
		valueColumn1Row1 = s.value;
	    }
	}
	assertFalse(slnAlg.areValidRows());
    }

    /**
     * Test areValidCols() with valid columns
     */
    @Test
    public void testValidCols() {
	Board validColBoard = new Board(new GenerationAlgorithm().puzzle);
	SolutionAlgorithm slnAlg = new SolutionAlgorithm(validColBoard);
	assertTrue(slnAlg.areValidRows());
    }

    /**
     * Test areValidCols() with invalid columns
     */
    @Test
    public void testInvalidCols() {
	Board invalidColBoard = new Board(new GenerationAlgorithm().puzzle);
	SolutionAlgorithm slnAlg = new SolutionAlgorithm(invalidColBoard);
	int valueColumn1Row1;
	int valueColum2Row1;
	for (Square s : slnAlg.getAllSquares()) {
	    if (s.col == 1 && s.row == 1) {
		valueColumn1Row1 = s.value;
	    }
	    if (s.col == 2 && s.row == 1) {
		valueColumn1Row1 = s.value;
	    }
	}
	assertFalse(slnAlg.areValidRows());
    }
}