package com.roastlechon.games.sudoku.business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.roastlechon.games.sudoku.model.Square;
import com.roastlechon.games.sudoku.view.Board;
import com.roastlechon.games.sudoku.view.Field;
import com.roastlechon.games.sudoku.view.Zone;

public class SolutionAlgorithm {

    public boolean valid = false;
    private List<Square> allSquares;

    /**
     * Constructor for Solution Algorithm
     * 
     * @param board,
     *            the Board of the Sudoku
     */
    public SolutionAlgorithm(Board board) {
	allSquares = new ArrayList<Square>();
	for (int i = 0; i < board.getComponents().length; i++) {
	    Zone z = (Zone) board.getComponents()[i];
	    for (int j = 0; j < z.getComponents().length; j++) {
		Field f = (Field) z.getComponents()[j];
		Square square = f.getSquare();
		if (f.getValue() == null || f.getValue().equals(0)) {
		    this.valid = false;
		    return;
		}
		String value = (String) f.getValue();
		square.setValue(Integer.valueOf(value));
		allSquares.add(square);
	    }
	}
	this.valid = isValid();
    }

    /**
     * Checks if the the zone squares are valid
     * 
     * @param allSquares,
     *            List<Square> with squares having values from 1 to 9
     * @return true if valid and false otherwise
     */
    public boolean isValid() {
	if (areValidZones() && areValidCols() && areValidRows()) {
	    return true;
	} else {
	    return false;
	}
    }

    /**
     * Checks zones to see if valid
     * 
     * @param allSquares,
     *            list of all squares in the puzzle
     * @return true if valid false otherwise
     */
    public boolean areValidZones() {
	boolean valid = true;
	List<Square> zone1 = new ArrayList<Square>();
	List<Square> zone2 = new ArrayList<Square>();
	List<Square> zone3 = new ArrayList<Square>();
	List<Square> zone4 = new ArrayList<Square>();
	List<Square> zone5 = new ArrayList<Square>();
	List<Square> zone6 = new ArrayList<Square>();
	List<Square> zone7 = new ArrayList<Square>();
	List<Square> zone8 = new ArrayList<Square>();
	List<Square> zone9 = new ArrayList<Square>();

	for (int i = 0; i < allSquares.size() && valid; i++) {
	    Square square = allSquares.get(i);
	    if (square.zone == 1) {
		zone1.add(square);
		if (zone1.size() == 9) {
		    valid = isUnique(zone1);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (square.zone == 2) {
		zone2.add(square);
		if (zone2.size() == 9) {
		    valid = isUnique(zone2);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (square.zone == 3) {
		zone3.add(square);
		if (zone3.size() == 9) {
		    valid = isUnique(zone3);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (square.zone == 4) {
		zone4.add(square);
		if (zone4.size() == 9) {
		    valid = isUnique(zone4);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (square.zone == 5) {
		zone5.add(square);
		if (zone5.size() == 9) {
		    valid = isUnique(zone5);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (square.zone == 6) {
		zone6.add(square);
		if (zone6.size() == 9) {
		    valid = isUnique(zone6);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (square.zone == 7) {
		zone7.add(square);
		if (zone7.size() == 9) {
		    valid = isUnique(zone7);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (square.zone == 8) {
		zone8.add(square);
		if (zone8.size() == 9) {
		    valid = isUnique(zone8);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (square.zone == 9) {
		zone9.add(square);
		if (zone9.size() == 9) {
		    valid = isUnique(zone9);
		    if (!valid) {
			return false;
		    }
		}
	    }
	}
	return valid;
    }

    /**
     * Checks if rows are valid
     * 
     * @param allSquares,
     *            list of all squares in the puzzle
     * @return true if valid, false otherwise
     */
    public boolean areValidRows() {
	boolean valid = true;
	List<Square> rowSquares1 = new ArrayList<Square>();
	List<Square> rowSquares2 = new ArrayList<Square>();
	List<Square> rowSquares3 = new ArrayList<Square>();
	List<Square> rowSquares4 = new ArrayList<Square>();
	List<Square> rowSquares5 = new ArrayList<Square>();
	List<Square> rowSquares6 = new ArrayList<Square>();
	List<Square> rowSquares7 = new ArrayList<Square>();
	List<Square> rowSquares8 = new ArrayList<Square>();
	List<Square> rowSquares9 = new ArrayList<Square>();

	for (int i = 0; i < allSquares.size() && valid; i++) {
	    Square square = allSquares.get(i);
	    if (square.row == 1) {
		rowSquares1.add(square);
		if (rowSquares1.size() == 9) {
		    System.out.println("row 1: ");
		    for (int j = 0; j < rowSquares1.size(); j++) {
			System.out.println(rowSquares1.get(j));
		    }
		    valid = isUnique(rowSquares1);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (square.row == 2) {
		rowSquares2.add(square);
		if (rowSquares2.size() == 9) {
		    valid = isUnique(rowSquares2);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (square.row == 3) {
		rowSquares3.add(square);
		if (rowSquares3.size() == 9) {
		    valid = isUnique(rowSquares3);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (square.row == 4) {
		rowSquares4.add(square);
		if (rowSquares4.size() == 9) {
		    valid = isUnique(rowSquares4);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (square.row == 5) {
		rowSquares5.add(square);
		if (rowSquares5.size() == 9) {
		    valid = isUnique(rowSquares5);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (square.row == 6) {
		rowSquares6.add(square);
		if (rowSquares6.size() == 9) {
		    valid = isUnique(rowSquares6);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (square.row == 7) {
		rowSquares7.add(square);
		if (rowSquares7.size() == 9) {
		    valid = isUnique(rowSquares7);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (square.row == 8) {
		rowSquares8.add(square);
		if (rowSquares8.size() == 9) {
		    valid = isUnique(rowSquares8);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (square.row == 9) {
		rowSquares9.add(square);
		if (rowSquares9.size() == 9) {
		    valid = isUnique(rowSquares9);
		    if (!valid) {
			return false;
		    }
		}
	    }

	}
	return valid;
    }

    /**
     * checks if columns are valid
     * 
     * @return returns true if valid and false otherise
     */
    public boolean areValidCols() {
	boolean valid = true;
	List<Square> colSquares1 = new ArrayList<Square>();
	List<Square> colSquares2 = new ArrayList<Square>();
	List<Square> colSquares3 = new ArrayList<Square>();
	List<Square> colSquares4 = new ArrayList<Square>();
	List<Square> colSquares5 = new ArrayList<Square>();
	List<Square> colSquares6 = new ArrayList<Square>();
	List<Square> colSquares7 = new ArrayList<Square>();
	List<Square> colSquares8 = new ArrayList<Square>();
	List<Square> colSquares9 = new ArrayList<Square>();

	for (int i = 0; i < allSquares.size() && valid; i++) {
	    Square colSquare = allSquares.get(i);
	    if (colSquare.col == 1) {
		colSquares1.add(colSquare);
		if (colSquares1.size() == 9) {
		    valid = isUnique(colSquares1);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (colSquare.col == 2) {
		colSquares2.add(colSquare);
		if (colSquares2.size() == 9) {
		    valid = isUnique(colSquares2);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (colSquare.col == 3) {
		colSquares3.add(colSquare);
		if (colSquares3.size() == 9) {
		    valid = isUnique(colSquares3);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (colSquare.col == 4) {
		colSquares4.add(colSquare);
		if (colSquares4.size() == 9) {
		    valid = isUnique(colSquares4);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (colSquare.col == 5) {
		colSquares5.add(colSquare);
		if (colSquares5.size() == 9) {
		    valid = isUnique(colSquares5);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (colSquare.col == 6) {
		colSquares6.add(colSquare);
		if (colSquares6.size() == 9) {
		    valid = isUnique(colSquares6);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (colSquare.col == 7) {
		colSquares7.add(colSquare);
		if (colSquares7.size() == 9) {
		    valid = isUnique(colSquares7);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (colSquare.col == 8) {
		colSquares8.add(colSquare);
		if (colSquares8.size() == 9) {
		    valid = isUnique(colSquares8);
		    if (!valid) {
			return false;
		    }
		}
	    }
	    if (colSquare.col == 9) {
		colSquares9.add(colSquare);
		if (colSquares9.size() == 9) {
		    valid = isUnique(colSquares9);
		    if (!valid) {
			return false;
		    }
		}
	    }

	}
	return valid;
    }

    /**
     * Checks if the values in the given List<Square> are unique
     * 
     * @param zoneSquares,
     *            List<Square> to be checked
     * @return true if all values are unique and false otherwise
     */
    public boolean isUnique(List<Square> squares) {
	List<Integer> list = new ArrayList<Integer>();

	for (int i = 0; i < squares.size(); i++) {
	    list.add(squares.get(i).getValue());
	}

	Set<Integer> set = new HashSet<Integer>(list);
	if (set.size() < squares.size()) {
	    return false;
	} else {
	    return true;
	}
    }

    /**
     * @return the list of squares in the puzzle
     */
    public List<Square> getAllSquares() {
	return allSquares;
    }

}
