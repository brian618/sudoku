package com.roastlechon.games.sudoku.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

import com.roastlechon.games.sudoku.model.Square;

public class GenerationAlgorithm {

    public List<Square> squares;
    public List<List<Square>> puzzle;
    // public int difficulty;

    /**
     * Constructor for GenerationAlgorithm
     */
    public GenerationAlgorithm(int level) {
	this.squares = generate();
	this.puzzle = createPuzzle(this.squares, level);
	// this.difficulty = level;
    }

    /**
     * Generates a complete sudoku with no blanks
     */
    public GenerationAlgorithm() {
	this.squares = generate();
	this.puzzle = createPuzzle(this.squares);
    }

    /**
     * Chooses a random number from the provided interger array
     * 
     * @param available,
     *            non-empty integer array
     * @return a random int from the array
     */
    public int getRandomNumber(Integer[] available) {
	int random = 0;
	Random r = new Random();
	random = r.nextInt(available.length);
	return available[random];
    }

    /**
     * Generates a sequence of numbers for the Sudoku
     * 
     * @return List<Square>, a list of squares containing numbers for the puzzle
     */
    public List<Square> generate() {
	List<Square> squares = new ArrayList<Square>();
	List<Integer[]> available = new ArrayList<Integer[]>();
	for (int x = 0; x < 81; x++) {
	    Integer[] numbers = new Integer[9];
	    for (int z = 0; z < 9; z++) {
		numbers[z] = z + 1;
	    }
	    available.add(numbers);
	}
	int i = 0; // index of fields
	while (i < 81) {
	    if (available.get(i).length != 0) {
		// random value
		Integer n = getRandomNumber(available.get(i));
		// index of random number
		int l = Arrays.asList(available.get(i)).indexOf(n);
		Square square = new Square(i, n);
		if (validSquare(squares, square) == true) {
		    squares.add(square);
		    available.set(i, ArrayUtils.remove(available.get(i), l));
		    i++;
		} else {
		    available.set(i, ArrayUtils.remove(available.get(i), l));
		}
	    } else {
		Integer[] numbers = new Integer[9];
		for (int z = 0; z < 9; z++) {
		    numbers[z] = z + 1;
		}
		available.set(i, numbers);
		i--;
		squares.remove(i);
	    }
	}
	return squares;
    }

    /**
     * Checks if the square is a valid square by asserting the invariant. Sudoku
     * Invariant: any two squares that share a row, column, or 3x3 zone have a
     * different value
     * 
     * @param squares,
     *            List of every square in the puzzle
     * @param square,
     *            new square to be added into the puzzle
     * @return true if the invariant is satisfied and false otherwise
     */
    public boolean validSquare(List<Square> squares, Square square) {
	for (int i = 0; i < squares.size(); i++) {
	    if (squares.get(i).row == square.row | squares.get(i).col == square.col
		    | squares.get(i).zone == square.zone) {
		if (squares.get(i).value == square.value) {
		    return false;
		}
	    }
	}
	return true;
    }

    /**
     * Creates a Sudoku puzzle
     * 
     * @param squares,
     *            list of all squares in the puzzle
     * @return List<List<Square>>, a list containing squares separated into 9,
     *         3x3 zones.
     */
    public List<List<Square>> createPuzzle(List<Square> squares, int difficulty) {
	List<List<Square>> zoneArray = new ArrayList<List<Square>>();

	List<Square> zoneSquares1 = new ArrayList<Square>();
	zoneSquares1 = getZoneSquares(squares, 1);
	// zoneSquares1 = removeSquaresFromZone(zoneSquares1, 0);
	zoneSquares1 = removeSquaresFromZone(zoneSquares1, (4 + difficulty));

	List<Square> zoneSquares2 = new ArrayList<Square>();
	zoneSquares2 = getZoneSquares(squares, 2);
	// zoneSquares2 = removeSquaresFromZone(zoneSquares2, 0);
	zoneSquares2 = removeSquaresFromZone(zoneSquares2, (3 + difficulty));

	List<Square> zoneSquares3 = new ArrayList<Square>();
	zoneSquares3 = getZoneSquares(squares, 3);
	// zoneSquares3 = removeSquaresFromZone(zoneSquares3, 0);
	zoneSquares3 = removeSquaresFromZone(zoneSquares3, (4 + difficulty));

	List<Square> zoneSquares4 = new ArrayList<Square>();
	zoneSquares4 = getZoneSquares(squares, 4);
	// zoneSquares4 = removeSquaresFromZone(zoneSquares4, (0));
	zoneSquares4 = removeSquaresFromZone(zoneSquares4, (4 + difficulty));

	List<Square> zoneSquares5 = new ArrayList<Square>();
	zoneSquares5 = getZoneSquares(squares, 5);
	// zoneSquares5 = removeSquaresFromZone(zoneSquares5, 0);
	zoneSquares5 = removeSquaresFromZone(zoneSquares5, (1 + 3 * difficulty));

	List<Square> zoneSquares6 = new ArrayList<Square>();
	zoneSquares6 = getZoneSquares(squares, 6);
	// zoneSquares6 = removeSquaresFromZone(zoneSquares6, 0);
	zoneSquares6 = removeSquaresFromZone(zoneSquares6, (4 + difficulty));

	List<Square> zoneSquares7 = new ArrayList<Square>();
	zoneSquares7 = getZoneSquares(squares, 7);
	// zoneSquares7 = removeSquaresFromZone(zoneSquares7, 0);
	zoneSquares7 = removeSquaresFromZone(zoneSquares7, (4 + difficulty));

	List<Square> zoneSquares8 = new ArrayList<Square>();
	zoneSquares8 = getZoneSquares(squares, 8);
	// zoneSquares8 = removeSquaresFromZone(zoneSquares8, 0);
	zoneSquares8 = removeSquaresFromZone(zoneSquares8, (3 + difficulty));

	List<Square> zoneSquares9 = new ArrayList<Square>();
	zoneSquares9 = getZoneSquares(squares, 9);
	// zoneSquares9 = removeSquaresFromZone(zoneSquares9, 1);
	zoneSquares9 = removeSquaresFromZone(zoneSquares9, (4 + difficulty));

	zoneArray.add(zoneSquares1);
	zoneArray.add(zoneSquares2);
	zoneArray.add(zoneSquares3);
	zoneArray.add(zoneSquares4);
	zoneArray.add(zoneSquares5);
	zoneArray.add(zoneSquares6);
	zoneArray.add(zoneSquares7);
	zoneArray.add(zoneSquares8);
	zoneArray.add(zoneSquares9);

	return zoneArray;
    }

    /**
     * Creates a complete Sudoku puzzle with no blanks
     * 
     * @param squares,
     *            list of all squares in the puzzle
     * @return List<List<Square>>, a list containing squares separated into 9,
     *         3x3 zones.
     */
    public List<List<Square>> createPuzzle(List<Square> squares) {
	List<List<Square>> zoneArray = new ArrayList<List<Square>>();

	List<Square> zoneSquares1 = new ArrayList<Square>();
	zoneSquares1 = getZoneSquares(squares, 1);

	List<Square> zoneSquares2 = new ArrayList<Square>();
	zoneSquares2 = getZoneSquares(squares, 2);

	List<Square> zoneSquares3 = new ArrayList<Square>();
	zoneSquares3 = getZoneSquares(squares, 3);

	List<Square> zoneSquares4 = new ArrayList<Square>();
	zoneSquares4 = getZoneSquares(squares, 4);

	List<Square> zoneSquares5 = new ArrayList<Square>();
	zoneSquares5 = getZoneSquares(squares, 5);

	List<Square> zoneSquares6 = new ArrayList<Square>();
	zoneSquares6 = getZoneSquares(squares, 6);

	List<Square> zoneSquares7 = new ArrayList<Square>();
	zoneSquares7 = getZoneSquares(squares, 7);

	List<Square> zoneSquares8 = new ArrayList<Square>();
	zoneSquares8 = getZoneSquares(squares, 8);

	List<Square> zoneSquares9 = new ArrayList<Square>();
	zoneSquares9 = getZoneSquares(squares, 9);

	zoneArray.add(zoneSquares1);
	zoneArray.add(zoneSquares2);
	zoneArray.add(zoneSquares3);
	zoneArray.add(zoneSquares4);
	zoneArray.add(zoneSquares5);
	zoneArray.add(zoneSquares6);
	zoneArray.add(zoneSquares7);
	zoneArray.add(zoneSquares8);
	zoneArray.add(zoneSquares9);

	return zoneArray;
    }

    /**
     * Removes squares from the Sudoku to create a puzzle for solving
     * 
     * @param squares,
     *            list of all squares in the puzzle
     * @param numberToRemove,
     *            number of squares to remove
     * @return the modified list of all squares in the game
     */
    public List<Square> removeSquaresFromZone(List<Square> squares, int numberToRemove) {
	Integer[] temp = createArrayOfNineRandomNumbers();
	Integer[] indexesToBeRemoved = new Integer[numberToRemove];

	for (int i = 0; i < numberToRemove; i++) {
	    indexesToBeRemoved[i] = temp[i];
	}

	for (int i = 0; i < numberToRemove; i++) {
	    int squareIndex = indexesToBeRemoved[i] - 1;
	    Square tempSquare = squares.get(squareIndex);
	    tempSquare.setValue(0);
	    tempSquare.setInputSquare();
	    squares.set(squareIndex, tempSquare);
	}

	return squares;
    }

    /**
     * Make a list of squares that occupy the same zone
     * 
     * @param squares,
     *            the list of all squares in the puzzle
     * @param zone,
     *            the zone to be extracted
     * @return List<Square> that are from the specified zone
     */
    public List<Square> getZoneSquares(List<Square> squares, int zone) {
	List<Square> tempSquares = new ArrayList<Square>();
	for (int i = 0; i < 81; i++) {
	    if (squares.get(i).zone == zone) {
		tempSquares.add(squares.get(i));
	    }
	}
	// System.out.println(tempSquares.toString());
	return tempSquares;
    }

    /**
     * @return Integer[] of 9 random unique numbers from 1 to 9
     */
    public static Integer[] createArrayOfNineRandomNumbers() {
	Integer[] randomArray = new Integer[9];
	Random random = new Random();

	for (int i = 0; i < randomArray.length; i++) {
	    randomArray[i] = i + 1;
	}

	for (int i = 0; i < randomArray.length; i++) {
	    int r = random.nextInt(randomArray.length);
	    int temp = randomArray[i];
	    randomArray[i] = randomArray[r];
	    randomArray[r] = temp;
	}

	return randomArray;
    }

}
