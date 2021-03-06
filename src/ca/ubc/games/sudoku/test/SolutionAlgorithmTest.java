package ca.ubc.games.sudoku.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

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
	// index of column 1 row 1
	Square c1r1 = new Square();
	// index of column1 row 2
	Square c1r2 = new Square();
	List<Square> allSquares = slnAlg.getAllSquares();
	for (Square s : allSquares) {
	    if (s.col == 1 && s.row == 1) {
		c1r1 = s;
	    }
	    if (s.col == 1 && s.row == 2) {
		c1r2 = s;
	    }
	}
	int swapVal1 = c1r1.value;
	int swapVal2 = c1r2.value;
	c1r1.value = swapVal2;
	c1r2.value = swapVal1;
	assertFalse(slnAlg.areValidRows());
    }

    /**
     * Test areValidCols() with valid columns
     */
    @Test
    public void testValidCols() {
	Board validColBoard = new Board(new GenerationAlgorithm().puzzle);
	SolutionAlgorithm slnAlg = new SolutionAlgorithm(validColBoard);
	assertTrue(slnAlg.areValidCols());
    }

    /**
     * Test areValidCols() with invalid columns
     */
    @Test
    public void testInvalidCols() {
	Board invalidColBoard = new Board(new GenerationAlgorithm().puzzle);
	SolutionAlgorithm slnAlg = new SolutionAlgorithm(invalidColBoard);

	// index of column 1 row 1
	Square c1r1 = new Square();
	// index of column1 row 2
	Square c2r1 = new Square();
	List<Square> allSquares = slnAlg.getAllSquares();
	for (Square s : allSquares) {
	    if (s.col == 1 && s.row == 1) {
		c1r1 = s;
	    }
	    if (s.col == 2 && s.row == 1) {
		c2r1 = s;
	    }
	}
	int swapVal1 = c1r1.value;
	int swapVal2 = c2r1.value;
	c1r1.value = swapVal2;
	c2r1.value = swapVal1;
	assertFalse(slnAlg.areValidCols());
    }

    /**
     * Test isUnique for unique squares
     */
    @Test
    public void testUniqueSquares() {
	SolutionAlgorithm sln = new SolutionAlgorithm(new Board(new GenerationAlgorithm().puzzle));
	List<Square> squares = new ArrayList<Square>();
	for (int i = 0; i < 9; i++) {
	    squares.add(new Square(i, i + 1));
	}
	assertTrue(sln.isUnique(squares));
    }

    /**
     * Test isUnique for non-unique squares
     */
    @Test
    public void testNonUniqueSquares() {
	SolutionAlgorithm sln = new SolutionAlgorithm(new Board(new GenerationAlgorithm().puzzle));
	List<Square> squares = new ArrayList<Square>();
	for (int i = 0; i < 9; i++) {
	    squares.add(new Square(i, 1));
	}
	assertFalse(sln.isUnique(squares));
    }

    /**
     * Test that appropriate number of squares removed
     */
    public void testGenerationAlgorithm() {
	GenerationAlgorithm gln = new GenerationAlgorithm(0);
	List<Square> squares = new ArrayList<Square>();
	for (int i = 0; i < 9; i++) {
	    squares.add(new Square(i, 1));
	}
	List<Square> after_remove = gln.removeSquaresFromZone(squares, 1);
	boolean now = after_remove.size() == 8;
	assertTrue(now);
    }

    /**
     * Makes sure that the appropriate number of squares is removed when nothing
     * is removed
     */
    public void testRemoveSquare() {
	GenerationAlgorithm gln = new GenerationAlgorithm(0);
	List<Square> squares = new ArrayList<Square>();
	for (int i = 0; i < 9; i++) {
	    squares.add(new Square(i, 1));
	}
	List<Square> after_remove = gln.removeSquaresFromZone(squares, 0);
	assertFalse(after_remove.size() == 9);
    }

    /**
     * Tests that there are only at most 9 square per zone
     */

    public void testNumSquare() {
	GenerationAlgorithm gln = new GenerationAlgorithm(0);
	List<Square> squares = new ArrayList<Square>();
	for (int i = 0; i < 81; i++) {
	    squares.add(new Square(i / 9, (i % 9 + 1)));
	}
	List<Square> after_remove = gln.getZoneSquares(squares, 1);
	assertTrue(after_remove.size() <= 9);
    }

    /**
     * tests that array of size nine is returned from
     * createArrayofNineRandomNumbers
     */
    public void testNineNumbers() {
	GenerationAlgorithm gln = new GenerationAlgorithm(0);
	@SuppressWarnings("static-access")
	Integer[] array_now = gln.createArrayOfNineRandomNumbers();
	assertTrue(array_now.length == 9);
    }

    /**
     * test that all entries are unique
     */
    public void testUnique() {
	GenerationAlgorithm gln = new GenerationAlgorithm(0);
	@SuppressWarnings("static-access")
	Integer[] array_now = gln.createArrayOfNineRandomNumbers();
	for (int n = 0; n < 9; n++) {
	    for (int k = n + 1; k < 9; k++) {
		assertTrue(array_now[n] != array_now[k]);
	    }
	}
    }
}